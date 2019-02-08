package com.magical.oms.services;

import com.magical.oms.dao.OrderDao;
import com.magical.oms.dto.OrderDto;
import com.magical.oms.dto.ProductDto;
import com.magical.oms.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с сущностью Заказ
 */
@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;

    /**
     * Получение заказа
     *
     * @param id - id заказа
     * @return возвращает DTO заказа
     */
    public OrderDto getOrderById(int id) {
        return convertToDto(orderDao.getOrderById(id));
    }

    /**
     * Добавление заказа
     *
     * @param order - DTO заказа
     * @return возвращает true в случае успешного добавления, в противном случае - false
     */
    public boolean addOrder(OrderDto order) {
        return orderDao.addOrder(convertFromDto(order));
    }

    /**
     * Обновление заказа
     *
     * @param order - DTO обновленного заказа
     * @return возвращает true в случае успешного обновления, в противном случае - false
     */
    public boolean updOrder(OrderDto order) {
        return orderDao.updOrder(convertFromDto(order));
    }

    /**
     * Удаление заказа
     *
     * @param id - id заказа
     * @return возвращает true в случае успешного удаления, в противном случае - false
     */
    public boolean removeOrderById(int id) {
        return orderDao.removeOrderById(id);
    }

    /**
     * Получение списка заказов для страницы пагинации
     *
     * @param pageNo   - номер страницы пагинации
     * @param pageSize - количество записей для страницы пагинации
     * @return возвращает список DTO заказов
     */
    public List<OrderDto> getAllOrders(final int pageNo, final int pageSize) {
        List<OrderDto> ordersDto = new ArrayList<>();
        List<Order> list = orderDao.getAllOrders(pageNo, pageSize);
        list.stream().forEach(i -> ordersDto.add(convertToDto(i)));
        return ordersDto;
    }

    /**
     * Получение списка всех заказов
     *
     * @return возвращает список DTO заказов
     */
    public List<OrderDto> getAllOrders() {
        List<OrderDto> ordersDto = new ArrayList<>();
        List<Order> list = orderDao.getAllOrders(1, getCountElements());
        list.stream().forEach(i -> ordersDto.add(convertToDto(i)));
        return ordersDto;
    }

    /**
     * Получение списка заказов покупателя
     *
     * @param customerId - id покупателя
     * @return возвращает список DTO заказов
     */
    public List<OrderDto> getOrdersByCustomerId(int customerId) {
        List<OrderDto> ordersDto = new ArrayList<>();
        List<Order> list = orderDao.getOrdersByCustomerId(customerId);
        list.stream().forEach(i -> ordersDto.add(convertToDto(i)));
        return ordersDto;
    }

    /**
     * Получение заказа для продукта
     *
     * @param productId - id продукта
     * @return возвращает DTO заказа
     */
    public OrderDto getOrderByProductId(int productId) {
        return convertToDto(orderDao.getOrderByProductId(productId));
    }

    /**
     * Получение количества заказов
     *
     * @return возвращает количество заказов
     */
    public int getCountElements() {
        return orderDao.getCountRows();
    }

    /**
     * Получение количества страниц пагинации
     *
     * @param pageSize - количество записей на странице пагинации
     * @return возвращает количество страниц пагинации
     */
    public int getCountPages(int pageSize) {
        return (int) (Math.ceil(getCountElements() / pageSize));
    }

    /**
     * Конвертация DTO заказа в объект модели заказа
     *
     * @param sourceDto - DTO заказа
     * @return возвращает объект модели заказа
     */
    private Order convertFromDto(OrderDto sourceDto) {
        Order order = new Order();
        order.setId(sourceDto.getId());
        order.setCreationDate(sourceDto.getCreationDate());
        order.setDeadlineDate(sourceDto.getDeadlineDate());
        order.setOrderCost(sourceDto.getOrderCost());
        order.setPrepayAmount(sourceDto.getPrepayAmount());
        order.setOrderComment(sourceDto.getOrderComment());
        order.setCustomerId(sourceDto.getCustomer().getId());
        order.setDeliveryId(sourceDto.getDeliveryId());
        return order;
    }

    /**
     * Конвертация объекта модели заказа в DTO
     *
     * @param source - объект модели заказа
     * @return возвращает DTO заказа
     */
    private OrderDto convertToDto(Order source) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(source.getId());
        orderDto.setCreationDate(source.getCreationDate());
        Date deadlineDate = source.getDeadlineDate();
        orderDto.setDeadlineDate(deadlineDate);
        if (deadlineDate.after(new Date(System.currentTimeMillis()))) {
            orderDto.setComplete(true);
        } else {
            orderDto.setComplete(false);
        }
        orderDto.setPrepayAmount(source.getPrepayAmount());
        orderDto.setOrderComment(source.getOrderComment());
        List<ProductDto> orderProducts = productService.getProductsByOrderId(source.getId());
        float costOfOrder = 0;
        for (ProductDto product : orderProducts) {
            costOfOrder += product.getCost();
        }
        orderDto.setOrderCost(costOfOrder);
        orderDto.setProductsList(orderProducts);
        orderDto.setDeliveryId(source.getDeliveryId());
        orderDto.setCustomer(customerService.getCustomerById(source.getCustomerId()));
        return orderDto;
    }

}
