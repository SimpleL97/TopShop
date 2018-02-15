package ua.com.funnybus.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.dto.filter.SimpleFilter;
import ua.com.funnybus.entity.Act;

public interface ActService {
	void save(Act act);
	void update(Act act);
	List<Act> findAll();
	Act findOne(int id);
	void delete(int id);
	Page<Act> findAll(Pageable pageable);
	Act findByTitle(String title);
	Act findByInfo(String info);
	Page<Act> findAll(SimpleFilter filter,Pageable pageable);
}
