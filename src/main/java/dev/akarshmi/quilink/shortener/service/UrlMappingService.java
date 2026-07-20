package dev.akarshmi.quilink.shortener.service;

import dev.akarshmi.quilink.shortener.dto.UrlMappingRequest;
import dev.akarshmi.quilink.shortener.dto.UrlMappingResponse;
import org.springframework.stereotype.Service;

@Service
public interface UrlMappingService {
    UrlMappingResponse shortener(UrlMappingRequest request);
}
