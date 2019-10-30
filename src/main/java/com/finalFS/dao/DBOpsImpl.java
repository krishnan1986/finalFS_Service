package com.finalFS.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


	public void setup()
	{
		 sessionfactory= HibernateUtil.getSessionFactory();
		 session= sessionfactory.openSession(); // create new session

		 tx=session.beginTransaction();
	}

}
