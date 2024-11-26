package pe.edu.I202216675.crd;

import jakarta.persistence.*;
import pe.edu.I202216675.Entidades.Country;
import java.util.List;

public class JPAFind {

    public static void main(String[] args) {

        // Crear el EntityManagerFactory y EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cl1-jpa-pu");
        EntityManager em = emf.createEntityManager();

        try {
            // Iniciar la transacción
            em.getTransaction().begin();


            Country peru = em.find(Country.class, "PER");

            if (peru != null) {
                // Verificar ciudades y filtrar por población > 700,000
                if (peru.getCities() != null) {
                    List<String> cityNames = peru.getCities().stream()
                            .filter(city -> city.getPopulation() > 700000)
                            .map(city -> city.getName())  // Obtener solo los nombres
                            .toList();

                    if (cityNames.isEmpty()) {
                        System.out.println("No se encontraron ciudades peruanas con población > 700,000.");
                    } else {
                        System.out.println("Ciudades peruanas con población > 700,000:");
                        cityNames.forEach(System.out::println);
                    }
                } else {
                    System.out.println("No se encontraron ciudades para el país 'PER'.");
                }
            } else {
                System.out.println("El país con el código 'PER' no fue encontrado.");
            }

            // transacción
            em.getTransaction().commit();

        } catch (Exception e) {

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
