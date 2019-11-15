package com.finalFS.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finalFS.model.Project;
import com.finalFS.model.Task;
import com.finalFS.util.HibernateUtil;
@Component
public class TaskDao implements TaskOperations {
	
	@Autowired
	HibernateUtil hibernateUtil;
	
	Transaction tx;
	Session session;
	SessionFactory sessionfactory;

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> fetchTasks() {
		// TODO Auto-generated method stub
		//set up
		setup();
		String hql = "FROM Task";
		Query query =  session.createQuery(hql);
		List<Task> results = query.list();
		
		return results;
	}
	
	/*
	 * public List<Task> findByName(String name) { setup(); String hql =
	 * "FROM Task where name=:name"; Query query = session.createQuery(hql);
	 * query.setParameter("name",name);
	 * 
	 * }
	 */

	@Override
	public void insertTask(Task task) {
		// TODO Auto-generated method stub
		setup();
		session.save(task);
		tx.commit();
		session.close();
	}
	
	public void setup()
	{
		 sessionfactory= HibernateUtil.getSessionFactory();
		 session= sessionfactory.openSession(); // create new session

		 tx=session.beginTransaction();
	}

	@Override
	public void updateTask(Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Task> fetchTasks(String taskname) {
		// TODO Auto-generated method stub
		//List<Task> fromDB= new ArrayList<Task>();
		
		  // build criteria
		//	session
		 setup();
		
		/*
		 * SQLQuery q
		 * =session.createSQLQuery("select * from task where name=:taskname");
		 * q.addEntity(Task.class); q.setParameter("taskname", taskname);
		 */
		
		 /* CriteriaBuilder cb =session.getCriteriaBuilder(); CriteriaQuery<Task> tc ;
		  Criterion name= Restrictions.eq("name", filter.getName());
		 */
		/*
		 * Criterion priority= Restrictions.eq("priority", filter.getPriority());
		 * 
		 * Criterion startDate= Restrictions.eq("StartDate", filter.getStartDate());
		 * 
		 * Criterion endDate= Restrictions.eq("EndDate", filter.getEndDate());
		 */
		 // tc.add(Restrictions.or(name);//priority,startDate,endDate));
		  //tc.add(name);
		
		  Criteria c= session.createCriteria(Task.class);
		  c.add(Restrictions.eq("taskname",taskname));
		 
		 // List<Task>fromDB= (List<Task>)q.list();
		 List<Task> fromDB=c.list();
		return fromDB;
	}

	
	@Override
	public Long getProjectId(String name) {
		// TODO Auto-generated method stub
		setup();
		String hql = "SELECT pid FROM Project WHERE name=:projectname";
		Query query =session.createQuery(hql);
		query.setParameter("projectname", name);
		  
		return (Long) query.uniqueResult();
	}

	@Override
	public Long getUserId(String fname) {
		// TODO Auto-generated method stub
		setup();
		String hql = "SELECT id FROM User WHERE firstName=:firstName";
		Query query =session.createQuery(hql);
		query.setParameter("firstName", fname);
		  
		return (Long) query.uniqueResult();
	}

	public void deleteTask(String taskname)
	{
		setup();
		
	//	setup();
		String hql = "DELETE FROM Task where name=:task_name";
		Query query = session.createQuery(hql);
		query.setParameter("task_name", taskname);
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);
		tx.commit();
	//	session.delete(task);
		session.close();
		
	}
	
}
