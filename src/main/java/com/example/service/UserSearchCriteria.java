package com.example.service;

import org.springframework.util.Assert;

public class UserSearchCriteria {

	private String firsname;

	public UserSearchCriteria() {
	}

	public UserSearchCriteria(String firsname) {
		Assert.notNull(firsname, "Firsname must not be null");
		this.firsname = firsname;
	}

	public String getFirstname() {
		return this.firsname;
	}

	public void setFirstname(String firsname) {
		this.firsname = firsname;
	}

}
