package com.finalFS.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalFS.dao.DBOperations;
import com.finalFS.model.User;

@RestController
@RequestMapping("/")
public class BaseController {
	@Autowired
	DBOperations daoObj;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/addUser", produces = "application/json", consumes = "application/json")
	public void addUsertoDB(@RequestBody Map<String, String> user) {
		// daoObj.addUser(user);
		User userObj = new User();
		userObj.setEmployeeID(user.get("EmployeeId"));
		userObj.setFirstName(user.get("firstname"));
		userObj.setLastName(user.get("lastname"));
		daoObj.addUser(userObj);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/searchUser/{id}",produces = "application/json")
	public ResponseEntity<User> searchUser(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(daoObj.getUserbyid(id));
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(path = "/UpdateUser")
	public void UpdateUser(@RequestBody User user) {
		// daoObj.addUser(user);
		/*
		 * User userObj = new User(); userObj.setEmployeeID(user.get("EmployeeId"));
		 * userObj.setFirstName(user.get("firstname"));
		 * userObj.setLastName(user.get("lastname"));
		 */
		daoObj.updateUser(user);
	}

}
