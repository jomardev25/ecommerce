package com.jomardev25.ecommerce.searchservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jomardev25.ecommerce.searchservice.dto.SearchSuggestionKeywordInfo;
import com.jomardev25.ecommerce.searchservice.service.SearchSuggestionService;

@RestController
public class SearchSuggestionController {

	@Autowired
    private SearchSuggestionService searchSuggestionService;

	public void loadSearchSuggestions() {
        searchSuggestionService.loadSearchSuggestionToMap();
    }

    @GetMapping("/search-suggestion")
    public ResponseEntity<?> searchKeyword(@RequestParam String q) {
        return ResponseEntity.ok(searchSuggestionService.searchKeywordFromMap(q));
    }

    @GetMapping("/default-search-suggestion")
    public ResponseEntity<?> defaultSearchSuggestions() {
        List<SearchSuggestionKeywordInfo> resultList = searchSuggestionService.getDefaultSearchSuggestions();
        return ResponseEntity.ok(resultList);
    }
}
