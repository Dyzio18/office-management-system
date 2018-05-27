package com.example.service;

import com.example.model.User;
import java.util.*;

public interface UserService {
	User findUserByEmail(String email);
	void saveUser(User user);
	List <User> getAll();
	void updateUser(User user);
	User findById(Integer user_id);
	void deleteUser(User user);
    List<User> findUserByLastName(String lastName);
	List<User> findUserByName(String name);
}
