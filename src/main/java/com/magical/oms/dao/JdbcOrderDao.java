package com.magical.oms.dao;

import com.magical.oms.model.Order;
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
public class JdbcOrderDao implements OrderDao {
    private static Logger logger = LoggerFactory.getLogger(JdbcOrderDao.class);
    private final String INSERT_SQL = "INSERT INTO orders(creation_date,deadline_date,order_cost,prepay_amount,order_comment,customer_id) VALUES(?,?,?,?,?,?)";
    private final String UPDATE_SQL = "UPDATE orders SET creation_date = ?, deadline_date = ?, order_cost = ?, prepay_amount = ?, order_comment = ?, customer_id =   WHERE id = ?";
    //todo: обновление в products.id_order при update'е
    private final String REMOVE_SQL = "DELETE FROM orders WHERE id = ?";
    private final String FETCH_SQL = "SELECT * FROM orders ORDER BY id";
    private final String FETCH_BY_ID_SQL = "SELECT * FROM orders WHERE id = ?";
    private final String FETCH_BY_CUSTOMER_ID_SQL = "SELECT * FROM orders JOIN customers WHERE orders.customer_id = customers.id AND customers.id = ?";
    private final String FETCH_BY_PRODUCT_ID_SQL = "SELECT * FROM orders JOIN products WHERE products.id_order = orders.id AND products.id = ?";
    private final String COUNT_PAGES_SQL = "SELECT count(*) FROM orders ORDER BY id";

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        logger.info("init jdbcTemplate for " + JdbcOrderDao.class + " repository");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Order getOrderById(int id) {
        logger.info("getOrderById() for  id = " + id);
        List<Order> orderList = this.jdbcTemplate.query(FETCH_BY_ID_SQL + id, new OrderRowMapper());
        return orderList.get(0);
    }

    @Override
    public boolean addOrder(Order order) {
        int result = jdbcTemplate.update(INSERT_SQL, new Object[]{order.getCreationDate(),order.getDeadlineDate(),
                                                                  order.getOrderCost(),order.getPrepayAmount(),
                                                                  order.getOrderComment(),order.getCustomerId()
        });

        if (result > 0) {
            logger.info("addOrder() with id = " + order.getId());
            return true;
        } else {
            logger.error("Not add Order with id = " + order.getId());
            return false;
        }
    }

    @Override
    public boolean updOrder(Order order) {
        int result = jdbcTemplate.update(UPDATE_SQL, new Object[]{order.getCreationDate(),order.getDeadlineDate(),
                order.getOrderCost(),order.getPrepayAmount(),
                order.getOrderComment(),order.getCustomerId(),order.getId()});
        if (result > 0) {
            logger.info("updOrder() with id = " + order.getId());
            return true;
        } else {
            logger.error("Not upd Order with id = " + order.getId());
            return false;
        }
    }

    @Override
    public boolean removeOrderById(int id) {
        logger.info("removeOrderById() is called");
        int result = jdbcTemplate.update(REMOVE_SQL, new Object[]{id});
        if (result > 0) {
            logger.info("removeOrder() with id = " + id);
            return true;
        } else {
            logger.error("Not remove Order with id = " + id);
            return false;
        }
    }

    @Override
    public int getCountRows() {
        logger.info("getCountRows() is called");
        return this.jdbcTemplate.queryForObject(COUNT_PAGES_SQL, Integer.class);
    }

    @Override
    public List<Order> getAllOrders(int pageNo, int pageSize) {
        logger.info("getAllOrders() is called");
        PaginationHelper<Order> pageHelper = new PaginationHelper<>();
        Page ordersPage = pageHelper.fetchPage(this.jdbcTemplate, COUNT_PAGES_SQL, FETCH_SQL, new Object[]{},
                pageNo, pageSize, new OrderRowMapper());
        return ordersPage.getPageItems();
    }

    @Override
    public List<Order> getOrdersByCustomerId(int customerId) {
        logger.info("getOrdersByCustomerId() is called");
        List<Order> orderList = this.jdbcTemplate.query(FETCH_BY_CUSTOMER_ID_SQL + customerId, new OrderRowMapper());
        return orderList;
    }

    @Override
    public Order getOrderByProductId(int productId) {
        logger.info("getOrderByProductId() is called");
        List<Order> orderList = this.jdbcTemplate.query(FETCH_BY_PRODUCT_ID_SQL + productId, new OrderRowMapper());
        return orderList.get(0);
    }

    class OrderRowMapper implements RowMapper {

        @Override
        public Order mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setCreationDate(resultSet.getDate("creation_date"));
            order.setDeadlineDate(resultSet.getDate("deadline_date"));
            order.setOrderCost(resultSet.getFloat("order_cost"));
            order.setPrepayAmount(resultSet.getFloat("prepay_amount"));
            order.setOrderComment(resultSet.getString("order_comment"));
            order.setCustomerId(resultSet.getInt("customer_id"));

            return order;
        }

    }

}
