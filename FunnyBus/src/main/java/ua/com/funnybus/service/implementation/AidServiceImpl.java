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
import ua.com.funnybus.dao.AidDao;
import ua.com.funnybus.entity.Aid;
import ua.com.funnybus.service.AidService;
import ua.com.funnybus.service.FileWriter;
import ua.com.funnybus.service.FileWriter.Folder;


@Service
public class AidServiceImpl implements AidService {

	@Autowired
	private AidDao aidDao;
	
	@Autowired
	private FileWriter fileWriter;
	
	public void save(Aid aid) {
		Date date = new Date();
		aid.setDate(date);
		MultipartFile file = aid.getFile();
		aid = aidDao.saveAndFlush(aid);
		if(fileWriter.write(Folder.AID, file, aid.getId())){
			aid.setVersion(aid.getVersion()+1);
			aidDao.save(aid);
		}
	}

	public void update(Aid aid) {
		aidDao.save(aid);
	}

	public List<Aid> findAll() {
		return aidDao.findAll();
	}

	public Aid findOne(int id) {
		return aidDao.findOne(id);
	}

	public void delete(int id) {
		aidDao.delete(id);
	}

	@Override
	public Page<Aid> findAll(Pageable pageable) {
		return aidDao.findAll(pageable);
	}

	@Override
	public Aid findByTitle(String title) {
		return aidDao.findByTitle(title);
	}

	@Override
	public Aid findByInfo(String info) {
		return aidDao.findByInfo(info);
	}

	@Override
	public Page<Aid> findAll(SimpleFilter filter, Pageable pageable) {
		return aidDao.findAll(findByTypeLike(filter), pageable);
	}
	
	private Specification<Aid> findByTypeLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("title")), filter.getSearch().toLowerCase()+"%");
		};
	}

}
