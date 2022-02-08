package com.java.rest.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.rest.model.Courses;

public interface CourseDao {
 
	
	
	public  List<Courses> getallCourses() throws SQLException;
	public Courses getCourseBYid(int id ) throws SQLException;
}
