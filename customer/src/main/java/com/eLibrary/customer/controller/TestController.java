package com.eLibrary.customer.controller;


import com.eLibrary.customer.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.SNIHostName;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/api")
public class TestController {

//    @Value("${customer-service.minimum}")
//    public int minimum;
    @Autowired
    private Configuration configuration;

    @Autowired
    private Environment environment;
    @GetMapping("/get-info")
    public String testMethod() {
        try {
            String address = InetAddress.getLocalHost().getHostAddress();
            System.out.println("*******************************************************");
            System.out.println("hello from the other side " + environment.getProperty("local.server.port") + " " + address);
            System.out.println("*******************************************************");
            return "hello from the other side " + address + " " + address.substring(address.length()-3);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
        //String address = InetAddress.getLocalHost().getHostAddress();

    }
}
