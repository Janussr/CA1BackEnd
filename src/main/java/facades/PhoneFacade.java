package facades;

import dtos.Phone.PhoneDTO;
import dtos.Phone.PhonesDTO;
import entities.Phone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.List;

public class PhoneFacade {
    private static PhoneFacade instance;
    private static EntityManagerFactory emf;


    public static PhoneFacade getPhoneFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PhoneFacade();
        }
        return instance;
    }

    public PhonesDTO getAllPhones() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Phone> query = em.createQuery("SELECT p FROM Phone p ", Phone.class);
            List<Phone> phoneList = query.getResultList();
            em.getTransaction().commit();

            return new PhonesDTO(phoneList);
        } finally {
            em.close();
        }
    }

    public PhoneDTO getPhoneById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Phone phone = em.find(Phone.class, id);
            return new PhoneDTO(phone);
        } finally {
            em.close();
        }
    }

    public PhoneDTO createPhone(PhoneDTO phoneDTO) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Phone phone = new Phone(phoneDTO.getNumber(), phoneDTO.getDescription());

        // Should not be longer than 8 if its a danish number.
        if ((phone.getNumber().length() == 0)) {
            throw new WebApplicationException("Number is missing", 400);
        }
        try {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return new PhoneDTO(phone);
    }


    // edit


    public PhoneDTO deletePhone(PhoneDTO phoneDTO) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Phone phone = em.createQuery("SELECT p FROM Phone p WHERE p.number = :phone", Phone.class)
                .setParameter("phone", phoneDTO.getNumber())
                .getSingleResult();
        try {
            em.getTransaction().begin();
            em.remove(phone);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PhoneDTO(phone);
    }


}
