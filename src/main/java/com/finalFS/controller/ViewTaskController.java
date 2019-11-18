package com.finalFS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.finalFS.dao.TaskDao;
import com.finalFS.model.Task;;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ViewTask")
public class ViewTaskController {
	
	 
		@Autowired
		TaskDao taskdao;
	
	@GetMapping(path="/tasks",produces="application/json")
	      //          @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = "application/json")
	public  List<Task> getTaskFromUI()
	{ 
  
		List<Task> results =taskdao.fetchTasks();
		//list.setTasks(results);
	    return results;
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/{projectName}",produces = "application/json")
	public ResponseEntity<List<Task>> searchTask(@PathVariable("projectName") String name) {
		return ResponseEntity.ok().body(taskdao.getTasksByProjectName(name));
	}
	
	
	
	@PutMapping
	//(value="/updateTask")
	public void updateTasktoDB(@RequestBody Task taskRequest)
	{
		taskdao.updateTask(taskRequest);
	}
	
	

	// @RequestMapping(value = "/searchTask", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
		@GetMapping(value="/searchTask")
		@ResponseBody
		 public List<Task> searchTasksinDB(@RequestParam  String name)
		 {
			/*
			 * Task taskfilter =new Task(); taskfilter.setName(name);
			 */
			List<Task> results =taskdao.fetchTasks(name );
				//list.setTasks(results);
			    return results;
		 }
		
		
}