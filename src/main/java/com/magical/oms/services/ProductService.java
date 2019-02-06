package com.magical.oms.services;

import com.magical.oms.dao.ProductDao;
import com.magical.oms.dto.ProductDto;
import com.magical.oms.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public List<ProductDto> getAllProducts() {
        List<ProductDto> productsDto = new ArrayList<>();
        List<Product> list = productDao.getAllProducts(1, getCountElements());
        list.stream().forEach(i -> productsDto.add(convertToDto(i)));
        return productsDto;
    }

    public List<ProductDto> getAllProducts(int pageNo, int pageSize) {
        List<ProductDto> productsDto = new ArrayList<>();
        List<Product> list = productDao.getAllProducts(pageNo, pageSize);
        list.stream().forEach(i -> productsDto.add(convertToDto(i)));
        return productsDto;
    }

    public ProductDto getProductById(int id) {
        return convertToDto(productDao.getProductById(id));
    }

    public boolean removeProductById(int id) {
        return productDao.removeProductById(id);
    }

    public boolean addProduct(ProductDto product) {
        return productDao.addProduct(convertFromDto(product));
    }

    public boolean updProduct(ProductDto product) {
        return productDao.updProduct(convertFromDto(product));
    }

    public List<ProductDto> getProductsByOrderId(int orderId) {
        List<ProductDto> productsDto = new ArrayList<>();
        List<Product> list = productDao.getProductsByOrderId(orderId);
        list.stream().forEach(i -> productsDto.add(convertToDto(i)));
        return productsDto;
    }

    public List<ProductDto> getProductsByCustomerId(int customerId) {
        List<ProductDto> productsDto = new ArrayList<>();
        List<Product> list = productDao.getProductsByCustomerId(customerId);
        list.stream().forEach(i -> productsDto.add(convertToDto(i)));
        return productsDto;
    }

    public int getCountElements() {
        return productDao.getCountRows();
    }

    public int getCountPages(int pageSize) {
        return (int)(Math.ceil(getCountElements() / pageSize));
    }

    private Product convertFromDto(ProductDto sourceDto) {
        Product product = new Product();
        product.setId(sourceDto.getId());
        product.setName(sourceDto.getName());
        product.setImg(sourceDto.getImg());
        product.setSize(sourceDto.getSize());
        product.setCost(sourceDto.getCost());
        product.setOrderId(sourceDto.getOrderId());
        return product;
    }

    private ProductDto convertToDto(Product source) {
        ProductDto productDto = new ProductDto();
        productDto.setId(source.getId());
        productDto.setName(source.getName());
        productDto.setImg(source.getImg());
        productDto.setCost(source.getCost());
        productDto.setSize(source.getSize());
        int orderId = source.getOrderId();
        if (orderId > 0) {
            productDto.setOrderId(orderId);
            productDto.setSold(true);
        } else {
            productDto.setSold(false);
        }
        return productDto;
    }
}
