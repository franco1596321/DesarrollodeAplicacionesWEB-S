package pe.edu._5.cl1_jpa_data_alejandro_franco.Entidades;

import jakarta.persistence.*;
import lombok.*;
@Data @NoArgsConstructor@AllArgsConstructor
@Entity@Table(name = "countrylanguage")
public class Language {

    @EmbeddedId
    private LanguagePk lenguagePk;

    @Column(name = "ID")
    private int id;


    @Column(name = "IsOfficial", nullable = false)
    private Boolean isOfficial;

    @ManyToOne
    @JoinColumn(name ="CountryCode")
    @MapsId("country")
    private Country country;}

