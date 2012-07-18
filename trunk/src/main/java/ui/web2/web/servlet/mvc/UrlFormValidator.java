package ui.web2.web.servlet.mvc;

import org.springframework.validation.Errors;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
        String email = registration.getEmailString();
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






