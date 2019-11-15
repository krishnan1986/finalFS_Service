package com.finalFS.dao;

import java.util.List;

import com.finalFS.model.Task;

public interface TaskOperations {
	
	public List<Task> fetchTasks();
	public List<Task> fetchTasks(String name);
	public void insertTask(Task task);
	
	public void updateTask(Task task);
	
	public Long getProjectId(String name);
	
	public Long getUserId(String name);

}
