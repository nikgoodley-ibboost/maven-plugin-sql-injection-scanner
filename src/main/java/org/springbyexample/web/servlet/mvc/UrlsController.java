package ui.web.org.springbyexample.web.servlet.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ui.web.org.springbyexample.web.jpa.bean.Person;
import ui.web.org.springbyexample.web.jpa.bean.UrlToCheck;
import ui.web.org.springbyexample.web.jpa.dao.UrlDao;

import java.util.Collection;
import java.util.Date;

@Controller
public class UrlsController {
    private static final String SEARCH_VIEW_KEY = "redirect:search.html";
    private static final String SEARCH_MODEL_KEY = "urls";

    @Autowired
    protected UrlDao urlDao = null;


    @ModelAttribute
    public UrlToCheck newRequest(@RequestParam(required=false) Integer id) {
        return (id != null ? urlDao.findUrlToCheckById(id) : new UrlToCheck());
    }

    @RequestMapping(value="/urls/form", method=RequestMethod.GET)
    public void form() {}

    @RequestMapping(value="/urls/form", method=RequestMethod.POST)
    public void form(UrlToCheck person, Model model) {


        model.addAttribute("statusMessageKey", "person.form.msg.success");
    }

    @RequestMapping(value="/urls/search", method= RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY)
    Collection<UrlToCheck> search() {
        return urlDao.findUrlToCheck();
    }

    @RequestMapping(value="/urls/delete", method=RequestMethod.POST)
    public String delete(UrlToCheck urlToCheck) {
        //urlDao.delete(urlToCheck);

        return SEARCH_VIEW_KEY;
    }

}
