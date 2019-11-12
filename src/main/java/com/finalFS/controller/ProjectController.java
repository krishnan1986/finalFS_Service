package com.finalFS.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalFS.dao.DBOperations;
import com.finalFS.model.Project;

@RestController
@RequestMapping("/")
public class ProjectController {
	
	@Autowired
	DBOperations dbo; 
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path="/addProject")
	public void addProjectToDB(@RequestBody Map<String, String> project ) throws ParseException
	{
		// daoObj.addUser(user);
				Project p = new Project();
				p.setName(project.get("projectname"));
				p.setPriority(project.get("priority"));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				p.setStartDate(format.parse(project.get("StartDate")));
				p.setEndDate(format.parse(project.get("endDate")));
				p.setManager(project.get("managerName"));
				dbo.addProject(p);
	}
	

}
