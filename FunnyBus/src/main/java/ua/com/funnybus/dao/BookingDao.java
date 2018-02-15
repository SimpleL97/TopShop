package ua.com.funnybus.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.funnybus.entity.Booking;

public interface BookingDao extends JpaRepository<Booking, Integer> {
	@Query("SELECT b FROM Booking b LEFT JOIN FETCH b.trip LEFT JOIN FETCH b.user")
	List<Booking> findAll();
	
	@Query(value="SELECT b FROM Booking b LEFT JOIN FETCH b.trip LEFT JOIN FETCH b.user where b.trip.way=?1",
			countQuery="SELECT count(b.id) FROM Booking b where b.trip.way=?1")
	Page<Booking> findAllOnWay(String way, Pageable pageable);
	
	@Query("SELECT b FROM Booking b LEFT JOIN FETCH b.trip LEFT JOIN FETCH b.user where b.id=?1")
	Booking findOne(int id);
}
