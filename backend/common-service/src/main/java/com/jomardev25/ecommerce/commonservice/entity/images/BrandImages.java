package com.jomardev25.ecommerce.commonservice.entity.images;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jomardev25.ecommerce.commonservice.entity.categories.ProductBrandCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class BrandImages {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String title;

    private String imageLocalPath;

    private String imageURL;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private ProductBrandCategory productBrandCategory;

    public BrandImages(String title, String imageLocalPath, String imageURL) {
        this.title = title;
        this.imageLocalPath = imageLocalPath;
        this.imageURL = imageURL;
    }

}