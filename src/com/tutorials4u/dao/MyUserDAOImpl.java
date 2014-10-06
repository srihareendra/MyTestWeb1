package com.tutorials4u.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.tutorials4u.domain.User;

public class MyUserDAOImpl implements UserDAO {
	
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
	
	public MyUserDAOImpl(){
		
	    try {
	    	 // Load the JDBC driver.
		    Class.forName("org.hsqldb.jdbcDriver");
		    System.out.println(" MyUserDAOImpl HSQLDriver Loaded.");
		    // Establish the connection to the database.
		    String url = "jdbc:hsqldb:data/tutorial";

		    Connection conn = DriverManager.getConnection(url, "sa", "");
		    System.out.println(" MyUserDAOImpl Got Connection.");
			Statement st = conn.createStatement();
			st.executeUpdate("create table User (USER_ID int,USER_NAME varchar" +
					",USER_GENDER varchar,USER_COUNTRY varchar,USER_ABOUT_YOU varchar" +
					",USER_MAILING_LIST varchar);");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Used to save or update a user.
	 */
	public void saveOrUpdateUser(User user) {
		try {
			session.saveOrUpdate(user);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * Used to delete a user.
	 */
	public void deleteUser(Long userId) {
		try {
			User user = (User) session.get(User.class, userId);
			session.delete(user);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
	}
	
	/**
	 * Used to list all the users.
	 */
	@SuppressWarnings("unchecked")
	public List<User> listUser() {
		List<User> courses = null;
		try {
			courses = session.createQuery("from User").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
	}

	/**
	 * Used to list a single user by Id.
	 */
	public User listUserById(Long userId) {
		User user = null;
		try {
			user = (User) session.get(User.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
