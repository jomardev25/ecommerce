package com.jomardev25.ecommerce.commonservice.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterAttributesDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	Integer id;
    String value;

}