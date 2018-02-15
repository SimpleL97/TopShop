package ua.com.dto.form;

import org.springframework.web.multipart.MultipartFile;

import ua.com.topshop.entity.Memory;
import ua.com.topshop.entity.Producer;

public class GraphicForm {

	private int id;
	private String name;
	private String frequency;
	private String memory_value;
	private String data_bus;
	private String price;
	
	private MultipartFile file;
	
	private Memory memory;
	private Producer producer;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getMemory_value() {
		return memory_value;
	}
	public void setMemory_value(String memory_value) {
		this.memory_value = memory_value;
	}
	public String getData_bus() {
		return data_bus;
	}
	public void setData_bus(String data_bus) {
		this.data_bus = data_bus;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Memory getMemory() {
		return memory;
	}
	public void setMemory(Memory memory) {
		this.memory = memory;
	}
	public Producer getProducer() {
		return producer;
	}
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
}