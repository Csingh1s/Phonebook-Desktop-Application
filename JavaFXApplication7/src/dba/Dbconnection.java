package dba;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chand
 */
public class Dbconnection {
    public static  Connection pmtconnection() 
    {
        Connection conn= null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;DatabaseName=Phonebook;username=sa;password=chandra#721";
            conn=DriverManager.getConnection(url);
        } catch (ClassNotFoundException |SQLException ex) {
            Logger.getLogger(Dbconnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Not working");
        }
        
        return conn;
    }

    }

