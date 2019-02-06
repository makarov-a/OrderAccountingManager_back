package com.magical.oms.controllers;

import com.magical.oms.dto.OrderDto;
import com.magical.oms.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService service;
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/orders")
    public List<OrderDto> getOrders() {
        logger.info("GET /orders");
        List<OrderDto> list = service.getAllOrders();
        return list;
    }

    @GetMapping("/orders/count")
    public int getCountElements() {
        logger.info("GET /orders/count");
        return service.getCountElements();
    }

    @RequestMapping(
            value = "/orders",
            params = { "page", "size" },
            method = RequestMethod.GET
    )
    public List<OrderDto> getOrders(@RequestParam("page") int page, @RequestParam("size") int size) {
        logger.info("GET /orders?page="+page+"&size="+size);
        List<OrderDto> list = service.getAllOrders(page,size);
        return list;
    }

    @GetMapping("/orders/{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Integer orderId) {
        logger.info("GET /orders/"+orderId);
        return service.getOrderById(orderId);
    }

    @RequestMapping(value = "/orders", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    boolean addOrder(@RequestBody @Valid OrderDto orderDto) {
        logger.info("POST /orders");
        return service.addOrder(orderDto);
    }

    @RequestMapping(value = "/orders", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    boolean updOrder(@RequestBody @Valid OrderDto orderDto) {
        logger.info("PUT /orders for order with id"+orderDto.getId());
        return service.updOrder(orderDto);
    }

    @RequestMapping(value = "/orders/{orderId}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void removeOrder(@PathVariable("orderId") Integer orderId) {
        logger.info("DELETE /orders/"+orderId);
        service.removeOrderById(orderId);
    }

}
