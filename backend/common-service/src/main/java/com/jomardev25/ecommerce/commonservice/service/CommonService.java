package com.jomardev25.ecommerce.commonservice.service;

import java.util.HashMap;

import com.jomardev25.ecommerce.commonservice.dto.ProductInfoDTO;
import com.jomardev25.ecommerce.commonservice.entity.info.ProductInfo;
import com.jomardev25.ecommerce.commonservice.model.FilterAttributesResponse;
import com.jomardev25.ecommerce.commonservice.model.HomeTabsDataResponse;
import com.jomardev25.ecommerce.commonservice.model.MainScreenResponse;
import com.jomardev25.ecommerce.commonservice.model.SearchSuggestionResponse;

public interface CommonService {

	 MainScreenResponse getHomeScreenData(String apiName);

	 FilterAttributesResponse getFilterAttributesByProducts(String queryParams);

	 ProductInfoDTO getProductsByCategories(String queryParams);

	 HashMap<Integer, ProductInfo> getProductsById(String queryParams);

	 HomeTabsDataResponse getBrandsAndApparelsByGender(String apiName);

	 SearchSuggestionResponse getSearchSuggestionList();

}
