package com.tc.farecapping.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ZoneValidator implements ConstraintValidator<Zone, Integer>{

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
			return value!=null && (value.intValue() == 1 || value.intValue() == 2);
	}

}
