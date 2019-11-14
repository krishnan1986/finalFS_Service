package com.finalFS.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finalFS.model.Project;
import com.finalFS.model.User;
import com.finalFS.util.HibernateUtil;

@Component
public class DBOpsImpl implements DBOperations {
	

	@Autowired
	HibernateUtil hibernateUtil;
	
	Transaction tx;
	Session session;
	SessionFactory sessionfactory;
	
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		setup();
		session.save(user);
		tx.commit();
		session.close();
		
	}

	
	@Override
	public User getUserbyid(Long id) {
		// TODO Auto-generated method stub
		setup();
		User user= (User)session.get(User.class, id);
		return user;
	}
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		setup();
		session.update(user);
		tx.commit();
		session.close();
	}


	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		setup();
		session.delete(user);
		tx.commit();
		session.close();
		
	}


	@Override
	public void addProject(Project project) {
		setup();
		session.save(project);
		tx.commit();
		session.close();
		
	}


	@Override
	public List<Project> fetchProjects() {
		// TODO Auto-generated method stub
		setup();
		String hql= "FROM Project";
		Query query =  session.createQuery(hql);
		List<Project> results = query.list();
		
		return results;
	}


	@Override
	public List<User> fetchUsers() {
		// TODO Auto-generated method stub
		setup();
		String hql= "FROM User";
		Query query =  session.createQuery(hql);
		List<User> results = query.list();
		
		return results;
	}


	public void setup()
	{
		 sessionfactory= HibernateUtil.getSessionFactory();
		 session= sessionfactory.openSession(); // create new session

		 tx=session.beginTransaction();
	}

}
