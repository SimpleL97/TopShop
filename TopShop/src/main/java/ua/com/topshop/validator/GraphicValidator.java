package ua.com.topshop.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.dto.form.GraphicForm;
import ua.com.topshop.service.GraphicService;

public class GraphicValidator implements Validator {

	private static final  Pattern REGDEC = Pattern.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");
	private static final  Pattern REG = Pattern.compile("^([0-9]{1,10})$");
	
	private GraphicService graphicService;

	public GraphicValidator(GraphicService graphicService) {
		this.graphicService = graphicService;
	}

	public boolean supports(Class<?> clazz) {
		return GraphicForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		GraphicForm form = (GraphicForm)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can't be empty");
		if(!REG.matcher(form.getFrequency()).matches()){
			errors.rejectValue("frequency", "", "Write only integers");
		}
		if(!REG.matcher(form.getMemory_value()).matches()){
			errors.rejectValue("memory_value", "", "Write only integers");
		}
		if(!REG.matcher(form.getData_bus()).matches()){
			errors.rejectValue("data_bus", "", "Write only integers");
		}
		if(!REGDEC.matcher(form.getPrice()).matches()){
			errors.rejectValue("price", "", "Can be separated , or . or write only numbers");
		}
		if(errors.getFieldError("name")==null && errors.getFieldError("frequency")==null 
				&& errors.getFieldError("memory_value")==null && errors.getFieldError("data_bus")==null 
				&& errors.getFieldError("price")==null){
			if(graphicService.findUnique(form.getName(), form.getFrequency(), form.getData_bus(),
					form.getMemory_value(), form.getPrice(), form.getMemory(), form.getProducer())!=null){
				errors.rejectValue("name", "", "Aready exist");
			}
		}
	}
}
