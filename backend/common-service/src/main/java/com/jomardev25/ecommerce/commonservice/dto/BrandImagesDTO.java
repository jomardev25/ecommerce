package com.jomardev25.ecommerce.commonservice.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BrandImagesDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title;
    private String imageLocalPath;
    private String imageURL;
    private BrandCategoryDTO brandInfo;

}

@Getter
@Setter
@NoArgsConstructor
@ToString
class BrandCategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;

}