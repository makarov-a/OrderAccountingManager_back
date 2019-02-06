package com.magical.oms.controllers;

import com.magical.oms.dto.DeliveryInfoDto;
import com.magical.oms.services.DeliveryInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DeliveryInfoController {
    @Autowired
    private DeliveryInfoService service;
    private static Logger logger = LoggerFactory.getLogger(DeliveryInfoController.class);

    @GetMapping("/deliveries")
    public List<DeliveryInfoDto> getDeliveries() {
        logger.info("GET /deliveries");
        List<DeliveryInfoDto> list = service.getAllDeliveryInfo();
        return list;
    }

    @GetMapping("/deliveries/count")
    public int getCountElements() {
        logger.info("GET /deliveries/count");
        return service.getCountElements();
    }

    @RequestMapping(
            value = "/deliveries",
            params = { "page", "size" },
            method = RequestMethod.GET
    )
    public List<DeliveryInfoDto> getDeliveries(@RequestParam("page") int page, @RequestParam("size") int size) {
        logger.info("GET /deliveries?page="+page+"&size="+size);
        List<DeliveryInfoDto> list = service.getAllDeliveryInfo(page,size);
        return list;
    }

    @GetMapping("/deliveries/{deliveryId}")
    public DeliveryInfoDto getOrder(@PathVariable("deliveryId") Integer deliveryId) {
        logger.info("GET /deliveries/"+deliveryId);
        return service.getDeliveryInfoById(deliveryId);
    }

    @RequestMapping(value = "/deliveries", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    boolean addDelivery(@RequestBody @Valid DeliveryInfoDto deliveryDto) {
        logger.info("POST /deliveries");
        return service.addDeliveryInfo(deliveryDto);
    }

    @RequestMapping(value = "/deliveries", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    boolean updDelivery(@RequestBody @Valid DeliveryInfoDto deliveryDto) {
        logger.info("PUT /deliveries for delivery with id"+ deliveryDto.getId());
        return service.updDeliveryInfo(deliveryDto);
    }

    @RequestMapping(value = "/deliveries/{deliveryId}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void removeDelivery(@PathVariable("deliveryId") Integer deliveryId) {
        logger.info("DELETE /deliveries/"+deliveryId);
        service.removeDeliveryInfoById(deliveryId);
    }
}
