package com.example.eurekaclient3.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Client_3_Controller {

    @Autowired
    public RestTemplate restTemplate;

    @RequestMapping("/eureka-client-3")
    public String sayHello() throws JSONException {

        // returning JSON instead of String
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("message","Hello from Client-3");
        return jsonObject.toString();

        //return "Hello from Client-3";
    }
}
