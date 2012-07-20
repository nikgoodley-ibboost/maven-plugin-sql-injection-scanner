/*
 * Copyright 2007-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ui.web2.web.servlet.mvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ui.web2.web.jpa.bean.Person;
import ui.web2.web.jpa.dao.PersonDao;


/**
 * Person form controller.
 *
 * @author David Winterfeldt
 */
@Controller
public class PersonsController {

    private static final String SEARCH_VIEW_KEY = "redirect:search.html";
    private static final String SEARCH_MODEL_KEY = "persons";

   // @Autowired
    protected PersonDao personDao = null;

    /**
     * For every request for this controller, this will
     * create a person instance for the form.
     */
    //d@ModelAttribute
    public Person newRequest(@RequestParam(required=false) Integer id) {
        return (id != null ? personDao.findPersonById(id) : new Person());
    }

    /**
     * <p>Person form request.</p>
     *
     * <p>Expected HTTP GET and request '/person/form'.</p>
     */
    @RequestMapping(value="/person/form", method=RequestMethod.GET)
    public void form() {}

    /**
     * <p>Saves a person.</p>
     *
     * <p>Expected HTTP POST and request '/person/form'.</p>
     */
    @RequestMapping(value="/person/form", method=RequestMethod.POST)
    public void form(Person person, Model model) {
        if (person.getCreated() == null) {
            person.setCreated(new Date());
        }

        Person result = personDao.save(person);

        // set id from create
        if (person.getId() == null) {
            person.setId(result.getId());
        }

        model.addAttribute("statusMessageKey", "person.form.msg.success");
    }

    /**
     * <p>Deletes a person.</p>
     *
     * <p>Expected HTTP POST and request '/person/delete'.</p>
     */
    @RequestMapping(value="/person/delete", method=RequestMethod.POST)
    public String delete(Person person) {
        personDao.delete(person);

        return SEARCH_VIEW_KEY;
    }

    /**
     * <p>Searches for all persons and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/person/search'.</p>
     */
    @RequestMapping(value="/person/search", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<Person> search() {
        return personDao.findPersons();
    }

}
