/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Katarzyna
 */
public class FillTable {
    
    
    public void FillHours(JTable tabela, ResultSet rs)throws SQLException{

        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        String godz1, godz2, godz3, godz4;
        int i=10;
                    
        Object[] row = new Object[i];

        while(rs.next()){
            
            godz1 = rs.getString("godzina1");
            godz2 = rs.getString("godzina2");
            godz3 = rs.getString("godzina3");
            godz4 = rs.getString("godzina4");
            
            String pelnaGodzina = godz1+":"+godz2+"-"+godz3+":"+godz4;
            //System.out.println("pelnaGodzina: " + pelnaGodzina);

            row[0] = pelnaGodzina;
            
            model.addRow(row); //dodajemy rząd do tabeli
            tabela.setModel(model); //ustawiamy nowy model tabeli
            model.fireTableDataChanged();
        }

    }
    
    public void FillCell(JTable tabela, ResultSet rs) throws SQLException{
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        IdentifyId sprawdzId = new IdentifyId();
        
        while(rs.next()) {
            String przedmiot_id = rs.getString("plan_przedmiot");
            String nauczyciel_id = rs.getString("plan_nauczyciel");
            String sala_id = rs.getString("plan_sala");
            String klasa_id = rs.getString("plan_klasa");
            String dzien = rs.getString("plan_dzien");
            String godzina_id = rs.getString("plan_godzina");
            
            String nazwaPrzedmiotu = sprawdzId.SprawdzString(przedmiot_id, "Przedmioty");
            String nazwaNauczyciela = sprawdzId.SprawdzString(nauczyciel_id, "Nauczyciele");
            String nazwaSali = sprawdzId.SprawdzString(sala_id, "Sale1");
            String nazwaKlasy = sprawdzId.SprawdzString(klasa_id, "Klasy");
            String pelnaGodzina = sprawdzId.SprawdzString(godzina_id, "Godzina");
            
            System.out.println(nazwaPrzedmiotu + " " + nazwaNauczyciela + " " + nazwaSali);
            
            String wstawString = nazwaPrzedmiotu + " " + nazwaNauczyciela + " " + nazwaSali;
            
            if(dzien.equals("poniedziałek")) {
                System.out.println("Pierwszy if działa");
                
                for(int i=0; i<10; i++) {

                    if(pelnaGodzina.equals(model.getValueAt(i, 0))) {
                        model.setValueAt(wstawString, i, 1);
                        tabela.setModel(model);
                        System.out.println("Drugi if działa");
                    }
                }
            }
            if (dzien.equals("wtorek")) {
                for(int i=0; i<10; i++) {
                    if(pelnaGodzina.equals(model.getValueAt(i, 0))) {
                        model.setValueAt(wstawString, i, 2);
                        tabela.setModel(model);
                        System.out.println("Sprawdzam drugi warunek");
                    }
                }
            }
            if (dzien.equals("środa")) {
                for(int i=0; i<10; i++) {

                    if(pelnaGodzina.equals(model.getValueAt(i, 0))) {
                        model.setValueAt(wstawString, i, 3);
                        tabela.setModel(model);
                    }
                }
            }
            if (dzien.equals("czwartek")) {
                for(int i=0; i<10; i++) {

                    if(pelnaGodzina.equals(model.getValueAt(i, 0))) {
                        model.setValueAt(wstawString, i, 4);
                        tabela.setModel(model);
                    }
                }
            }
            if (dzien.equals("piątek")) {
                for(int i=0; i<10; i++) {

                    if(pelnaGodzina.equals(model.getValueAt(i, 0))) {
                        model.setValueAt(wstawString, i, 5);
                        tabela.setModel(model);
                    }
                }
            } 
            
            
 
        }
        
        
    }
}
