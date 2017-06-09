/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author potoc
 */
public class DataBase {
            static String url = "jdbc:sqlite:Planzajec.db";
            Connection conn = null;
            ResultSet rs = null;
            PreparedStatement pst = null;
      
     public static Connection Connection()
     {
                try
                {
                  Class.forName("org.sqlite.JDBC");
                  Connection conn=DriverManager.getConnection(url);
                  return conn;
                }
                  catch (ClassNotFoundException | SQLException e)
                {
                   JOptionPane.showMessageDialog(null,e.getMessage());
                    return null;
                }
     }
     public static void CreateTableLogin()
     {
         String sql = "CREATE TABLE IF NOT EXISTS Login (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL UNIQUE,\n"
                + "     pass text NOT NULL UNIQUE\n"
                + ");";
      
          try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement())
           {
            
            stmt.execute(sql);
            // stmt.close();
           }
           catch (SQLException e) 
           {
            System.out.println(e.getMessage());
           }
    }
    
     public static void InsertIntoLogin(String name, String pass)
     {
         String sql1 = "SELECT COUNT(*) AS rowcount FROM Login";  
         String sql2 = "INSERT INTO Login(name,pass) VALUES(?,?)";
       
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql2)) 
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql1);
            rs.next();
            int count = rs.getInt("rowcount");
            rs.close();
            
            if (count != 3)
            {
                pstmt.setString(1, name);
                pstmt.setString(2, pass);
                pstmt.executeUpdate();
            }
           // stmt.close();
        } 
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
     }
     
     public static void CreateTableClass(){
         String createTableKlasy = "CREATE TABLE IF NOT EXISTS klasy (\n"
                 +"kla_id integer primary key not null \n"
                 +"kla_nazwa text \n"
                 +"kla_wychowawca integer unique);";
         
         try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
             
                stmt.execute(createTableKlasy);
                //stmt.close();
                
         } catch(SQLException e){
             System.out.println(e.getMessage());
         }
         
     }
     
     public static void CreateTablePrzedmioty()
     {
         String sql = "CREATE TABLE IF NOT EXISTS Przedmioty (\n"
                + "	Prz_ID integer NOT NULL PRIMARY KEY,\n"
                + "	Prz_NAME text NOT NULL,\n"
                + ");";
         try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement())
           { 
            stmt.execute(sql);
           }
           catch (SQLException e) 
           {
            System.out.println(e.getMessage());
           }
     }
     
      public static void CreateTableTeacher(){
         String createTableNauczyciel = "CREATE TABLE IF NOT EXISTS NAUCZYCIELE (\n"
                 +"NAU_ID integer primary key not null \n"
                 +"NAU_IMIE text \n"
                 +"NAU_NAZWISKO text \n"
                 +"PRZ_NAME_1 text \n"
                 +"PRZ_NAME_2 text \n"
                 +"PRZ_NAME_3 text \n"
                 +"PRZ_NAME_4 text \n"
                 +"PRZ_NAME_5 text \n;";
         
         try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()){
             stmt.execute(createTableNauczyciel);
             stmt.close();
         } catch(SQLException e){
             System.out.println(e.getMessage());
         }
         
     }
      
    /**
     *
     */
    public static void CreateTableSale()
     {
         String sql = "CREATE TABLE IF NOT EXISTS Sale (\n"
                + "	sal_id integer PRIMARY KEY NOT NULL UNIQUE,\n"
                + "	sal_numer text NOT NULL UNIQUE,\n"
                + "     sal_rodzaj integer NOT NULL \n"
                + ");";
      
          try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement())
           {
            
            stmt.execute(sql);
            // stmt.close();
           }
           catch (SQLException e) 
           {
               
            System.out.println(e.getMessage());
           }
          
    }
       
        public static void CreateTablePlan()
     {
         String sql = "CREATE TABLE IF NOT EXISTS Planzajec4 (\n"
                + "	plan_id integer PRIMARY KEY NOT NULL UNIQUE,\n"
                + "	plan_klasa integer NOT NULL ,\n"
                + "     plan_dzien string NOT NULL, \n"
                + "     plan_godzina integer NOT NULL, \n"              
                + "     plan_przedmiot integer NOT NULL, \n"
                 + "    plan_nauczyciel integer NOT NULL, \n"
                + "     plan_sala integer NOT NULL \n"
                + ");";
      
          try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement())
           {
            
            stmt.execute(sql);
            // stmt.close();
           }
           catch (SQLException e) 
           {
               
            System.out.println(e.getMessage());
           }
          
    }
          
    
      
}

