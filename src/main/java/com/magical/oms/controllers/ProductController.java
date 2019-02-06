package com.magical.oms.controllers;

import com.magical.oms.dto.ProductDto;
import com.magical.oms.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/products")
    public List<ProductDto> getProducts() {
        logger.info("GET /products");
        List<ProductDto> list = service.getAllProducts();
        return list;
    }

    @GetMapping("/products/count")
    public int getCountElements() {
        logger.info("GET /products/count");
        return service.getCountElements();
    }

    @RequestMapping(
            value = "/products",
            params = { "page", "size" },
            method = RequestMethod.GET
    )
    public List<ProductDto> getProducts(@RequestParam("page") int page, @RequestParam("size") int size) {
        logger.info("GET /products?page="+page+"&size="+size);
        List<ProductDto> list = service.getAllProducts(page,size);
        return list;
    }

    @GetMapping("/products/{productId}")
    public ProductDto getOrder(@PathVariable("productId") Integer productId) {
        logger.info("GET /products/"+productId);
        return service.getProductById(productId);
    }

    @RequestMapping(value = "/products", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    boolean addOrder(@RequestBody @Valid ProductDto productDto) {
        logger.info("POST /products");
        return service.addProduct(productDto);
    }

    @RequestMapping(value = "/products", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    boolean updOrder(@RequestBody @Valid ProductDto productDto) {
        logger.info("PUT /products for product with id"+productDto.getId());
        return service.updProduct(productDto);
    }

    @RequestMapping(value = "/products/{productId}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void removeOrder(@PathVariable("productId") Integer productId) {
        logger.info("DELETE /products/"+productId);
        service.removeProductById(productId);
    }

}
