package com.jomardev25.ecommerce.authenticationservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;

	public User(int id, String firstName, String lastName, String username, String password, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(String firstName, String lastName, String username, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}

}
