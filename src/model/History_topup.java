/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Freddy
 */
public class History_topup {
    private static final String dbURL = "jdbc:mysql://localhost:3306/etool";
    private static final String username = "root";
    private static final String password = "";
    public static boolean topup(String input, int saldo)
    {
        int hasildata[]=new int[3];
        boolean hasil=false;
        try{
            File file= new File(input);
            FileInputStream imageInFile = new FileInputStream(file);
            try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
                String sql = "SELECT id_pengguna,saldo FROM Pengguna WHERE barcode_data = ?";
		PreparedStatement statement=conn.prepareStatement(sql);
                statement.setBinaryStream(1, imageInFile,(int) file.length());
              //          System.out.println(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                int id= result.getInt(1);
                hasildata[0]=id;
		int tempsaldo = result.getInt(2);
                hasildata[1]=tempsaldo;
                }
            } catch (SQLException ex) {
                Logger.getLogger(History_topup.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("Image not found" + e);	
        } 
        
        boolean insertdata=false;
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            String sql="insert into history_popup(id_pengguna,jumlah_topup,waktu) values(?,?,now())";
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setInt(1,hasildata[0] );
            statement.setInt(2, saldo);
            int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0)
                {
                    insertdata=true;
                    //System.out.println("A new user was inserted successfully!");
                }
        } catch (SQLException ex) {
            Logger.getLogger(History_topup.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean updatedata=false;
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            String sql="update pengguna set saldo=? where id_pengguna=?";
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setInt(1, saldo+hasildata[1]);
            statement.setInt(2, hasildata[0]);
            //System.out.println(statement);
            int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0)
                {
                    updatedata=true;
                    //System.out.println("A new user was inserted successfully!");
                }
        } catch (SQLException ex) {
            Logger.getLogger(History_topup.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(updatedata&&insertdata)hasil=true;
        return hasil;
    }
}
