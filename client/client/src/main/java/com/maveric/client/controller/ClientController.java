package com.maveric.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    public ClientController() {
        this.restTemplate = new RestTemplate();
    }

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final String GLOBAL_ERROR = "Some technical Error Please try again after some time";
    private RestTemplate restTemplate;
    @GetMapping("/get")
    public String getMessage(){
        return "GET methos is working";
        //return "Get method is working";
    }
    @GetMapping("getAll")
    public String getDataFromMicroservice() {
        String url = "http://localhost:8084/api/v1/auth/all"; // Replace with your microservice API endpoint
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }
}

