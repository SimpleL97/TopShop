package ua.com.funnybus.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.dto.filter.SimpleFilter;
import ua.com.funnybus.entity.Nev;


public interface NevService {
	void save(Nev nev);
	void update(Nev nev);
	List<Nev> findAll();
	Nev findOne(int id);
	void delete(int id);
	Page<Nev> findAll(Pageable pageable);
	Nev findByTitle(String title);
	Nev findByInfo(String info);
	Page<Nev> findAll(SimpleFilter filter,Pageable pageable);
}
