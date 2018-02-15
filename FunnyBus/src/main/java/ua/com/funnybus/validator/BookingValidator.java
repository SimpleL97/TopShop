package ua.com.funnybus.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.com.dto.form.BookingForm;

public class BookingValidator implements Validator {

	private static final  Pattern REG = Pattern.compile("^([u0410-\u042F\u0430-\u044F\u0456\u0457\u0454\u0406\u0407\u0404]{1,50})$");
	private static final  Pattern REGPHONE = Pattern.compile("^([0-9]{10})");
	
	public BookingValidator() {
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return BookingForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BookingForm form =(BookingForm) target;
		if(!REGPHONE.matcher(form.getPhone()).matches()){
			errors.rejectValue("phone", "", "формат: (0XX)XXXXXXX");
		}
		if(!REG.matcher(form.getFirstName()).matches()){
			errors.rejectValue("firstName", "", "Тільки букви");
		}
		if(!REG.matcher(form.getSecondName()).matches()){
			errors.rejectValue("secondName", "", "Тільки букви");
		}
		
	}

}
