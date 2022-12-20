package com.jomardev25.ecommerce.commonservice.entity.info;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class OrderInfo implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private int customerId;

    private String timestamp;

    private String deliveryStatus;

    private String trackPackageLink;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressInfo addressInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private OrderInfo orderInfo;

    public OrderInfo(int customerId, String timestamp, String deliveryStatus, String trackPackageLink, OrderInfo orderInfo) {
        this.customerId = customerId;
        this.timestamp = timestamp;
        this.deliveryStatus = deliveryStatus;
        this.trackPackageLink = trackPackageLink;
        this.orderInfo = orderInfo;
    }
}
