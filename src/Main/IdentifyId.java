/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Katarzyna
 */
public class IdentifyId {
    
    ResultSet result = null;
    PreparedStatement preper = null;
    Connection conn = null;
    
    // metoda sprawdza jaka jest wartość dla danego numeru ID w podanej tabeli
    public String SprawdzString(String numer_id, String tabela) throws SQLException{
        conn = DataBase.Connection();
        
        String nazwa = "coś jest nie tak";
        
        String wybierzPrzedmiot = "select prz_name from przedmioty where prz_id like '" + numer_id + "'";
        String wybierzNauczyciela = "select nau_imie, nau_nazwisko from nauczyciele where nau_id like '" + numer_id + "'";
        String wybierzSale = "select sal_numer from sale1 where sal_id like '" + numer_id + "'";
        String wybierzKlase = "select kla_nazwa from klasy where kla_id like '" + numer_id + "'";
        
        String wybierzGodz = "select godzina1, godzina2, godzina3, godzina4 from godzina where id_godz like '"+ numer_id + "'";

       
        if(tabela.equals("Przedmioty")) {
            preper = conn.prepareStatement(wybierzPrzedmiot);
            result = preper.executeQuery();
            String przedmiot = result.getString("prz_name");
            return przedmiot;
        }
        if(tabela.equals("Nauczyciele")) { 
            preper = conn.prepareStatement(wybierzNauczyciela);
            result = preper.executeQuery();
            String nauImie = result.getString("nau_imie");
            String nauNazw = result.getString("nau_nazwisko");
            return nauNazw + " " + nauImie;
        }
        if(tabela.equals("Sale1")) {
            preper = conn.prepareStatement(wybierzSale);
            result = preper.executeQuery();
            String sala = result.getString("sal_numer");
            return sala;
        }
        if(tabela.equals("Klasy")) {
            preper = conn.prepareStatement(wybierzKlase);
            result = preper.executeQuery();
            String klasa = result.getString("kla_nazwa");
            return klasa;
        }
        if(tabela.equals("Godzina")) {
            preper = conn.prepareStatement(wybierzGodz);
            result = preper.executeQuery();
            String godz1 = result.getString("godzina1");
            String godz2 = result.getString("godzina2");
            String godz3 = result.getString("godzina3");
            String godz4 = result.getString("godzina4");
            String pelnaGodz = godz1 + ":" + godz2 + "-" + godz3 + ":" + godz4;
            return pelnaGodz;
        }
        result.close();
        return nazwa;
    }
    
    //metoda sprawdza jakie jest ID dla podanej tabeli i podanej wartości
    public String SprawdzId(String nazwa, String tabela) throws SQLException {
        String numer_id = "0";
        conn = DataBase.Connection();
        
        String wybierzKlase = "select * from Klasy where kla_nazwa like '"+ nazwa +"'";
        
        if(tabela.equals("Klasy")) {
            preper = conn.prepareStatement(wybierzKlase);
            result = preper.executeQuery();
            String numer_klasy = result.getString("kla_id");
            return numer_klasy;
        }
        
        return numer_id;
    }
    
}
