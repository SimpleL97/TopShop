package ua.com.topshop.entity;

import java.math.BigDecimal;

import javax.persistence.*;

import java.util.List;

@NamedQueries({
	@NamedQuery(name="findOneGraphic", query="select a from Graphic a where a.name =:param"),
	@NamedQuery(name="findAllGraphic", query="from Graphic")
})

@Entity
public class Graphic {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private String name;
	private int frequency;
	private int memory_value;
	private int data_bus;
	private BigDecimal price;
	private int version;

	@ManyToOne(fetch=FetchType.LAZY)
	private Memory memory;
	
	@OneToMany(mappedBy="graphic")
	private List<Orderr> orders;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Producer producer;
	
	public Graphic() {
	}
	

	public Graphic(String name) {
		this.name = name;
	}


	public Graphic(String name,int frequency, int memory_value, int data_bus,
			BigDecimal price, Memory memory, Producer producer) {
		this.name=name;
		this.frequency = frequency;
		this.memory_value = memory_value;
		this.data_bus = data_bus;
		this.price = price;
		this.memory = memory;
	}
	
	public Graphic(String name, int frequency, int memory_value, int data_bus,
			BigDecimal price) {
		this.name=name;
		this.frequency = frequency;
		this.memory_value = memory_value;
		this.data_bus = data_bus;
		this.price = price;
	}


	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


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


	public int getFrequency() {
		return frequency;
	}


	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}


	public int getMemory_value() {
		return memory_value;
	}


	public void setMemory_value(int memory_value) {
		this.memory_value = memory_value;
	}


	public int getData_bus() {
		return data_bus;
	}


	public void setData_bus(int data_bus) {
		this.data_bus = data_bus;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public Memory getMemory() {
		return memory;
	}


	public void setMemory(Memory memory) {
		this.memory = memory;
	}




	public List<Orderr> getOrders() {
		return orders;
	}


	public void setOrders(List<Orderr> orders) {
		this.orders = orders;
	}


	public Producer getProducer() {
		return producer;
	}


	public void setProducer(Producer producer) {
		this.producer = producer;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Graphic other = (Graphic) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
