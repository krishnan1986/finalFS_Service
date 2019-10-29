package com.finalFS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalFS.dao.DBOpsImpl;
import com.finalFS.model.User;

@RestController
public class BaseController {
	@Autowired
	DBOpsImpl daoObj;

	@PostMapping
	public void addUsertoDB(User user)
	{
		daoObj.addUser(user);
	}
	
}
