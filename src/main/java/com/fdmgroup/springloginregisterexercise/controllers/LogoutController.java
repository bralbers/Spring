package com.fdmgroup.springloginregisterexercise.controllers;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The logout controller is used to handle invalidating the user session.
 * 
 * @author Brian.Albers
 * @version 1.0
 */
@Controller
public class LogoutController {

	private Logger myLogger = Logger.getLogger(LogoutController.class);
	/**
	 * This method is used to make the logout jsp page appear
	 * 
	 * @return The name of the jsp page for logging out
	 */
	@RequestMapping(value = "/logoutService")
	private String logoutUser() {
		myLogger.info("INFO:Logout page has been summoned");
		return "logout";
	}

	/**
	 * This method when called will invalidate the current user session
	 * 
	 * @param request
	 *            Part of the HttpServletRequest. This is used to invalidated the
	 *            session.
	 * @return The name of the home page for a redirection
	 */
	@RequestMapping(value = "/logoutUser", method = RequestMethod.POST)
	public String logoutUser(HttpServletRequest request) {
		request.getSession().invalidate();
		myLogger.info("INFO:Session has been invalidated");
		myLogger.info("INFO:Sending client back to home page");
		return "index";
	}
}
