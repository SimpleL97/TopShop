package ua.com.topshop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.dto.filter.SimpleFilter;
import ua.com.topshop.dao.MemoryDao;
import ua.com.topshop.entity.Memory;
import ua.com.topshop.service.MemoryService;

@Service
public class MemoryServiceImpl implements MemoryService {

	@Autowired
	private MemoryDao memoryDao;
	
	public void save(Memory memory) {
		memoryDao.save(memory);
	}

	public void update(Memory memory) {
		memoryDao.save(memory);
	}

	public List<Memory> findAll() {
		return memoryDao.findAll();
	}

	public Memory findOne(int id) {
		return memoryDao.findOne(id);
	}

	public void delete(String type) {
		memoryDao.delete(findByType(type));
	}

	public Memory findByType(String type) {
		return memoryDao.findByType(type);
	}

	public void delete(int id) {
		memoryDao.delete(id);
	}

	@Override
	public Page<Memory> findAll(Pageable pageable, SimpleFilter filter) {
		return memoryDao.findAll(findByTypeLike(filter), pageable);
	}
	
	private Specification<Memory> findByTypeLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("type")), filter.getSearch().toLowerCase()+"%");
		};
	}
}
