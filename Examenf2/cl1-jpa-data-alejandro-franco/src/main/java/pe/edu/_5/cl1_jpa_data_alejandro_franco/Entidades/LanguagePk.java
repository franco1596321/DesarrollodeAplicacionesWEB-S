package pe.edu._5.cl1_jpa_data_alejandro_franco.Entidades;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Embeddable@Data@NoArgsConstructor@AllArgsConstructor
public class LanguagePk {
    private String language;
    private String country;}
