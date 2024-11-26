package pe.edu.I202216675.crd;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pe.edu.I202216675.Entidades.City;
import pe.edu.I202216675.Entidades.Country;
import pe.edu.I202216675.Entidades.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class JPAPersist {

    public static void main(String[] args) {
        // Crear el EntityManagerFactory y EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cl1-jpa-pu");
        EntityManager em = emf.createEntityManager();

        try {
            // Iniciar la transacción
            em.getTransaction().begin();

            // Crear el país imaginario
            Country newCountry = new Country();
            newCountry.setCode("PEC");
            newCountry.setName("FranImagi");
            newCountry.setContinent("Asia");
            newCountry.setRegion("RegionNuev");
            newCountry.setPopulation(1000000);

            // Crear las ciudades
            City city1 = new City();
            city1.setName("Ciuda1");
            city1.setPopulation(500000);
            city1.setCountry(newCountry);

            City city2 = new City();
            city2.setName("Ciuda2");
            city2.setPopulation(300000);
            city2.setCountry(newCountry);

            City city3 = new City();
            city3.setName("Ciuda3");
            city3.setPopulation(200000);
            city3.setCountry(newCountry);

            // Crear los lenguajes
            Language lang1 = new Language();
            lang1.setId(1); // Asignar manualmente el ID
            lang1.setLanguage("Lengua1");
            lang1.setOfficial("T");
            lang1.setCountry(newCountry);

            Language lang2 = new Language();
            lang2.setId(2); // Asignar manualmente el ID
            lang2.setLanguage("Ingles");
            lang2.setOfficial("F");
            lang2.setCountry(newCountry);

            // Asociar las ciudades y lenguajes al país
            newCountry.setCities(Arrays.asList(city1, city2, city3));
            newCountry.setLanguages(Arrays.asList(lang1, lang2));

            // Persistir el país
            em.persist(newCountry);

            // Confirmar la transacción
            em.getTransaction().commit();
            System.out.println("País imaginario registrado exitosamente");

        } catch (Exception e) {
            // Manejo de errores y revertir la transacción si es necesario
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();

        } finally {
            // Cerrar el EntityManager
            em.close();
            emf.close();
        }
    }
}
