package com.mycompany.app;

import com.mycompany.app.src.Server;
import com.mycompany.app.src.Personne;
import com.mycompany.app.src.PersonneBuilder;
import com.mycompany.app.src.PersonneDao;
import com.mycompany.app.src.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
        if (args.length < 1) {
            System.out.println("Miss one argument");
            return;
        }
        int port = Integer.parseInt(args[0]);

        Server server = Server.getInstance();
        server.init(port);
        Dao<Personne> my_dao = new PersonneDao();
        my_dao.init();
        while(true) {
            String str = server.do_read();
            if (str != "")
                System.out.println("The str is : " + str);
                String mots[] = str.split(":");
                Personne obj = new PersonneBuilder().set_nom(mots[0]).set_prenom(mots[1]).build();
                my_dao.addPersonne(obj);
                my_dao.getAllPersonnes();
                my_dao.deletePersonne(obj);
                my_dao.getAllPersonnes();
            }
    }
}
