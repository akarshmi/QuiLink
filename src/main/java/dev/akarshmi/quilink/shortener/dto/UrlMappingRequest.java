package dev.akarshmi.quilink.shortener.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record UrlMappingRequest(
        @NotBlank
        @URL
        String longUrl
) {
}
