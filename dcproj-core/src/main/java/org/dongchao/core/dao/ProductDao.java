package org.dongchao.core.dao;

import org.dongchao.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhaodongchao on 2017/5/15.
 */
public interface ProductDao extends JpaRepository<Product, Long> {
}
