package com.magical.oms.services;

import com.magical.oms.dao.DeliveryInfoDao;
import com.magical.oms.dto.DeliveryInfoDto;
import com.magical.oms.model.DeliveryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryInfoService {
    @Autowired
    private DeliveryInfoDao deliveryDao;

    public DeliveryInfoDto getDeliveryInfoById(int id) {
        return convertToDto(deliveryDao.getDeliveryInfoById(id));
    }

    public boolean addDeliveryInfo(DeliveryInfoDto delivery) {
        return deliveryDao.addDeliveryInfo(convertFromDto(delivery));
    }

    public boolean updDeliveryInfo(DeliveryInfoDto delivery) {
        return deliveryDao.updDeliveryInfo(convertFromDto(delivery));
    }

    public boolean removeDeliveryInfoById(int id) {
        return deliveryDao.removeDeliveryInfoById(id);
    }

    public List<DeliveryInfoDto> getAllDeliveryInfo(final int pageNo, final int pageSize) {
        List<DeliveryInfoDto> deliveryDto = new ArrayList<>();
        List<DeliveryInfo> list = deliveryDao.getAllDeliveryInfo(pageNo, pageSize);
        list.stream().forEach(i -> deliveryDto.add(convertToDto(i)));
        return deliveryDto;
    }

    public List<DeliveryInfoDto> getAllDeliveryInfo() {
        List<DeliveryInfoDto> deliveryDto = new ArrayList<>();
        List<DeliveryInfo> list = deliveryDao.getAllDeliveryInfo(1, getCountElements());
        list.stream().forEach(i -> deliveryDto.add(convertToDto(i)));
        return deliveryDto;
    }

    public List<DeliveryInfoDto> getDeliveryInfoByCustomerId(int customerId) {
        List<DeliveryInfoDto> deliveryDto = new ArrayList<>();
        List<DeliveryInfo> list = deliveryDao.getDeliveryInfoByCustomerId(customerId);
        list.stream().forEach(i -> deliveryDto.add(convertToDto(i)));
        return deliveryDto;
    }

    public DeliveryInfoDto getDeliveryInfoByOrderId(int orderId) {
        return convertToDto(deliveryDao.getDeliveryInfoByOrderId(orderId));
    }

    public int getCountElements() {
        return deliveryDao.getCountRows();
    }

    public int getCountPages(int pageSize) {
        return (int) (Math.ceil(getCountElements() / pageSize));
    }

    private DeliveryInfo convertFromDto(DeliveryInfoDto sourceDto) {
        DeliveryInfo delivery = new DeliveryInfo();
        delivery.setId(sourceDto.getId());
        delivery.setCustomerId(sourceDto.getCustomerId());
        delivery.setAddress(sourceDto.getAddress());
        delivery.setDeliveryCost(sourceDto.getCostDelivery());
        return delivery;
    }

    private DeliveryInfoDto convertToDto(DeliveryInfo source) {
        DeliveryInfoDto deliveryDto = new DeliveryInfoDto();
        deliveryDto.setId(source.getId());
        deliveryDto.setCustomerId(source.getCustomerId());
        deliveryDto.setAddress(source.getAddress());
        deliveryDto.setCostDelivery(source.getDeliveryCost());
        return deliveryDto;
    }

}
