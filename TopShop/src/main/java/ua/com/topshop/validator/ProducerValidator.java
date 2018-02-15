package ua.com.topshop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.topshop.entity.Producer;
import ua.com.topshop.service.ProducerService;

public class ProducerValidator implements Validator {
	
	private final ProducerService producerService;

	public ProducerValidator(ProducerService producerService) {
		this.producerService = producerService;
	}
	
	public boolean supports(Class<?> clazz) {
		return Producer.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Producer producer = (Producer) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codeName", "", "Can't be empty");
		if(producerService.findByCodeName(producer.getCodeName())!=null){
			errors.rejectValue("codeName", "", "Already exist");
		}
	}
}
