package pe.edu._5.cl1_jpa_data_alejandro_franco.Entidades;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Data
@NoArgsConstructor@AllArgsConstructor
@Entity@Table(name = "country")
public class Country {
    @Id
    @Column(name = "Code", length = 3)
    private String code;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Continent")
    private String continent;
    @Column(name = "Region")
    private String region;
    @Column(name = "Population")
    private int population;
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<City> cities;
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Language> languages;
}