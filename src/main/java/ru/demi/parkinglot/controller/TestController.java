package ru.demi.parkinglot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/echo/{param}")
    public String echo(@PathVariable String param) {
        return param;
    }
}
