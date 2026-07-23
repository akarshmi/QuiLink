package dev.akarshmi.quilink.shortener.controller;

import dev.akarshmi.quilink.common.constants.ShortenerConstants;
import dev.akarshmi.quilink.shortener.dto.UrlMappingRequest;
import dev.akarshmi.quilink.shortener.dto.UrlMappingResponse;
import dev.akarshmi.quilink.shortener.service.UrlMappingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ShortenerConstants.SHORTENER_URL)
@RequiredArgsConstructor
public class UrlMappingController {
    private final UrlMappingService urlMappingService;

    @PostMapping()
    public ResponseEntity<UrlMappingResponse> shorten(@Valid @RequestBody UrlMappingRequest request) {
        return ResponseEntity.ok(urlMappingService.shortener(request));
    }

}
