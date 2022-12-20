package com.jomardev25.ecommerce.authenticationservice.service;

import java.util.List;

import com.jomardev25.ecommerce.authenticationservice.entity.User;
import com.jomardev25.ecommerce.authenticationservice.response.UserResponse;

public interface UserService {

	List<User> findAllUsers();

	UserResponse findUserById();

	UserResponse saveUser();

	UserResponse updateUser();

	Long deleteUserById();
}
