package com.eLibrary.customer.controller;

import com.eLibrary.customer.domain.CustomerInfo;
import com.eLibrary.customer.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("eLibrary/v1/api")
public class UserController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @PostMapping("/user")
    public void createUser(){
        CustomerInfo customerInfo = new CustomerInfo("BroHam Niloy","Bro", "Bro@Gmail.com","0980","Chittagong", true);
        userInfoRepository.save(customerInfo);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(){
        List<CustomerInfo> users = userInfoRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{userName}")
    public CustomerInfo getUserByUserName(@PathVariable(name = "userName") String userName){
        Optional<CustomerInfo> customerInfo = userInfoRepository.findCustomerInfoByUserName(userName);
        if (customerInfo.isPresent()){
            return customerInfo.get();
        }
        return null;
    }
}
