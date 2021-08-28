package com.moon.formationBatch.mappers;

import com.moon.formationBatch.domaine.Formateur;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;


// on crée une classe qui va inserer les données dans notre BDD en implementant  ItemPreparedStatementSetter
public class FormateurItemPreparedStatementSetter implements ItemPreparedStatementSetter<Formateur> {

    public static final String FORMATEURS_INSERT_QUERY = "insert into formateurs (id,nom, prenom,adresse_email) VALUES (?,?,?,?);";
    @Override
    public void setValues(Formateur formateur, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1,formateur.getId());
        preparedStatement.setString(2,formateur.getNom());
        preparedStatement.setString(3,formateur.getPrenom());
        preparedStatement.setString(4,formateur.getAdresseEmail());
    }
}
