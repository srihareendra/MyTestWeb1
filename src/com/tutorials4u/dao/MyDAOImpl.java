package com.tutorials4u.dao;

import java.util.ArrayList;
import java.util.List;

import com.tutorials4u.domain.User;

public class MyDAOImpl implements UserDAO {
	List<User> userLst ;
	public MyDAOImpl(){
		userLst = new ArrayList<User>();	
		User usr = new User();
		usr.setId(1L);
		usr.setName("test");
		usr.setGender("m");
		usr.setAboutYou("erwerwer");
		usr.setMailingList(true);
		userLst.add(usr);
		User usr1 = new User();
		usr1.setId(1L);
		usr1.setName("test");
		usr1.setGender("m");
		usr1.setAboutYou("erwerwer");
		usr1.setMailingList(true);
		userLst.add(usr1);
		
	}

	@Override
	public void saveOrUpdateUser(User user) {
		userLst.add(user);

	}

	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
		return userLst;
	}

	@Override
	public User listUserById(Long userId) {
		// TODO Auto-generated method stub
		return userLst.get(userId.intValue());
	}

	@Override
	public void deleteUser(Long userId) {
		
		System.out.println("deleting user .... ");
		userLst.remove(userId);
		// TODO Auto-generated method stub

	}

}
