package ua.com.funnybus.service;

import java.util.List;

import ua.com.funnybus.entity.User;

public interface UserService {
	void save(User user);
	void update(User user);
	List<User> findAll();
	User findOne(int id);
	void delete(int id);
	User findByPhone(String phone);
	User findUniqueOrCreate(String phone, String firstName, String secondName);
	User findForReviewOrCreate(String firstName, String secondName);
}
