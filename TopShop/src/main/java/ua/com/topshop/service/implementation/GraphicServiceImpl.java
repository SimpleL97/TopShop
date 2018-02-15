package ua.com.topshop.service.implementation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.com.dto.filter.GraphicFilter;
import ua.com.dto.form.GraphicForm;
import ua.com.topshop.dao.GraphicDao;
import ua.com.topshop.dao.MemoryDao;
import ua.com.topshop.dao.ProducerDao;
import ua.com.topshop.dao.UserDao;
import ua.com.topshop.entity.Graphic;
import ua.com.topshop.entity.Memory;
import ua.com.topshop.entity.Producer;
import ua.com.topshop.service.FileWriter;
import ua.com.topshop.service.GraphicService;
import ua.com.topshop.service.FileWriter.Folder;
import ua.com.topshop.specification.GraphicSpecification;

@Service
public class GraphicServiceImpl implements GraphicService  {

	@Autowired
	private GraphicDao graphicDao;
	
	@Autowired
	private MemoryDao memoryDao;
	
	@Autowired
	private ProducerDao producerDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FileWriter fileWriter;

	
	public void save(GraphicForm form) {
		MultipartFile file = form.getFile();
		Graphic entity = new Graphic();
		entity.setName(form.getName());
		entity.setFrequency(Integer.parseInt(form.getFrequency()));
		entity.setData_bus(Integer.parseInt(form.getData_bus()));
		entity.setMemory_value(Integer.parseInt(form.getMemory_value()));
		entity.setPrice(new BigDecimal(form.getPrice()));
		entity.setMemory(form.getMemory());
		entity.setProducer(form.getProducer());
		entity.setId(form.getId());
		graphicDao.saveAndFlush(entity);
		if(fileWriter.write(Folder.GRAPHIC, file, entity.getId())){
			entity.setVersion(entity.getVersion()+1);
			graphicDao.save(entity);
		}
	}

	public void update(Graphic graphic) {
		graphicDao.save(graphic);
	}

	public List<Graphic> findAll() {
		return  graphicDao.findAll();
	}

	public Graphic findOne(int id) {
		return graphicDao.findOne(id);
	}

	public void delete(String name) {
		graphicDao.delete(findByName(name));
	}

	public Graphic findByName(String name) {
		return graphicDao.findByName(name);
	}
	
	public void addProducerToGraphic(String graphicName, String producerCodeName){
		Graphic graphic = graphicDao.findByName(graphicName);
		Producer producer = producerDao.findByCodeName(producerCodeName);
		
		graphic.setProducer(producer);
		graphicDao.save(graphic);
	}
	
	public void addAlltoGraphic(String graphicName, int frequency, int memory_value, int data_bus,
			BigDecimal price, String  memoryType, String producerCodeName){
		Graphic graphic = graphicDao.findByName(graphicName);
		Producer producer = producerDao.findByCodeName(producerCodeName);
		Memory memory = memoryDao.findByType(memoryType);
		
		graphic.setFrequency(frequency);
		graphic.setMemory_value(memory_value);
		graphic.setData_bus(data_bus);
		graphic.setPrice(price);
		graphic.setMemory(memory);
		graphic.setProducer(producer);
		
		graphicDao.save(graphic);
	}
	
	public void createAllGraphic(String graphicName, int frequency, int memory_value, int data_bus,
			BigDecimal price, String  memoryType, String producerCodeName){
		Producer producer = producerDao.findByCodeName(producerCodeName);
		Memory memory = memoryDao.findByType(memoryType);
		Graphic graphic = new Graphic(graphicName, frequency,memory_value,data_bus,price,memory,producer);
		graphicDao.save(graphic);
	}

	public void deleteProducerFromGraphic(String graphicName) {
		Graphic graphic = graphicDao.findByName(graphicName);
		
		graphic.setProducer(null);
		graphicDao.save(graphic);
	}

	public List<Graphic> findByProducerId(int id) {
		return graphicDao.findByProducerId(id);
	}

	public void delete(int id) {
		graphicDao.delete(id);
		
	}

	public GraphicForm findForm(int id) {
		Graphic entity = graphicDao.findOne(id);
		GraphicForm form = new GraphicForm();
		form.setName(entity.getName());
		form.setFrequency(String.valueOf(entity.getFrequency()));
		form.setData_bus(String.valueOf(entity.getData_bus()));
		form.setMemory_value(String.valueOf(entity.getMemory_value()));
		form.setPrice(String.valueOf(entity.getPrice()));
		form.setId(entity.getId());
		form.setMemory(entity.getMemory());
		form.setProducer(entity.getProducer());
		return form;
	}

	public Graphic findUnique(String name, String frequency, String data_bus, String memory_value, String price, Memory memory, Producer producer) {
		return graphicDao.findUnique(name, Integer.parseInt(frequency), Integer.parseInt(data_bus), Integer.parseInt(memory_value) ,new BigDecimal(price.replace(',', '.')), memory.getId(), producer.getId());
	}

	@Override
	public Page<Graphic> findAll(Pageable pageable, GraphicFilter filter) {
		return graphicDao.findAll(new GraphicSpecification(filter), pageable);
	}
}
