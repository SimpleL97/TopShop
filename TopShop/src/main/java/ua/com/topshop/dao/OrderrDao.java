package ua.com.topshop.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.topshop.entity.Orderr;

public interface OrderrDao extends JpaRepository<Orderr, Integer>, JpaSpecificationExecutor<Orderr> {
	
	@Query("SELECT o FROM Orderr o LEFT JOIN FETCH o.user LEFT JOIN FETCH o.graphic WHERE o.id=?1")
	Orderr findOne(int id);
	
	@Query("SELECT o FROM Orderr o LEFT JOIN FETCH o.user LEFT JOIN FETCH o.graphic WHERE o.id=?1 and o.flag=?2")
	Orderr findOne(int id, boolean flag);
	
	@Query("SELECT pp FROM Orderr pp WHERE pp.graphic.id =?1")
	List<Orderr> findByGraphicId(int id);
	
	@Query(value="SELECT a FROM Orderr a LEFT JOIN FETCH a.user LEFT JOIN FETCH a.graphic",
			countQuery="SELECT count(a.id) FROM Orderr a")
	Page<Orderr> findAll(Pageable pageable);
	
	@Query("SELECT a FROM Orderr a LEFT JOIN FETCH a.user LEFT JOIN FETCH a.graphic")
	List<Orderr> findAll();
	
	@Query("SELECT o FROM Orderr o WHERE o.amount=?1 and o.user.id=?2 and o.graphic.id=?3")
	Orderr findUnique(int amount, int userId, int graphicId);
	
	@Query("select o from Orderr o WHERE o.user.id=?1") 
	List<Orderr> findByUserId(int userId);
	
	@Query("select o from Orderr o LEFT JOIN FETCH o.graphic where o.user.id=?1 and o.flag=?2")
	List<Orderr> findUserFlag(int userId, boolean flag);
}
