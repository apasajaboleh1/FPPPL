/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class Harga_Tol {
    private static final String dbURL = "jdbc:mysql://localhost:3306/etool";
    private static final String username = "root";
    private static final String password = "";
    public static int[] gethargaandid(int data,String tujuan,String asal)
    {
        int[] hasildata=new int[3];
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            String sql="SELECT id_harga,harga FROM harga_tol where golongan=? and tujuan=? and asal=?";
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setString(2, tujuan);
            statement.setInt(1, data);
            statement.setString(3, asal);
            //System.out.println(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                int id_harga=result.getInt(1);
                hasildata[0]=id_harga;
                int harga=result.getInt(2);
                hasildata[1]=harga;
                //System.out.println(hasildata[0]+" "+hasildata[1]+"\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Harga_Tol.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return hasildata;
    }
}
