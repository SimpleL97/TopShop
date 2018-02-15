package ua.com.topshop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.dto.filter.SimpleFilter;
import ua.com.topshop.dao.ProducerDao;
import ua.com.topshop.entity.Producer;
import ua.com.topshop.service.ProducerService;

@Service
public class ProducerServiceImpl implements ProducerService {

	@Autowired
	ProducerDao producerDao;
	
	public void save(Producer producer) {
		producerDao.save(producer);
	}

	public void update(Producer producer) {
		producerDao.save(producer);
	}

	public List<Producer> findAll() {
		return producerDao.findAll();
	}

	public Producer findOne(int id) {
		return producerDao.findOne(id);
	}

	public void delete(String codeName) {
		producerDao.delete(findByCodeName(codeName));
	}

	public Producer findByCodeName(String codeName) {
		return producerDao.findByCodeName(codeName);
	}

	public void delete(int id) {
		producerDao.delete(id);
	}

	@Override
	public Page<Producer> findAll(Pageable pageable, SimpleFilter filter) {
		return producerDao.findAll(findByCodeNameLike(filter), pageable);
	}
	
	private Specification<Producer> findByCodeNameLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("codeName")), filter.getSearch().toLowerCase()+"%");
		};
	}

}
