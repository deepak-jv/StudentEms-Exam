package com.java.rest.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.rest.model.Courses;
import com.java.rest.model.Students;

public interface StudentDao {
	
	public void save(Students player) throws SQLException;
	public void deleteById(int id) throws SQLException;
	public Students findById(int id) throws SQLException;
	public  List<Students> getallStudent() throws SQLException;
	public void update(String firstName,String lastName,int id) throws SQLException;
	public List<Students> findByFirstName(String firstName) throws SecurityException;

}
