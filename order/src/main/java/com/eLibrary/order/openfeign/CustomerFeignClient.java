package com.eLibrary.order.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("eLibrary-customer")
public interface CustomerFeignClient {
    @GetMapping("/eLibrary/v1/api/user/{userName}")
    Object findUserByUserName(@PathVariable(name = "userName") String userName);
}
