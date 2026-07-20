package dev.akarshmi.quilink.shortener.dto;

import java.time.Instant;

public record UrlMappingResponse(
        String longUrl,
        String shortCode,
        Instant createdAt
) {
}
