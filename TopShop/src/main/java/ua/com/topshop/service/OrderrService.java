package ua.com.topshop.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.topshop.entity.Orderr;

public interface OrderrService {
	void save(Orderr order);
	void update(Orderr order);
	void delete(int id);
	List<Orderr> findAll();
	Orderr findOne(int id);
	Orderr findOne(int id, boolean flag);
	List<Orderr> findByUserId(int id);
	List<Orderr> findByGraphicId(int id);
	Page<Orderr> findAll(Pageable pageable);
	Orderr findUnique(int amount, int userId, int graphicId);
	List<Orderr> findUserFlag(int userId, boolean flag);
	BigDecimal sum(int userId, boolean flag);
}
