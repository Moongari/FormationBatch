package com.moon.formationBatch.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Formateur {

    private Integer id;
    private String nom;
    private String prenom;
    private String adresseEmail;

}
