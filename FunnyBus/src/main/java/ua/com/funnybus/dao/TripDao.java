package ua.com.funnybus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.funnybus.entity.Trip;

public interface TripDao extends JpaRepository<Trip, Integer> {
	Trip findByWay(String way);
	
	@Query("select t from Trip t where t.way=?1")
	List<Trip> findOnWay(String way);
}
