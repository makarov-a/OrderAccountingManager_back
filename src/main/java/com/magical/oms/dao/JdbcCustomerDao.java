package com.magical.oms.dao;

import com.magical.oms.model.Customer;

import java.util.List;

public class JdbcCustomerDao implements CustomerDao{
    @Override
    public Customer getCustomerById(int id) {
        return null;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean updCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean removeCustomerById(int id) {
        return false;
    }

    @Override
    public List<Customer> getAllCustomers(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public int getCountPages(int size) {
        return 0;
    }

    @Override
    public Customer getCustomerByOrderId(int orderId) {
        return null;
    }

    @Override
    public Customer getCustomerByProductId(int productId) {
        return null;
    }

    @Override
    public Customer getCustomerByDeliveryInfoId(int deliveryId) {
        return null;
    }
}
