package com.jomardev25.ecommerce.commonservice.entity.images;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class CarouselImages implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String link;

    private String imageLocalPath;

    private String imageURL;

    public CarouselImages(String link, String imageLocalPath, String imageURL) {
        this.link = link;
        this.imageLocalPath = imageLocalPath;
        this.imageURL = imageURL;
    }
}