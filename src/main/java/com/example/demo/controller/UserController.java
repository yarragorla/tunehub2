package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Users;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController 
{
	@Autowired
	UserService service;
	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user)
	{
		boolean userStatus = service.emailExists(user.getEmail());
		if(userStatus==false)
		{
			service.adddUser(user );
			System.out.print("user added successfully");
		}
		else
		{
			return "alreadyExists";
		}
		return "home";
	}
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,
			@RequestParam("password") String password, HttpSession session)
	{
		if(service.validateUser(email, password)==true)
		{
			String role = service.getRole(email);
			session.setAttribute("email", email);
			if(role.equals("Admin"))
			{
				return "adminHome";
			}
			else 
			{
				return "customerHome";
			}
		}
		else
		{
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "login";
	}
	
	
}