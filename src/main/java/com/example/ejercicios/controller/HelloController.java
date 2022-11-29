package com.example.ejercicios.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final Logger log = LoggerFactory.getLogger(HelloController.class);

    /**
     * hello()
     * devuelve un saludo
     */
    @GetMapping("/api/hello")
    @ApiOperation("Saluda")
    public String hello() {
        return "Hello";
    }
}
