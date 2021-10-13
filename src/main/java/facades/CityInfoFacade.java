package facades;

import dtos.CityInfo.CityInfoDTO;
import dtos.CityInfo.CityInfosDTO;
import entities.CityInfo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class CityInfoFacade {
    private static CityInfoFacade instance;
    private static EntityManagerFactory emf;


    public static CityInfoFacade getCityInfoFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CityInfoFacade();
        }
        return instance;
    }


    public CityInfosDTO getAllCityInfo() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo c", CityInfo.class);
            List<CityInfo> cityInfoList = query.getResultList();
            return new CityInfosDTO(cityInfoList);
        } finally {
            em.close();
        }
    }

    public CityInfoDTO getCityById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            CityInfo cityInfo = em.find(CityInfo.class, id);
            return new CityInfoDTO(cityInfo);
        } finally {
            em.close();
        }
    }

    public CityInfoDTO getCityByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo c WHERE c.city = :name", CityInfo.class);
            query.setParameter("name", name);
            CityInfo cityInfo = query.getSingleResult();
            return new CityInfoDTO(cityInfo);
        } finally {
            em.close();
        }
    }

    public List<Integer> getDanishZipCodes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Integer> query = em.createQuery("SELECT c.zipCode FROM CityInfo c WHERE c.zipCode > 999 AND c.zipCode NOT BETWEEN 3900 AND 3999 AND c.zipCode < 10000", Integer.class);
            List<Integer> danishZipCode = query.getResultList();
            return danishZipCode;
        } finally {
            em.close();
        }
    }

    public CityInfoDTO getCityByZipCode(int zipcode) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo c WHERE c.zipCode = :zipcode", CityInfo.class);
            query.setParameter("zipcode", zipcode);
           // List<CityInfo> cityInfoList = query.getResultList();
            CityInfo cityInfo = query.getSingleResult();
            return new CityInfoDTO(cityInfo);
        } finally {
            em.close();
        }
    }


}


