package ua.com.funnybus.service.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.dto.form.ReviewForm;
import ua.com.funnybus.dao.ReviewDao;
import ua.com.funnybus.entity.Review;
import ua.com.funnybus.entity.User;
import ua.com.funnybus.service.ReviewService;
import ua.com.funnybus.service.UserService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private UserService userService;

	public void save(Review review) {
		Date date = new Date();
		review.setFlag(false);
		review.setDate(date);
		reviewDao.save(review);
	}

	public void update(Review review) {
		Date date = new Date();
		review.setDate(date);
		reviewDao.save(review);
	}

	public List<Review> findAll(boolean flag) {
		return reviewDao.findAll(flag);
	}

	public Review findOne(int id, boolean flag) {
		return reviewDao.findOne(id,flag);
	}

	public void delete(int id) {
		reviewDao.delete(id);
	}

	@Override
	public Page<Review> findAll(Pageable pageable, boolean flag) {
		return reviewDao.findAll(flag, pageable);
	}

	@Override
	public void save(ReviewForm form) {
		Date date = new Date();
		User user= userService.findForReviewOrCreate(form.getFirstName(), form.getSecondName());
		Review review = new Review();
		review.setText(form.getText());
		review.setUser(user);
		review.setFlag(false);
		review.setDate(date);
		reviewDao.save(review);
	}
}
