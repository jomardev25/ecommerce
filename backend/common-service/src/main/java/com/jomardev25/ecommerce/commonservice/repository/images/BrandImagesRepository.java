package com.jomardev25.ecommerce.commonservice.repository.images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jomardev25.ecommerce.commonservice.entity.images.BrandImages;

import java.util.List;

public interface BrandImagesRepository extends JpaRepository<BrandImages, Integer> {

    @Query(value = "SELECT DISTINCT b FROM BrandImages b")
    List<BrandImages> getAllData();

}