package com.feedr.controllers;

import com.feedr.protobuf.DemoProto.Demo;
import com.feedr.util.ProtobufUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    /*
    * Note: API paths have to cannot be accessed by browser
    * */

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String helloWorld() {
        return "Hello World";
    }

    @RequestMapping(path = "/demo", method = RequestMethod.GET)
    public String demo() throws InvalidProtocolBufferException {
        Demo.Builder builder =  Demo.newBuilder();
        builder.setUsername("Marlon");
        builder.setPassword("Password");
        String result = ProtobufUtil.protobufToJSON(builder.build());
        return result;
    }
}
