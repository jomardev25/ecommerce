package com.jomardev25.ecommerce.authenticationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jomardev25.ecommerce.authenticationservice.entity.User;

public interface UserRepositoy extends JpaRepository<User, Integer> {

}
