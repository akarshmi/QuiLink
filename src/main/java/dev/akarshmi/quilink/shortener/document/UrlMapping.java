package dev.akarshmi.quilink.shortener.document;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "urls")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UrlMapping {

    @Id
    private String id;

    @Indexed(unique = true)
    private String shortCode;

    private String longUrl;

    private Instant createdAt;

    @Indexed(expireAfter = "0s" )
    private Instant expiresAt;

    private long clickCount  = 0;
    private boolean isActive = true;
}
