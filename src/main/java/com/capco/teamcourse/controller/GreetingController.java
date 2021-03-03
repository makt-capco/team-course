package com.capco.teamcourse.controller;

import com.capco.teamcourse.response.HelloMessageDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GreetingController {


    @GetMapping(value = "/helloWorld", produces = MediaType.APPLICATION_JSON_VALUE)
    public HelloMessageDto getHelloWorld() {

        return new HelloMessageDto("Hello World");

    }


}
