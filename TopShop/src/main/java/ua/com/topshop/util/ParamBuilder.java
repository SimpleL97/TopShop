package ua.com.topshop.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import ua.com.dto.filter.SimpleFilter;

public class ParamBuilder {
	public static String getParams(Pageable pageable){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		return buffer.toString();
	}
	
	public static String getParams(Pageable pageable, SimpleFilter filter){
		if(filter.getSearch().isEmpty()) getParams(pageable);
		StringBuilder buffer = new StringBuilder(getParams(pageable));
		buffer.append("&search=");
		buffer.append(filter.getSearch());
		return buffer.toString();
	}
}
