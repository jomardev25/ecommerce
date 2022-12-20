package com.jomardev25.ecommerce.commonservice.repository.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jomardev25.ecommerce.commonservice.entity.categories.SortByCategory;

import java.util.List;

public interface SortByCategoryRepository extends JpaRepository<SortByCategory, Integer> {

    @Query(value = "SELECT s FROM SortByCategory s")
    List<SortByCategory> getAllData();

    SortByCategory findByType(String type);

}
