package com.magical.oms.dao;

import com.magical.oms.model.DeliveryInfo;

import java.util.List;

/**
 * Интерфейс DAO для сущности "Информация о доставке"
 */
public interface DeliveryInfoDao {
    /**
     * Получение информации о доставке
     * @param id - id записи с информацией о доставке
     * @return возвращает Информацию о доставке
     */
    DeliveryInfo getDeliveryInfoById(int id);

    /**
     * Добавление новой информации о доставке
     * @param delivery - информация о доставке
     * @return возвращает true в случае успешного добавления, в противном случае false
     */
    boolean addDeliveryInfo(DeliveryInfo delivery);

    /**
     * Обновление информации о доставке
     * @param delivery - обновленная информация о доставке
     * @return возвращает true в случае успешного обновления, в противном случае false
     */
    boolean updDeliveryInfo(DeliveryInfo delivery);

    /**
     * Удаление информации о доставке
     * @param id - id записи с информацией о доставке
     * @return
     */
    boolean removeDeliveryInfoById(int id);

    /**
     * Получение количества записей Информации о доставке
     * @return возвращает количество записей информации о доставке
     */
    int getCountRows();

    /**
     * Получение списка записей "информации о доставке"
     * @param pageNo - номер страницы пагинации
     * @param pageSize - количество записей на странице пагинации
     * @return возвращает список информаций о доставке
     */
    List<DeliveryInfo> getAllDeliveryInfo(final int pageNo, final int pageSize);

    /**
     * Получение списка "информации о доставке" для покупателя
     * @param customerId - id покупателя
     * @return возвращает список информаций о доставке
     */
    List<DeliveryInfo> getDeliveryInfoByCustomerId(int customerId);

    /**
     * Получение информации о доставке для заказа
     * @param orderId - id заказа
     * @return возвращает информацию о доставке
     */
    DeliveryInfo getDeliveryInfoByOrderId(int orderId);
}
