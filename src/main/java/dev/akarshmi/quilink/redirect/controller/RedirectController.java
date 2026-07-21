package dev.akarshmi.quilink.redirect.controller;

import dev.akarshmi.quilink.redirect.dto.RedirectResponse;
import dev.akarshmi.quilink.redirect.service.RedirectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class RedirectController {

    private final RedirectService  redirectService;

    @GetMapping("/v1/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode
    ) {
        // now first we will check for now that directly later we will use redis for that

        // first find that is that shortcode is available?
        RedirectResponse response = redirectService.getRedirect(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(response.longUrl()))
                .build();
    }

    @GetMapping
    public ResponseEntity<Void> ping() {
        return ResponseEntity.ok().build();
    }

}
