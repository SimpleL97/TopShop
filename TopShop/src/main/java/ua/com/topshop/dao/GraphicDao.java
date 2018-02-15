package ua.com.topshop.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.topshop.entity.Graphic;

public interface GraphicDao extends JpaRepository<Graphic, Integer>, JpaSpecificationExecutor<Graphic>{

	Graphic findByName(String name);
	
	@Query("SELECT g FROM Graphic g LEFT JOIN FETCH g.producer LEFT JOIN FETCH g.memory")
	List<Graphic> findAll();
	
	@Query("select g from Graphic g where g.producer.id= ?1")
	List<Graphic> findByProducerId(int id);
	
	@Query("SELECT g FROM Graphic g LEFT JOIN FETCH g.producer LEFT JOIN FETCH g.memory WHERE g.id=?1")
	Graphic findOne(int id);
	
	@Query("SELECT g FROM Graphic g WHERE g.name=?1 and g.frequency=?2 and g.data_bus=?3 and g.memory_value =?4 and g.price =?5 and g.memory.id =?6 and g.producer.id=?7")
	Graphic findUnique(String name, int frequency, int data_bus, int memory_value, BigDecimal price, int memoryId, int producerId);
	
	@Query(value="SELECT g FROM Graphic g LEFT JOIN FETCH g.memory LEFT JOIN FETCH g.producer",
			countQuery="SELECT count(g.id) FROM Graphic g")
	Page<Graphic> findAll(Pageable pageable);
}