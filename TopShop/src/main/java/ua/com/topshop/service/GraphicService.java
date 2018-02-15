package ua.com.topshop.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.dto.filter.GraphicFilter;
import ua.com.dto.form.GraphicForm;
import ua.com.topshop.entity.Graphic;
import ua.com.topshop.entity.Memory;
import ua.com.topshop.entity.Producer;

public interface GraphicService {
	void save(GraphicForm graphic);
	void update(Graphic graphic);
	List<Graphic> findAll();
	Graphic findOne(int id);
	void delete(int id);
	void delete(String name);
	Graphic findByName(String name);
	GraphicForm findForm(int id);
	Graphic findUnique(String name, String frequency, String data_bus, 
			String memory_value, String price, Memory memory, Producer producer);
	void addProducerToGraphic(String graphicName, String producerCodeName);
	void addAlltoGraphic(String graphicName, int frequency, int memory_value, int data_bus,
			BigDecimal price, String  memoryType, String producerCodeName);
	void createAllGraphic(String graphicName, int frequency, int memory_value, int data_bus,
			BigDecimal price, String  memoryType, String producerCodeName);
	void deleteProducerFromGraphic(String graphicName);
	List<Graphic> findByProducerId(int id);
	
	Page<Graphic> findAll(Pageable pageable, GraphicFilter filter);
}
