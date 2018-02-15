package ua.com.funnybus.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.dto.form.BookingForm;
import ua.com.funnybus.dao.BookingDao;
import ua.com.funnybus.entity.Booking;
import ua.com.funnybus.entity.Trip;
import ua.com.funnybus.entity.User;
import ua.com.funnybus.manager.Smser;
import ua.com.funnybus.service.BookingService;
import ua.com.funnybus.service.UserService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private UserService userService;
	
	public void save(Booking booking) {
		Smser smser = new Smser(booking);
		smser.userSender();
		smser.adminSender();
		bookingDao.save(booking);
	}

	public void update(Booking booking) {
		bookingDao.save(booking);
	}

	public List<Booking> findAll() {
		return bookingDao.findAll();
	}

	public Booking findOne(int id) {
		return bookingDao.findOne(id);
	}

	public void delete(int id) {
		bookingDao.delete(id);
	}

	@Override
	public Page<Booking> findAllOnWay(String way, Pageable pageable) {
		return bookingDao.findAllOnWay(way, pageable);
	}

	@Override
	public void save(BookingForm bookingForm) {
		User user= userService.findUniqueOrCreate(bookingForm.getPhone(),bookingForm.getFirstName(),bookingForm.getSecondName());
		Trip trip=bookingForm.getTrip();
		Booking booking=new Booking();
		booking.setUser(user);
		booking.setTrip(trip);
//		Smser smser = new Smser(booking);
//		smser.userSender();
//		smser.adminSender();
		bookingDao.save(booking);
	}


}
