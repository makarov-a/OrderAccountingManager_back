package com.magical.oms.controllers;

import com.magical.oms.dto.CustomerDto;
import com.magical.oms.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class CustomerController {
    @Autowired
    private CustomerService service;
    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/customers")
    public List<CustomerDto> getCustomers() {
        logger.info("GET /customers");
        List<CustomerDto> list = service.getAllCustomers();
        return list;
    }

    @GetMapping("/customers/count")
    public int getCountElements() {
        logger.info("GET /customers/count");
        return service.getCountElements();
    }

    @RequestMapping(
            value = "/customers",
            params = { "page", "size" },
            method = RequestMethod.GET
    )
    public List<CustomerDto> getCustomers(@RequestParam("page") int page, @RequestParam("size") int size) {
        logger.info("GET /customers?page="+page+"&size="+size);
        List<CustomerDto> list = service.getAllCustomers(page,size);
        return list;
    }

    @GetMapping("/customers/{customerId}")
    public CustomerDto getOrder(@PathVariable("customerId") Integer customerId) {
        logger.info("GET /customers/"+customerId);
        return service.getCustomerById(customerId);
    }

    @RequestMapping(value = "/customers", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    boolean addCustomer(@RequestBody @Valid CustomerDto customerDto) {
        logger.info("POST /customers");
        return service.addCustomer(customerDto);
    }

    @RequestMapping(value = "/customers", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    boolean updCustomer(@RequestBody @Valid CustomerDto customerDto) {
        logger.info("PUT /customers for customer with id"+ customerDto.getId());
        return service.updCustomer(customerDto);
    }

    @RequestMapping(value = "/customers/{customerId}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void removeCustomer(@PathVariable("customerId") Integer customerId) {
        logger.info("DELETE /customers/"+customerId);
        service.removeCustomerById(customerId);
    }
}
