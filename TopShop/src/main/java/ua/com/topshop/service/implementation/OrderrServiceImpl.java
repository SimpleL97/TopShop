package ua.com.topshop.service.implementation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.topshop.dao.OrderrDao;
import ua.com.topshop.entity.Orderr;
import ua.com.topshop.service.OrderrService;

@Service
public class OrderrServiceImpl implements OrderrService {

	@Autowired
	private OrderrDao orderDao;
	
	@Override
	public void save(Orderr order) {
		orderDao.save(order);
	}

	@Override
	public void update(Orderr order) {
		orderDao.save(order);
	}

	@Override
	public void delete(int id) {
		orderDao.delete(id);
	}

	@Override
	public List<Orderr> findAll() {
		return orderDao.findAll();
	}

	@Override
	public Orderr findOne(int id) {
		return orderDao.findOne(id);
	}

	@Override
	public Orderr findOne(int id, boolean flag) {
		return orderDao.findOne(id, flag);
	}

	@Override
	public List<Orderr> findByUserId(int id) {
		return orderDao.findByUserId(id);
	}

	@Override
	public List<Orderr> findByGraphicId(int id) {
		return orderDao.findByGraphicId(id);
	}

	@Override
	public Page<Orderr> findAll(Pageable pageable) {
		return orderDao.findAll(pageable);
	}

	@Override
	public Orderr findUnique(int amount, int userId, int graphicId) {
		return orderDao.findUnique(amount, userId, graphicId);
	}

	@Override
	public List<Orderr> findUserFlag(int userId, boolean flag) {
		return orderDao.findUserFlag(userId, flag);
	}

	@Override
	public BigDecimal sum(int userId, boolean flag) {
		List<Orderr> pr = orderDao.findUserFlag(userId, flag);
		BigDecimal sum = new BigDecimal("0");
		for (int i = 0; i < pr.size(); i++) {
			Orderr order = pr.get(i);
			sum = sum.add(order.getGraphic().getPrice().multiply(new BigDecimal(order.getAmount())));
		}
		return sum;
	}
}
