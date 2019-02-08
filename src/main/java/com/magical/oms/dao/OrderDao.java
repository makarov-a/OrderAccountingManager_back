package com.magical.oms.dao;

import com.magical.oms.model.Order;

import java.util.List;

/**
 * Интерфейс DAO для сущности Заказ
 */
public interface OrderDao {
    /**
     * Получение заказа из хранилища
     * @param id id заказа
     * @return возвращает заказ
     */
    Order getOrderById(int id);

    /**
     * Добавление нового заказа в хранилище
     * @param order - новый заказ
     * @return возвращает true в случае успешного добавления заказа, в противном случае - false
     */
    boolean addOrder(Order order);

    /**
     * Обновление заказа в хранилище
     * @param order - обновленный заказ
     * @return возвращает true в случае успешного обновления заказа, в противном случае - false
     */
    boolean updOrder(Order order);

    /**
     * Удаление заказа из хранилища
     * @param id - id заказа
     * @return возвращает true в случае успешного удаления заказа, в противном случае - false
     */
    boolean removeOrderById(int id);

    /**
     * Получение количества заказов
     * @return возвращает число, соответствующее количеству заказов
     */
    int getCountRows();

    /**
     * Получение списка заказов
     * @param pageNo - номер страницы пагинации
     * @param pageSize - количество записей на странице пагинации
     * @return возвращает список заказов
     */
    List<Order> getAllOrders(final int pageNo, final int pageSize);

    /**
     * Получение списка заказов покупателя
     * @param customerId - id покупателя
     * @return возвращает список заказов
     */
    List<Order> getOrdersByCustomerId(int customerId);

    /**
     * Получение заказа к которому принадлежит продукт (игрушка)
     * @param productId - id-продукта
     * @return вовзвращает заказ
     */
    Order getOrderByProductId(int productId);
}
