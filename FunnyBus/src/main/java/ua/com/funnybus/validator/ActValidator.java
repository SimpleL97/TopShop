package ua.com.funnybus.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.com.funnybus.entity.Act;

public class ActValidator implements Validator {

	private static final  Pattern REGTEXT = Pattern.compile("^([а-яА-яa-zA-z0-9]{1,4000})$");
	
	private static final  Pattern REG = Pattern.compile("^([а-яА-яa-zA-z0-9]{1,50})$");

	public boolean supports(Class<?> clazz) {
		return Act.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Act act = (Act) target;
		if(!REG.matcher(act.getTitle()).matches()){
			errors.rejectValue("title", "", "Введіть не більше 50 символів");
		}
		if(!REGTEXT.matcher(act.getInfo()).matches()){
			errors.rejectValue("info", "", "Введіть не більше 4000 символів");
		}
	}
}
