package com.mycompany.app.src;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Dao<T> {
   public Connection connection = null;
   public Statement statement = null;

   public abstract void init();
   public abstract List<T> getAllPersonnes();
   public abstract void addPersonne(T personne);
   public abstract void deletePersonne(T personne);
}