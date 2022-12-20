package com.jomardev25.ecommerce.commonservice.entity.info;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class ContactInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String email;

    private String office;

    private String mobile;

    private String other;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contactInfo")
    @JsonIgnore
    private List<BankInfo> banks;

    public ContactInfo(String email, String office, String mobile, String other) {
        this.email = email;
        this.office = office;
        this.mobile = mobile;
        this.other = other;
    }

}