package com.example.eurekaclient1.controller;

import com.example.eurekaclient1.feign.MyFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.http.RestTemplateEurekaHttpClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableHystrix
public class Client_1_Controller {

    @Autowired
    private MyFeignClient myFeignClient;

    @RequestMapping("/hello")
    @HystrixCommand(fallbackMethod = "homeFallback")
    public String sayHello() throws JSONException {

        // returning JSON instead of String
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("message","Hello from Client-1");
        jsonObject.put("message-2",new JSONObject(myFeignClient.client2Response()));
        return jsonObject.toString();

        //return "Hello from Client-1";
    }

    public String homeFallback() throws JSONException {

        // returning JSON instead of String
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("message","Hello from Client-1");
        jsonObject.put("message-2","Hello 8092 is down");
        return jsonObject.toString();

        //return "Hello from Client-1";
    }
}
