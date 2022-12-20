package com.jomardev25.ecommerce.commonservice.service.impl;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import org.javatuples.Pair;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jomardev25.ecommerce.commonservice.dto.ApparelImagesDTO;
import com.jomardev25.ecommerce.commonservice.dto.BrandImagesDTO;
import com.jomardev25.ecommerce.commonservice.dto.ProductInfoDTO;
import com.jomardev25.ecommerce.commonservice.entity.images.ApparelImages;
import com.jomardev25.ecommerce.commonservice.entity.images.BrandImages;
import com.jomardev25.ecommerce.commonservice.entity.images.CarouselImages;
import com.jomardev25.ecommerce.commonservice.entity.info.ProductInfo;
import com.jomardev25.ecommerce.commonservice.model.FilterAttributesResponse;
import com.jomardev25.ecommerce.commonservice.model.HomeTabsDataResponse;
import com.jomardev25.ecommerce.commonservice.model.MainScreenResponse;
import com.jomardev25.ecommerce.commonservice.model.SearchSuggestionResponse;
import com.jomardev25.ecommerce.commonservice.repository.categories.ApparelCategoryRepository;
import com.jomardev25.ecommerce.commonservice.repository.categories.GenderCategoryRepository;
import com.jomardev25.ecommerce.commonservice.repository.categories.ProductBrandCategoryRepository;
import com.jomardev25.ecommerce.commonservice.repository.categories.SortByCategoryRepository;
import com.jomardev25.ecommerce.commonservice.repository.images.ApparelImagesRepository;
import com.jomardev25.ecommerce.commonservice.repository.images.BrandImagesRepository;
import com.jomardev25.ecommerce.commonservice.repository.images.CarouselImagesRepository;
import com.jomardev25.ecommerce.commonservice.repository.info.ProductInfoRepository;
import com.jomardev25.ecommerce.commonservice.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
    private ProductInfoRepository productInfoRepository;

	@Autowired
    private BrandImagesRepository brandImagesRepository;

	@Autowired
    private ApparelImagesRepository apparelImagesRepository;

	@Autowired
    private CarouselImagesRepository carouselImagesRepository;

	@Autowired
    private SortByCategoryRepository sortByCategoryRepository;

	@Autowired
    private GenderCategoryRepository genderCategoryRepository;

	@Autowired
    private ApparelCategoryRepository apparelCategoryRepository;

	 @Autowired
	 private ProductBrandCategoryRepository productBrandCategoryRepository;

	@Autowired
    private ModelMapper modelMapper;

	@Cacheable(key = "#apiName", value = "mainScreenResponse")
    public MainScreenResponse getHomeScreenData(String apiName) {

        List<BrandImages> brandList = brandImagesRepository.getAllData();
        Type listType = new TypeToken<List<BrandImagesDTO>>() {}.getType(); // Check Object Mapper

        List<BrandImagesDTO> brandDTOList = modelMapper.map(brandList, listType);

        List<ApparelImages> apparelList = apparelImagesRepository.getAllData();
        listType = new TypeToken<List<ApparelImagesDTO>>() {}.getType();
        List<ApparelImagesDTO> apparelDTOList = modelMapper.map(apparelList, listType);
        List<CarouselImages> carouselList = carouselImagesRepository.getAllData();

        return new MainScreenResponse(brandDTOList, apparelDTOList, carouselList);
    }

	@Cacheable(key = "#queryParams", value = "filterAttributesResponse")
    public FilterAttributesResponse getFilterAttributesByProducts(String queryParams) {
        HashMap<String, String> conditionMap = getConditionMapFromQuery(queryParams);

        if (conditionMap != null && !conditionMap.isEmpty()) {
            FilterAttributesResponse filterAttributesResponse = productInfoRepository.getFilterAttributesByProducts(conditionMap);
            filterAttributesResponse.setSortby(sortByCategoryRepository.getAllData());
            return filterAttributesResponse;
        }
        return null;
    }

	@Cacheable(key = "#queryParams", value = "productInfoDTO")
	public ProductInfoDTO getProductsByCategories(String queryParams) {
		HashMap<String, String> conditionMap = getConditionMapFromQuery(queryParams);
        ProductInfoDTO productInfoDTO = null;

        if (conditionMap != null && !conditionMap.isEmpty()) {
            Pair<Long, List<ProductInfo>> result = productInfoRepository.getProductsByCategories(conditionMap);
            if (result != null) {
                productInfoDTO = new ProductInfoDTO(result.getValue0(), result.getValue1());

            }
        }

        return productInfoDTO;
	}

	@Cacheable(key = "#queryParams", value = "hashMap")
	public HashMap<Integer, ProductInfo> getProductsById(String queryParams) {
		String[] productIds = queryParams.split(",");
        HashMap<Integer, ProductInfo> resultMap = null;

        if (productIds.length > 0) {
            List<ProductInfo> result = productInfoRepository.getProductsById(productIds);

            if (result != null) {
                resultMap = new HashMap<>();
                for (ProductInfo info : result) {
                    resultMap.put(info.getId(), info);
                }
            }
        }

        return resultMap;
	}


	@Cacheable(key = "#apiName", value = "homeTabsDataResponse")
    public HomeTabsDataResponse getBrandsAndApparelsByGender(String apiName) {
        return productInfoRepository.getBrandsAndApparelsByGender();
    }

	public SearchSuggestionResponse getSearchSuggestionList() {
        return new SearchSuggestionResponse(genderCategoryRepository.getAllData(),
                productBrandCategoryRepository.getAllData(), apparelCategoryRepository.getAllData(),
                productInfoRepository.getGenderAndApparelByIdAndName(),
                productInfoRepository.getGenderAndBrandByIdAndName(),
                productInfoRepository.getApparelAndBrandByIdAndName(),
                productInfoRepository.getGenderApparelBrandByIdAndName(),
                productInfoRepository.getProductByName());
    }

	private HashMap<String, String> getConditionMapFromQuery(String queryParams) {
        // append :: at the end so that we can split even if there is just one condition
        // for ex. ?q=brand=1::
        queryParams = queryParams.concat("::");
        String[] separatedConditions = queryParams.split("::");

        if (separatedConditions.length > 0) {
            HashMap<String, String> conditionMap = new HashMap<>();
            for (String condition : separatedConditions) {
                String[] categories = condition.split("=");
                if (categories.length > 1) {
                    conditionMap.put(categories[0], categories[1]);
                }
            }
            return conditionMap;
        }
        return null;
    }

}
