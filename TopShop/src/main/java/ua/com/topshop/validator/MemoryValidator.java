package ua.com.topshop.validator;

import org.springframework.validation.*;

import ua.com.topshop.entity.Memory;
import ua.com.topshop.service.MemoryService;

public class MemoryValidator implements Validator {

	private final MemoryService memoryService;

	public MemoryValidator(MemoryService memoryService) {
		this.memoryService = memoryService;
	}

	public boolean supports(Class<?> clazz) {
		return Memory.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Memory memory = (Memory) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "", "Can't be empty");
		if(memoryService.findByType(memory.getType())!=null){
			errors.rejectValue("type", "", "Already exist");
		}
	}
}
