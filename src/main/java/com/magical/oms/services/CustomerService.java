package com.magical.oms.services;

import com.magical.oms.dao.CustomerDao;
import com.magical.oms.dto.CustomerDto;
import com.magical.oms.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с сущностью "Покупатель"
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private DeliveryInfoService deliveryService;

    /**
     * Получение DTO покупателя
     *
     * @param id - id покупателя
     * @return возвращает DTO покупателя
     */
    public CustomerDto getCustomerById(int id) {
        return convertToDto(customerDao.getCustomerById(id));
    }

    /**
     * Добавление нового покупателя
     *
     * @param customer - DTO покупателя
     * @return возвращает true в случае успешного добавления, в противном случае false
     */
    public boolean addCustomer(CustomerDto customer) {
        return customerDao.addCustomer(convertFromDto(customer));
    }

    /**
     * Обновление данных покупателя
     *
     * @param customer - DTO обновленного покупателя
     * @return возвращает true в случае успешного обновления, в противном случае false
     */
    public boolean updCustomer(CustomerDto customer) {
        return customerDao.updCustomer(convertFromDto(customer));
    }

    /**
     * Удаление покупателя
     *
     * @param id - id покуателя
     * @return возвращает true в случае успешного удаления, в противном случае false
     */
    public boolean removeCustomerById(int id) {
        return customerDao.removeCustomerById(id);
    }

    /**
     * Получение списка покупателей для страницы пагинации
     *
     * @param pageNo   - номер страницы пагинации
     * @param pageSize - количество записей для страницы пагинации
     * @return возвращает список покупателей
     */
    public List<CustomerDto> getAllCustomers(final int pageNo, final int pageSize) {
        List<CustomerDto> customersDto = new ArrayList<>();
        List<Customer> list = customerDao.getAllCustomers(pageNo, pageSize);
        list.stream().forEach(i -> customersDto.add(convertToDto(i)));
        return customersDto;
    }

    /**
     * Получение списка всех покупателей
     *
     * @return возвращает список покупателей
     */
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customersDto = new ArrayList<>();
        List<Customer> list = customerDao.getAllCustomers(1, getCountElements());
        list.stream().forEach(i -> customersDto.add(convertToDto(i)));
        return customersDto;
    }

    /**
     * Получение покупателя для заказа
     *
     * @param orderId - id заказа
     * @return возвращает DTO покупателя для выбранного заказа
     */
    public CustomerDto getCustomerByOrderId(int orderId) {
        return convertToDto(customerDao.getCustomerByOrderId(orderId));
    }

    /**
     * Получение покупателя, соответствующего информации о доставке
     *
     * @param deliveryId - id информации о доставке
     * @return возвращает DTO покупателя
     */
    public CustomerDto getCustomerByDeliveryInfoId(int deliveryId) {
        return convertToDto(customerDao.getCustomerByDeliveryInfoId(deliveryId));
    }

    /**
     * Получение количества покупателей
     *
     * @return количество покупателей
     */
    public int getCountElements() {
        return customerDao.getCountRows();
    }

    /**
     * Получение количества страниц пагинации
     *
     * @param pageSize - количество записей для страницы пагинации
     * @return
     */
    public int getCountPages(int pageSize) {
        return (int) (Math.ceil(getCountElements() / pageSize));
    }

    /**
     * Конвертирование DTO покупателя в объекто модели покупателя
     *
     * @param sourceDto - DTO покупателя
     * @return возвращает объект модели покупателя
     */
    private Customer convertFromDto(CustomerDto sourceDto) {
        Customer customer = new Customer();
        customer.setId(sourceDto.getId());
        customer.setName(sourceDto.getName());
        customer.setNickname(sourceDto.getNickname());
        customer.setPhone(sourceDto.getPhone());
        customer.setContactComment(sourceDto.getComment());
        return customer;
    }

    /**
     * Конвертирование объекта модели покупателя в DTO покупателя
     *
     * @param source - объект модели покупателя
     * @return возвращает объект DTO покупателя
     */
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
