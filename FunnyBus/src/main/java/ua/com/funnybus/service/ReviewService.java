package ua.com.funnybus.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.dto.form.ReviewForm;
import ua.com.funnybus.entity.Review;

public interface ReviewService {
	void save(Review review);
	void update(Review review);
	List<Review> findAll(boolean flag);
	Review findOne(int id, boolean flag);
	void delete(int id);
	Page<Review> findAll(Pageable pageable, boolean flag);
	void save(ReviewForm form);
}
