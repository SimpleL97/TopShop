package ua.com.funnybus.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.funnybus.entity.Review;

public interface ReviewDao extends JpaRepository<Review, Integer>{
	@Query("SELECT r FROM Review r where r.flag=?1")
	List<Review> findByFlag(boolean flag);
	Review findByText(String text);
	
	@Query("SELECT r FROM Review r LEFT JOIN FETCH r.user where r.flag=?1")
	List<Review> findAll(boolean flag);
	
	@Query(value="SELECT r FROM Review r LEFT JOIN FETCH r.user where r.flag=?1",
			countQuery="SELECT count(r.id) FROM Review r where r.flag=?1")
	Page<Review> findAll(boolean flag, Pageable pageable);
	
	@Query("SELECT r FROM Review r LEFT JOIN FETCH r.user where r.id=?1 and r.flag=?2")
	Review findOne(int id, boolean flag);
}
