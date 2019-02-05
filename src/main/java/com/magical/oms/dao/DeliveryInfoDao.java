package com.magical.oms.dao;

import com.magical.oms.model.DeliveryInfo;

import java.util.List;

public interface DeliveryInfoDao {
    DeliveryInfo getDeliveryInfoById(int id);

    boolean addDeliveryInfo(DeliveryInfo delivery);

    boolean updDeliveryInfo(DeliveryInfo delivery);

    boolean removeDeliveryInfoById(int id);

    int getCountRows();

    List<DeliveryInfo> getAllDeliveryInfo(final int pageNo, final int pageSize);

    List<DeliveryInfo> getDeliveryInfoByCustomerId(int customerId);

    DeliveryInfo getDeliveryInfoByOrderId(int orderId);
}
