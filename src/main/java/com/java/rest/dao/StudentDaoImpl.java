package com.java.rest.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.rest.model.Courses;
import com.java.rest.model.Students;

@Repository
public class StudentDaoImpl implements StudentDao {
	@Autowired
	EntityManager entityManager;

	@Override
	@Transactional
	public void save(Students student) throws SQLException {

		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(student);
	}

	@Override
	@Transactional
	public void deleteById(int id) throws SQLException {

		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete from Students where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();

	}

	@Override
	@Transactional
	public Students findById(int id) throws SQLException {

		Session session = entityManager.unwrap(Session.class);
		Students student = session.get(Students.class, id);

		return student;
	}

	

	@Override
	@Transactional
	public void update(String firstName,String lastName,int id) throws SQLException {


		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("update Students set firstName=:firstName,lastName=:lastName where id=:id");
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
//		query.setParameter("course", course);
		query.setParameter("id", id);
		query.executeUpdate();
		
	}

	@Override 
	@Transactional
	public List<Students> findByFirstName(String firstName) throws SecurityException {

		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("from Students where firstName=:firstName");
		query.setParameter("firstName", firstName);
		return query.getResultList();
		
	}

	@Override
	public List<Students> getallStudent() throws SQLException {

		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("from Students");
		return query.getResultList();
	}

}
