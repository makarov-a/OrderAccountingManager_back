package com.magical.oms.dao;

import com.magical.oms.model.Product;

import java.util.List;

/**
 * Интерфейс DAO для сущности Продукт
 */
public interface ProductDao {
    /**
     * Получение продукта
     * @param id - id продукта
     * @return возвращает данные продукта
     */
    Product getProductById(int id);

    /**
     * Добавление нового продукта
     * @param product - данные продукта
     * @return возвращает true в случае успешного добавления, в противном случае false
     */
    boolean addProduct(Product product);

    /**
     * Обновление продукта
     * @param product - обновленные данные для продукта
     * @return возвращает true в случае успешного обновления, в противном случае false
     */
    boolean updProduct(Product product);

    /**
     * Удаление продукта
     * @param id - id продукта
     * @return возвращает true в случае успешного удаления, в противном случае false
     */
    boolean removeProductById(int id);

    /**
     * Получение списка всех продуктов
     * @param pageNo - номер страницы пагинации
     * @param pageSize - количество записей на странице пагинации
     * @return
     */
    List<Product> getAllProducts(final int pageNo, final int pageSize);

    /**
     * Получение количества продуктов
     * @return возвращает количество продуктов
     */
    int getCountRows();

    /**
     * Получение списка продуктов для заказа
     * @param orderId - id заказа
     * @return возвращает список продуктов
     */
    List<Product> getProductsByOrderId(int orderId);

    /**
     * Получение списка продуктов для покупателя
     * @param customerId - id покупателя
     * @return возвращает список продуктов
     */
    List<Product> getProductsByCustomerId(int customerId);
}