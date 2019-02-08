package com.magical.oms.dao;

import com.magical.oms.model.Customer;

import java.util.List;

/**
 * Интерфейс DAO для сущности Покупатель
 */
public interface CustomerDao {
    /**
     * Получение данных покупателя
     * @param id - id покупателя
     * @return возвращает данные покупателя
     */
    Customer getCustomerById(int id);

    /**
     * Добавление нового покупателя
     * @param customer - новый покупатель
     * @return возвращает true в случае успешного добавления, в противном случае false
     */
    boolean addCustomer(Customer customer);

    /**
     * Обновление данных покупателя
     * @param customer - обновленные данные покупателя
     * @return возвращает true в случае успешного обновления, в противном случае false
     */
    boolean updCustomer(Customer customer);

    /**
     * Удаление данных покупателя
     * @param id - id покупателя
     * @return возвращает true в случае успешного удаления, в противном случае false
     */
    boolean removeCustomerById(int id);

    /**
     * Получение списка всех покупателей
     * @param pageNo - номер страницы пагинации
     * @param pageSize - количество записей на странице пагинации
     * @return возвращает список покупателей
     */
    List<Customer> getAllCustomers(final int pageNo, final int pageSize);

    /**
     * Получение количества всех покупателей
     * @return возвращает количество записей покупателей
     */
    int getCountRows();

    /**
     * Получение данных покупателя для заказа
     * @param orderId - id заказа
     * @return возвращает данные покупателя
     */
    Customer getCustomerByOrderId(int orderId);

    /**
     * Получение покупателя для информации о доставке
     * @param deliveryId - id информации о доставке
     * @return возвращает данные покупателя
     */
    Customer getCustomerByDeliveryInfoId(int deliveryId);

}
