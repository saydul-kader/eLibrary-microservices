package com.eLibrary.order.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "eLibrary-product")
public interface ProductFeignClient {
    @GetMapping("/eLibrary/v1/api/product/{productCode}")
    Object findProductByProductCode(@PathVariable(name = "productCode") String productCode);
}
