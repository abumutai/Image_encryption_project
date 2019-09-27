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
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
public class Test2 {
     public static Connection getConnection(){
     
        Connection conn = null;
        try {
           conn=  DriverManager.getConnection("jdbc:mysql://localhost/testing", "root", "");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return conn;
    }

 public void testing(String files,String messages) throws FileNotFoundException{
       PreparedStatement ps;
       ResultSet rs;
         InputStream file= new FileInputStream(new File(files));
       String query="INSERT INTO filemanager(files,messages)VALUES(?,?)";
        try {
            ps=getConnection().prepareStatement(query);
            ps.setBlob(1, file);
            ps.setString(2, messages);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex);
        }    
   }
 public void tests(String key) throws FileNotFoundException, IOException{
     PreparedStatement ps = null;
     ResultSet rs;
     String messages=null;
     String query="SELECT * FROM filemanager WHERE messages=?";
         try {
             ps=getConnection().prepareStatement(query);
             ps.setString(1,key);
             rs=ps.executeQuery();
             if(rs.next()){
                 messages="incorrect";
             
                  query= "SELECT * FROM fileManager WHERE messages=?";
                try {
                    ps=getConnection().prepareStatement(query);
                    ps.setString(1, key);
                    rs=ps.executeQuery();
                    if(rs.next()){
                       File file=new File("C:\\Users\\SUDO\\Desktop\\image1.png");
                       FileOutputStream fos=new FileOutputStream(file);
                        byte[] img;
                        Blob blob= rs.getBlob("files");
                        img=blob.getBytes(1,(int)blob.length());
                        fos.write(img);
                        
                       
                    }
                    else{
                       JOptionPane.showMessageDialog(null,"Could find image","ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Encrypted.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
         } catch (SQLException ex) {
             Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
         }
 }
}