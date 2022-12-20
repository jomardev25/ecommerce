package com.jomardev25.ecommerce.commonservice.controller;

import java.util.HashMap;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jomardev25.ecommerce.commonservice.dto.ProductInfoDTO;
import com.jomardev25.ecommerce.commonservice.entity.info.ProductInfo;
import com.jomardev25.ecommerce.commonservice.model.FilterAttributesResponse;
import com.jomardev25.ecommerce.commonservice.model.HomeTabsDataResponse;
import com.jomardev25.ecommerce.commonservice.model.MainScreenResponse;
import com.jomardev25.ecommerce.commonservice.model.SearchSuggestionResponse;
import com.jomardev25.ecommerce.commonservice.service.CommonService;
import com.jomardev25.ecommerce.commonservice.service.LoadSampleDataService;

@RestController
public class CommonServiceController {

	@Autowired
	private Environment environment;

	@Autowired
	private CommonService commonService;

	@Autowired
	private LoadSampleDataService loadSampleDataService;

	public void initSampleData() {
		if(Objects.equals(environment.getProperty("ACTIVE_PROFILE"), "dev")) {
			loadSampleDataService.loadSampleData();
		}
	}

	@GetMapping(value = "/products", params = "q")
    public ResponseEntity<?> getProductsByCategories(@RequestParam("q") String queryParams) {

        ProductInfoDTO productInfoDTO = commonService.getProductsByCategories(queryParams);

        if (productInfoDTO == null) {
            return ResponseEntity.badRequest().body("Query has not followed the required format.");
        }

        return ResponseEntity.ok(productInfoDTO);

    }

	@GetMapping(value = "/products", params = "product_id")
    public ResponseEntity<?> getProductsById(@RequestParam("product_id") String queryParams) {

        HashMap<Integer, ProductInfo> resultMap = commonService.getProductsById(queryParams);

        if (resultMap == null) {
            return ResponseEntity.badRequest().body("Query has not followed the required format.");
        }

        return ResponseEntity.ok(resultMap);
    }

	@GetMapping("/home")
    public ResponseEntity<?> getMainScreenData() {
        MainScreenResponse mainScreenInfoList = commonService.getHomeScreenData("homeAPI");
        if (mainScreenInfoList == null) {
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(mainScreenInfoList);
    }

	@GetMapping("/tabs")
    public ResponseEntity<?> getHomeTabsDataResponse() {
        HomeTabsDataResponse homeTabsDataResponse = commonService.getBrandsAndApparelsByGender("tabsAPI");
        if (homeTabsDataResponse == null) {
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(homeTabsDataResponse);
    }

	@GetMapping(value = "/filter", params = "q")
    public ResponseEntity<?> getFilterAttributesByProducts(@RequestParam("q") String queryParams) {

        String[] splitParams = queryParams.split("=");
        if(splitParams.length >= 1 && splitParams[0].equals("productname")){
            queryParams="category=all";
        }

        FilterAttributesResponse result = commonService.getFilterAttributesByProducts(queryParams);

        if (result == null) {
            return ResponseEntity.badRequest().body("Query has not followed the required format.");
        }

        return ResponseEntity.ok(result);
    }

	@GetMapping("/search-suggestion-list")
    public ResponseEntity<?> getSearchSuggestionList() {
        SearchSuggestionResponse searchSuggestionList = commonService.getSearchSuggestionList();
        if (searchSuggestionList == null) {
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(searchSuggestionList);
    }
}
