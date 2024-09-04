package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Component("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Page<User> findUser(UserSearchCriteria criteria, Pageable pageable) {
		String name = criteria.getFirstname();
		if (!StringUtils.hasLength(name)) {
			return this.userRepository.findAll(null);
		}
		return this.userRepository.findByFirstname(name.trim(), pageable);
	}

	@Override
	public List<User> findAllUser() {
		return (List<User>) this.userRepository.findAll();
	}

}
