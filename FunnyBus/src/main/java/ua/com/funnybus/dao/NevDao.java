package ua.com.funnybus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.funnybus.entity.Nev;

public interface NevDao extends JpaRepository<Nev, Integer>,JpaSpecificationExecutor<Nev>{
	Nev findByTitle(String title);
	Nev findByDate(String date);
	Nev findByInfo(String info);
}
