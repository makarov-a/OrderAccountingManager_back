package com.magical.oms.services;

import com.magical.oms.dao.OrderDao;
import com.magical.oms.dto.CustomerDto;
import com.magical.oms.dto.OrderDto;
import com.magical.oms.dto.ProductDto;
import com.magical.oms.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;

    public OrderDto getOrderById(int id){
        return convertToDto(orderDao.getOrderById(id));
    }

    public boolean addOrder(OrderDto order){
        return orderDao.addOrder(convertFromDto(order));
    }

    public boolean updOrder(OrderDto order){
        return orderDao.updOrder(convertFromDto(order));
    }

    public boolean removeOrderById(int id){
        return orderDao.removeOrderById(id);
    }

    public List<OrderDto> getAllOrders(final int pageNo, final int pageSize){
        List<OrderDto> ordersDto = new ArrayList<>();
        List<Order> list = orderDao.getAllOrders(pageNo, pageSize);
        list.stream().forEach(i -> ordersDto.add(convertToDto(i)));
        return ordersDto;
    }

    public List<OrderDto> getAllOrders(){
        List<OrderDto> ordersDto = new ArrayList<>();
        List<Order> list = orderDao.getAllOrders(0, getCountElements());
        list.stream().forEach(i -> ordersDto.add(convertToDto(i)));
        return ordersDto;
    }

    public List<OrderDto> getOrdersByCustomerId(int customerId){
        List<OrderDto> ordersDto = new ArrayList<>();
        List<Order> list = orderDao.getOrdersByCustomerId(customerId);
        list.stream().forEach(i -> ordersDto.add(convertToDto(i)));
        return ordersDto;
    }

    public OrderDto getOrderByProductId(int productId){
        return convertToDto(orderDao.getOrderByProductId(productId));
    }

    public int getCountElements() {
        return orderDao.getCountRows();
    }

    public int getCountPages(int pageSize) {
        return (int) (Math.ceil(getCountElements() / pageSize));
    }

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
