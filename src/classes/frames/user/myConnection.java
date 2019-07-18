/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.frames.user;

/**
 *
 * @author MUTAI
 */
import classes.*;
import java.sql.Connection;
import java.sql.DriverManager;
public class myConnection {
     public static Connection getConnection(){
     
        Connection conn = null;
        try {
           conn=  DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return conn;
    }

}
