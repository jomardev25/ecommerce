package com.jomardev25.ecommerce.commonservice.repository.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jomardev25.ecommerce.commonservice.entity.categories.ApparelCategory;

import java.util.List;

public interface ApparelCategoryRepository extends JpaRepository<ApparelCategory, Integer> {

    @Query(value = "SELECT c FROM ApparelImages c where c.apparelCategory.type=?1 and" + " c.genderCategory.type=?2")
    ApparelCategory findByClothesTypeAndGender(String clothesType, String Gender);

    @Query(value = "SELECT c FROM ApparelCategory c")
    List<ApparelCategory> getAllData();

    ApparelCategory findByType(String title);

}
