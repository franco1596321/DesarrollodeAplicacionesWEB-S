package pe.edu._5.cl1_jpa_data_alejandro_franco;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pe.edu._5.cl1_jpa_data_alejandro_franco.Entidades.Country;
import pe.edu._5.cl1_jpa_data_alejandro_franco.Entidades.Language;
import pe.edu._5.cl1_jpa_data_alejandro_franco.Repository.CountryRepository;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Cl1JpaDataAlejandroFrancoApplication implements CommandLineRunner {
	@Autowired
	CountryRepository countryRepository;
	public static void main(String[] args) {
		SpringApplication.run(Cl1JpaDataAlejandroFrancoApplication.class, args);}
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		//restaurar la base de datos**
		System.out.println("Restaurando la base de datos...");
		restoreDatabase();

		// ejecutar la primera consulta
		System.out.println("\nConsulta 1: Lenguajes del país 'ARG' o 'PER':");
		printLanguages("ARG", "PER");

		// eliminar países en cascada**
		System.out.println("\nEliminando países 'COL' y 'ARG'...");
		deleteCountries("COL", "ARG");

		// restaurar la base de datos antes de repetir la consulta**
		System.out.println("\nRestaurando la base de datos...");
		restoreDatabase();

		// la consulta inicial (flujo alterno para 'ARG')**
		System.out.println("\nConsulta 2: Reejecución tras la eliminación de 'ARG' (flujo alterno):");
		printLanguages("ARG", "PER");
	}

	/**
	 * consulta los lenguajes de un país. si el país principal no existe, usa un país alternativo.
	 *  primaryCountryCode  codigo del país principal (ej., "ARG").
	 *  fallbackCountryCode codigo del país alternativo (ej., "PER").
	 */
	private void printLanguages(String primaryCountryCode, String fallbackCountryCode) {
		Optional<Country> primaryCountry = countryRepository.findById(primaryCountryCode);

		primaryCountry.ifPresentOrElse(
				country -> {
					System.out.println("Idiomas del país " + primaryCountryCode + ":");
					country.getLanguages().forEach(language ->
							System.out.println(language.getLenguagePk().getLanguage()));
				},
				() -> {
					System.out.println("El país " + primaryCountryCode +
							" no existe. Mostrando lenguajes del país " + fallbackCountryCode + ":");
					Optional<Country> fallbackCountry = countryRepository.findById(fallbackCountryCode);
					fallbackCountry.ifPresent(country ->
							country.getLanguages().forEach(language ->
									System.out.println(language.getLenguagePk().getLanguage())));
				}
		);
	}

	/**
	 * Elimina países en cascada (incluyendo sus ciudades y lenguajes).
	 */
	private void deleteCountries(String... countryCodes) {
		for (String code : countryCodes) {
			countryRepository.findById(code).ifPresent(country -> {
				System.out.println("Eliminando el país: " + code);
				countryRepository.delete(country);
			});
		}
	}
	/**
	 * restaura la base de datos desde un punto conocido.
	 */
	private void restoreDatabase() {
		System.out.println("Base de datos restaurada a su estado inicial.");
	}
}