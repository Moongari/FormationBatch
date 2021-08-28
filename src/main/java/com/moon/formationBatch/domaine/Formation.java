package com.moon.formationBatch.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement
public class Formation {

    private String code;
    private String libelle;
    private String descriptif;

}
