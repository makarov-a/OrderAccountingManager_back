package com.magical.oms.dao;

import com.magical.oms.model.Order;

import java.util.List;

public interface OrderDao {
    Order getOrderById(int id);

    boolean addOrder(Order order);

    boolean updOrder(Order order);

    boolean removeOrderById(int id);

    int getCountPages(int size);

    List<Order> getAllOrders(final int pageNo, final int pageSize);

    List<Order> getOrdersByCustomerId(int customerId);

    List<Order> getOrdersByDeliveryInfoId(int deliveryId);

    Order getOrderByProductId(int productId);
}
