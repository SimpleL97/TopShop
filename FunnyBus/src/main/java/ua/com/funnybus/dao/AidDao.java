package ua.com.funnybus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.funnybus.entity.Aid;

public interface AidDao extends JpaRepository<Aid, Integer>,JpaSpecificationExecutor<Aid> {
	Aid findByTitle(String title);
	Aid findByDate(String date);
	Aid findByInfo(String info);
}
