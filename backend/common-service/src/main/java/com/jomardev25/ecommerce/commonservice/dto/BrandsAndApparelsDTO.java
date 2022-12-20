package com.jomardev25.ecommerce.commonservice.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BrandsAndApparelsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	List<FilterAttributesDTO> brands;
    List<FilterAttributesDTO> apparels;

}
