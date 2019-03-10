package com.spring.rest.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.example.domain.User;

/**
 * User Repository to perform CRUD operations
 * @author shahg
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
