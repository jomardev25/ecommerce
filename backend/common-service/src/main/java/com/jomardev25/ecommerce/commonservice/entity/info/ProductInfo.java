package com.jomardev25.ecommerce.commonservice.entity.info;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jomardev25.ecommerce.commonservice.entity.categories.ApparelCategory;
import com.jomardev25.ecommerce.commonservice.entity.categories.GenderCategory;
import com.jomardev25.ecommerce.commonservice.entity.categories.PriceRangeCategory;
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
@Table(indexes = {@Index(columnList = "gender_id, apparel_id, brand_id, price")})
public class ProductInfo implements Serializable {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int sellerId;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private ProductBrandCategory productBrandCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id")
    @JsonIgnore
    private GenderCategory genderCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apparel_id")
    @JsonIgnore
    private ApparelCategory apparelCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id")
    @JsonIgnore
    private PriceRangeCategory priceRangeCategory;

    private double price;

    private int availableQuantity;

    private int deliveryTime;

    private float ratings;

    private boolean verificationStatus;

    private String imageLocalPath;

    @OneToMany(mappedBy = "orderInfo")
    @JsonIgnore
    private List<OrderInfo> orders;

    private String imageURL;

    public ProductInfo(int sellerId, String name, Date publicationDate, ProductBrandCategory productBrandCategory,
                       GenderCategory genderCategory, ApparelCategory apparelCategory,
                       PriceRangeCategory priceRangeCategory,
                       double price, int availableQuantity, int deliveryTime, float ratings,
                       boolean verificationStatus, String imageLocalPath, String imageURL) {
        this.sellerId = sellerId;
        this.name = name;
        this.publicationDate = publicationDate;
        this.productBrandCategory = productBrandCategory;
        this.genderCategory = genderCategory;
        this.apparelCategory = apparelCategory;
        this.priceRangeCategory = priceRangeCategory;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.deliveryTime = deliveryTime;
        this.ratings = ratings;
        this.verificationStatus = verificationStatus;
        this.imageLocalPath = imageLocalPath;
        this.imageURL = imageURL;
    }
}