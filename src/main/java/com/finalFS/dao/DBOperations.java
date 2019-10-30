package com.finalFS.dao;

import com.finalFS.model.User;

public interface DBOperations {

	public void addUser(User user);

	public User getUserbyid(Long id);
	
	public void updateUser(User user);
}
