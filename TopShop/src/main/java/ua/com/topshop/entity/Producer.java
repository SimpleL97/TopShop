package ua.com.topshop.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Producer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String codeName;
	
	@OneToMany(mappedBy="producer")
	private List<Graphic> graphics;
	
	public Producer() {
		// TODO Auto-generated constructor stub
	}

	public Producer(String codeName) {
		this.codeName = codeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public List<Graphic> getGraphics() {
		return graphics;
	}

	public void setGraphics(List<Graphic> graphics) {
		this.graphics = graphics;
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
		Producer other = (Producer) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
