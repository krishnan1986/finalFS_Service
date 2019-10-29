package com.finalFS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalFS.dao.DBOpsImpl;
import com.finalFS.model.User;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/home")
public class BaseController {
	@Autowired
	DBOpsImpl daoObj;

	@PostMapping(value="/addUser")
	public void addUsertoDB(User user)
	{
		daoObj.addUser(user);
	}
	
}
