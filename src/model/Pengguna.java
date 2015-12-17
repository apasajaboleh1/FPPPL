/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.*;
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
public class Pengguna {
    private static final String dbURL = "jdbc:mysql://localhost:3306/etool";
    private static final String username = "root";
    private static final String password = "";
    
    public static int[] getidandsaldo(String data) 
    {
        int[] hasildata= new int[3];
        try
        {
            File file= new File(data);
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
		int saldo = result.getInt(2);
                hasildata[1]=saldo;
                }
            } catch (SQLException ex) {
            Logger.getLogger(Pengguna.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("Image not found" + e);	
        } 
        
        
        
    return hasildata;
    }

    public Pengguna(String input) {
        try {
            File file=new File(input);
            FileInputStream imageInFile = new FileInputStream(file);
            try (Connection conn = DriverManager.getConnection(dbURL, username, password)) 
            {
                String sql="insert into pengguna(nama_pengguna,alamat_pengguna,saldo,barcode_data) values (?,?,?,?)";
                PreparedStatement statement=conn.prepareStatement(sql);
                statement.setString(1, "Freddy Hermawan Yuwono");
                statement.setString(2, "Manyar Tirtoasri 4 ");
                statement.setInt(3, 200000);
                statement.setBinaryStream(1, imageInFile,(int) file.length());
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0)
                {
                    System.out.println("A new user was inserted successfully!");
                }
            } 
            catch (SQLException ex) {
                Logger.getLogger(Pengguna.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(Pengguna.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pengguna.class.getName()).log(Level.SEVERE, null, ex);
        }
        
	
                
    }
    public static boolean updatesaldo(int id_pengguna,int harga,int saldo)
    {
        boolean hasildata=false;
        if(saldo-harga<0)return hasildata;
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) 
        {
            String sql="Update pengguna set saldo=? where id_pengguna=?";
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setInt(1, saldo-harga);
            statement.setInt(2, id_pengguna);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
		hasildata=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pengguna.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasildata;
    }
    public static int getsaldo(int id_pengguna)
    {
        int saldo_pengguna = 0;
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            String sql = "SELECT saldo FROM Pengguna WHERE id_pengguna= ?";
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setInt(1, id_pengguna);
            //System.out.println(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()){
		int saldo = result.getInt(1);
                saldo_pengguna=saldo;         
            }
        }   catch (SQLException ex) {
            Logger.getLogger(Pengguna.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saldo_pengguna;
    }
}
