package com.magical.oms.dao;

import com.magical.oms.model.Product;

import java.util.List;

public interface ProductDao {
    Product getProductById(int id);

    boolean addProduct(Product product);

    boolean updProduct(Product product);

    boolean removeProductById(int id);

    List<Product> getAllProducts(final int pageNo, final int pageSize);

    int getCountRows();

    List<Product> getProductsByOrderId(int orderId);

    List<Product> getProductsByCustomerId(int customerId);
}