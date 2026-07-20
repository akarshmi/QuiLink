package dev.akarshmi.quilink.redirect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class RedirectController {

    @GetMapping
    public void redirect(
            @RequestParam String shortCode
    ) {
        // now first we will check for now that directly later we will use redis for that


    }

}
