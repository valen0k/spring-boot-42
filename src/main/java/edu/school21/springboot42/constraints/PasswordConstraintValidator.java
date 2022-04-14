package edu.school21.springboot42.constraints;

import org.passay.*;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new WhitespaceRule()
        ));
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);
        if (LocaleContextHolder.getLocale().toString().equals("ru")) {
            List<String> tmp = new ArrayList<>();
            for (String s : messages) {
                if (s.contains("8"))
                    tmp.add("Пароль должен содержать не менее 8 символов");
                else if (s.contains("uppercase"))
                    tmp.add("Пароль должен содержать хотя бы одну заглавную букву");
                else if (s.contains("lowercase"))
                    tmp.add("Пароль должен содержать хотя бы одну строчную букву");
                else if (s.contains("digit"))
                    tmp.add("Пароль должен содержать хотя бы одну цифру");
            }
            messages = tmp;
        }
        String messageTemplate = messages.stream()
                .collect(Collectors.joining("\n"));
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}