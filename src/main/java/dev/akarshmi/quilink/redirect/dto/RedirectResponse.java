package dev.akarshmi.quilink.redirect.dto;

import java.time.Instant;

public record RedirectResponse(
        String longUrl,
        boolean active,
        Instant expiresAt
) {
}
