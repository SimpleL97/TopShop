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
import ua.com.funnybus.dao.NevDao;
import ua.com.funnybus.entity.Nev;
import ua.com.funnybus.service.FileWriter;
import ua.com.funnybus.service.NevService;
import ua.com.funnybus.service.FileWriter.Folder;

@Service
public class NevServiceImpl implements NevService {

	@Autowired
	private NevDao nevDao;
	
	@Autowired
	private FileWriter fileWriter;

	public void save(Nev nev) {
		Date date = new Date();
		nev.setDate(date);
		MultipartFile file = nev.getFile();
		nev = nevDao.saveAndFlush(nev);
		if(fileWriter.write(Folder.NEW, file, nev.getId())){
			nev.setVersion(nev.getVersion()+1);
			nevDao.save(nev);
		}
	}

	public void update(Nev nev) {
		nevDao.save(nev);
	}

	public List<Nev> findAll() {
		return nevDao.findAll();
	}

	public Nev findOne(int id) {
		return nevDao.findOne(id);
	}

	public void delete(int id) {
		nevDao.delete(id);
	}

	@Override
	public Page<Nev> findAll(Pageable pageable) {
		return nevDao.findAll(pageable);
	}

	@Override
	public Nev findByTitle(String title) {
		return nevDao.findByTitle(title);
	}

	@Override
	public Nev findByInfo(String info) {
		return nevDao.findByInfo(info);
	}

	@Override
	public Page<Nev> findAll(SimpleFilter filter, Pageable pageable) {
		return nevDao.findAll(findByTypeLike(filter), pageable);
	}
	
	private Specification<Nev> findByTypeLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("title")), filter.getSearch().toLowerCase()+"%");
		};
	}
}
