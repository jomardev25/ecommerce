package com.jomardev25.ecommerce.commonservice.entity.images;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jomardev25.ecommerce.commonservice.entity.categories.ApparelCategory;
import com.jomardev25.ecommerce.commonservice.entity.categories.GenderCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class ApparelImages implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String title;

    private String imageLocalPath;

    private String imageURL;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "apparel_id", referencedColumnName = "id")
    @JsonIgnore
    private ApparelCategory apparelCategory;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id")
    @JsonIgnore
    private GenderCategory genderCategory;

    public ApparelImages(String title, String imageLocalPath, String imageURL) {
        this.title = title;
        this.imageLocalPath = imageLocalPath;
        this.imageURL = imageURL;
    }

}
