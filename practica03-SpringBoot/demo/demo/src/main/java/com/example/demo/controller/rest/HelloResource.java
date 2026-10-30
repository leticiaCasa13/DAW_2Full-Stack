package com.example.demo.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(HelloResource.HELLO_RESOURCE)
public class HelloResource {


        public static final String HELLO_RESOURCE = "/hello";
        @GetMapping
        public String hello(@RequestParam(defaultValue = "World") String name) {
            return String.format("Hello %s!", name);
        }


        @GetMapping("/demo/bye")
        public String bye() {
            return "Buenas noches" +
                    "Señora";
        }
    }




