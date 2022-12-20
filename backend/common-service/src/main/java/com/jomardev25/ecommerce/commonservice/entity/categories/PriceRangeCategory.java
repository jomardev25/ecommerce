package com.jomardev25.ecommerce.commonservice.entity.categories;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jomardev25.ecommerce.commonservice.entity.info.ProductInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class PriceRangeCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    private int id;

    private String type;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "priceRangeCategory")
    @JsonIgnore
    private List<ProductInfo> productInfos;

    public PriceRangeCategory(int id, String type) {
        this.id = id;
        this.type = type;
    }

}
