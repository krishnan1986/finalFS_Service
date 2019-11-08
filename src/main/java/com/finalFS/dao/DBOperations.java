package com.finalFS.dao;

import com.finalFS.model.Project;
import com.finalFS.model.User;

public interface DBOperations {

	public void addUser(User user);

	public User getUserbyid(Long id);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public void addProject(Project project);
}
