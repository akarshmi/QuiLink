package dev.akarshmi.quilink.redirect.service;

import dev.akarshmi.quilink.redirect.dto.RedirectResponse;

public interface RedirectService {
    RedirectResponse getRedirect(String shortCode);
}
