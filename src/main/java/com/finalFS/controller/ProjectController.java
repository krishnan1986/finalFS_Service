package com.finalFS.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	@PostMapping(path = "/addProject")
	public void addProjectToDB(@RequestBody Map<String, String> project) throws ParseException {
		// daoObj.addUser(user);
		Project p = new Project();
		p.setName(project.get("projectname"));
		p.setPriority(project.get("priority"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (project.containsKey("StartDate") && project.containsKey("endDate")) {
			// if(startOpt.isPresent() && endOpt.isPresent()) {
			if(format.parse(project.get("StartDate")).before(format.parse(project.get("endDate")))) {
			p.setStartDate(format.parse(project.get("StartDate")));
			p.setEndDate(format.parse(project.get("endDate")));
			}
		}
	p.setManager(project.get("managerName"));dbo.addProject(p);

	}

	@CrossOrigin(origins = "http://localhost:4200")

	@GetMapping(path = "/viewProjects", produces = "application/json")
	// @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces =
	// "application/json")
	public List<Project> getProjectsFromUI() {

		List<Project> results = dbo.fetchProjects();
		// list.setTasks(results);
		return results;

	}

}
