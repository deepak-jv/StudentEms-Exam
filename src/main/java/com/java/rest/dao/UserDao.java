package com.java.rest.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.rest.model.UserRegistration;

public interface UserDao {

	public void save(UserRegistration user) throws SQLException;
	public List<UserRegistration> getall() throws SQLException; 
	public UserRegistration getById(int id) throws SQLException; 
	public void update(UserRegistration user) throws SQLException;
	public UserRegistration getByName(String name) throws SQLException;
}
