package com.magical.oms.services;

import com.magical.oms.dao.CustomerDao;
import com.magical.oms.dao.DeliveryInfoDao;
import com.magical.oms.dto.CustomerDto;
import com.magical.oms.dto.DeliveryInfoDto;
import com.magical.oms.model.Customer;
import com.magical.oms.model.DeliveryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private DeliveryInfoService deliveryService;

    CustomerDto getCustomerById(int id) {
        return convertToDto(customerDao.getCustomerById(id));
    }

    boolean addCustomer(CustomerDto customer) {
        return customerDao.addCustomer(convertFromDto(customer));
    }

    boolean updCustomer(CustomerDto customer) {
        return customerDao.updCustomer(convertFromDto(customer));
    }

    boolean removeCustomerById(int id) {
        return customerDao.removeCustomerById(id);
    }

    List<CustomerDto> getAllCustomers(final int pageNo, final int pageSize) {
        List<CustomerDto> customersDto = new ArrayList<>();
        List<Customer> list = customerDao.getAllCustomers(pageNo, pageSize);
        list.stream().forEach(i -> customersDto.add(convertToDto(i)));
        return customersDto;
    }

    List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customersDto = new ArrayList<>();
        List<Customer> list = customerDao.getAllCustomers(0, getCountElements());
        list.stream().forEach(i -> customersDto.add(convertToDto(i)));
        return customersDto;
    }

    CustomerDto getCustomerByOrderId(int orderId) {
        return convertToDto(customerDao.getCustomerByOrderId(orderId));
    }

    CustomerDto getCustomerByDeliveryInfoId(int deliveryId) {
        return convertToDto(customerDao.getCustomerByDeliveryInfoId(deliveryId));
    }

    public int getCountElements() {
        return customerDao.getCountRows();
    }

    public int getCountPages(int pageSize) {
        return (int) (Math.ceil(getCountElements() / pageSize));
    }

    private Customer convertFromDto(CustomerDto sourceDto) {
        Customer customer = new Customer();
        customer.setId(sourceDto.getId());
        customer.setName(sourceDto.getName());
        customer.setNickname(sourceDto.getNickname());
        customer.setPhone(sourceDto.getPhone());
        customer.setContactComment(sourceDto.getComment());
        return customer;
    }

    private CustomerDto convertToDto(Customer source) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(source.getId());
        customerDto.setName(source.getName());
        customerDto.setNickname(source.getNickname());
        customerDto.setPhone(source.getPhone());
        customerDto.setComment(source.getContactComment());
        customerDto.setDeliveries(deliveryService.getDeliveryInfoByCustomerId(source.getId()));
        return customerDto;
    }
}
