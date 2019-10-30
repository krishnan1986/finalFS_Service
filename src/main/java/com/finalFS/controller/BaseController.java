package com.finalFS.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalFS.dao.DBOperations;
import com.finalFS.dao.DBOpsImpl;
import com.finalFS.model.User;

@RestController
@RequestMapping("/")
public class BaseController {
	@Autowired
	DBOperations daoObj;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path="/addUser",produces="application/json", consumes="application/json")
	public void addUsertoDB(@RequestBody Map<String,String> user)
	{
		//daoObj.addUser(user);
		User userObj = new User();
		userObj.setEmployeeID(user.get("EmployeeId"));
		userObj.setFirstName(user.get("firstname"));
		userObj.setLastName(user.get("lastname"));
		daoObj.addUser(userObj);
		
		
	}
	
}
