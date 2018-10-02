package com.fdmgroup.springloginregisterexercise.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import com.fdmgroup.springloginregisterexercise.model.User;

/**
 * Class that interacts with user object via three different methods. The first
 * method persists an User object unto a database. The other method is
 * overloaded. Both version are for finding a user object that has been stored
 * in a database but one method checks only usernames and the other check for
 * username/password combo.
 * 
 * @author Brian.Albers
 * @version 1.0
 *
 */
public class UsersDAO implements Users<User> {

	private EntityManagerFactory emf;

	private Logger myLogger = Logger.getLogger(UsersDAO.class);

	public UsersDAO() {
		super();
		myLogger.info("INFO:Default constructor called for the UserDAO");
	}

	public UsersDAO(EntityManagerFactory emf) {
		super();
		this.emf = emf;
		myLogger.info("INFO:EntityManagerFactory was supplied for UserDAO constructor");
	}

	public EntityManagerFactory getEmf() {
		myLogger.info("INFO:Returning EntityManagerFactory");
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		myLogger.info("INFO:Setting the EntityManagerFactory");
		this.emf = emf;
	}

	/**
	 * This method writes a User object as a String into a file.
	 * 
	 * @param user
	 *            A User object containing a username, password, first name, last
	 *            name, and an email. Used to create a user for a web application.
	 * @see User
	 */
	@Override
	public void addUser(User user) {
		myLogger.info("INFO:addUser method is adding user to database");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		myLogger.info("INFO:EntityManager is beginning the transaction");

		em.persist(user);
		myLogger.info("INFO:Sending User object to the database");

		em.getTransaction().commit();

		em.close();
		myLogger.info("INFO:EntityManager has been closed");
	}

	/**
	 * This method is used to find a specific user from the database based on the
	 * username supplied by the client.
	 * 
	 * @param username
	 *            The username of the User object to be found. Provided by the
	 *            client.
	 * @return The user object that was found based on the supplied username. Will
	 *         be null if no matching user is found.
	 * 
	 * @see User
	 */
	public User getUser(String username) {
		User user = null;
		myLogger.info("INFO:Null User object has been created.");

		EntityManager em = emf.createEntityManager();

		user = em.find(User.class, username);
		myLogger.info("INFO:Searching for a specified User");

		em.close();
		myLogger.info("INFO:EntityManager has been closed");

		myLogger.info("INFO:Returning User object");
		return user;
	}

	/**
	 * THis method is used to remove an User object from the databse.
	 * 
	 * @param username
	 *            The supplied username of the User object to be removed. The client
	 *            provides the usrename.
	 */
	public void removeUser(String username) {
		EntityManager em = emf.createEntityManager();

		User user = em.find(User.class, username);
		myLogger.info("INFO:Searching for a specified User");

		em.getTransaction().begin();
		myLogger.info("INFO:EntityManager is beginning the transaction");

		em.remove(user);
		myLogger.info("INFO:User object has been removed form the database");

		em.getTransaction().commit();
	}

	/**
	 * This method is used to update an existing User object in the database.
	 * 
	 * @param user
	 *            User object provided by the client.
	 */
	public void updateUser(User user) {
		EntityManager em = emf.createEntityManager();

		User userToBeUpdated = em.find(User.class, user.getUsername());
		myLogger.info("INFO:Searching for a specified User");

		em.getTransaction().begin();
		myLogger.info("INFO:EntityManager is beginning the transaction");

		userToBeUpdated.setPassword(user.getPassword());
		userToBeUpdated.setFirstName(user.getFirstName());
		userToBeUpdated.setLastName(user.getLastName());
		userToBeUpdated.setEmail(user.getEmail());
		myLogger.info("INFO:Setting User object with the updated parameters");

		em.getTransaction().commit();
	}

	/**
	 * This method uses a list to gather all users in a database and then checks if
	 * any of the user's username field patches the supplied username string.
	 * 
	 * @param username
	 *            A username supplied by the client
	 * @return A boolean showing whether the username was found or not
	 */
	@Override
	public Boolean findUser(String username, String password) {
		myLogger.info("INFO:findUser method is looking for username/password combo in the database");
		Boolean isFound = false;

		myLogger.info("INFO:List to hold all users in the database has been created");
		List<User> users = new ArrayList<User>();

		myLogger.info("INFO:EntityManager has been created");
		EntityManager em = emf.createEntityManager();

		myLogger.info("INFO:Query being sent to the database to SELECT all users");
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);

		myLogger.info("INFO:Storing query results in the List");
		users = query.getResultList();

		myLogger.info("INFO:EntityManager has been closed");
		em.close();

		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				myLogger.info("INFO:Found username/password combo in the database");
				isFound = true;
			}
		}
		return isFound;
	}

	/**
	 * This method uses a list to gather all users in a database and then checks if
	 * a user's username/password combonation matches a supplied username/password
	 * combo.
	 * 
	 * @param username
	 *            A username supplied by the client
	 * @return A boolean showing whether the username/password combo was found or
	 *         not
	 */
	@Override
	public Boolean findUser(String username) {
		myLogger.info("INFO:findUser method is looking for username in the database");
		Boolean isFound = false;

		myLogger.info("INFO:List to hold all users in the database has been created");
		List<User> users = new ArrayList<User>();

		myLogger.info("INFO:EntityManager has been created");
		EntityManager em = emf.createEntityManager();

		myLogger.info("INFO:Query being sent to the database to SELECT all users");
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);

		myLogger.info("INFO:Storing query results in the List");
		users = query.getResultList();

		myLogger.info("INFO:EntityManager has been closed");
		em.close();

		for (User user : users) {
			if (user.getUsername().equals(username)) {
				myLogger.info("INFO:Found username in the database");
				isFound = true;
			}
		}
		return isFound;
	}
}