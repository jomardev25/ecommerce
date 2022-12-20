package com.jomardev25.ecommerce.commonservice.dto;

import java.io.Serializable;
import java.util.List;

import com.jomardev25.ecommerce.commonservice.entity.info.ProductInfo;

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
public class ProductInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long totalCount;
    private List<ProductInfo> products;

}
