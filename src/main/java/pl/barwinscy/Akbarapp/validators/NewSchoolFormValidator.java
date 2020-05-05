package pl.barwinscy.Akbarapp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.barwinscy.Akbarapp.dto.PhoneDTO;
import pl.barwinscy.Akbarapp.dto.SchoolDto;

@Component
public class NewSchoolFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return SchoolDto.class.equals(aClass) || PhoneDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SchoolDto dto = (SchoolDto) o;
        validateGeneralInfo(errors, dto);
    }
    
    private void validateGeneralInfo(Errors errors, SchoolDto dto) {
        if (dto.getName().isBlank()) {
            errors.rejectValue("name", "newSchoolForm.validator.name.blank");
        }
        if (!dto.getZipCode().matches("^\\d{2}[-]\\d{3}$")) {
            if (!dto.getZipCode().isEmpty()) {
                errors.rejectValue("zipCode", "newSchoolForm.validator.zipCode.invalid");
            }
        }
        if (!dto.getCity().matches("^[A-ZĆŁŚŹŻ][a-ząęćłóśźż]+(?:[\\s-]{1}[a-ząęćłóśźżA-ZĆŁŚŹŻ]+)*$")) {
            if (!dto.getCity().isEmpty()) {
                errors.rejectValue("city", "newSchoolForm.validator.city.invalid");
            }
        }
        if (!dto.getBorough().matches("^[A-ZĆŁŚŹŻ][a-ząęćłóśźż]+(?:[\\s-]{1}[a-ząęćłóśźżA-ZĆŁŚŹŻ]+)*$")) {
            if (!dto.getBorough().isEmpty()) {
                errors.rejectValue("borough", "newSchoolForm.validator.borough.invalid");
            }
        }
        if (!dto.getPhoneNumber().matches("^[0-9 -]{9,12}$")) {
            if (!dto.getPhoneNumber().isEmpty()) {
                errors.rejectValue("phoneNumber", "newSchoolForm.validator.phoneNumber.invalid");
            }
        }
    }
}
