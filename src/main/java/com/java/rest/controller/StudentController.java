package com.java.rest.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.rest.dao.CourseDao;
import com.java.rest.dao.StudentDao;
import com.java.rest.dao.UserDao;
import com.java.rest.model.Courses;
import com.java.rest.model.StudentErorrResponse;
import com.java.rest.model.Students;
import com.java.rest.model.UserRegistration;
import com.rest.Exception.StudentException;

@RestController
public class StudentController {

	@Autowired
	StudentDao studentDao;
	@Autowired
	CourseDao courseDao;

	@PostMapping("/api/v1/token:88517723sdfafe242259917076202/students/add")
	public String saveStudent(@RequestParam String firstName, String lastName, Courses course, int courseId)
			throws SQLException {

		
		System.out.println(courseId);
		List<Integer> course_id = new ArrayList<Integer>();
		List<Courses> course_list = courseDao.getallCourses();
		for (Courses courses : course_list) {

			course_id.add(courses.getId());
		}
		Students student = new Students();
		student.setFirstName(firstName);
		student.setLastName(lastName);

		if (course_id.isEmpty()) {
			course.setId(0);
			student.addCourse(course);
		} else {
			
			if (course_id.contains(courseId)) {
				Courses courses = courseDao.getCourseBYid(courseId);
				if(courses.getCourse().equals(course.getCourse())) {
				student.addCourse(courses);
				}
				else {
					String errorMsg = "id already exists, course id and name not matched";
					throw new StudentException(errorMsg);
				}
			} else {
				
				course.setId(0);
				student.addCourse(course);
			}
		}

		studentDao.save(student);
		return "Student added successfully";

	}

	@GetMapping("/api/v1/token:88517723sdfafe242259917076202/students")
	public List<Students> allStudent() throws SQLException {

		return studentDao.getallStudent();
	}

	@GetMapping("/api/v1/token:88517723sdfafe242259917076202/students/{id}")
	public Students getStudentById(@PathVariable int id) throws SQLException {

		List<Students> students = studentDao.getallStudent();
		List<Integer> ids = new ArrayList<Integer>();
		for (Students students2 : students) {

			ids.add(students2.getId());
		}

		if (ids.contains(id)) {
	
			return studentDao.findById(id);

		}

		else {

			String errorMsg = "Student not found with given id: " + id;
			throw new StudentException(errorMsg);
		}
	}

	@PutMapping("/api/v1/token:88517723sdfafe242259917076202/students/{id}")
	public String updateStudent(@RequestParam int student_id, String firstName, String lastName)
			throws SQLException {

		Students student = new Students();
		student.setId(student_id);
		student.setFirstName(firstName);
		student.setLastName(lastName);

		studentDao.update(firstName,lastName,student_id);

		return "updated successfully";
	}

	@GetMapping("/api/v1/token:88517723sdfafe242259917076202/students/delete/{id}")
	public String deleteStudent(@PathVariable int id) throws SQLException {

		List<Students> students = studentDao.getallStudent();
		List<Integer> ids = new ArrayList<Integer>();
		for (Students students2 : students) {

			ids.add(students2.getId());
		}

		if (ids.contains(id)) {

			studentDao.deleteById(id);
			return "deleted successfully";

		}

		else {

			String errorMsg = "Student not found with given id: " + id;
			throw new StudentException(errorMsg);
		}

	}

	@GetMapping("/api/v1/token:88517723sdfafe242259917076202/students/data/{firstName}")
	public List<Students> getByFirstName(@PathVariable String firstName) {

		return studentDao.findByFirstName(firstName);
	}

	@ExceptionHandler
	public ResponseEntity<StudentErorrResponse> handelException(StudentException exc) {
		StudentErorrResponse error = new StudentErorrResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());

		return new ResponseEntity<StudentErorrResponse>(error, HttpStatus.NOT_FOUND);
	}
}
