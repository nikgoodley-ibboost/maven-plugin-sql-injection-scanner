package ui.web2.web.servlet.mvc;

import org.hibernate.validator.constraints.impl.EmailValidator;
import org.hibernate.validator.constraints.impl.URLValidator;
import org.hibernate.validator.engine.ConstraintValidatorContextImpl;
import org.springframework.validation.Errors;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: nshevchenko
 * Date: 18.07.12
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */
@Component("urlFormValidator")
public class UrlFormValidator {

    public boolean supports(Class<?> klass) {
        return UrlForm.class.isAssignableFrom(klass);
    }

    public void validate(Object target, Errors errors) {
        UrlForm registration = (UrlForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "urlString",
                "NotEmpty.form.urlString");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailString",
                "NotEmpty.form.emailString");
        if(errors.hasErrors()){
            return;
        }
        String email = registration.getEmailString();
        EmailValidator emailValidator= new EmailValidator();
        if(!emailValidator.isValid(email, null)){
            errors.rejectValue("emailString", "form.invalidEmail");
        }

        try{
            new URL(registration.getUrlString());
        }
        catch(MalformedURLException mue){
            errors.rejectValue("urlString", "form.invalidUrl");
        }


        /*if ((userName.length()) > 50) {
            errors.rejectValue("userName",
                    "lengthOfUser.registration.userName",
                    "User Name must not more than 50 characters.");
        }
        if (!(registration.getPassword()).equals(registration
                .getConfirmPassword())) {
            errors.rejectValue("password",
                    "matchingPassword.registration.password",
                    "Password and Confirm Password Not match.");
        } */
    }
}






