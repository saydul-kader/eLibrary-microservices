package com.eLibrary.order.service.impl;

import com.eLibrary.order.configuration.KafkaProducerConfig;
import com.eLibrary.order.configuration.LoggerConfig;
import com.eLibrary.order.domain.OrderInfo;
import com.eLibrary.order.domain.dto.OrderDTO;
import com.eLibrary.order.openfeign.CustomerFeignClient;
import com.eLibrary.order.openfeign.ProductFeignClient;
import com.eLibrary.order.repository.OrderRepository;
import com.eLibrary.order.service.OrderService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private CustomerFeignClient customerFeignClient;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaProducerConfig kafkaProducerConfig;

    @Override
    public ResponseEntity<?> createOrder(OrderDTO orderInfo) {
        String productCode = orderInfo.getProductCode();
        String userName = orderInfo.getUserName();
        Map productResponse = (Map) productFeignClient.findProductByProductCode(productCode);
        Map customerResponse = (Map) customerFeignClient.findUserByUserName(userName);
        OrderInfo orderInformation = generateOrderInfo(productResponse, customerResponse);
        orderRepository.save(orderInformation);
//        try{
//            kafkaProducerConfig.publishMessage("NOTIFICATION", orderInformation);
//        } catch (Exception ex){
//            LoggerConfig.log(Level.SEVERE, "Unable to publish data in Kafka");
//        }
        return new ResponseEntity<>(orderInformation, HttpStatus.OK);
    }

    @Override
    public OrderInfo generateOrderInfo(Map productResponse, Map customerResponse) {
        long price = (long) productResponse.get("amount");
        long quantity = (long) productResponse.get("quantity");

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(UUID.randomUUID().toString());
        orderInfo.setCustomerId(customerResponse.get("id").toString());
        orderInfo.setCustomerAddress(customerResponse.get("address").toString());
        orderInfo.setCustomerName(customerResponse.get("userName").toString());
        orderInfo.setProductCode(productResponse.get("productCode").toString());
        orderInfo.setProductDetails(productResponse.get("productDetails").toString());
        orderInfo.setAmount(price * quantity);
        orderInfo.setCompleted(true);
        return orderInfo;
    }
}
