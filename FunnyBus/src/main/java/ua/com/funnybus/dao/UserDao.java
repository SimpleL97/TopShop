package ua.com.funnybus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.funnybus.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{
	User findByPhone(String phone);
	
	@Query("SELECT u FROM User u where u.phone=?1 and u.firstName=?2 and u.secondName=?3")
	User findUnique(String phone, String firstName, String secondName);
	
	@Query("SELECT u FROM User u where u.firstName=?1 and u.secondName=?2")
	List<User> findForReview(String firstName, String secondName);
}
