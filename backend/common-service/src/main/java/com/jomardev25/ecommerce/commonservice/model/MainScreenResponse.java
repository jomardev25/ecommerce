package com.jomardev25.ecommerce.commonservice.model;

import java.io.Serializable;
import java.util.List;

import com.jomardev25.ecommerce.commonservice.dto.ApparelImagesDTO;
import com.jomardev25.ecommerce.commonservice.dto.BrandImagesDTO;
import com.jomardev25.ecommerce.commonservice.entity.images.CarouselImages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MainScreenResponse implements Serializable {


	private static final long serialVersionUID = 1L;
	private List<BrandImagesDTO> brands;
    private List<ApparelImagesDTO> apparels;
    private List<CarouselImages> carousels;

    public MainScreenResponse(List<BrandImagesDTO> brands, List<ApparelImagesDTO> apparels, List<CarouselImages> carousels) {
        this.brands = brands;
        this.apparels = apparels;
        this.carousels = carousels;
    }
}
