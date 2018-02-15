package ua.com.funnybus.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.dto.form.BookingForm;
import ua.com.funnybus.entity.Booking;

public interface BookingService {
	void save(Booking booking);
	void save(BookingForm bookingForm);
	void update(Booking booking);
	List<Booking> findAll();
	Booking findOne(int id);
	void delete(int id);
	Page<Booking> findAllOnWay(String way, Pageable pageable);
}
