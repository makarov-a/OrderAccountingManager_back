package com.magical.oms.services;

import com.magical.oms.dao.DeliveryInfoDao;
import com.magical.oms.dto.DeliveryInfoDto;
import com.magical.oms.model.DeliveryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с сущностью Информации о доставке
 */
@Service
public class DeliveryInfoService {
    @Autowired
    private DeliveryInfoDao deliveryDao;

    /**
     * Получение информации о доставке
     *
     * @param id - id информации о доставке
     * @return возвращает DTO информации о доставке
     */
    public DeliveryInfoDto getDeliveryInfoById(int id) {
        return convertToDto(deliveryDao.getDeliveryInfoById(id));
    }

    /**
     * Добавление информации о доставке
     *
     * @param delivery - DTO информации о доставке
     * @return возвращает true в случае успешного добавления, в противном случае false
     */
    public boolean addDeliveryInfo(DeliveryInfoDto delivery) {
        return deliveryDao.addDeliveryInfo(convertFromDto(delivery));
    }

    /**
     * Обновление информации о доставке
     *
     * @param delivery - DTO информации о доставке
     * @return возвращает true в случае успешного обновления, false - в противном случае
     */
    public boolean updDeliveryInfo(DeliveryInfoDto delivery) {
        return deliveryDao.updDeliveryInfo(convertFromDto(delivery));
    }

    /**
     * Удаление информации о доставке
     *
     * @param id - id информации о доставке
     * @return возвращает true в случае успешного удаления, false - в противном случае
     */
    public boolean removeDeliveryInfoById(int id) {
        return deliveryDao.removeDeliveryInfoById(id);
    }

    /**
     * Получение списка информации о доставке для страницы пагинации
     *
     * @param pageNo   - номер страницы пагинации
     * @param pageSize - количество записей на странице пагинации
     * @return возвращает список DTO информации о доставке
     */
    public List<DeliveryInfoDto> getAllDeliveryInfo(final int pageNo, final int pageSize) {
        List<DeliveryInfoDto> deliveryDto = new ArrayList<>();
        List<DeliveryInfo> list = deliveryDao.getAllDeliveryInfo(pageNo, pageSize);
        list.stream().forEach(i -> deliveryDto.add(convertToDto(i)));
        return deliveryDto;
    }

    /**
     * Получение всего списка информации о доставке
     *
     * @return возвращает список DTO информации о доставке
     */
    public List<DeliveryInfoDto> getAllDeliveryInfo() {
        List<DeliveryInfoDto> deliveryDto = new ArrayList<>();
        List<DeliveryInfo> list = deliveryDao.getAllDeliveryInfo(1, getCountElements());
        list.stream().forEach(i -> deliveryDto.add(convertToDto(i)));
        return deliveryDto;
    }

    /**
     * Получение списка информации о доставке для покупателя
     *
     * @param customerId - id покупателя
     * @return возвращает список DTO информации о доставке
     */
    public List<DeliveryInfoDto> getDeliveryInfoByCustomerId(int customerId) {
        List<DeliveryInfoDto> deliveryDto = new ArrayList<>();
        List<DeliveryInfo> list = deliveryDao.getDeliveryInfoByCustomerId(customerId);
        list.stream().forEach(i -> deliveryDto.add(convertToDto(i)));
        return deliveryDto;
    }

    /**
     * Получение информации о доставке для заказа
     *
     * @param orderId - id заказа
     * @return возвращает DTO информации о доставке
     */
    public DeliveryInfoDto getDeliveryInfoByOrderId(int orderId) {
        return convertToDto(deliveryDao.getDeliveryInfoByOrderId(orderId));
    }

    /**
     * Получение количества записей информации о доставке
     *
     * @return возвращает количество записей
     */
    public int getCountElements() {
        return deliveryDao.getCountRows();
    }

    /**
     * Полулчение количества страниц пагинации
     *
     * @param pageSize - количество записей на странице пагинации
     * @return возращает количество страниц пагинации
     */
    public int getCountPages(int pageSize) {
        return (int) (Math.ceil(getCountElements() / pageSize));
    }

    /**
     * Конвертация DTO информации о доставке в объект модели информации о доставке
     *
     * @param sourceDto - DTO информации о доставке
     * @return возвращает объект модели информации о доставке
     */
    private DeliveryInfo convertFromDto(DeliveryInfoDto sourceDto) {
        DeliveryInfo delivery = new DeliveryInfo();
        delivery.setId(sourceDto.getId());
        delivery.setCustomerId(sourceDto.getCustomerId());
        delivery.setAddress(sourceDto.getAddress());
        delivery.setDeliveryCost(sourceDto.getCostDelivery());
        return delivery;
    }

    /**
     * Конвертация объекта модели информации о доставке в DTO информации о доставке
     *
     * @param source - объект модели информации о доставке
     * @return возвращает DTO информации о доставке
     */
    private DeliveryInfoDto convertToDto(DeliveryInfo source) {
        DeliveryInfoDto deliveryDto = new DeliveryInfoDto();
        deliveryDto.setId(source.getId());
        deliveryDto.setCustomerId(source.getCustomerId());
        deliveryDto.setAddress(source.getAddress());
        deliveryDto.setCostDelivery(source.getDeliveryCost());
        return deliveryDto;
    }

}
