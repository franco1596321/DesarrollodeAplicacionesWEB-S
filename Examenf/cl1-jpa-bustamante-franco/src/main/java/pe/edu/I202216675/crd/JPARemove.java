package pe.edu.I202216675.crd;
import pe.edu.I202216675.Entidades.Country;
import pe.edu.I202216675.Entidades.City;
import pe.edu.I202216675.Entidades.Language;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPARemove {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cl1-jpa-pu");
        EntityManager em = emf.createEntityManager();

        try {
            // iniciar la transacción
            em.getTransaction().begin();


            Country country = em.find(Country.class, "IMA");

            if (country != null) {

                System.out.println("Eliminando país: " + country.getName());
                System.out.println("Ciudades asociadas: " + country.getCities().size());
                System.out.println("Lenguajes asociados: " + country.getLanguages().size());

                // eliminar las entidades Language asociadas
                for (Language language : country.getLanguages()) {
                    em.remove(language);
                }

                // Eliminar las entidades City asociadas
                for (City city : country.getCities()) {
                    em.remove(city);
                }


                em.remove(country);
                System.out.println("El país y sus ciudades y lenguajes fueron eliminados.");
            } else {
                System.out.println("El país con el código 'IMA' no fue encontrado.");
            }

            // confirmar la transacción
            em.getTransaction().commit();

        } catch (Exception e) {
            //revertir la transacción si es necesario
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar el país: " + e.getMessage());
            e.printStackTrace();

        } finally {
            // Cerrar el EntityManager
            em.close();
            emf.close();
        }
    }
}
