package ui.web2.web.servlet.mvc;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import ui.web2.web.jpa.bean.UrlToCheck;
import ui.web2.web.jpa.dao.UrlDao;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class UrlController {
    private static final String SEARCH_VIEW_KEY = "redirect:search.html";
    private static final String SEARCH_MODEL_KEY = "urls";

    @Autowired
    protected UrlDao urlDao = null;

    @Autowired
    private UrlFormValidator urlFormValidator;

    public void setUrlFormValidator(UrlFormValidator urlFormValidator) {
        this.urlFormValidator = urlFormValidator;
    }



    @ModelAttribute
    public UrlForm newRequest(@RequestParam(required=false) Integer id) {
        UrlForm urlForm = new UrlForm();
        if(id!=null){
            UrlToCheck daoObject = urlDao.findUrlToCheckById(id);
            urlForm.setEmailString(daoObject.getEmailString());
            urlForm.setUrlString(daoObject.getUrlString());
        }
        return urlForm;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields(new String[] {"id"});
        //dataBinder.setRequiredFields(new String[] {"urlString", "emailString"});
        dataBinder.setAllowedFields(new String[] {});
    }

    @RequestMapping(value="/urls/form", method=RequestMethod.GET)
    public void form() {}

    @RequestMapping(value="/urls/form", method=RequestMethod.POST)
    public void form(UrlForm urlForm,  BindingResult result, Model model) {

        urlFormValidator.validate(urlForm, result);

        if (result.hasErrors()) {
            model.addAttribute("errorMessageKey", "form.incorrect");
        }
        else{
            UrlToCheck urlToCheck = new UrlToCheck();
            urlToCheck.setEmailString(urlForm.getEmailString());
            urlToCheck.setUrlString(urlForm.getUrlString());
            urlDao.save(urlToCheck);
            model.addAttribute("statusMessageKey", "url.form.msg.success");
        }

    }

    @RequestMapping(value="/urls/search", method= RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY)
    Collection<UrlForm> search() {
        Collection<UrlToCheck> urlsToCheck =  urlDao.findUrlToCheck();
        ArrayList<UrlForm> urlForms = new ArrayList<UrlForm>();
        for(UrlToCheck urlToCheck : urlsToCheck){
            UrlForm urlForm = new UrlForm();
            urlForm.setId(urlToCheck.getId());
            urlForm.setUrlString(urlForm.getUrlString());
            urlForms.add(urlForm);
        }
        return urlForms;
        
    }

    @RequestMapping(value="/urls/delete", method=RequestMethod.POST)
    public String delete(@RequestParam(required=true) Integer id) {
        UrlToCheck urlToCheckFromDB = urlDao.findUrlToCheckById(id);
        urlDao.delete(id);

        return SEARCH_VIEW_KEY;
    }

}
