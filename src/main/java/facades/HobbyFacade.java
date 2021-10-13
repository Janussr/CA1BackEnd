package facades;

import dtos.Hobby.HobbiesDTO;
import dtos.Hobby.HobbyDTO;
import entities.Hobby;
import jdk.nashorn.api.scripting.ScriptUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
import java.util.List;

public class HobbyFacade {

    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    private HobbyFacade() {

    }

    public static HobbyFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }

    public HobbiesDTO getAllHobbies() throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        List<Hobby> hobbies = em.createQuery("SELECT h FROM Hobby h", Hobby.class)
                .getResultList();

        if (hobbies == null) {
            throw new WebApplicationException("No hobbies were found");
        }

        return new HobbiesDTO(hobbies);
    }

    public HobbyDTO deleteHobby(HobbyDTO hobbyDTO) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Hobby hobby = em.createQuery("SELECT h FROM Hobby h WHERE h.name = :hobby", Hobby.class)
                .setParameter("hobby", hobbyDTO.getName())
                .getSingleResult();
        try {
            em.getTransaction().begin();
            em.remove(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new HobbyDTO(hobby);
    }

    public HobbyDTO createHobby(HobbyDTO hobbyDTO) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Hobby hobby = new Hobby(hobbyDTO.getName(), hobbyDTO.getWikiLink(), hobbyDTO.getCategory(), hobbyDTO.getType());

        if ((hobby.getName().length() == 0)) {
            throw new WebApplicationException("Name is missing", 400);
        }
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new HobbyDTO(hobby);
    }

}
