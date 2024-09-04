package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.entity.User;

public interface UserService {

	Page<User> findUser(UserSearchCriteria criteria, Pageable pageable);

	List<User> findAllUser();
}
