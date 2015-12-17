/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Freddy
 */
public class History_Transaksi {
    private static final String dbURL = "jdbc:mysql://localhost:3306/etool";
    private static final String username = "root";
    private static final String password = "";
    
    public static boolean masukdata(int id_pengguna,int id_harga,int dataoperator) {
        boolean hasildata=false;
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            String sql = "insert into history_transaksi(id_pengguna,id_harga,tanggal_transaksi,id_operator) values(?,?,now(),?)";
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setInt(1, id_pengguna);
            statement.setInt(2, id_harga);
            statement.setInt(3, dataoperator);
            //System.out.println(statement);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0)
            {
                hasildata=true;
            }
        }
       catch (SQLException ex) {
            Logger.getLogger(History_Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasildata;
    }

    public History_Transaksi(int datapengguna, int datatol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
