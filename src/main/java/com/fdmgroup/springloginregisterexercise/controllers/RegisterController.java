package com.fdmgroup.springloginregisterexercise.controllers;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.springloginregisterexercise.exception.UserException;
import com.fdmgroup.springloginregisterexercise.model.User;
import com.fdmgroup.springloginregisterexercise.persistence.UsersDAO;

@Controller
public class RegisterController {

	private Logger myLogger = Logger.getLogger(RegisterController.class);
	@Autowired
	private ApplicationContext context;

	@RequestMapping(value = "/register")
	private String registerUser(Model model) {
		User user = (User) context.getBean("userBean");
		myLogger.info("INFO:User bean has been summoned");
		model.addAttribute("newUser", user);
		myLogger.info("INFO:Register page is being summoned");
		return "register";
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	private String toDatabase(User user, HttpServletRequest request) {
		String confirmPassword = request.getParameter("confirmPassword");
		UsersDAO userDAO = (UsersDAO) context.getBean("userDAOBean");
		myLogger.info("INFO:UserDAO bean was summoned");
		Boolean isFound = userDAO.findUser(user.getUsername());
		myLogger.info("INFO:Checking if username already exists in database");

		if (user.getUsername().trim().length() < 5 || user.getUsername().isEmpty()) {
			request.setAttribute("IncorrectUsername", true);
			myLogger.warn("WARN:Username was invalid");
			throw (UserException) context.getBean("usernameExceptionBean");
		} else if (user.getPassword().trim().length() < 7 || user.getPassword().isEmpty()) {
			request.setAttribute("IncorrectPassword", true);
			myLogger.warn("WARN:Password was invalid");
			throw (UserException) context.getBean("passwordExceptionBean");
		} else if (confirmPassword.isEmpty() || !user.getPassword().equals(confirmPassword)) {
			request.setAttribute("IncorrectConfirmPassword", true);
			myLogger.warn("WARN:Confirmation Password was not the same as Password");
			throw (UserException) context.getBean("confirmPasswordExceptionBean");
		} else if (user.getFirstName().isEmpty()) {
			request.setAttribute("NullFirstName", true);
			myLogger.warn("WARN:First name was null");
			throw (UserException) context.getBean("firstNameExceptionBean");
		} else if (user.getLastName().isEmpty()) {
			request.setAttribute("NullLastName", true);
			myLogger.warn("WARN:Last name was null");
			throw (UserException) context.getBean("lastNameExceptionBean");
		} else if (user.getEmail().isEmpty()) {
			request.setAttribute("NullEmail", true);
			myLogger.warn("WARN:Email was null");
			throw (UserException) context.getBean("emailExceptionBean");
		} else if (isFound == true) {
			request.setAttribute("disallowRegistration", true);
			myLogger.warn("WARN:Username already exists in the database");
			throw (UserException) context.getBean("usernameAlreadyExistsExceptionBean");
		} else {
			userDAO.addUser(user);
			myLogger.info("INFO:User was added to database");
			return "index";
		}
	}

	@ExceptionHandler(UserException.class)
	public String handleException(HttpServletRequest request, Exception e) {
		request.setAttribute("exception", e);
		myLogger.warn("WARN:Exception was found, redirecting to page to show why");
		return "redirect";
	}
}