package ui.web2.web.servlet.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ui.web2.web.jpa.bean.UrlToCheck;
import ui.web2.web.jpa.dao.UrlDao;

import java.util.Collection;

@Controller
public class UrlController {
    private static final String SEARCH_VIEW_KEY = "redirect:search.html";
    private static final String SEARCH_MODEL_KEY = "urls";

    @Autowired
    protected UrlDao urlDao = null;


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

    @RequestMapping(value="/urls/form", method=RequestMethod.GET)
    public void form() {}

    @RequestMapping(value="/urls/form", method=RequestMethod.POST)
    public void form(UrlForm urlForm, Model model) {


        model.addAttribute("statusMessageKey", "url.form.msg.success");
    }

    @RequestMapping(value="/urls/search", method= RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY)
    Collection<UrlToCheck> search() {
        return urlDao.findUrlToCheck();
    }

    @RequestMapping(value="/urls/delete", method=RequestMethod.POST)
    public String delete(UrlForm urlToCheck) {
        //urlDao.delete(urlToCheck);

        return SEARCH_VIEW_KEY;
    }

}
