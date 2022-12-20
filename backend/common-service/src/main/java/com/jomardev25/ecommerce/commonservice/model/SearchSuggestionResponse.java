package com.jomardev25.ecommerce.commonservice.model;

import java.io.Serializable;
import java.util.List;

import com.jomardev25.ecommerce.commonservice.dto.SearchSuggestionForThreeAttrDTO;
import com.jomardev25.ecommerce.commonservice.dto.SearchSuggestionForTwoAttrDTO;
import com.jomardev25.ecommerce.commonservice.entity.categories.ApparelCategory;
import com.jomardev25.ecommerce.commonservice.entity.categories.GenderCategory;
import com.jomardev25.ecommerce.commonservice.entity.categories.ProductBrandCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class SearchSuggestionResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	List<GenderCategory> genderKeywords;
    List<ProductBrandCategory> brandKeywords;
    List<ApparelCategory> apparelKeywords;
    List<SearchSuggestionForTwoAttrDTO> genderApparelKeywords;
    List<SearchSuggestionForTwoAttrDTO> genderBrandKeywords;
    List<SearchSuggestionForTwoAttrDTO> apparelBrandKeywords;
    List<SearchSuggestionForThreeAttrDTO> threeAttrKeywords;
    List<String> productKeywords;

}
