package com.jomardev25.ecommerce.commonservice.repository.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jomardev25.ecommerce.commonservice.entity.categories.GenderCategory;

import java.util.List;

public interface GenderCategoryRepository extends JpaRepository<GenderCategory, Integer> {

    @Query(value = "SELECT g FROM GenderCategory g")
    List<GenderCategory> getAllData();

    GenderCategory findByType(String gender);

}