package pe.edu._5.cl1_jpa_data_alejandro_franco.Repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu._5.cl1_jpa_data_alejandro_franco.Entidades.Country;
import pe.edu._5.cl1_jpa_data_alejandro_franco.Entidades.Language;

public interface CountryRepository extends CrudRepository<Country,String> {
}
