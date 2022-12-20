package com.jomardev25.ecommerce.commonservice.repository.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jomardev25.ecommerce.commonservice.entity.categories.ProductBrandCategory;

import java.util.List;

public interface ProductBrandCategoryRepository extends JpaRepository<ProductBrandCategory, Integer> {

    @Query(value = "SELECT p FROM ProductBrandCategory p")
    List<ProductBrandCategory> getAllData();

    ProductBrandCategory findByType(String brandName);

}
