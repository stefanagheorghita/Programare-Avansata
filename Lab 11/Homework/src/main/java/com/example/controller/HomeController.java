package com.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@Tag(name = "Home", description = "The Swagger API")
public class HomeController {

    @GetMapping(value = {"/", "/swagger-ui", "/swagger-ui+html"})
    public String index() {
        return "redirect:/swagger-ui/index.html";
    }
}
