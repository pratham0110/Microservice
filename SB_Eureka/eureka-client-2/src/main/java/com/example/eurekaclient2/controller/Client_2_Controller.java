package com.example.eurekaclient2.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Client_2_Controller {

    @Autowired
    public RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String sayHello() throws JSONException {

        // returning JSON instead of String
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("message","Hello from Client-2");
        jsonObject.put("message-3",new JSONObject(
                restTemplate.exchange("http://localhost:8083/hello", HttpMethod.GET,null,String.class)
                        .getBody()));
        return jsonObject.toString();

        //return "Hello from Client-2";
    }
}
