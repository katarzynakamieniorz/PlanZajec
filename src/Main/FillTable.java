/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Katarzyna
 */
public class FillTable {
    
    public void FillHours(JTable tabela, ResultSet rs)throws SQLException{
        //ResultSetMetaData meta = rs.getMetaData(); 
        //int kolumny = 1; //meta.getColumnCount();
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        String godz1, godz2, godz3, godz4;
        int i=10;

        while(rs.next()){
            godz1 = rs.getString("godzina1");
            godz2 = rs.getString("godzina2");
            godz3 = rs.getString("godzina3");
            godz4 = rs.getString("godzina4");
            
            String pelnaGodzina = godz1+":"+godz2+"-"+godz3+":"+godz4;
            System.out.println("pelnaGodzina: " + pelnaGodzina);
            
            Object[] row = new Object[i];
            row[0] = pelnaGodzina;
            
            model.addRow(row); //dodajemy rząd do tabeli
            tabela.setModel(model); //ustawiamy nowy model tabeli
            model.fireTableDataChanged();
        
        }
        
    }
    
    public void FillLessons(JTable tabela, ResultSet rs) throws SQLException {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        
        while(rs.next()){
            
        }
    }
    
}
