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
	//	session.update(user);
		
		String sql = "SELECT id From User where firstName=:fname or lastName=:lName or employeeID=:eid";
		Query query = session.createQuery(sql);
		query.setParameter("fname", user.getFirstName());
		query.setParameter("lName", user.getLastName());
		query.setParameter("eid", user.getEmployeeID());
		
		Long userid= (Long)query.uniqueResult();
		
		user.setId(userid);
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


	@Override
	public List<Project> sortBySDate() {
		setup();
		String query="from Project order by startDate";
		List<Project> res=session.createQuery(query).list();
		return res;
	}

    @Override
    public List<Project> sortByEDate() {
        setup();
        String query="from Project order by endDate";
        List<Project> res=session.createQuery(query).list();
        return res;
    }

    @Override
    public List<Project> sortByPrio() {
        setup();
        String query="from Project order by priority";
        List<Project> res=session.createQuery(query).list();
        return res;
    }

    @Override
    public List<Project> sortByStatus() {
        return null;
    }


    @Override
	public List<User> sortByFname() {
		setup();
		String query="from User order by firstName";
		List<User> res=session.createQuery(query).list();
		return res;
	}


	@Override
	public List<User> sortByLname() {
		setup();
		String query="from User order by lastName";
		List<User> res=session.createQuery(query).list();
		return res;
	}


	@Override
	public List<User> sortByUserid() {
		setup();
		String query="from User order by employeeID";
		List<User> res=session.createQuery(query).list();
		return res;
	}


	@Override
	public Project getProjectbyid(Long id) {
		setup();
		Project p= (Project)session.get(Project.class, id);
		return p;
	}


	@Override
	public void deleteProject(Project p) {
		// TODO Auto-generated method stub
		setup();
		session.delete(p);
		tx.commit();
		session.close();
	}

}