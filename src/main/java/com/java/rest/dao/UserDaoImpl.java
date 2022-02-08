package com.java.rest.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.rest.model.UserRegistration;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	EntityManager entityManager;

	@Override
	public void save(UserRegistration user) throws SQLException {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(user);
	}

	@Override
	public List<UserRegistration> getall() throws SQLException {

		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("from UserRegistration");
//		Query query1 = session.createQuery("from Courses");

		return query.getResultList();
	}

	@Override
	@Transactional
	public void update(UserRegistration user) throws SQLException {
		
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(user);

	}

	@Override
	public UserRegistration getById(int id) throws SQLException {


		Session session = entityManager.unwrap(Session.class);
		UserRegistration user = session.get(UserRegistration.class, id);
		
		return user;
	}

	@Override
	@Transactional
	public UserRegistration getByName(String name) throws SQLException {

		Session session =  entityManager.unwrap(Session.class);
		Query query = session.createQuery("from UserRegistration where name=:name");
		query.setParameter("name", name);
		
		return (UserRegistration) query.getSingleResult();
	}
	


}
