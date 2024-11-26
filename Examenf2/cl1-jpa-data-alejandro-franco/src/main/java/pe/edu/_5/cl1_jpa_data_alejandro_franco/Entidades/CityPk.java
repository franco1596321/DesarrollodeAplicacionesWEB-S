package pe.edu._5.cl1_jpa_data_alejandro_franco.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data@Embeddable@NoArgsConstructor@AllArgsConstructor
public class CityPk {
    private int id;
    private String country;}
