package com.magical.oms.dao;

import com.magical.oms.model.Product;
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
public class JdbcProductDao implements ProductDao {
    private static Logger logger = LoggerFactory.getLogger(JdbcProductDao.class);
    private final String INSERT_SQL = "INSERT INTO products(name,cost,size,img,id_order) VALUES(?,?,?,?,?)";
    private final String UPDATE_SQL = "UPDATE products SET name = ?, cost = ?, size = ?, img = ?, id_order = ?  WHERE id =?";
    private final String REMOVE_SQL = "DELETE FROM products WHERE id =";
    private final String FETCH_SQL = "SELECT * FROM products ORDER BY id DESC ";
    private final String FETCH_BY_ID_SQL = "SELECT * FROM products WHERE id =";
    private final String FETCH_BY_ORDER_ID_SQL = "SELECT * FROM products WHERE id_order =";
    private final String FETCH_BY_CUSTOMER_ID_SQL = "SELECT * FROM products JOIN orders WHERE products.id_order = orders.id AND orders.customer_id =";
    private final String COUNT_PAGES_SQL = "SELECT count(*) FROM products ORDER BY id DESC ";
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        logger.info("init jdbcTemplate for " + JdbcProductDao.class + " repository");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Product getProductById(int id) {
        logger.info("getProductById() for  id = " + id);
        List<Product> productList = this.jdbcTemplate.query(FETCH_BY_ID_SQL + id, new ProductRowMapper());
        return productList.get(0);
    }

    @Override
    public boolean addProduct(Product product) {
        int result = jdbcTemplate.update(INSERT_SQL, new Object[]{product.getName(), product.getCost(),
                product.getSize(), product.getImg(), product.getOrderId()});
        if (result > 0) {
            logger.info("addProduct() with  name = " + product.getName());
            return true;
        } else {
            logger.error("Not add Product with  name = " + product.getName());
            return false;
        }
    }

    @Override
    public boolean updProduct(Product product) {
        int result = jdbcTemplate.update(UPDATE_SQL, new Object[]{product.getName(), product.getCost(),
                product.getSize(), product.getImg(), product.getOrderId(), product.getId()});
        if (result > 0) {
            logger.info("updProduct() with  name = " + product.getName());
            return true;
        } else {
            logger.error("Not update Product with  name = " + product.getName());
            return false;
        }
    }

    @Override
    public boolean removeProductById(int id) {
        logger.info("removeProductById() is called");

        int result = jdbcTemplate.update(REMOVE_SQL, new Object[]{id});
        if (result > 0) {
            logger.info("Product with id=" + id + "is deleted");
            return true;
        } else {
            logger.error("Product with id=" + id + "is NOT deleted");
            return false;
        }
    }

    @Override
    public List<Product> getAllProducts(int pageNo, int pageSize) {
        logger.info("getAllProducts() is called");
        PaginationHelper<Product> pageHelper = new PaginationHelper<>();
        Page productsPage = pageHelper.fetchPage(this.jdbcTemplate, COUNT_PAGES_SQL, FETCH_SQL, new Object[]{},
                pageNo, pageSize, new ProductRowMapper());
        return productsPage.getPageItems();
    }

    @Override
    public int getCountRows() {
        logger.info("getCountRows() is called");
        return this.jdbcTemplate.queryForObject(COUNT_PAGES_SQL, Integer.class);
    }

    @Override
    public List<Product> getProductsByOrderId(int orderId) {
        logger.info("getProductsByOrderId() is called");
        List<Product> productList = this.jdbcTemplate.query(FETCH_BY_ORDER_ID_SQL + orderId, new ProductRowMapper());
        return productList;
    }

    @Override
    public List<Product> getProductsByCustomerId(int customerId) {
        logger.info("getProductsByCustomerId() is called");
        List<Product> productList = this.jdbcTemplate.query(FETCH_BY_CUSTOMER_ID_SQL + customerId, new ProductRowMapper());
        return productList;
    }

    class ProductRowMapper implements RowMapper {

        @Override
        public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setCost(resultSet.getFloat("cost"));
            product.setSize(resultSet.getFloat("size"));
            product.setImg(resultSet.getString("img"));
            product.setOrderId(resultSet.getInt("id_order"));
            logger.info("name = "+resultSet.getString("name"));
            return product;
        }

    }
}

