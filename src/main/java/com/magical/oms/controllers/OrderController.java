package com.magical.oms.controllers;

import com.magical.oms.dto.OrderDto;
import com.magical.oms.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService service;
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/orders")
    public List<OrderDto> getOrders() {
        List<OrderDto> list = service.getAllOrders();
        logger.info("Result for getOrders():\n" + list.toString());
        return list;
    }

    @GetMapping("/orders/{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Integer orderId) {
        logger.info("Result for getOrder():\n" + service.getOrderById(orderId).toString());
        return service.getOrderById(orderId);
    }
}
