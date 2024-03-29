package com.finalFS.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalFS.dao.TaskDao;
import com.finalFS.model.ParentTask;
import com.finalFS.model.Project;
import com.finalFS.model.Task;
import com.finalFS.model.User;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class AddTaskController {
	@Autowired
	TaskDao taskdao;
	
	@PostMapping(path="/addTask",produces="application/json", consumes="application/json")
//    @RequestMapping(value="/addTask",method=RequestMethod.POST,  headers="Accept=application/json")
/*
* @POST
* 
* @Path("/addTask")
* 
* @Consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
* 
*/
/*public ResponseEntity<Object> addTasktoDB(@RequestParam(name= "taskname") String taskname,
				@RequestParam(name="priority") String priority,
				@RequestParam(name="parentTaskid")String ptaskid,
				@RequestParam(name="startDate") LocalDate startDate,
				@RequestParam(name="endDate") LocalDate endDate)
*/
public ResponseEntity<String> addTask(@RequestBody Map<String,String> task,HttpServletRequest request)
{
		Task requestTask = new Task();
		ParentTask pt = new ParentTask();
		Project pmodel =new Project();
		User user = new User();
		pmodel.setName(task.get("selectedProjectName"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		requestTask.setName((String) task.get("taskname"));
		try {
			if(task.containsKey("startDate")) {
			requestTask.setStartDate(sdf.parse((String) (task.get("startDate"))));
			requestTask.setEndDate(sdf.parse((String) task.get("endDate")));
			requestTask.setPriority(Integer.valueOf (task.get("Priority")));
			}
			
			pt.setName(((String) task.get("parentTask")));
			
			// get the project id from project name
		  Long foreignkey=taskdao.getProjectId(task.get("selectedProjectName"));
		  pmodel.setPid(foreignkey);
		  //set user working on task as owner
		  String fullName=task.get("taskOwner");
		  user.setFirstName(fullName.substring(0,fullName.indexOf(" ")));
		  user.setLastName(fullName.substring(fullName.indexOf(" ")+1,fullName.length()));
		  
		  user.setId(taskdao.getUserId(user.getFirstName()));
		  user.setProject(pmodel);
		  requestTask.setUser(user);
		  requestTask.setProject(pmodel);
		  requestTask.setPtask(pt);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
taskdao.insertTask(requestTask);
return ResponseEntity.status(HttpStatus.CREATED).build();
//System.out.println(taskname+":"+priority+ptaskid+""+startDate.toString()+endDate.toString());
}
}
