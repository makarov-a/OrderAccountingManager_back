package com.magical.oms.dao;

import com.magical.oms.model.Customer;

import java.util.List;

public interface CustomerDao {
    Customer getCustomerById(int id);

    boolean addCustomer(Customer customer);

    boolean updCustomer(Customer customer);

    boolean removeCustomerById(int id);

    List<Customer> getAllCustomers(final int pageNo, final int pageSize);

    int getCountRows();

    Customer getCustomerByOrderId(int orderId);

    Customer getCustomerByProductId(int productId);

    Customer getCustomerByDeliveryInfoId(int deliveryId);

}
