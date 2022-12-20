package com.jomardev25.ecommerce.commonservice.entity.info;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class AddressInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstLine;

    private String secondLine;

    private String zipCode;

    private String state;

    private String country;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "addressInfo")
    @JsonIgnore
    private List<BankInfo> banks;

    @OneToOne(mappedBy = "addressInfo")
    private OrderInfo order;

    public AddressInfo(String firstLine, String secondLine, String zipCode, String state, String country) {
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.zipCode = zipCode;
        this.state = state;
        this.country = country;
    }

}
