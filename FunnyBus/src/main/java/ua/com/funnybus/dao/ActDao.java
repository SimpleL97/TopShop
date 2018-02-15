package ua.com.funnybus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.funnybus.entity.Act;

public interface ActDao extends JpaRepository<Act, Integer>,JpaSpecificationExecutor<Act> {
	
	Act findByTitle(String title);
	Act findByDate(String date);
	Act findByInfo(String info);
}
