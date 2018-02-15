package ua.com.funnybus.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.com.funnybus.entity.Nev;

public class NevValidator implements Validator {

	private static final  Pattern REGTEXT = Pattern.compile("^([а-яА-яa-zA-z0-9]{1,4000})$");
	
	private static final  Pattern REG = Pattern.compile("^([а-яА-яa-zA-z0-9]{1,50})$");

	public boolean supports(Class<?> clazz) {
		return Nev.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Nev nev = (Nev) target;
		if(!REG.matcher(nev.getTitle()).matches()){
			errors.rejectValue("title", "", "Введіть не більше 50 символів");
		}
		if(!REGTEXT.matcher(nev.getInfo()).matches()){
			errors.rejectValue("info", "", "Введіть не більше 4000 символів");
		}
	}
}
