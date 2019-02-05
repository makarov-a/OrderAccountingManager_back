package com.magical.oms.dao;

import com.magical.oms.model.Customer;
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
public class JdbcCustomerDao implements CustomerDao {
    private static Logger logger = LoggerFactory.getLogger(JdbcCustomerDao.class);
    private final String INSERT_SQL = "INSERT INTO customers(name,nickname,phone,contact_comment) VALUES(?,?,?,?,?)";
    private final String UPDATE_SQL = "UPDATE customers SET name = ?, nickname = ?, phone = ?, contact_comment WHERE id = ?";
    private final String REMOVE_SQL = "DELETE FROM customers WHERE id =";
    private final String FETCH_ALL_SQL = "SELECT * FROM customers ORDER BY id";
    private final String FETCH_BY_ID_SQL = "SELECT * FROM customers WHERE id =";
    private final String FETCH_BY_DELIVERY_ID_SQL = "SELECT * FROM customers JOIN delivery_info WHERE delivery_info.customer_id = customers.id AND delivery_info.id =";
    private final String FETCH_BY_ORDER_ID_SQL = "SELECT * FROM products JOIN orders WHERE order.customer_id = customers.id AND orders.id =";
    private final String COUNT_PAGES_SQL = "SELECT count(*) FROM customers ORDER BY id";
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        logger.info("init jdbcTemplate for " + JdbcCustomerDao.class + " repository");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Customer getCustomerById(int id) {
        logger.info("getCustomerById() for  id = " + id);
        List<Customer> customerList = this.jdbcTemplate.query(FETCH_BY_ID_SQL + id, new CustomerRowMapper());
        return customerList.get(0);
    }

    @Override
    public boolean addCustomer(Customer customer) {
        int result = jdbcTemplate.update(INSERT_SQL, new Object[]{customer.getName(),customer.getNickname(),customer.getPhone(),customer.getContactComment()});
        if (result > 0) {
            logger.info("addCustomer() with name = " + customer.getName());
            return true;
        } else {
            logger.error("Not add Customer with name = " + customer.getName());
            return false;
        }
    }

    @Override
    public boolean updCustomer(Customer customer) {
        int result = jdbcTemplate.update(UPDATE_SQL, new Object[]{customer.getName(),customer.getNickname(),customer.getPhone(),customer.getContactComment(),customer.getId()});
        if (result > 0) {
            logger.info("updCustomer() with name = " + customer.getName());
            return true;
        } else {
            logger.error("Not update Customer with name = " + customer.getName());
            return false;
        }
    }

    @Override
    public boolean removeCustomerById(int id) {
        logger.info("removeCustomerById() is called");
        int result = jdbcTemplate.update(REMOVE_SQL, new Object[]{id});
        if (result > 0) {
            logger.info("Customer with id=" + id + "is deleted");
            return true;
        } else {
            logger.error("Customer with id=" + id + "is NOT deleted");
            return false;
        }
    }

    @Override
    public List<Customer> getAllCustomers(int pageNo, int pageSize) {
        logger.info("getAllCustomers() is called");
        PaginationHelper<Customer> pageHelper = new PaginationHelper<>();
        Page customersPage = pageHelper.fetchPage(this.jdbcTemplate, COUNT_PAGES_SQL, FETCH_ALL_SQL, new Object[]{},
                pageNo, pageSize, new CustomerRowMapper());
        return customersPage.getPageItems();
    }

    @Override
    public int getCountRows() {
        logger.info("getCountRows() is called");
        return this.jdbcTemplate.queryForObject(COUNT_PAGES_SQL, Integer.class);
    }

    @Override
    public Customer getCustomerByOrderId(int orderId) {
        logger.info("getCustomerByOrderId() is called");
        List<Customer> customerList = this.jdbcTemplate.query(FETCH_BY_ORDER_ID_SQL + orderId, new CustomerRowMapper());
        return customerList.get(0);
    }

    @Override
    public Customer getCustomerByDeliveryInfoId(int deliveryId) {
        logger.info("getCustomerByDeliveryInfoId() is called");
        List<Customer> customerList = this.jdbcTemplate.query(FETCH_BY_DELIVERY_ID_SQL + deliveryId, new CustomerRowMapper());
        return customerList.get(0);
    }

    class CustomerRowMapper implements RowMapper {

        @Override
        public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("name"));
            customer.setNickname(resultSet.getString("nickname"));
            customer.setPhone(resultSet.getString("phone"));
            customer.setContactComment(resultSet.getString("contact_comment"));
            return customer;
        }

    }

}
