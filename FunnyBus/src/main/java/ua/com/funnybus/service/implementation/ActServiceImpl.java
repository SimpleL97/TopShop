package ua.com.funnybus.service.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.com.dto.filter.SimpleFilter;
import ua.com.funnybus.dao.ActDao;
import ua.com.funnybus.entity.Act;
import ua.com.funnybus.service.ActService;
import ua.com.funnybus.service.FileWriter;
import ua.com.funnybus.service.FileWriter.Folder;

@Service
public class ActServiceImpl implements ActService {

	@Autowired
	private ActDao actDao;
	
	@Autowired
	private FileWriter fileWriter;
	
	public void save(Act act) {
		Date date = new Date();
		act.setDate(date);
		MultipartFile file = act.getFile();
		act = actDao.saveAndFlush(act);
		if(fileWriter.write(Folder.ACT, file, act.getId())){
			act.setVersion(act.getVersion()+1);
			actDao.save(act);
		}
	}

	public void update(Act act) {
		actDao.save(act);
	}

	public List<Act> findAll() {
		return actDao.findAll();
	}

	public Act findOne(int id) {
		return actDao.findOne(id);
	}

	public void delete(int id) {
		actDao.delete(id);
	}
	
	@Override
	public Page<Act> findAll(Pageable pageable){
		return actDao.findAll(pageable);
	}

	@Override
	public Act findByTitle(String title) {
		return actDao.findByTitle(title);
	}

	@Override
	public Act findByInfo(String info) {
		return actDao.findByInfo(info);
	}
	
	private Specification<Act> findByTypeLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("title")), filter.getSearch().toLowerCase()+"%");
		};
	}

	@Override
	public Page<Act> findAll(SimpleFilter filter, Pageable pageable) {
		return actDao.findAll(findByTypeLike(filter), pageable);
	}
}
