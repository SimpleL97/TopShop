package ua.com.funnybus.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.com.dto.form.ReviewForm;

public class ReviewValidator implements Validator {

	private static final  Pattern REG = Pattern.compile("^([u0410-\u042F\u0430-\u044F\u0456\u0457\u0454\u0406\u0407\u0404]{1,50})$");
	
	public ReviewValidator() {
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ReviewForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ReviewForm form =(ReviewForm) target;
		if(!REG.matcher(form.getFirstName()).matches()){
			errors.rejectValue("firstName", "", "Тільки букви");
		}
		if(!REG.matcher(form.getSecondName()).matches()){
			errors.rejectValue("secondName", "", "Тільки букви");
		}
	}
}