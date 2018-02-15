package ua.com.funnybus.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.dto.filter.SimpleFilter;
import ua.com.funnybus.entity.Aid;

public interface AidService {
	void save(Aid aid);
	void update(Aid aid);
	List<Aid> findAll();
	Aid findOne(int id);
	void delete(int id);
	Page<Aid> findAll(Pageable pageable);
	Aid findByTitle(String title);
	Aid findByInfo(String info);
	Page<Aid> findAll(SimpleFilter filter,Pageable pageable);
}
