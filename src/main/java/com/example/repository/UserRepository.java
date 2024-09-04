package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.example.entity.User;

@EnableJpaRepositories
public interface UserRepository extends CrudRepository<User, String> {

	Page<User> findAll(Pageable pageable);

	Page<User> findByFirstname(String firstName, Pageable pageable);

	User findByUsername(String userName);

}
