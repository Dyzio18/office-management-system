package com.example.service;

import com.example.model.User;
import java.util.*;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public List <User> getAll();

	public User findById(Integer user_id);
}
