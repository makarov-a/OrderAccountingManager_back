package com.magical.oms.dao;

import com.magical.oms.model.DeliveryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class JdbcDeliveryInfoDao implements DeliveryInfoDao {
    private static Logger logger = LoggerFactory.getLogger(JdbcDeliveryInfoDao.class);
    private final String INSERT_SQL = "INSERT INTO delivery_info(customer_id,address,delivery_cost) VALUES(?,?,?)";
    private final String UPDATE_SQL = "UPDATE delivery_info SET customer_id = ?, address = ?, delivery_cost = ? WHERE id = ?";
    private final String REMOVE_SQL = "DELETE FROM delivery_info WHERE id = ?";
    private final String FETCH_ALL_SQL = "SELECT * FROM delivery_info ORDER BY id";
    private final String FETCH_BY_ID_SQL = "SELECT * FROM delivery_info WHERE id = ?";
    private final String FETCH_BY_CUSTOMER_ID_SQL = "SELECT * FROM delivery_info WHERE customer_id = ?";
    private final String FETCH_BY_ORDER_ID_SQL = "SELECT * FROM delivery_info JOIN orders WHERE delivery_info.customer_id = orders.customer_id AND orders.id = ?";
    private final String COUNT_PAGES_SQL = "SELECT count(*) FROM delivery_info ORDER BY id";
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        logger.info("init jdbcTemplate for " + JdbcDeliveryInfoDao.class + " repository");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public DeliveryInfo getDeliveryInfoById(int id) {
        logger.info("getDeliveryInfoById() for  id = " + id);
        List<DeliveryInfo> deliveryList = this.jdbcTemplate.query(FETCH_BY_ID_SQL + id, new DeliveryRowMapper());
        return deliveryList.get(0);
    }

    @Override
    public boolean addDeliveryInfo(DeliveryInfo delivery) {
        int result = jdbcTemplate.update(INSERT_SQL, new Object[]{delivery.getCustomerId(),delivery.getAddress(),delivery.getDeliveryCost()});
        if (result > 0) {
            logger.info("addDeliveryInfo() with  address = " + delivery.getAddress());
            return true;
        } else {
            logger.error("Not add DeliveryInfo with  name = " + delivery.getAddress());
            return false;
        }
    }

    @Override
    public boolean updDeliveryInfo(DeliveryInfo delivery) {
        int result = jdbcTemplate.update(UPDATE_SQL, new Object[]{delivery.getCustomerId(),delivery.getAddress(),delivery.getDeliveryCost(),delivery.getId()});
        if (result > 0) {
            logger.info("updDeliveryInfo() with address = " +delivery.getAddress());
            return true;
        } else {
            logger.error("Not update DeliveryInfo with address = " + delivery.getAddress());
            return false;
        }
    }

    @Override
    public boolean removeDeliveryInfoById(int id) {
        logger.info("removeDeliveryInfoById() is called");

        int result = jdbcTemplate.update(REMOVE_SQL, new Object[]{id});
        if (result > 0) {
            logger.info("DeliveryInfo with id=" + id + "is deleted");
            return true;
        } else {
            logger.error("DeliveryInfo with id=" + id + "is NOT deleted");
            return false;
        }
    }

    @Override
    public int getCountRows() {
        logger.info("getCountRows() is called");
        return this.jdbcTemplate.queryForObject(COUNT_PAGES_SQL, Integer.class);
    }

    @Override
    public List<DeliveryInfo> getAllDeliveryInfo(int pageNo, int pageSize) {
        logger.info("getAllProducts() is called");
        PaginationHelper<DeliveryInfo> pageHelper = new PaginationHelper<>();
        Page deliveriesPage = pageHelper.fetchPage(this.jdbcTemplate, COUNT_PAGES_SQL, FETCH_ALL_SQL, new Object[]{},
                pageNo, pageSize, new DeliveryRowMapper());
        return deliveriesPage.getPageItems();
    }

    @Override
    public List<DeliveryInfo> getDeliveryInfoByCustomerId(int customerId) {
        logger.info("getDeliveryInfoByCustomerId() is called");
        List<DeliveryInfo> deliveryList = this.jdbcTemplate.query(FETCH_BY_CUSTOMER_ID_SQL + customerId, new DeliveryRowMapper());
        return deliveryList;
    }

    @Override
    public DeliveryInfo getDeliveryInfoByOrderId(int orderId) {
        logger.info("getDeliveryInfoByOrderId() is called");
        List<DeliveryInfo> deliveryList = this.jdbcTemplate.query(FETCH_BY_ORDER_ID_SQL + orderId, new DeliveryRowMapper());
        return deliveryList.get(0);
    }

    class DeliveryRowMapper implements RowMapper {

        @Override
        public DeliveryInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            DeliveryInfo deliveryInfo = new DeliveryInfo();
                deliveryInfo.setId(resultSet.getInt("id"));
                deliveryInfo.setCustomerId(resultSet.getInt("customer_id"));
                deliveryInfo.setAddress(resultSet.getString("address"));
                deliveryInfo.setDeliveryCost(resultSet.getFloat("delivery_cost"));
            return deliveryInfo;
        }

    }
}
