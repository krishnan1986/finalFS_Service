package com.finalFS.dao;

import java.util.List;

import com.finalFS.model.Project;
import com.finalFS.model.User;

public interface DBOperations {

	public void addUser(User user);

	public User getUserbyid(Long id);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public void addProject(Project project);
	public List<Project> fetchProjects();
	
	public List<User> fetchUsers();

	public List<Project> sortBySDate();

	public List<Project> sortByEDate();

	public List<Project> sortByPrio();

	public List<Project> sortByStatus();
	

	public List<User> sortByFname();
	
	public List<User> sortByLname();
	
	public List<User> sortByUserid();
}