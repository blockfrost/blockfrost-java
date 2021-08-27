package io.blockfrost.sdk.api;

import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.IPFSObject;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.impl.IPFSServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

public class IPFSServiceTests extends TestBase {

    IPFSService ipfsService;

    @BeforeEach
    public void setup() {
        ipfsService = new IPFSServiceImpl(Constants.BLOCKFROST_IPFS_URL, ipfsProjectId);
    }

    @Test
    public void add_willReturn_ipfsObject() throws IOException, APIException {
        File tempFile = File.createTempFile("test", "ipfs");
        PrintWriter pw = new PrintWriter(tempFile);
        pw.println("Hello Temp File");
        pw.close();

        IPFSObject ipfsObject = ipfsService.add(tempFile);
        System.out.println(ipfsObject);

        assertNotNull(ipfsObject.getName());
        assertEquals("QmWAhBXx11KUkLxkYMNNCvriLWtPLRqw8Eo4EKHFeMBA5a", ipfsObject.getIpfsHash());
        assertEquals(24, ipfsObject.getSize());
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
}
