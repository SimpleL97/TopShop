package ua.com.topshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.topshop.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
	@Query("SELECT distinct u FROM User u LEFT JOIN FETCH u.orders o LEFT JOIN FETCH o.graphic where u.email=?1")
	User findByEmail(String email);
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.orders where u.name=?1")
	User findByName(String name);
}
