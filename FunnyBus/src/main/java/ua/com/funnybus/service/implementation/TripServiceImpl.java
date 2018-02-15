package ua.com.funnybus.service.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.funnybus.dao.TripDao;
import ua.com.funnybus.entity.Trip;
import ua.com.funnybus.service.TripService;

@Service
public class TripServiceImpl implements TripService {

	@Autowired
	private TripDao tripDao;

	public void save(Trip trip) {
	}

	public void update(Trip trip) {
		tripDao.save(trip);
	}

	public List<Trip> findAll() {
		return tripDao.findAll();
	}

	public Trip findOne(int id) {
		return tripDao.findOne(id);
	}

	public void delete(int id) {
		tripDao.delete(id);
	}

	@Override
	public List<Trip> findOnWay(String way) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String day=dateFormat.format(date);
		try {
			date=dateFormat.parse(day);
			List<Trip> triping=tripDao.findOnWay(way);
			List<Trip> trips=new ArrayList<Trip>();
			for (int i = 0; i < triping.size(); i++) {
				Trip trip = triping.get(i);
				if(trip.getDate().after(date) || trip.getDate().equals(date)){
					trips.add(trip);
				}
			}
			return trips;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new ArrayList<Trip>();
	}
}
