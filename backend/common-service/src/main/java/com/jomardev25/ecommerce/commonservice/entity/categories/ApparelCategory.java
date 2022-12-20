package com.jomardev25.ecommerce.commonservice.entity.categories;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jomardev25.ecommerce.commonservice.entity.images.ApparelImages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class ApparelCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	private String type;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "apparelCategory")
	@JsonIgnore
	private List<ApparelImages> apparelImages;

	public ApparelCategory(String type) {
        this.type = type;
    }

}
