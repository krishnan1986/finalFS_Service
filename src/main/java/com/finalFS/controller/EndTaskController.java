package com.finalFS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalFS.dao.TaskDao;

@RestController
@RequestMapping("/")
public class EndTaskController {
	@Autowired
	TaskDao taskdao;

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value="/endTask/{taskname}")
 //@RequestMapping(value = "/endTask/{taskname}", method = RequestMethod.DELETE)
 //@ResponseBody
public void removeTask(@PathVariable("taskname") String taskName )
{
		
	// find by name 
	taskdao.deleteTask(taskName);
	//taskdao.deleteTask(restask);
}
	
}
