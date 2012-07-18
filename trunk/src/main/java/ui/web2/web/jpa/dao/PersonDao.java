package ui.web2.web.jpa.dao;

import java.util.Collection;

import ui.web.org.springbyexample.web.jpa.bean.Address;
import ui.web.org.springbyexample.web.jpa.bean.Person;


/**
 * Person DAO interface.
 *
 * @author David Winterfeldt
 */
public interface PersonDao {

    /**
     * Find person by id.
     */
    public Person findPersonById(Integer id);

    /**
     * Find persons.
     */
    public Collection<Person> findPersons();

    /**
     * Find persons using a start index and max number of results.
     */
    public Collection<Person> findPersons(final int startIndex, final int maxResults);

    /**
     * Find persons by last name.
     */
    public Collection<Person> findPersonsByLastName(String lastName);

    /**
     * Saves person.
     */
    public Person save(Person person);

    /**
     * Deletes person.
     */
    public void delete(Person person);

    /**
     * Saves address to person by adding or updating record.
     */
    public Person saveAddress(Integer id, Address address);

    /**
     * Deletes address.
     */
    public Person deleteAddress(Integer id, Integer addressId);

}

