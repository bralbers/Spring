package com.fdmgroup.springloginregisterexercise.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fdmgroup.springloginregisterexercise.model.User;
import com.fdmgroup.springloginregisterexercise.persistence.UsersDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/WEB-INF/dispatcher-servlet.xml")
public class UserDAOTest {

	@Autowired
	private ApplicationContext context;
	
    private User user;
    private UsersDAO usersDAO;
    
    private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	
	private String incorrectUsername;
	private String incorrectPassword;
    
    @Before
    public void setUp() {
    	usersDAO = (UsersDAO) context.getBean("userDAOBean");
    	user = (User) context.getBean("userBean");
    	
    	username = "jdoe";
		password = "dragonborn";
		firstName = "John";
		lastName = "Doe";
		email = "example@domain.com";
		
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		
		usersDAO.addUser(user);
    }
    
    @Test
	public void testIfUsernameExists() {
		Boolean isFound = usersDAO.findUser(username);
		assertTrue(isFound);
	}
	
	@Test
	public void testIfUsernamePasswordComboExists() {
		Boolean isFound = usersDAO.findUser(username, password);
		assertTrue(isFound);
	}
	
	@Test
	public void testIfUsernameDoesNotExist() {
		Boolean isFound = usersDAO.findUser(incorrectUsername);
		assertFalse(isFound);	
	}
	
	@Test
	public void testIfUsernamePasswordDoesNotExist_WithIncorrectUsername() {
		Boolean isFound = usersDAO.findUser(incorrectUsername,password);
		assertFalse(isFound);
	}
	
	@Test
	public void testIfUsernamePasswordDoesNotExist_WithIncorrectPassword() {
		Boolean isFound = usersDAO.findUser(username,incorrectPassword);
		assertFalse(isFound);
	}
}