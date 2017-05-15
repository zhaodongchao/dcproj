package org.dongchao.web.webservice.impl;

import org.dongchao.core.dao.ProductDao;
import org.dongchao.model.entity.Product;
import org.dongchao.web.webservice.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaodongchao on 2017/5/15.
 */
@Component("productServiceImpl")
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> retrieveAllProducts() {
        return productDao.findAll();
    }

    @Override
    public Product retrieveProductById(Long id) {
        return productDao.findOne(id);
    }

    @Override
    public List<Product> retrieveProductByName(String name) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProductById(long id, Map<String, Object> fieldMap) {
        return null;
    }

    @Override
    public Product deleteProductById(long id) {
        return null;
    }
}
