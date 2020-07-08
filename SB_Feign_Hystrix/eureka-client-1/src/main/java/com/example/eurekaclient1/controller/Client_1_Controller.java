package com.example.eurekaclient1.controller;

import com.example.eurekaclient1.feign.MyFeignClient;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.http.RestTemplateEurekaHttpClient;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Client_1_Controller {

    @Autowired
    private MyFeignClient myFeignClient;

    @RequestMapping("/hello")
    public String sayHello() throws JSONException {

        // returning JSON instead of String
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("message","Hello from Client-1");
        jsonObject.put("message-2",new JSONObject(myFeignClient.client2Response()));
        return jsonObject.toString();

        //return "Hello from Client-1";
    }
}
