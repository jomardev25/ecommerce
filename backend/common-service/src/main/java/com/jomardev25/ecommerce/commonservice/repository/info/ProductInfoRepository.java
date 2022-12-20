package com.jomardev25.ecommerce.commonservice.repository.info;

import java.util.HashMap;
import java.util.List;

import org.javatuples.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jomardev25.ecommerce.commonservice.dto.SearchSuggestionForThreeAttrDTO;
import com.jomardev25.ecommerce.commonservice.dto.SearchSuggestionForTwoAttrDTO;
import com.jomardev25.ecommerce.commonservice.entity.info.ProductInfo;
import com.jomardev25.ecommerce.commonservice.model.FilterAttributesResponse;
import com.jomardev25.ecommerce.commonservice.model.HomeTabsDataResponse;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {

	Pair<Long, List<ProductInfo>> getProductsByCategories(HashMap<String, String> conditionMap);

    List<ProductInfo> getProductsById(String[] productIds);

    FilterAttributesResponse getFilterAttributesByProducts(HashMap<String, String> conditionMap);

    HomeTabsDataResponse getBrandsAndApparelsByGender();

    List<SearchSuggestionForThreeAttrDTO> getGenderApparelBrandByIdAndName();

    List<SearchSuggestionForTwoAttrDTO> getGenderAndApparelByIdAndName();

    List<SearchSuggestionForTwoAttrDTO> getGenderAndBrandByIdAndName();

    List<SearchSuggestionForTwoAttrDTO> getApparelAndBrandByIdAndName();

    @Query(value = "SELECT DISTINCT p.name FROM ProductInfo p")
    List<String> getProductByName();

}
