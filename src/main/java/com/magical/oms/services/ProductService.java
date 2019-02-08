package com.magical.oms.services;

import com.magical.oms.dao.ProductDao;
import com.magical.oms.dto.ProductDto;
import com.magical.oms.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с сущностью Продукт
 */
@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    /**
     * Получение списка всех продуктов
     *
     * @return возвращает список DTO продуктов
     */
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productsDto = new ArrayList<>();
        List<Product> list = productDao.getAllProducts(1, getCountElements());
        list.stream().forEach(i -> productsDto.add(convertToDto(i)));
        return productsDto;
    }

    /**
     * Получение списка продуктов для страницы пагинации
     *
     * @param pageNo   - номер страницы пагинации
     * @param pageSize - количество записей на странице пагинации
     * @return возвращает список DTO продуктов
     */
    public List<ProductDto> getAllProducts(int pageNo, int pageSize) {
        List<ProductDto> productsDto = new ArrayList<>();
        List<Product> list = productDao.getAllProducts(pageNo, pageSize);
        list.stream().forEach(i -> productsDto.add(convertToDto(i)));
        return productsDto;
    }

    /**
     * Получение продукта
     *
     * @param id - id продукта
     * @return возвращает DTO продукта
     */
    public ProductDto getProductById(int id) {
        return convertToDto(productDao.getProductById(id));
    }

    /**
     * Удаление продукта
     *
     * @param id - id продукта
     * @return возвращает true в случае успшеного удаления, в противном случае - false
     */
    public boolean removeProductById(int id) {
        return productDao.removeProductById(id);
    }

    /**
     * Добавление продукта
     *
     * @param product - DTO продукта
     * @return возвращает true в случае успешного добавления, в противном случае - false
     */
    public boolean addProduct(ProductDto product) {
        return productDao.addProduct(convertFromDto(product));
    }

    /**
     * Обновление продукта
     *
     * @param product - DTO обновленного продукта
     * @return возвращает true в случае успешного добавления, в противном случае - false
     */
    public boolean updProduct(ProductDto product) {
        return productDao.updProduct(convertFromDto(product));
    }

    /**
     * Получение списка DTO продуктов для заказа
     *
     * @param orderId - id заказа
     * @return возвращение списка DTO продуктов
     */
    public List<ProductDto> getProductsByOrderId(int orderId) {
        List<ProductDto> productsDto = new ArrayList<>();
        List<Product> list = productDao.getProductsByOrderId(orderId);
        list.stream().forEach(i -> productsDto.add(convertToDto(i)));
        return productsDto;
    }

    /**
     * Получение списка продуктов для покупателя
     *
     * @param customerId - id покупателя
     * @return возвращает список DTO продуктов
     */
    public List<ProductDto> getProductsByCustomerId(int customerId) {
        List<ProductDto> productsDto = new ArrayList<>();
        List<Product> list = productDao.getProductsByCustomerId(customerId);
        list.stream().forEach(i -> productsDto.add(convertToDto(i)));
        return productsDto;
    }

    /**
     * Получение количества продуктов
     *
     * @return возвращает количество продуктов
     */
    public int getCountElements() {
        return productDao.getCountRows();
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
     * Конвертация DTO продукта в объект модели продукта
     *
     * @param sourceDto - DTO продукта
     * @return возвращает объект модели продукта
     */
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

    /**
     * Конвертация объекта модели продукта в DTO продукта
     *
     * @param source - объект модели продукта
     * @return возвращает DTO продукта
     */
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
