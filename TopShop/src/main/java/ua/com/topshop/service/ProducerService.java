package ua.com.topshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.dto.filter.SimpleFilter;
import ua.com.topshop.entity.Producer;

public interface ProducerService {
	void save(Producer producer);
	void update(Producer producer);
	List<Producer> findAll();
	Producer findOne(int id);
	void delete(int id);
	void delete(String codeName);
	Producer findByCodeName(String codeName);
	Page<Producer> findAll(Pageable pageable, SimpleFilter filter);
}
