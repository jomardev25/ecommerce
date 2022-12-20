package com.jomardev25.ecommerce.commonservice.repository.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jomardev25.ecommerce.commonservice.entity.categories.PriceRangeCategory;

import java.util.List;

public interface PriceRangeCategoryRepository extends JpaRepository<PriceRangeCategory, Integer> {

    @Query(value = "SELECT p FROM PriceRangeCategory p")
    List<PriceRangeCategory> getAllData();

    PriceRangeCategory findByType(String type);

}
