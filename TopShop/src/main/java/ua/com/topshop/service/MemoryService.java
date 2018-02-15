package ua.com.topshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.dto.filter.SimpleFilter;
import ua.com.topshop.entity.Memory;

public interface MemoryService {
	void save(Memory memory);
	void update(Memory memory);
	List<Memory> findAll();
	Memory findOne(int id);
	void delete(int id);
	void delete(String type);
	Memory findByType(String type);
	Page<Memory> findAll(Pageable pageable, SimpleFilter filter);
}
