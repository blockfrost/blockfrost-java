package io.blockfrost.sdk.api.model.nutlink;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NutLinkAddress {

    /**
     * Bech32 encoded address
     */
    private String address;

    /**
     * URL do specific metadata file
     */
    private String metadataUrl;

    /**
     * Hash of the metadata file
     */
    private String metadataHash;

    /**
     * The cached metadata of the metadata_url file
     */
    private Map<String, Object> metadata = new HashMap<String, Object>();
}
