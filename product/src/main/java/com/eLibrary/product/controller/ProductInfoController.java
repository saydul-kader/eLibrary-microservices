package com.eLibrary.product.controller;


import com.eLibrary.product.domain.ProductInfo;
import com.eLibrary.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eLibrary/v1/api")
public class ProductInfoController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/product")
    public void saveNewProduct(){
        ProductInfo productInfo1 = new ProductInfo();
        productInfo1.setProductName("Intel Core i7 7700k");
        productInfo1.setProductCode("CP7023");
        productInfo1.setProductDetails("Best cpu");
        productInfo1.setPrice(42000);
        productInfo1.setCategory("Processor");
        productInfo1.setQuantity(14);

        productRepository.save(productInfo1);
    }

    @GetMapping("/product")
    public ResponseEntity<?> getAllProducts(){
        List<ProductInfo> productList = productRepository.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/product/{productCode}")
    public ResponseEntity<?> getProductByProductCode(@PathVariable(name = "productCode") String productCode){
        System.out.println(productCode);
        Optional<ProductInfo> productInfo = productRepository.findProductInfoByProductCode(productCode);
        return new ResponseEntity<>(productInfo, HttpStatus.OK);
    }

    @GetMapping("/product/category/{category}")
    public ResponseEntity<?> getProductByProductCategory(@PathVariable(name = "category") String productCategory){
        Optional<ProductInfo> productInfo = productRepository.findProductInfoByCategory(productCategory);
        return new ResponseEntity<>(productInfo, HttpStatus.OK);
    }
}
