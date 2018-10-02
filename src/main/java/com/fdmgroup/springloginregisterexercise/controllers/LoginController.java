package com.fdmgroup.springloginregisterexercise.controllers;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.springloginregisterexercise.model.User;
import com.fdmgroup.springloginregisterexercise.persistence.UsersDAO;

/**
 * The login controller handles two different implementations of the news site.
 * The first method is used to create a new User object for login purposes. The
 * second method is used to confirm if user information entered is valid.
 * 
 * @author Brian.Albers
 * @version 1.0
 */
@Controller
public class LoginController {

	private Logger myLogger = Logger.getLogger(LoginController.class);

	@Autowired
	private ApplicationContext context;

	/**
	 * This method sets up a new user object and sends a client to the login page.
	 * 
	 * @param model
	 *            Used to pass along any needed attributes
	 * @return The name of the jsp page to bring up
	 */
	@RequestMapping(value = "/loginService")
	private String loginUser(Model model) {
		User user = (User) context.getBean("userBean");
		myLogger.info("INFO:userBean has been summoned");
		model.addAttribute("newUser", user);
		myLogger.info("INFO:new user object has been made");
		myLogger.info("INFO:login page has been summoned");
		return "login";
	}

	/**
	 * This method finds if the credentials entered by the user are valid. If they
	 * are then a new session is started. If they are not then the client is
	 * redirect to the homepage.
	 * 
	 * @param user
	 *            The user object containing the credentials supplied by the client
	 * @param request
	 *            Part of the HttpServletRequest. This is used to start the session.
	 * @return The name of the jsp page to bring up
	 */
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	private String startSession(User user, HttpServletRequest request) {
		UsersDAO userDAO = (UsersDAO) context.getBean("userDAOBean");
		myLogger.info("INFO: UserDAO bean was summoned");
		Boolean isFound = userDAO.findUser(user.getUsername(), user.getPassword());
		myLogger.info("INFO:Checked if provided credentials have a match in th database");
		if (isFound == true) {
			request.getSession().setAttribute("status", "live");
			myLogger.info("INFO:Session is now live");
			return "index";
		}
		myLogger.warn("WARN:No match found with provided credentials");
		return "index";
	}
}
