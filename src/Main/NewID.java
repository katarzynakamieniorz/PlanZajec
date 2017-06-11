/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.DataBase.url;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Katarzyna
 */
public class NewID {
    int number1 = 0;
    int number2;
    
    public int UstawNumer(String id_klasy, String tabela){

        Connection conn = DataBase.Connection();
        PreparedStatement pst;
        ResultSet rs;
        
        String wyszukaj = "select max(" + id_klasy + ") from " + tabela;

        try{
            pst = conn.prepareStatement(wyszukaj);
            rs = pst.executeQuery();

            if(rs.next()) {
                number1 = rs.getInt(1);
            }
            number2 = number1 + 1;
            pst.close();
            rs.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        } 

        return number2;
    }
    //po chuja to skopiowałaś????
    public int UstawNumerNauczyciela(String id_nau, String tabela){

        Connection conn = DataBase.Connection();
        PreparedStatement pst;
        ResultSet rs;
        
        String wyszukaj = "select max(" + id_nau + ") from " + tabela;

        try{
            pst = conn.prepareStatement(wyszukaj);
            rs = pst.executeQuery();

            if(rs.next()) {
                number1 = rs.getInt(1);
            }
            number2 = number1 + 1;
            pst.close();
            rs.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        } 

        return number2;
    }
}
