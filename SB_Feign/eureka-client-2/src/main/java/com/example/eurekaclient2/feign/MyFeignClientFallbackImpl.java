package com.example.eurekaclient2.feign;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MyFeignClientFallbackImpl implements MyFeignClient {

    @Override
    public String client3Response(){

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("message", "Hello from Client-2");
            jsonObject.put("message-3", "Hello 8093 is down");
        }
        catch(JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }
}
