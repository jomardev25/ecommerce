package com.jomardev25.ecommerce.commonservice.entity.categories;

import java.io.Serializable;

import javax.persistence.Entity;
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
public class SortByCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    private int id;

    private String type;

    public SortByCategory(int id, String type) {
        this.id = id;
        this.type = type;
    }

}