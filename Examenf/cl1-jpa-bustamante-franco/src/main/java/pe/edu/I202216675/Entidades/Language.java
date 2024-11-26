package pe.edu.I202216675.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "countrylanguage")
public class Language {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "Language")
    private String language;

    @Column(name = "IsOfficial")
    private String isOfficial;

    @ManyToOne
    @JoinColumn(name = "CountryCode", referencedColumnName = "Code")
    private Country country;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String isOfficial() {
        return isOfficial;
    }

    public void setOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
