package com.finalFS.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.finalFS.dao.DBOperations;
import com.finalFS.dao.TaskDao;
import com.finalFS.model.User;

@RestController
@RequestMapping("/")
public class BaseController {
	@Autowired
	DBOperations daoObj;
	
	@Autowired
	TaskDao taskdao;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/addUser", produces = "application/json", consumes = "application/json")
	public void addUsertoDB(@RequestBody Map<String, String> user) {
		// daoObj.addUser(user);
		User userObj = new User();
		userObj.setEmployeeID(user.get("employeeID"));
		userObj.setFirstName(user.get("firstName"));
		userObj.setLastName(user.get("lastName"));
		daoObj.addUser(userObj);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/searchUser/{id}",produces = "application/json")
	public ResponseEntity<User> searchUser(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(daoObj.getUserbyid(id));
	}

	@CrossOrigin(origins = "http://localhost:4200")
	//@PutMapping(path = "/UpdateUser",consumes={"application/json"})
	@RequestMapping(value="/UpdateUser", method=RequestMethod.PUT ,consumes="application/json")
	public void UpdateUser(@RequestBody Map<String, String> userMap) {
		// daoObj.addUser(user);
		/*
		 * User userObj = new User(); userObj.setEmployeeID(user.get("EmployeeId"));
		 * userObj.setFirstName(user.get("firstname"));
		 * userObj.setLastName(user.get("lastname"));
		 */
		User userObj = new User();
		userObj.setEmployeeID(userMap.get("employeeID"));
		userObj.setFirstName(userMap.get("firstName"));
		userObj.setLastName(userMap.get("lastName"));
		daoObj.updateUser(userObj);
	}

	@CrossOrigin(origins= "http://localhost:4200")
	@DeleteMapping(path="/deleteUser/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
	 User dbuser=daoObj.getUserbyid(id);
		daoObj.deleteUser(dbuser);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path="/viewUsers",produces="application/json")
    //          @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = "application/json")
public  List<User> getUsersFromUI()
{ 

	List<User> results =daoObj.fetchUsers();
	//list.setTasks(results);
  return results;
	
}
	
	
	
	
	
}
