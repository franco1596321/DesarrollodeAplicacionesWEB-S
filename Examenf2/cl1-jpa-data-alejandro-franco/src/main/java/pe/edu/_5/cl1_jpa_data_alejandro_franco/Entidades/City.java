package pe.edu._5.cl1_jpa_data_alejandro_franco.Entidades;

import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name = "city") @Data
@NoArgsConstructor @AllArgsConstructor
public class City {
    @EmbeddedId
    private CityPk cityPk;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Population")
    private int population;
    @ManyToOne
    @JoinColumn(name = "CountryCode")
    @MapsId("country")
    private Country country;}