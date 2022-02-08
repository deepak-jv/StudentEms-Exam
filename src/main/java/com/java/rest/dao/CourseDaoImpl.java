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

@Repository
public class CourseDaoImpl implements CourseDao {
	
	@Autowired
	EntityManager entityManager;

	@Override
	@Transactional
	public Courses getCourseBYid(int id) throws SQLException {
		
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("from Courses where id = :id");
		query.setParameter("id", id);
		
		
		return (Courses) query.getSingleResult();
	}
	@Override
	@Transactional
	public List<Courses> getallCourses() throws SQLException {

		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("from Courses");
		return query.getResultList();
	}
}
