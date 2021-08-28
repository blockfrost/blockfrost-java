package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.IPFSObject;
import io.blockfrost.sdk.api.model.PinState;
import io.blockfrost.sdk.api.model.PinItem;
import io.blockfrost.sdk.api.model.PinResponse;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.api.util.OrderEnum;
import io.blockfrost.sdk.impl.IPFSServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IPFSServiceTests extends TestBase {

    static IPFSService ipfsService;

    @BeforeAll
    public static void setup() {
        ipfsService = new IPFSServiceImpl(Constants.BLOCKFROST_IPFS_URL, ipfsProjectId);
    }

    @Test
    public void add_willReturn_ipfsObject() throws IOException, APIException {
        File tempFile = createFileWithContent("Hello Temp File");

        IPFSObject ipfsObject = ipfsService.add(tempFile);
        System.out.println(ipfsObject);

        assertNotNull(ipfsObject.getName());
        assertEquals("QmWAhBXx11KUkLxkYMNNCvriLWtPLRqw8Eo4EKHFeMBA5a", ipfsObject.getIpfsHash());
        assertEquals(24, ipfsObject.getSize());
    }

    private IPFSObject addFileToIpfs(String content) throws IOException, APIException {
        File file = createFileWithContent(content);
        return ipfsService.add(file);
    }
    private static File createFileWithContent(String content) throws IOException {
        File tempFile = File.createTempFile("test", "ipfs");
        PrintWriter pw = new PrintWriter(tempFile);
        pw.println(content);
        pw.close();
        return tempFile;
    }

    @Test
    public void add_willThrowFileNotFoundException_WhenFileNotExists() throws IOException, APIException {
        File file = new File("ThisFileDoesntExist1213.txt");

        assertThrows(FileNotFoundException.class, () -> {
            IPFSObject ipfsObject = ipfsService.add(file);
        });
    }

    @Test
    public void add_willThrowIOException_WhenFileNull() throws IOException, APIException {
        assertThrows(IOException.class, () -> {
            IPFSObject ipfsObject = ipfsService.add(null);
        });
    }

    @Test
    public void get_willReturnContent_WhenValidIpfsHash() throws APIException, IOException {
        byte[] content = ipfsService.get("QmWAhBXx11KUkLxkYMNNCvriLWtPLRqw8Eo4EKHFeMBA5a");
        assertEquals("Hello Temp File", new String(content).trim());
    }

    @Test
    public void pinAdd_willReturn_pinState() throws APIException, IOException {
        //Add the file incase it's removed
        File file = createFileWithContent("Hello Temp File");
        ipfsService.add(file);

        String ipfsHash = "QmWAhBXx11KUkLxkYMNNCvriLWtPLRqw8Eo4EKHFeMBA5a";
        PinResponse pinResponse = ipfsService.pinAdd(ipfsHash);

        System.out.println(pinResponse);
        assertEquals(ipfsHash, pinResponse.getIpfsHash());
        assertEquals(pinResponse.getState(), PinState.queued);
    }

    @Test
    public void getPinnedObjectsWithPagination_willReturn_pinnedObjects() throws APIException, IOException, InterruptedException {
        //Add 15 items
        List<IPFSObject> ipfsObjects = new ArrayList<>();
        for(int i=0; i < 14; i++) {
            File file = createFileWithContent("Hello Temp File-" + i);
            IPFSObject ipfsObject = ipfsService.add(file);
            ipfsService.pinAdd(ipfsObject.getIpfsHash());
            ipfsObjects.add(ipfsObject);
            Thread.sleep(100);
        }

        List<PinItem> pinnedItems1 = ipfsService.getPinnedObjects(5, 1, OrderEnum.desc);
        List<PinItem> pinnedItems2 = ipfsService.getPinnedObjects(5, 2, OrderEnum.desc);
        List<PinItem> pinnedItems3 = ipfsService.getPinnedObjects(5, 3, OrderEnum.desc);
        System.out.println(pinnedItems1);

        assertEquals(5, pinnedItems1.size());
        assertEquals(5, pinnedItems2.size());
        assertTrue(pinnedItems3.size() >= 4, "Pinned items size in 3 page should be equal or more than 4");

        assertNotNull(pinnedItems1.get(0).getIpfsHash());
        assertNotEquals(0, pinnedItems1.get(0).getTimeCreated());
        assertNotEquals(0, pinnedItems1.get(0).getTimePinned());
       // assertNotEquals(0, pinnedItems1.get(0).getSize());
        assertNotNull(pinnedItems1.get(0).getState());
    }

    @Test
    public void getPinnedObjects_willReturn_AllPinItems() throws APIException, IOException, InterruptedException {
        //Add 14 items
        List<IPFSObject> ipfsObjects = new ArrayList<>();
        for(int i=0; i < 14; i++) {
            File file = createFileWithContent("Hello Temp File-" + i);
            IPFSObject ipfsObject = ipfsService.add(file);
            ipfsService.pinAdd(ipfsObject.getIpfsHash());
            ipfsObjects.add(ipfsObject);
            Thread.sleep(100);
        }

        ((IPFSServiceImpl)ipfsService).setDefaultFetchSize(10);
        List<PinItem> pinnedItems = ipfsService.getPinnedObjects();
        assertTrue(pinnedItems.size() >= 14, "Pinned items size should be 14 or more");
    }

    @Test
    public void getPinnedObjectByIpfsPath_willReturn_pinnedObject() throws APIException, IOException {
        //Add object and pin
        IPFSObject ipfsObject = addFileToIpfs("Hello Temp File");
        String ipfsHash = ipfsObject.getIpfsHash();
        ipfsService.pinAdd(ipfsObject.getIpfsHash());

        PinItem pinItem = ipfsService.getPinnedObjectByIpfsPath(ipfsHash);
        System.out.println(pinItem);

        assertTrue(pinItem.getTimeCreated() > 0);
        assertTrue(pinItem.getTimePinned() > 0);
        assertEquals(ipfsHash, pinItem.getIpfsHash());
        assertNotNull(pinItem.getState());
        assertEquals(24, pinItem.getSize());
    }

    @Test
    public void getPinnedObjectByIpfsPath_willUnpinAndReturn_UnpinnedObject() throws APIException, IOException {
        //Add object and pin
        IPFSObject ipfsObject = addFileToIpfs("Hello Temp File");
        ipfsService.pinAdd(ipfsObject.getIpfsHash());

        //Remove pin
        PinItem pinItem = ipfsService.removePinnedObject(ipfsObject.getIpfsHash());
        System.out.println(pinItem);

        assertEquals(ipfsObject.getIpfsHash(), pinItem.getIpfsHash());
        assertEquals(PinState.unpinned, pinItem.getState());
        //The following fields should not be set
        assertEquals(0, pinItem.getTimeCreated());
        assertEquals(0, pinItem.getTimePinned());
        assertNull(pinItem.getSize());
    }
}
