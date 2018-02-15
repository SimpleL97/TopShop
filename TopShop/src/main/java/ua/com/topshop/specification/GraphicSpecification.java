package ua.com.topshop.specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ua.com.dto.filter.GraphicFilter;
import ua.com.topshop.entity.Graphic;

public class GraphicSpecification implements Specification<Graphic> {

	private final GraphicFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	private static final Pattern REGDEC = Pattern.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");
	private static final Pattern REG = Pattern.compile("^([0-9]{1,17})$");
	
	
	
	
	public GraphicSpecification(GraphicFilter filter) {
		this.filter = filter;
		if(REGDEC.matcher(filter.getMaxPrice()).matches()){
			filter.setMaxPriceValue(new BigDecimal(filter.getMaxPrice().replace(',', '.')));
		}
		if(REGDEC.matcher(filter.getMinPrice()).matches()){
			filter.setMinPriceValue(new BigDecimal(filter.getMinPrice().replace(',', '.')));
		}
		if(REG.matcher(filter.getMaxFrequency()).matches()){
			filter.setMaxFrequencyValue(Integer.parseInt(filter.getMaxFrequency()));
		}
		if(REG.matcher(filter.getMinFrequency()).matches()){
			filter.setMinFrequencyValue(Integer.parseInt(filter.getMinFrequency()));
		}
		if(REG.matcher(filter.getMaxData_bus()).matches()){
			filter.setMaxData_busValue(Integer.parseInt(filter.getMaxData_bus()));
		}
		if(REG.matcher(filter.getMinData_bus()).matches()){
			filter.setMinData_busValue(Integer.parseInt(filter.getMinData_bus()));
		}
		if(REG.matcher(filter.getMaxMemory_value()).matches()){
			filter.setMaxMemoryValue(Integer.parseInt(filter.getMaxMemory_value()));
		}
		if(REG.matcher(filter.getMinMemory_value()).matches()){
			filter.setMinMemoryValue(Integer.parseInt(filter.getMinMemory_value()));
		}
	}

	private void filterByMemory(Root<Graphic> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getMemoryId().isEmpty()){
			predicates.add(root.get("memory").in(filter.getMemoryId()));
		}
	}
	
	private void filterByProducer(Root<Graphic> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getProducerId().isEmpty()){
			predicates.add(root.get("producer").in(filter.getProducerId()));
		}
	}
	
	private void filterByPrice(Root<Graphic> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMaxPriceValue()!=null){
			predicates.add(cb.le(root.get("price"), filter.getMaxPriceValue()));
		}
		if(filter.getMinPriceValue()!=null){
			predicates.add(cb.ge(root.get("price"), filter.getMinPriceValue()));
		}
	}
	
	private void filterByFrequency(Root<Graphic> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMaxFrequencyValue()!=null){
			predicates.add(cb.le(root.get("frequency"), filter.getMaxFrequencyValue()));
		}
		if(filter.getMinFrequencyValue()!=null){
			predicates.add(cb.ge(root.get("frequency"), filter.getMinFrequencyValue()));
		}
	}
	
	private void filterByData_bus(Root<Graphic> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMaxData_busValue()!=null){
			predicates.add(cb.le(root.get("data_bus"), filter.getMaxData_busValue()));
		}
		if(filter.getMinData_busValue()!=null){
			predicates.add(cb.ge(root.get("data_bus"), filter.getMinData_busValue()));
		}
	}
	
	private void filterByMemory_value(Root<Graphic> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMaxMemoryValue()!=null){
			predicates.add(cb.le(root.get("memory_value"), filter.getMaxMemoryValue()));
		}
		if(filter.getMinMemoryValue()!=null){
			predicates.add(cb.ge(root.get("memory_value"), filter.getMinMemoryValue()));
		}
	}
	
	private void fetch(Root<Graphic> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			query.distinct(true);
			root.fetch("memory");
			root.fetch("producer");
		}
	}
	
	@Override
	public Predicate toPredicate(Root<Graphic> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		fetch(root, query);
		filterByMemory(root, query, cb);
		filterByProducer(root, query, cb);
		filterByPrice(root, query, cb);
		filterByFrequency(root, query, cb);
		filterByData_bus(root, query, cb);
		filterByMemory_value(root, query, cb);
		if(predicates.isEmpty()) return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}
}
