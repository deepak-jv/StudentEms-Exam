package com.java.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Courses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String course;
	
//	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
//	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "course_id"),
//	inverseJoinColumns = @JoinColumn(name = "student_id"))
//	@ManyToOne(cascade = CascadeType.ALL)

//	private List<Students> student;	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
//	public List<Students> getStudent() {
//		return student;
//	}
//	public void setStudent(List<Students> student) {
//		this.student = student;
//	}


//	public void addStudent(Students tempStudent) {
//
//		if (student == null) {
//			student = new ArrayList<Students>();
//		}
//		student.add(tempStudent);
//		tempStudent.setStudent(this);
//	}

	
	
	
	

	
	
	
	
	
	
}
