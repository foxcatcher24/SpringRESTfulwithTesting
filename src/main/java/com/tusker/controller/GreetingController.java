package com.tusker.controller;

import com.tusker.DTO.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    // Declare Variables
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    // url request mapping
    // adding /greeting
    // http://localhost:8080/greeting
    @RequestMapping("/greeting")
    public Greeting greeting( @RequestParam(value = "name", defaultValue = "World" ) String name ){
        return new Greeting( counter.incrementAndGet(), String.format( template, name ) );
    }

}
