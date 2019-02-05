package com.magical.oms.dao;

import com.magical.oms.model.DeliveryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JdbcDeliveryInfoDao implements DeliveryInfoDao {
    private static Logger logger = LoggerFactory.getLogger(JdbcDeliveryInfoDao.class);
    private final String INSERT_SQL = "INSERT INTO delivery(customer_id,address,delivery_cost) VALUES(?,?,?)";
    private final String UPDATE_SQL = "UPDATE delivery SET customer_id = ?, address = ?, delivery_cost = ? WHERE id = ?";
    private final String REMOVE_SQL = "DELETE FROM delivery WHERE id = ?";
    private final String FETCH_SQL = "SELECT * FROM delivery ORDER BY id";
    private final String FETCH_SQL_BY_ID = "SELECT * FROM delivery WHERE id = ?";
    private final String COUNT_PAGES_SQL = "SELECT count(*) FROM delivery ORDER BY id";
    private final String FETCH_SQL_BY_CUSTOMER_ID = "SELECT * FROM deliver WHERE customer_id = ?";
    private final String FETCH_SQL_BY_ORDER_ID = "SELECT * FROM delivery JOIN orders WHERE delivery.customer_id = orders.customer_id AND orders.id = ?";
    @Override
    public DeliveryInfo getDeliveryInfoById(int id) {
        return null;
    }

    @Override
    public boolean addDeliveryInfo(DeliveryInfo delivery) {
        return false;
    }

    @Override
    public boolean updDeliveryInfo(DeliveryInfo delivery) {
        return false;
    }

    @Override
    public boolean removeDeliveryInfoById(int id) {
        return false;
    }

    @Override
    public int getCountPages(int size) {
        return 0;
    }

    @Override
    public List<DeliveryInfo> getAllDeliveryInfo(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public List<DeliveryInfo> getDeliveryInfoByCustomerId(int customerId) {
        return null;
    }

    @Override
    public DeliveryInfo getDeliveryInfoByOrderId(int orderId) {
        return null;
    }
}
