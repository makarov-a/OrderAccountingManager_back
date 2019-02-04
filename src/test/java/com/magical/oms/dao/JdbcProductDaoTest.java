package com.magical.oms.dao;

import com.magical.oms.OrderAccountingManagerApplication;
import com.magical.oms.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = OrderAccountingManagerApplication.class)
public class JdbcProductDaoTest {
    @Autowired
    private JdbcProductDao productDao;

    @Test
    public void getProductById() {
        Product product = productDao.getProductById(1);
        assertNotNull(product);
    }

    @Test
    public void addProduct() {
        assertTrue(productDao.addProduct(getProduct()));
    }

    @Test
    public void updProduct() {
        Product beforeUpdate = productDao.getProductById(1);
        String nameBeforeUpdate = beforeUpdate.getName();
        beforeUpdate.setName(beforeUpdate.getName() + "test");
        productDao.updProduct(beforeUpdate);
        String nameAfterUpdate = productDao.getProductById(1).getName();
        assertNotEquals(nameBeforeUpdate, nameAfterUpdate);
    }


    @Test
    public void getAllProducts() {
        List<Product> products = productDao.getAllProducts(0, 5);
        assertNotNull(products);
        assertTrue(products.size() > 0);
    }

    @Test
    public void getCountRows() {
        List<Product> products = productDao.getAllProducts(0, 5);
        int countRows = productDao.getCountRows();

    }

    @Test
    public void getProductsByOrderId() {
        List<Product> products = productDao.getProductsByOrderId(1);
        assertNotNull(products);

    }

    @Test
    public void getProductsByCustomerId() {
        List<Product> products = productDao.getProductsByCustomerId(1);
        assertNotNull(products);

    }

    private Product getProduct() {
        Product product = new Product();
        product.setName("Test product Name");
        product.setCost(300);
        product.setSize(25);
        product.setImg("test.png");
        product.setOrderId(2);
        return product;
    }
}