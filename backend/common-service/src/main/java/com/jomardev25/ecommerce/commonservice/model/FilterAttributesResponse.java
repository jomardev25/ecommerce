package com.jomardev25.ecommerce.commonservice.model;

import java.io.Serializable;
import java.util.List;

import com.jomardev25.ecommerce.commonservice.dto.FilterAttributesWithTotalItemsDTO;
import com.jomardev25.ecommerce.commonservice.entity.categories.SortByCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FilterAttributesResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<FilterAttributesWithTotalItemsDTO> brands;
    private List<FilterAttributesWithTotalItemsDTO> genders;
    private List<FilterAttributesWithTotalItemsDTO> apparels;
    private List<SortByCategory> sortby;
    private List<FilterAttributesWithTotalItemsDTO> prices;

}
