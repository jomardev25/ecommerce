package com.jomardev25.ecommerce.searchservice.service;

import java.util.List;

import com.jomardev25.ecommerce.searchservice.dto.SearchSuggestionKeywordInfo;

public interface SearchSuggestionService {
	void loadSearchSuggestionToMap();

    List<SearchSuggestionKeywordInfo> searchKeywordFromMap(String q);

    List<SearchSuggestionKeywordInfo> getDefaultSearchSuggestions();
}
