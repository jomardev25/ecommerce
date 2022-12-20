package com.jomardev25.ecommerce.commonservice.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilterAttributesWithTotalItemsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	Integer id;
    String value;
    Long totalItems;

    public FilterAttributesWithTotalItemsDTO(Integer id, String value, Long totalItems) {
        this.id = id;
        this.value = value;
        this.totalItems = totalItems;
    }

}