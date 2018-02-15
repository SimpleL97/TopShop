package ua.com.dto.filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GraphicFilter {
	
	private String minPrice = "";
	private String maxPrice = "";

	private String minFrequency = "";
	private String maxFrequency = "";
	
	private String minData_bus = "";
	private String maxData_bus = "";
	
	private String minMemory_value = "";
	private String maxMemory_value = "";
	
	
	private BigDecimal maxPriceValue;
	private BigDecimal minPriceValue;
	
	private Integer maxFrequencyValue;
	private Integer minFrequencyValue;
	
	private Integer maxData_busValue;
	private Integer minData_busValue;
	
	private Integer maxMemoryValue;
	private Integer minMemoryValue;
	
	private List<Integer> memoryId = new ArrayList<>();
	
	private List<Integer> producerId = new ArrayList<>();

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getMinFrequency() {
		return minFrequency;
	}

	public void setMinFrequency(String minFrequency) {
		this.minFrequency = minFrequency;
	}

	public String getMaxFrequency() {
		return maxFrequency;
	}

	public void setMaxFrequency(String maxFrequency) {
		this.maxFrequency = maxFrequency;
	}

	public String getMinData_bus() {
		return minData_bus;
	}

	public void setMinData_bus(String minData_bus) {
		this.minData_bus = minData_bus;
	}

	public String getMaxData_bus() {
		return maxData_bus;
	}

	public void setMaxData_bus(String maxData_bus) {
		this.maxData_bus = maxData_bus;
	}

	public String getMinMemory_value() {
		return minMemory_value;
	}

	public void setMinMemory_value(String minMemory_value) {
		this.minMemory_value = minMemory_value;
	}

	public String getMaxMemory_value() {
		return maxMemory_value;
	}

	public void setMaxMemory_value(String maxMemory_value) {
		this.maxMemory_value = maxMemory_value;
	}

	public BigDecimal getMaxPriceValue() {
		return maxPriceValue;
	}

	public void setMaxPriceValue(BigDecimal maxPriceValue) {
		this.maxPriceValue = maxPriceValue;
	}

	public BigDecimal getMinPriceValue() {
		return minPriceValue;
	}

	public void setMinPriceValue(BigDecimal minPriceValue) {
		this.minPriceValue = minPriceValue;
	}

	public Integer getMaxFrequencyValue() {
		return maxFrequencyValue;
	}

	public void setMaxFrequencyValue(Integer maxFrequencyValue) {
		this.maxFrequencyValue = maxFrequencyValue;
	}

	public Integer getMinFrequencyValue() {
		return minFrequencyValue;
	}

	public void setMinFrequencyValue(Integer minFrequencyValue) {
		this.minFrequencyValue = minFrequencyValue;
	}

	public Integer getMaxData_busValue() {
		return maxData_busValue;
	}

	public void setMaxData_busValue(Integer maxData_busValue) {
		this.maxData_busValue = maxData_busValue;
	}

	public Integer getMinData_busValue() {
		return minData_busValue;
	}

	public void setMinData_busValue(Integer minData_busValue) {
		this.minData_busValue = minData_busValue;
	}

	public Integer getMaxMemoryValue() {
		return maxMemoryValue;
	}

	public void setMaxMemoryValue(Integer maxMemoryValue) {
		this.maxMemoryValue = maxMemoryValue;
	}

	public Integer getMinMemoryValue() {
		return minMemoryValue;
	}

	public void setMinMemoryValue(Integer minMemoryValue) {
		this.minMemoryValue = minMemoryValue;
	}

	public List<Integer> getMemoryId() {
		return memoryId;
	}

	public void setMemoryId(List<Integer> memoryId) {
		this.memoryId = memoryId;
	}

	public List<Integer> getProducerId() {
		return producerId;
	}

	public void setProducerId(List<Integer> producerId) {
		this.producerId = producerId;
	}

	
}
