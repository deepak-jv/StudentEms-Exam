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

import com.java.rest.dao.UserDao;
import com.java.rest.model.UserErorrResponse;
import com.java.rest.model.UserRegistration;
import com.rest.Exception.UserException;

@RestController
public class UserController {

	@Autowired
	UserDao userDao;

	@PostMapping("/user/add")
	public String addUser(@RequestParam String name, String contact, String email, String password)
			throws SQLException {

		List<String> list = new ArrayList<String>();
		List<UserRegistration> users = userDao.getall();

		for (UserRegistration user : users) {
			list.add(user.getName());
			list.add(user.getPassword());
		}

		if (list.contains(name) || list.contains(password)) {
			System.out.println(list);
			String errorMessage = "User or Password already exists please try again ...!";
			throw new UserException(errorMessage);

		}

		else {
			UserRegistration user = new UserRegistration();
			user.setName(name);
			user.setContact(contact);
			user.setEmail(email);
			user.setPassword(password);
			userDao.save(user);
			return "User Successfully Added";
		}
	}

//	@GetMapping("/user/{id}")
//	public UserRegistration getUserById(@PathVariable int id) throws SQLException {
//
//		return userDao.getById(id);
//
//	}
//
	@PutMapping("/user/{id}")
	public String updateUser(@RequestParam int id, String name, String contact, String email, String password)
			throws SQLException {

		UserRegistration user = new UserRegistration();
		user.setId(id);
		user.setName(name);
		user.setContact(contact);
		user.setEmail(email);
		user.setPassword(password);
		userDao.update(user);

		return "updated successfully";

	}

	@PostMapping("/login")
	public List<String> login(@RequestParam String name, String password) throws SQLException {

		List<String> name_list = new ArrayList<String>();
		List<UserRegistration> users = userDao.getall();
		for (UserRegistration user1 : users) {
			name_list.add(user1.getName());
		}

		if (name_list.contains(name)) {
			UserRegistration user = userDao.getByName(name);
			String user_name = user.getName();
			String user_password = user.getPassword();

			if (user_name.equals(name) && password.equals(user_password)) {

				List<String> urls = new ArrayList<String>();

				urls.add("To add Student data =  /api/v1/token:88517723sdfafe242259917076202/students/add");
				urls.add("To get all student data = /api/v1/token:88517723sdfafe242259917076202/students");
				urls.add("To get student by his id = /api/v1/token:88517723sdfafe242259917076202/{id}");
				urls.add("To update student by his id = /api/v1/token:88517723sdfafe242259917076202/students/{id}");
				urls.add("To get single by his first name = /api/v1/token:88517723sdfafe242259917076202/students/data/{firstName}");
				urls.add("To delete Student by his id =  /api/v1/token:88517723sdfafe242259917076202/students/delete/{id}");

				return urls;
			} else {
				String errorMessage = "invalid credentials";
				throw new UserException(errorMessage);

			}
		} else {
			String errorMessage = "This name is not registered please register first";
			throw new UserException(errorMessage);
		}

	}

	@ExceptionHandler
	public ResponseEntity<UserErorrResponse> handleException(UserException exc) {

		UserErorrResponse error = new UserErorrResponse();
		error.setMessage(exc.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimestamp(System.currentTimeMillis());

		return new ResponseEntity<UserErorrResponse>(error, HttpStatus.BAD_REQUEST);
	}

}
