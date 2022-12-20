package com.jomardev25.ecommerce.commonservice.repository.images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jomardev25.ecommerce.commonservice.entity.images.ApparelImages;

import java.util.List;

public interface ApparelImagesRepository extends JpaRepository<ApparelImages, Integer> {

    @Query(value = "SELECT DISTINCT c FROM ApparelImages c")
    List<ApparelImages> getAllData();

}