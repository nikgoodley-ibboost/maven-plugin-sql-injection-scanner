package ui.web2.web.jpa.dao;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ui.web.org.springbyexample.web.jpa.bean.Address;
import ui.web.org.springbyexample.web.jpa.bean.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Person DAO implementation.
 *
 * @author David Winterfeldt
 */
@Repository
@Transactional(readOnly = true)
public class PersonDaoImpl implements PersonDao {

    private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Find persons.
     */
    public Person findPersonById(Integer id) {
        return em.find(Person.class, id);
    }

    /**
     * Find persons using a start index and max number of results.
     */
    @SuppressWarnings("unchecked")
    public Collection<Person> findPersons(final int startIndex, final int maxResults) {
        return em.createQuery("select p from Person p order by p.lastName, p.firstName")
                .setFirstResult(startIndex).setMaxResults(maxResults).getResultList();
    }

    /**
     * Find persons.
     */
    @SuppressWarnings("unchecked")
    public Collection<Person> findPersons() {
        return em.createQuery("select p from Person p order by p.lastName, p.firstName").getResultList();
    }

    /**
     * Find persons by last name.
     */
    @SuppressWarnings("unchecked")
    public Collection<Person> findPersonsByLastName(String lastName) {
        return em.createQuery("select p from Person p where p.lastName = :lastName order by p.lastName, p.firstName")
                .setParameter("lastName", lastName).getResultList();
    }

    /**
     * Saves person.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Person save(Person person) {
        return em.merge(person);
    }

    /**
     * Deletes person.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void delete(Person person) {
        em.remove(em.merge(person));
    }

    /**
     * Saves address to person.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Person saveAddress(Integer id, Address address) {
        Person person = findPersonById(id);

        if (person.getAddresses().contains(address)) {
            person.getAddresses().remove(address);
        }

        person.getAddresses().add(address);

        return save(person);
    }

    /**
     * Deletes address from person.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Person deleteAddress(Integer id, Integer addressId) {
        Person person = findPersonById(id);

        Address address = new Address();
        address.setId(addressId);

        if (person.getAddresses().contains(address)) {
            for (Address a : person.getAddresses()) {
                if (a.getId().equals(addressId)) {
                    em.remove(a);
                    person.getAddresses().remove(address);

                    break;
                }
            }
        }

        return person;
    }

}