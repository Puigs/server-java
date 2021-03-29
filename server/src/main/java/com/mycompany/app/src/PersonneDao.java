package com.mycompany.app.src;

import com.mycompany.app.src.Dao;
import com.mycompany.app.src.Personne;
import java.util.*;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonneDao extends Dao<Personne> {

    private List<Personne> personnes = new ArrayList();

    public void init() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            this.statement = this.connection.createStatement();
            this.statement.setQueryTimeout(30);
            this.statement.executeUpdate("drop table if exists person");
            this.statement.executeUpdate("create table person (nom string, prenom string)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Personne> getAllPersonnes() {
        try {
            ResultSet rs = statement.executeQuery("select * from person");
            while(rs.next())
            {
                System.out.println("nom = " + rs.getString("nom"));
                System.out.println("prenom = " + rs.getString("prenom"));
                Personne obj = new PersonneBuilder().set_nom(rs.getString("nom")).set_prenom(rs.getString("prenom")).build();
                this.personnes.add(obj);
            }
            return personnes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnes;
    }

    public void addPersonne(Personne personne) {
        try {
            statement.executeUpdate("insert into person values('"+personne.get_nom()+"', '"+personne.get_prenom()+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePersonne(Personne personne) {
        try {
            statement.executeUpdate("delete from person where nom = '"+personne.get_nom()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}