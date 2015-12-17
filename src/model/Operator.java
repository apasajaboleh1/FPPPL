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
public class Operator {
    private static final String dbURL = "jdbc:mysql://localhost:3306/etool";
    private static final String username = "root";
    private static final String password = "";
    public static int loginuser (String usernamedata,String passworddata)
    {
        int hasil=0;
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            
            String sql="Select id_operator from operator where username=? and password=md5(?)";
            PreparedStatement statement=conn.prepareStatement(sql);
            statement.setString(1, usernamedata);
            statement.setString(2, passworddata);
            //System.out.println(statement);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                hasil=result.getInt(1);
            }
        } catch (SQLException ex) {
           Logger.getLogger(Operator.class.getName()).log(Level.SEVERE, null, ex);
       }
        return hasil;
    }
}
