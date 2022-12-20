package com.jomardev25.ecommerce.commonservice.model;

import java.io.Serializable;

import com.jomardev25.ecommerce.commonservice.dto.BrandsAndApparelsDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HomeTabsDataResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private BrandsAndApparelsDTO men;
    private BrandsAndApparelsDTO women;
    private BrandsAndApparelsDTO boys;
    private BrandsAndApparelsDTO girls;
    private BrandsAndApparelsDTO essentials;
    private BrandsAndApparelsDTO homeAndLiving;

}
