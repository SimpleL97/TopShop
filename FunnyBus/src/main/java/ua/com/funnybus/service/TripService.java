package ua.com.funnybus.service;

import java.util.List;

import ua.com.funnybus.entity.Trip;

public interface TripService {
	void save(Trip trip);
	void update(Trip trip);
	List<Trip> findAll();
	Trip findOne(int id);
	void delete(int id);
	List<Trip> findOnWay(String way);
}