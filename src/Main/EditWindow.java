/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.DataBase.Connection;
import static Main.DataBase.CreateTableSale;
import static Main.DataBase.url;
import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Katarzyna
 */
public class EditWindow extends javax.swing.JFrame {

    /**
     * Creates new form EditWindow
     */
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public EditWindow() {

        conn = DataBase.Connection();
        initComponents();
        
        
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "cleanPanel");

        Fillcombo();
        FillCombo2();
        FillComboTeachers();
        FillComboEditTeachers();
        FillComboTeacherinEditGroups();
        Fillgroup();  
        //FillComboTime();
        //FillCombosale();
    }
    
    // Funkcja czyszcząca pola w trybie tworzenia nowego nauczyciela
    public void reset(){
      
        nameField.setText("");
        lastnameFiled.setText("");
        
        jComboBox10.setSelectedIndex(0);
        jComboBox11.setSelectedIndex(0);
        jComboBox12.setSelectedIndex(0);
        jComboBox13.setSelectedIndex(0);
        jComboBox14.setSelectedIndex(0);
        classNumberField.setText(" ");
        buttonGroup1.clearSelection();
    }
    
    // Funkcja generująca nowe Id dla każdego nowego nauczyciela
    public void resetId()
    {
        NewID noweId = new NewID();
        int teacherId = noweId.UstawNumerNauczyciela("Nau_ID","Nauczyciele");
        
        String ajdi = Integer.toString(teacherId);
        idField.setText(ajdi);
    }

    private void Fillcombo() {
        try {
            String sql = "select * from przedmioty";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                String Prz_NAME = rs.getString("Prz_NAME");
                jComboBox9.addItem(Prz_NAME);
                jComboBox1.addItem(Prz_NAME);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void Fillgroup()
    {
        try {
            String group = "select * from klasy order by kla_nazwa ASC";
            pst = conn.prepareStatement(group);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                String nazwa = rs.getString("kla_nazwa");
               
                classcombo.addItem(nazwa);
                groupList.addItem(nazwa);
                jComboBox5.addItem(nazwa);
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 

    }

    private void FillCombo2() {

        try {

            String sql = "select * from Przedmioty";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                String name = rs.getString("Prz_NAME");
                jComboBox10.addItem(name);
                jComboBox11.addItem(name);
                jComboBox12.addItem(name);
                jComboBox13.addItem(name);
                jComboBox14.addItem(name);
                sub1.addItem(name);
                sub2.addItem(name);
                sub3.addItem(name);
                sub4.addItem(name);
                sub5.addItem(name);
                
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    private void FillComboTeachers() {
        try {
            String wyszukajNauczycieli = "select * from nauczyciele order by Nau_Nazwisko ASC";
            pst = conn.prepareStatement(wyszukajNauczycieli);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                String nazwisko = rs.getString("nau_nazwisko");
                String imie = rs.getString("nau_imie");
                String imieNazwisko = nazwisko + " " + imie;
                teacherBox.addItem(imieNazwisko);
                teacherList.addItem(imieNazwisko);
                jComboBox8.addItem(imieNazwisko);
                comboteacher.addItem(imieNazwisko);
               
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    private void FillComboTime()
    {
        try {
            String godz = "select * from Godzina";
            pst = conn.prepareStatement(godz);
            rs = pst.executeQuery();
            while(rs.next()) {
                String godz1 = rs.getString("godzina1");
                String godz2 = rs.getString("godzina2");
                String godz3 = rs.getString("godzina3");
                String godz4 = rs.getString("godzina4");
                String pelnaGodz = godz1 + ":" + godz2 + "-" + godz3 + ":" + godz4;
                timeCombo.addItem(pelnaGodz);
                listHour.addItem(pelnaGodz);
                hourList.addItem(pelnaGodz);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
        
    }
       private void FillCombosale()
    {
        try {
            String godz = "select * from Sale1";
            pst = conn.prepareStatement(godz);
            rs = pst.executeQuery();
            while(rs.next()) {
                String sala = rs.getString("sal_numer");
               
              
                classList.addItem(sala);
           
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
        
    }
    private void FillEditGroupItems()
{

     try
    {
        String classname = (String)classcombo.getSelectedItem();
        String sqlgroup = "Select * from klasy where kla_nazwa = '"+classname+"'";
         pst = conn.prepareStatement(sqlgroup);
            rs = pst.executeQuery();
            
        while(rs.next()) {
                int ID = rs.getInt("kla_id");
                String idstr = Integer.toString(ID);
                String nameclass = rs.getString("kla_nazwa");
                int IDwych = rs.getInt("kla_wychowawca");
                String idwych = Integer.toString(IDwych);
                
                textid.setText(idstr);
                classtext.setText(nameclass);
                
                
                
                
        } 
    }
     catch (SQLException ex) {
            Logger.getLogger(EditWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    private void FillEditTimeItems()
{

     try
    {
        String tajm = (String)timeCombo.getSelectedItem();
        String sqltajm = "Select * from Godzina where godzina1 ||':' || godzina2 ||'-' || godzina3 ||':' || godzina4 = '"+tajm+"'";
         pst = conn.prepareStatement(sqltajm);
            rs = pst.executeQuery();
            
        while(rs.next()) {
                int ID = rs.getInt("ID_godz");
                String idstr = Integer.toString(ID);
                String godz1 = rs.getString("godzina1");
                String godz2 = rs.getString("godzina2");
                String godz3 = rs.getString("godzina3");
                String godz4 = rs.getString("godzina4");
                String pelnaGodz = godz1 + ":" + godz2 + "-" + godz3 + ":" + godz4;
                idTimeField.setText(idstr);
                timeCombo.setSelectedItem(pelnaGodz);
        } 
    }
     catch (SQLException ex) {
            Logger.getLogger(EditWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     
private void FillEditTeachersItems()
{
    try
    {
        String NameSurname = (String)teacherscombo.getSelectedItem();
        String sql23 = "Select * from nauczyciele where Nau_Nazwisko ||' ' || Nau_Imie = '"+NameSurname+"'";
         pst = conn.prepareStatement(sql23);
            rs = pst.executeQuery();
            
        while(rs.next()) {
                int ID = rs.getInt("Nau_Id");
                String idstr = Integer.toString(ID);
                String nameteacher = rs.getString("Nau_Imie");
                String surnameteacher = rs.getString("Nau_Nazwisko");
                String subname1 = rs.getString("Prz_NAME_1");
                String subname2 = rs.getString("Prz_NAME_2");
                String subname3 = rs.getString("Prz_NAME_3");
                String subname4 = rs.getString("Prz_NAME_4");
                String subname5 = rs.getString("Prz_NAME_5");
                idtextfield.setText(idstr);
                nametext.setText(nameteacher);
                surnametext.setText(surnameteacher);
                sub1.setSelectedItem(subname1);
                sub2.setSelectedItem(subname2);
                sub3.setSelectedItem(subname3);
                sub4.setSelectedItem(subname4);
                sub5.setSelectedItem(subname5);
                
                
        } 
    }        
    
      catch (SQLException ex) {
            Logger.getLogger(EditWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
}
private void CleanEditTeachersItems()
{
     idtextfield.setText("");
            nametext.setText("");
            surnametext.setText("");
            teacherscombo.setSelectedIndex(0);
            sub1.setSelectedIndex(0);
            sub2.setSelectedIndex(0);
            sub3.setSelectedIndex(0);
            sub4.setSelectedIndex(0);
            sub5.setSelectedIndex(0);
    
}
private void CleanEditGroupItems()
{
     textid.setText("");
            classtext.setText("");
            classcombo.setSelectedIndex(0);
            comboteacher.setSelectedIndex(0);
            
    
}
private void CleanEditTime()
{
    idTimeField.setText("");
//    timeCombo.setSelectedIndex(0);
    hourList.setSelectedItem(0);
    listHour.setSelectedItem(0);
}
 private void FillComboEditTeachers() {
     

        try {
            String wyszukajNauczycieli = "select * from nauczyciele order by Nau_Nazwisko ASC";
            pst = conn.prepareStatement(wyszukajNauczycieli);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                String nazwisko = rs.getString("nau_nazwisko");
                String imie = rs.getString("nau_imie");
                String imieNazwisko = nazwisko + " " + imie;
                teacherscombo.addItem(imieNazwisko);
                //comboteacher.addItem(imieNazwisko);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    
 }
  private void FillComboTeacherinEditGroups() {
     

        try {
            
            String wyszukajNauczycieli = "SELECT * FROM nauczyciele LEFT JOIN klasy ON klasy.kla_wychowawca=nauczyciele.NAU_ID where klasy.kla_wychowawca IS NULL ";
            pst = conn.prepareStatement(wyszukajNauczycieli);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                String nazwisko = rs.getString("nau_nazwisko");
                String imie = rs.getString("nau_imie");
                String imieNazwisko = nazwisko + " " + imie;
               
                comboteacher.addItem(imieNazwisko);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    
 }
  
  private void Fillcombogodziny(){
       try{
           
            String a=klasapole.getText();
            String dzientyg = dzien.getText();
            hourList.removeAllItems();
            String klasaid = "Select * from klasy where kla_nazwa = '"+a+"'";
            pst=conn.prepareStatement(klasaid);
            rs=pst.executeQuery();
          
            
                int idklasa = rs.getInt("kla_id");
              String klaska = klasapole.getText();
        
         
        
                  String sql123 ="select ID_godz from Godzina EXCEPT select plan_godzina from Planzajec4 where plan_klasa = '"+idklasa+"' and plan_dzien = '"+dzientyg+"'";
                 pst=conn.prepareStatement(sql123);
            rs=pst.executeQuery();
            while(rs.next()){
                int tytul1=rs.getInt("ID_godz");
                String godzina = Integer.toString(tytul1);
                          hourList.addItem(godzina);
         
            
            
            }

            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Wybierz klase!", "Error", JOptionPane.ERROR_MESSAGE);
    }finally {
        try{
            rs.close();
            pst.close();
        }
        catch(Exception e){
    }
    }
                
            
    
    }
  private void Fillcombonauczyciel(){
      try{
           
            String przedmiot=przedmiotpole.getText();
            String dzientyg = dzien.getText();
            teacherList.removeAllItems();
            String tmp =(String)hourList.getSelectedItem();
       
            //String klasaid = "Select * from Nauczyciele where Prz_NAME_1 = '"+przedmiot+"' OR Prz_NAME_2 = '"+przedmiot+"' OR Prz_NAME_3 = '"+przedmiot+"' OR Prz_NAME_4 = '"+przedmiot+"' OR Prz_NAME_5 = '"+przedmiot+"'";
           
           
          String klasaid = "Select Nau_ID from Nauczyciele where Prz_NAME_1 = '"+przedmiot+"' OR Prz_NAME_2 = '"+przedmiot+"' OR Prz_NAME_3 = '"+przedmiot+"' OR Prz_NAME_4 = '"+przedmiot+"' OR Prz_NAME_5 = '"+przedmiot+"' EXCEPT select plan_nauczyciel from Planzajec4 where plan_dzien = '"+dzientyg+"' and plan_godzina = '"+tmp+"'";
            pst=conn.prepareStatement(klasaid);
            rs=pst.executeQuery();
          while(rs.next()){
                int idklasa = rs.getInt("Nau_ID");
                String nauczyciel = "Select * from Nauczyciele where Nau_ID = '"+idklasa+"'";
                pst=conn.prepareStatement(nauczyciel);
            rs=pst.executeQuery();
             while(rs.next()){
                 String imie = rs.getString("Nau_Imie");
                 String nazw = rs.getString("Nau_Nazwisko");
                 String full = imie + " " + nazw;
               
                teacherList.addItem(full);
              
            }
          }
//                String sql123 ="select ID_godz from Godzina EXCEPT select plan_godzina from Planzajec2 where plan_klasa = '"+idklasa+"' and plan_dzien = '"+dzientyg+"'";
//                 pst=conn.prepareStatement(sql123);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                int tytul1=rs.getInt("ID_godz");
//                String godzina = Integer.toString(tytul1);
//                          hourList.addItem(godzina);
//            }
            

            
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Wybierz przedmiot!", "Error", JOptionPane.ERROR_MESSAGE);
    }finally {
        try{
            rs.close();
            pst.close();
        }
        catch(Exception e){
    }
    }
  }
   private void Fillcombosala(){
      try{
           
            String przedmiot=przedmiotpole.getText();
            String dzientyg = dzien.getText();
            classList.removeAllItems();
            String tmp =(String)hourList.getSelectedItem();
       
            //String klasaid = "Select * from Nauczyciele where Prz_NAME_1 = '"+przedmiot+"' OR Prz_NAME_2 = '"+przedmiot+"' OR Prz_NAME_3 = '"+przedmiot+"' OR Prz_NAME_4 = '"+przedmiot+"' OR Prz_NAME_5 = '"+przedmiot+"'";
           
           
          String klasaid = "Select sal_id from Sale1 EXCEPT select plan_sala from Planzajec4 where plan_dzien = '"+dzientyg+"' and plan_godzina = '"+tmp+"'";
            pst=conn.prepareStatement(klasaid);
            rs=pst.executeQuery();
          while(rs.next()){
                int idklasa = rs.getInt("sal_id");
//                String nauczyciel = "Select * from Sale where sal_id = '"+idklasa+"'";
//                pst=conn.prepareStatement(nauczyciel);
//            rs=pst.executeQuery();
//             while(rs.next()){
//                 String imie = rs.getString("sal_numer");
//                 String nazw = rs.getString("sal_rodzaj");
                 String full = Integer.toString(idklasa);
               
                classList.addItem(full);
              
           // }
          }
//                String sql123 ="select ID_godz from Godzina EXCEPT select plan_godzina from Planzajec2 where plan_klasa = '"+idklasa+"' and plan_dzien = '"+dzientyg+"'";
//                 pst=conn.prepareStatement(sql123);
//            rs=pst.executeQuery();
//            while(rs.next()){
//                int tytul1=rs.getInt("ID_godz");
//                String godzina = Integer.toString(tytul1);
//                          hourList.addItem(godzina);
//            }
            

            
        }catch(SQLException e){
          JOptionPane.showMessageDialog(null, "Puste pole!", "Error", JOptionPane.ERROR_MESSAGE);
    }finally {
        try{
            rs.close();
            pst.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Puste pole!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
  }

   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        teacherPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lastnameFiled = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        addTeacherButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        jComboBox10 = new javax.swing.JComboBox();
        jComboBox11 = new javax.swing.JComboBox();
        jComboBox12 = new javax.swing.JComboBox();
        jComboBox13 = new javax.swing.JComboBox();
        jComboBox14 = new javax.swing.JComboBox();
        Bwyczysc = new javax.swing.JButton();
        groupPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        classField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        teacherBox = new javax.swing.JComboBox<>();
        addClassButton = new javax.swing.JButton();
        cleanPanel = new javax.swing.JPanel();
        teacherPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lessonPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        groupList = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        classList = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        teacherList = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        hourList = new javax.swing.JComboBox<>();
        previewPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        a = new javax.swing.JTextField();
        addLessonBtn = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        godzina1 = new javax.swing.JTextField();
        godzina2 = new javax.swing.JTextField();
        godzina3 = new javax.swing.JTextField();
        godzina4 = new javax.swing.JTextField();
        przedmiotpole = new javax.swing.JTextField();
        imiepole1 = new javax.swing.JTextField();
        salapole = new javax.swing.JTextField();
        dzien = new javax.swing.JTextField();
        dzientygodnia = new javax.swing.JComboBox<>();
        klasapole = new javax.swing.JTextField();
        classPanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        classNumberField = new javax.swing.JTextField();
        gymCheckBox = new javax.swing.JCheckBox();
        itCheckBox = new javax.swing.JCheckBox();
        addClassBtn = new javax.swing.JButton();
        normalCheckBox = new javax.swing.JCheckBox();
        CzyscButtonSale = new javax.swing.JButton();
        idtext = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        classcombo = new javax.swing.JComboBox<>();
        deleteButton = new javax.swing.JButton();
        Zapisz = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        textid = new javax.swing.JTextField();
        anulujbutton = new javax.swing.JButton();
        classtext = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        comboteacher = new javax.swing.JComboBox<>();
        changeTeacher = new javax.swing.JPanel();
        teacher = new javax.swing.JLabel();
        teacherscombo = new javax.swing.JComboBox<>();
        deletebutton = new javax.swing.JButton();
        subject = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        sub1 = new javax.swing.JComboBox<>();
        sub2 = new javax.swing.JComboBox<>();
        sub3 = new javax.swing.JComboBox<>();
        sub4 = new javax.swing.JComboBox<>();
        sub5 = new javax.swing.JComboBox<>();
        nametext = new javax.swing.JTextField();
        surnametext = new javax.swing.JTextField();
        name = new javax.swing.JLabel();
        surname = new javax.swing.JLabel();
        anulujbtn = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        idtextfield = new javax.swing.JTextField();
        changeLesson = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        mondayRadioBtn1 = new javax.swing.JRadioButton();
        tuesdayRadioBtn1 = new javax.swing.JRadioButton();
        wednesdayRadioBtn1 = new javax.swing.JRadioButton();
        thursdayRadioBtn1 = new javax.swing.JRadioButton();
        fridayRadioBtn1 = new javax.swing.JRadioButton();
        jLabel24 = new javax.swing.JLabel();
        listHour = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        edit2Button = new javax.swing.JButton();
        delete2Button = new javax.swing.JButton();
        previewPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        timePanel = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        addTime = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        time1 = new javax.swing.JTextField();
        time2 = new javax.swing.JTextField();
        time3 = new javax.swing.JTextField();
        time4 = new javax.swing.JTextField();
        changeTime = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        timeCombo = new javax.swing.JComboBox<>();
        deleteTimeButton = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        idTimeField = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        newMenu = new javax.swing.JMenu();
        groupItem1 = new javax.swing.JMenuItem();
        teachersItem1 = new javax.swing.JMenuItem();
        classItem1 = new javax.swing.JMenuItem();
        lessonItem = new javax.swing.JMenuItem();
        timeItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        groupItem2 = new javax.swing.JMenuItem();
        teachersItem2 = new javax.swing.JMenuItem();
        lessonItem2 = new javax.swing.JMenuItem();
        TimeItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new java.awt.CardLayout());

        teacherPanel.setToolTipText("");
        teacherPanel.setOpaque(false);

        jLabel1.setLabelFor(nameField);
        jLabel1.setText("Wprowadź dane nowego nauczyciela");

        jLabel2.setText("Imię");

        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

        jLabel3.setLabelFor(lastnameFiled);
        jLabel3.setText("Nazwisko");

        lastnameFiled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastnameFiledActionPerformed(evt);
            }
        });

        jLabel4.setText("Przedmioty");

        addTeacherButton.setText("Dodaj");
        addTeacherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTeacherButtonActionPerformed(evt);
            }
        });

        jLabel8.setText("ID");

        idField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idFieldActionPerformed(evt);
            }
        });

        jComboBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox10ActionPerformed(evt);
            }
        });

        jComboBox11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox11ActionPerformed(evt);
            }
        });

        jComboBox12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox12ActionPerformed(evt);
            }
        });

        jComboBox13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox13ActionPerformed(evt);
            }
        });

        jComboBox14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox14ActionPerformed(evt);
            }
        });

        Bwyczysc.setText("Wyczyść");
        Bwyczysc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BwyczyscActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout teacherPanelLayout = new javax.swing.GroupLayout(teacherPanel);
        teacherPanel.setLayout(teacherPanelLayout);
        teacherPanelLayout.setHorizontalGroup(
            teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teacherPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(teacherPanelLayout.createSequentialGroup()
                        .addGroup(teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(teacherPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lastnameFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(teacherPanelLayout.createSequentialGroup()
                                .addGroup(teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(teacherPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(39, 39, 39)
                        .addGroup(teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addTeacherButton, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(Bwyczysc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(564, Short.MAX_VALUE))
        );
        teacherPanelLayout.setVerticalGroup(
            teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teacherPanelLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addTeacherButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(teacherPanelLayout.createSequentialGroup()
                        .addGroup(teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(1, 1, 1)))
                .addGroup(teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastnameFiled, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bwyczysc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(216, Short.MAX_VALUE))
        );

        mainPanel.add(teacherPanel, "teacherPanel");

        groupPanel.setPreferredSize(new java.awt.Dimension(596, 328));

        jLabel5.setText("Dodaj nową klasę");

        jLabel6.setText("Klasa");

        jLabel7.setText("Wychowawca");

        teacherBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherBoxActionPerformed(evt);
            }
        });

        addClassButton.setText("Dodaj");
        addClassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClassButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout groupPanelLayout = new javax.swing.GroupLayout(groupPanel);
        groupPanel.setLayout(groupPanelLayout);
        groupPanelLayout.setHorizontalGroup(
            groupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(groupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(groupPanelLayout.createSequentialGroup()
                        .addGroup(groupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(groupPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(classField))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, groupPanelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(teacherBox, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(58, 58, 58)
                        .addComponent(addClassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(609, Short.MAX_VALUE))
        );
        groupPanelLayout.setVerticalGroup(
            groupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(groupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addClassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(groupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(teacherBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(401, Short.MAX_VALUE))
        );

        mainPanel.add(groupPanel, "groupPanel");

        javax.swing.GroupLayout cleanPanelLayout = new javax.swing.GroupLayout(cleanPanel);
        cleanPanel.setLayout(cleanPanelLayout);
        cleanPanelLayout.setHorizontalGroup(
            cleanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1044, Short.MAX_VALUE)
        );
        cleanPanelLayout.setVerticalGroup(
            cleanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );

        mainPanel.add(cleanPanel, "cleanPanel");

        jLabel9.setText("Wybierz nauczyciela którego dane chcesz edytować");

        javax.swing.GroupLayout teacherPanel2Layout = new javax.swing.GroupLayout(teacherPanel2);
        teacherPanel2.setLayout(teacherPanel2Layout);
        teacherPanel2Layout.setHorizontalGroup(
            teacherPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teacherPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(738, Short.MAX_VALUE))
        );
        teacherPanel2Layout.setVerticalGroup(
            teacherPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teacherPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(483, Short.MAX_VALUE))
        );

        mainPanel.add(teacherPanel2, "teacherPanel2");

        jLabel10.setText("Dodaj nową lekcję");

        jLabel11.setText("Klasa");

        groupList.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                groupListPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        groupList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupListActionPerformed(evt);
            }
        });

        jLabel12.setText("Sala");

        classList.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                classListPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        classList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classListActionPerformed(evt);
            }
        });

        jLabel13.setText("Nauczyciel");

        teacherList.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                teacherListPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        teacherList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherListActionPerformed(evt);
            }
        });

        jLabel14.setText("Dzień");

        jLabel15.setText("Godzina");

        hourList.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                hourListPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        hourList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hourListActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("tutaj będzie się znajdował podgląd planu uaktualniany po \ndodaniu nowej lekcji");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout previewPanelLayout = new javax.swing.GroupLayout(previewPanel);
        previewPanel.setLayout(previewPanelLayout);
        previewPanelLayout.setHorizontalGroup(
            previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(previewPanelLayout.createSequentialGroup()
                .addGroup(previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(previewPanelLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(previewPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        previewPanelLayout.setVerticalGroup(
            previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(previewPanelLayout.createSequentialGroup()
                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        addLessonBtn.setText("Dodaj");
        addLessonBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLessonBtnActionPerformed(evt);
            }
        });

        jLabel28.setText("Przedmiot");

        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        dzien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dzienActionPerformed(evt);
            }
        });

        dzientygodnia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Poniedzialek", "Wtorek", "Środa", "Czwartek", "Piątek" }));
        dzientygodnia.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                dzientygodniaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout lessonPanelLayout = new javax.swing.GroupLayout(lessonPanel);
        lessonPanel.setLayout(lessonPanelLayout);
        lessonPanelLayout.setHorizontalGroup(
            lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lessonPanelLayout.createSequentialGroup()
                .addComponent(addLessonBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(lessonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lessonPanelLayout.createSequentialGroup()
                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(lessonPanelLayout.createSequentialGroup()
                                .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(lessonPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(groupList, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(lessonPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(41, 41, 41)
                                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(lessonPanelLayout.createSequentialGroup()
                                                .addComponent(dzientygodnia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(lessonPanelLayout.createSequentialGroup()
                                                .addComponent(hourList, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(godzina1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(22, 22, 22)
                                .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(przedmiotpole, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(salapole, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(lessonPanelLayout.createSequentialGroup()
                                        .addComponent(godzina2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(godzina3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(godzina4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(dzien, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(14, 14, 14))
                    .addGroup(lessonPanelLayout.createSequentialGroup()
                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(lessonPanelLayout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(classList, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(lessonPanelLayout.createSequentialGroup()
                                        .addComponent(teacherList, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(119, 119, 119)
                                        .addComponent(imiepole1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 27, Short.MAX_VALUE))
                    .addGroup(lessonPanelLayout.createSequentialGroup()
                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(lessonPanelLayout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(previewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lessonPanelLayout.createSequentialGroup()
                    .addGap(404, 404, 404)
                    .addComponent(klasapole, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(540, Short.MAX_VALUE)))
        );
        lessonPanelLayout.setVerticalGroup(
            lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lessonPanelLayout.createSequentialGroup()
                .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(lessonPanelLayout.createSequentialGroup()
                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(lessonPanelLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(groupList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addComponent(jLabel14)
                                .addGap(27, 27, 27)
                                .addComponent(dzientygodnia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69))
                            .addGroup(lessonPanelLayout.createSequentialGroup()
                                .addComponent(dzien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)))
                        .addGap(18, 18, 18)
                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(hourList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(godzina1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(godzina4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(godzina3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(godzina2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(przedmiotpole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addComponent(previewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teacherList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imiepole1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salapole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(addLessonBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lessonPanelLayout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(klasapole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(448, Short.MAX_VALUE)))
        );

        mainPanel.add(lessonPanel, "lessonPanel");

        classPanel.setPreferredSize(new java.awt.Dimension(762, 434));
        classPanel.setRequestFocusEnabled(false);

        jLabel16.setText("Dodaj nową salę");

        jLabel17.setText("Numer sali");

        classNumberField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classNumberFieldActionPerformed(evt);
            }
        });

        buttonGroup1.add(gymCheckBox);
        gymCheckBox.setText("sala gimnastyczna");
        gymCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gymCheckBoxActionPerformed(evt);
            }
        });

        buttonGroup1.add(itCheckBox);
        itCheckBox.setText("sala komputerowa");

        addClassBtn.setText("Dodaj");
        addClassBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClassBtnActionPerformed(evt);
            }
        });

        buttonGroup1.add(normalCheckBox);
        normalCheckBox.setText("sala ogólna");
        normalCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                normalCheckBoxActionPerformed(evt);
            }
        });

        CzyscButtonSale.setText("Wyczyść");
        CzyscButtonSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CzyscButtonSaleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout classPanelLayout = new javax.swing.GroupLayout(classPanel);
        classPanel.setLayout(classPanelLayout);
        classPanelLayout.setHorizontalGroup(
            classPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(classPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(classPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itCheckBox)
                    .addComponent(gymCheckBox)
                    .addGroup(classPanelLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(97, 97, 97)
                        .addComponent(classNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(normalCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addGroup(classPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addClassBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(CzyscButtonSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(441, Short.MAX_VALUE))
        );
        classPanelLayout.setVerticalGroup(
            classPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(classPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(classPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(classNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addClassBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(CzyscButtonSale, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(normalCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gymCheckBox)
                .addGap(7, 7, 7)
                .addComponent(itCheckBox)
                .addContainerGap(272, Short.MAX_VALUE))
        );

        mainPanel.add(classPanel, "classPanel");

        jLabel18.setText("Wybierz klasę:");

        classcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Klasa" }));
        classcombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                classcomboItemStateChanged(evt);
            }
        });
        classcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classcomboActionPerformed(evt);
            }
        });

        deleteButton.setText("Usuń");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        Zapisz.setText("Zapisz");
        Zapisz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZapiszActionPerformed(evt);
            }
        });

        jLabel19.setText("Wybierz nowego wychowawcę:");

        textid.setEditable(false);
        textid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textidActionPerformed(evt);
            }
        });

        anulujbutton.setText("Anuluj");
        anulujbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anulujbuttonActionPerformed(evt);
            }
        });

        classtext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classtextActionPerformed(evt);
            }
        });

        jLabel21.setText("ID klasy");

        jLabel29.setText("Klasa");

        comboteacher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nauczyciel" }));
        comboteacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboteacherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout idtextLayout = new javax.swing.GroupLayout(idtext);
        idtext.setLayout(idtextLayout);
        idtextLayout.setHorizontalGroup(
            idtextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idtextLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(idtextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(idtextLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(idtextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(classcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboteacher, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(idtextLayout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(42, 42, 42)
                                .addGroup(idtextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(classtext, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textid, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(idtextLayout.createSequentialGroup()
                        .addComponent(Zapisz, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(anulujbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(617, Short.MAX_VALUE))
        );
        idtextLayout.setVerticalGroup(
            idtextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idtextLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(classcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(idtextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(textid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(idtextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(14, 14, 14)
                .addComponent(jLabel19)
                .addGap(26, 26, 26)
                .addComponent(comboteacher, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(idtextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anulujbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Zapisz, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(197, Short.MAX_VALUE))
        );

        mainPanel.add(idtext, "changeGroup");

        teacher.setText("Wybierz nauczyciela:");

        teacherscombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nauczyciel" }));
        teacherscombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                teacherscomboItemStateChanged(evt);
            }
        });
        teacherscombo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teacherscomboMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                teacherscomboMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                teacherscomboMousePressed(evt);
            }
        });
        teacherscombo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                teacherscomboComponentShown(evt);
            }
        });
        teacherscombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherscomboActionPerformed(evt);
            }
        });

        deletebutton.setText("Usuń");
        deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebuttonActionPerformed(evt);
            }
        });

        subject.setText("Zmień przedmioty:");

        saveButton.setText("Zapisz");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        sub1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sub1ActionPerformed(evt);
            }
        });

        sub3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sub3ActionPerformed(evt);
            }
        });

        surnametext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surnametextActionPerformed(evt);
            }
        });

        name.setText("Imię");

        surname.setText("Nazwisko");

        anulujbtn.setText("Anuluj");
        anulujbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anulujbtnActionPerformed(evt);
            }
        });

        jLabel20.setText("ID");

        idtextfield.setEditable(false);
        idtextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idtextfieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout changeTeacherLayout = new javax.swing.GroupLayout(changeTeacher);
        changeTeacher.setLayout(changeTeacherLayout);
        changeTeacherLayout.setHorizontalGroup(
            changeTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeTeacherLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(changeTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(changeTeacherLayout.createSequentialGroup()
                        .addComponent(teacher, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(changeTeacherLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(changeTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(changeTeacherLayout.createSequentialGroup()
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(anulujbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(deletebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 167, Short.MAX_VALUE))
                            .addGroup(changeTeacherLayout.createSequentialGroup()
                                .addGroup(changeTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(changeTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(sub5, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sub4, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sub3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sub2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sub1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(teacherscombo, javax.swing.GroupLayout.Alignment.LEADING, 0, 188, Short.MAX_VALUE))
                                    .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(changeTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(surname, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(name, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(18, 18, 18)
                        .addGroup(changeTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(changeTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(nametext, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                .addComponent(surnametext))
                            .addComponent(idtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(313, Short.MAX_VALUE))))
        );
        changeTeacherLayout.setVerticalGroup(
            changeTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeTeacherLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(teacher)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changeTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teacherscombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(idtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(subject)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changeTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sub1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(surnametext, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changeTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(changeTeacherLayout.createSequentialGroup()
                        .addComponent(sub2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sub3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sub4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sub5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(changeTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(deletebutton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(saveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(anulujbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(changeTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nametext, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(surname)))
                .addContainerGap(166, Short.MAX_VALUE))
        );

        mainPanel.add(changeTeacher, "changeTeacher");

        jLabel22.setText("Klasa:");

        jLabel23.setText("Dzień:");

        mondayRadioBtn1.setText("Poniedziałek");
        mondayRadioBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mondayRadioBtn1ActionPerformed(evt);
            }
        });

        tuesdayRadioBtn1.setText("Wtorek");

        wednesdayRadioBtn1.setText("Środa");
        wednesdayRadioBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wednesdayRadioBtn1ActionPerformed(evt);
            }
        });

        thursdayRadioBtn1.setText("Czwartek");
        thursdayRadioBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thursdayRadioBtn1ActionPerformed(evt);
            }
        });

        fridayRadioBtn1.setText("Piątek");

        jLabel24.setText("Godzina:");

        jLabel25.setText("Sala:");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });

        jLabel26.setText("Nauczyciel:");

        edit2Button.setText("Edytuj");
        edit2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit2ButtonActionPerformed(evt);
            }
        });

        delete2Button.setText("Usuń");
        delete2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete2ButtonActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setText("tutaj będzie się znajdował podgląd planu uaktualniany");
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout previewPanel1Layout = new javax.swing.GroupLayout(previewPanel1);
        previewPanel1.setLayout(previewPanel1Layout);
        previewPanel1Layout.setHorizontalGroup(
            previewPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(previewPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        previewPanel1Layout.setVerticalGroup(
            previewPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(previewPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        jLabel27.setText("Przedmiot:");

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wybierz--------" }));
        jComboBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout changeLessonLayout = new javax.swing.GroupLayout(changeLesson);
        changeLesson.setLayout(changeLessonLayout);
        changeLessonLayout.setHorizontalGroup(
            changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeLessonLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(changeLessonLayout.createSequentialGroup()
                        .addComponent(edit2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(delete2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(changeLessonLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(41, 41, 41)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(changeLessonLayout.createSequentialGroup()
                        .addGroup(changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, Short.MAX_VALUE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(listHour, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(changeLessonLayout.createSequentialGroup()
                                .addGroup(changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tuesdayRadioBtn1)
                                    .addComponent(mondayRadioBtn1)
                                    .addComponent(wednesdayRadioBtn1)
                                    .addComponent(thursdayRadioBtn1)
                                    .addComponent(fridayRadioBtn1))
                                .addGap(0, 38, Short.MAX_VALUE))
                            .addComponent(jComboBox9, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(previewPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        changeLessonLayout.setVerticalGroup(
            changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeLessonLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(changeLessonLayout.createSequentialGroup()
                        .addComponent(previewPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 123, Short.MAX_VALUE))
                    .addGroup(changeLessonLayout.createSequentialGroup()
                        .addGroup(changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(mondayRadioBtn1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tuesdayRadioBtn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wednesdayRadioBtn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thursdayRadioBtn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fridayRadioBtn1)
                        .addGap(16, 16, 16)
                        .addGroup(changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(listHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(24, 24, 24)
                        .addGroup(changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edit2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(delete2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        mainPanel.add(changeLesson, "changeLesson");

        jLabel30.setText("Wpisz przedział godziny lekcyjnej:");

        jLabel31.setText(":");

        jLabel32.setText("-");

        jLabel33.setText(":");

        addTime.setText("Dodaj");
        addTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTimeActionPerformed(evt);
            }
        });

        jLabel34.setText("Schemat: 00 : 00 - 00 : 00");

        javax.swing.GroupLayout timePanelLayout = new javax.swing.GroupLayout(timePanel);
        timePanel.setLayout(timePanelLayout);
        timePanelLayout.setHorizontalGroup(
            timePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timePanelLayout.createSequentialGroup()
                .addGroup(timePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(timePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(timePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(timePanelLayout.createSequentialGroup()
                                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(37, 37, 37))
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(timePanelLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(time1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(time2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel32)))
                .addGap(22, 22, 22)
                .addComponent(time3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(time4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(addTime, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(575, Short.MAX_VALUE))
        );
        timePanelLayout.setVerticalGroup(
            timePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timePanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(timePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(timePanelLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(12, 12, 12)
                        .addGroup(timePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addGroup(timePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(time2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(time1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, timePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(time3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(time4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(addTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jLabel34)
                .addContainerGap(376, Short.MAX_VALUE))
        );

        mainPanel.add(timePanel, "timePanel");

        jLabel35.setText("Wybierz godzinę, którą chcesz usunąć z planu");

        timeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Wybierz ---" }));
        timeCombo.setToolTipText("");
        timeCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                timeComboItemStateChanged(evt);
            }
        });
        timeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeComboActionPerformed(evt);
            }
        });

        deleteTimeButton.setText("Usuń");
        deleteTimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTimeButtonActionPerformed(evt);
            }
        });

        jLabel36.setText("ID");

        idTimeField.setEditable(false);
        idTimeField.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        idTimeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTimeFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout changeTimeLayout = new javax.swing.GroupLayout(changeTime);
        changeTime.setLayout(changeTimeLayout);
        changeTimeLayout.setHorizontalGroup(
            changeTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeTimeLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(changeTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(changeTimeLayout.createSequentialGroup()
                        .addComponent(timeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(deleteTimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(changeTimeLayout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(149, 149, 149)))
                .addContainerGap(701, Short.MAX_VALUE))
        );
        changeTimeLayout.setVerticalGroup(
            changeTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeTimeLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(changeTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(changeTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteTimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(376, Short.MAX_VALUE))
        );

        mainPanel.add(changeTime, "changeTime");

        newMenu.setText("Nowy");
        newMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newMenuMouseClicked(evt);
            }
        });
        newMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuActionPerformed(evt);
            }
        });

        groupItem1.setText("Grupy");
        groupItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupItem1ActionPerformed(evt);
            }
        });
        newMenu.add(groupItem1);

        teachersItem1.setText("Nauczyciele");
        teachersItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teachersItem1ActionPerformed(evt);
            }
        });
        newMenu.add(teachersItem1);

        classItem1.setText("Sale");
        classItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classItem1ActionPerformed(evt);
            }
        });
        newMenu.add(classItem1);

        lessonItem.setText("Lekcje");
        lessonItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessonItemActionPerformed(evt);
            }
        });
        newMenu.add(lessonItem);

        timeItem.setText("Godziny");
        timeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeItemActionPerformed(evt);
            }
        });
        newMenu.add(timeItem);

        jMenuBar1.add(newMenu);

        editMenu.setText("Edytuj");
        editMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editMenuMouseClicked(evt);
            }
        });

        groupItem2.setText("Grupy");
        groupItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupItem2ActionPerformed(evt);
            }
        });
        editMenu.add(groupItem2);

        teachersItem2.setText("Nauczyciele");
        teachersItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teachersItem2ActionPerformed(evt);
            }
        });
        editMenu.add(teachersItem2);

        lessonItem2.setText("Plan");
        lessonItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessonItem2ActionPerformed(evt);
            }
        });
        editMenu.add(lessonItem2);

        TimeItem.setText("Godzina");
        TimeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimeItemActionPerformed(evt);
            }
        });
        editMenu.add(TimeItem);

        jMenuBar1.add(editMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void groupItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupItem1ActionPerformed
        if (evt.getSource() == groupItem1) {
            CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel, "groupPanel");
        }
        
        reset();
    }//GEN-LAST:event_groupItem1ActionPerformed

    private void teachersItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teachersItem1ActionPerformed
        if (evt.getSource() == teachersItem1) {
            CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel, "teacherPanel");
            
            NewID noweId = new NewID();
            int teacherId = noweId.UstawNumerNauczyciela("Nau_ID","Nauczyciele");
        
            String ajdi = Integer.toString(teacherId);
            idField.setText(ajdi);
            
            idField.setEditable(false);

        }
    }//GEN-LAST:event_teachersItem1ActionPerformed

    private void groupItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupItem2ActionPerformed
        if (evt.getSource() == groupItem2) {
            CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel, "changeGroup");
            
            
        }
        reset();
    }//GEN-LAST:event_groupItem2ActionPerformed

    private void addLessonBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLessonBtnActionPerformed
 //String tmp1 =(String)classList.getSelectedItem();
 String tmp = klasapole.getText();
 String dzientygodnia1 = dzien.getText();
  String godzinaid =(String)classList.getSelectedItem();
  int godzinaidint = Integer.parseInt(godzinaid);
 String przedmiot = przedmiotpole.getText();
 String NameSurname = (String)teacherList.getSelectedItem();
    String salaid = (String)classList.getSelectedItem();
    int salaajdi = Integer.parseInt(salaid);
         
       try{
           String sql = "Select * from klasy where kla_nazwa = '" + tmp + "'";
           
                pst=conn.prepareStatement(sql);
                 rs=pst.executeQuery();
                 
           int idklasy =rs.getInt("kla_id");
          
//                     int idklasy1 = 4;
           String sql1 = "Select * from Przedmioty where Prz_NAME = '"+przedmiot+"'";    
               pst=conn.prepareStatement(sql1);
                 rs=pst.executeQuery();
             
             int przedmiotid =rs.getInt("Prz_ID");     
         
        String sql23 = "Select * from Nauczyciele where Nau_Nazwisko ||' ' || Nau_Imie = '"+NameSurname+"'";
         pst = conn.prepareStatement(sql23);
            rs = pst.executeQuery();   
             int nauczycielid =rs.getInt("Nau_ID");  
             a.setText(Integer.toString(nauczycielid));

       String sqlAddClass = "insert into Planzajec5 values ('2','" + idklasy + "','" + dzientygodnia1 + "','" + godzinaidint + "','" + przedmiotid + "','" + nauczycielid + "','" + salaajdi + "' );";
                    
     try (Statement stmt = conn.createStatement()) {
         stmt.executeUpdate(sqlAddClass);
     }
            pst.close();
            rs.close();
       }
       catch (Exception e){
           JOptionPane.showMessageDialog(null, "Uzupełnij odpowiednie dane!", "Error", JOptionPane.ERROR_MESSAGE);
       }
        
        
    }//GEN-LAST:event_addLessonBtnActionPerformed

    private void lessonItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessonItemActionPerformed
        if (evt.getSource() == lessonItem) {
            CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel, "lessonPanel");

        }
        
        reset();
    }//GEN-LAST:event_lessonItemActionPerformed

    private void classNumberFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classNumberFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_classNumberFieldActionPerformed

    private void gymCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gymCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gymCheckBoxActionPerformed

    private void addClassBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClassBtnActionPerformed
          Connection conn = DataBase.Connection();
          
        ResultSet rs;
        PreparedStatement pst;
        Statement stmt;
       
        NewID noweId = new NewID();
        int Idsali = noweId.UstawNumer("SAL_ID","SALE1");
        
        String ClassTyp = " ";
        if(gymCheckBox.isSelected()){
            ClassTyp = "WF";
        }
        else if (itCheckBox.isSelected()){
            ClassTyp = "IT";
        }
        else if (normalCheckBox.isSelected())
        {
            ClassTyp = "N";
        
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "Wybierz typ sali!", "Error", JOptionPane.ERROR_MESSAGE);
            }
         
        try {
            String ClassNumber = classNumberField.getText();
        String sqlAddClass = "insert into SALE1(SAL_ID, SAL_NUMER, SAL_RODZAJ) values \n"
                    + "('" + Idsali + "','" + ClassNumber + "','" + ClassTyp + "' );";
            stmt = conn.createStatement();

            stmt.executeUpdate(sqlAddClass);
            stmt.close();
           JOptionPane.showMessageDialog(null, "Dodano rekord do bazy");
                            
                    classNumberField.setText(" ");
                     buttonGroup1.clearSelection();
                   
                    
                    CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel, "classPanel");
              
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Uzupełnij odpowiednie dane!", "Error", JOptionPane.ERROR_MESSAGE);
        }
              
    }//GEN-LAST:event_addClassBtnActionPerformed

    private void classItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classItem1ActionPerformed
        if (evt.getSource() == classItem1) {
            CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel, "classPanel");
            
        }
        
        reset();
    }//GEN-LAST:event_classItem1ActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
    
        if(!"".equals(textid.getText()))
             {
                int p= JOptionPane.showConfirmDialog(null,"Czy na pewno chcesz usunąć rekord?","usun",JOptionPane.YES_NO_OPTION);
          
                 if(p == 0) 
                    
                    {
             
                       String sql = "delete from klasy where kla_id=?'";
                            
                        try
                           
                            {
                     
                           pst=conn.prepareStatement(sql);
                           pst.setString(1,textid.getText());
                           pst.execute();
                           JOptionPane.showMessageDialog(null, "Usunięto ! ");
                           int index = classcombo.getSelectedIndex();
                           classcombo.removeItemAt(index);
                           CleanEditGroupItems();
                         
                            
                            }           
                        catch (SQLException e){}
                        finally 
                        {
                                 try
                                 {
                                    rs.close();
                                    pst.close();
                                 }
                                catch(SQLException e){}

                         }
                    }
                else
                    {
                    CleanEditGroupItems();
                    }
            }       
         else
            {
             JOptionPane.showMessageDialog(null, "Wybierz grupę do usunięcia !");
             CleanEditGroupItems();
             }
                
       
      
         
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void ZapiszActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZapiszActionPerformed

        String NameSurname = (String)comboteacher.getSelectedItem(); 
        
        if("Nauczyciel".equals(NameSurname))
                {
                    JOptionPane.showMessageDialog(null, "Wybierz nowego nauczyciela!", "Error", JOptionPane.ERROR_MESSAGE);
                }
        else
        {
              String nameclass = classtext.getText();
                String Id = textid.getText();
                int id = Integer.parseInt(Id);
                
                try {
                   String sql1 = "Select * from nauczyciele where Nau_Nazwisko ||' ' || Nau_Imie = '"+NameSurname+"'";
                     pst = conn.prepareStatement(sql1);
                     rs = pst.executeQuery();
                    int ID = rs.getInt("nau_id");
                     pst.close();
                  String sql2 = "update klasy set kla_nazwa = '"+nameclass+"', kla_wychowawca = "+ID+"  where kla_id = "+id+"";
                       pst=conn.prepareStatement(sql2);
                           
                           pst.execute();       
               
                  JOptionPane.showMessageDialog(null, "Edytowano ! ");
                 int index = classcombo.getSelectedIndex();
                 classcombo.removeItemAt(index);
                 classcombo.addItem(nameclass);
                  CleanEditGroupItems();
                 
          
            
        }
               
                catch (SQLException e) {
              JOptionPane.showMessageDialog(null, "Uzupełnij wymagane pola lub wybierz innego wychowawcę!", "Error", JOptionPane.ERROR_MESSAGE);
            
                
       
             
        }
                 
        }
    }//GEN-LAST:event_ZapiszActionPerformed

    private void mondayRadioBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mondayRadioBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mondayRadioBtn1ActionPerformed

    private void wednesdayRadioBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wednesdayRadioBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wednesdayRadioBtn1ActionPerformed

    private void thursdayRadioBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thursdayRadioBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thursdayRadioBtn1ActionPerformed

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void edit2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit2ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit2ButtonActionPerformed

    private void delete2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete2ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delete2ButtonActionPerformed

    private void jComboBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox9ActionPerformed

    }//GEN-LAST:event_jComboBox9ActionPerformed

    private void hourListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hourListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hourListActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void teachersItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teachersItem2ActionPerformed
        if (evt.getSource() == teachersItem2) {
            CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel, "changeTeacher");
         
                   
            
        }
        reset();
    }//GEN-LAST:event_teachersItem2ActionPerformed

    private void lessonItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessonItem2ActionPerformed
        if (evt.getSource() == lessonItem2) {
            CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel, "changeLesson");
            
        }
        reset();
    }//GEN-LAST:event_lessonItem2ActionPerformed

    private void addClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClassButtonActionPerformed

        Connection conn = DataBase.Connection();
        ResultSet rs;
        PreparedStatement pst;
        Statement stmt;
        
        String wszyscyNauczyciele = "select * from nauczyciele";

        String className = classField.getText();
        String nauczyciel = teacherBox.getSelectedItem().toString();
        String idNauczyciela = null;
        
        NewID noweId = new NewID();
        int idklasy = noweId.UstawNumer("kla_id", "klasy");


        try {
            stmt = conn.createStatement();

            pst = conn.prepareStatement(wszyscyNauczyciele);
            rs = pst.executeQuery();
            
            while(rs.next()){
                String wyszukajNazwisko = rs.getString("nau_nazwisko");
                String wyszukajImie = rs.getString("nau_imie");
                String wyszukajImieNazwisko = wyszukajNazwisko + " " + wyszukajImie;
                
                if(wyszukajImieNazwisko.equals(nauczyciel)) {
                    
                    idNauczyciela = rs.getString("nau_id");
                    
                    String sqlAddClass = "insert into KLASY(KLA_ID, KLA_NAZWA, KLA_WYCHOWAWCA) values \n"
                    + "('" + idklasy + "','" + className + "','" + idNauczyciela + "' );";
        
                    stmt.executeUpdate(sqlAddClass);
                    
                    JOptionPane.showMessageDialog(null, "Dodano rekord do bazy");
                    classcombo.addItem(className);
                }
            }
            //stmt.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_addClassButtonActionPerformed

    private void teacherBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherBoxActionPerformed

    private void jComboBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox10ActionPerformed
        //String comboBox = jComboBox10.addItem("jeden");
        // JComboBox jComboBox10 = new JComboBox();
        // jComboBox10.addItem("asdf");
    }//GEN-LAST:event_jComboBox10ActionPerformed

    private void addTeacherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTeacherButtonActionPerformed

        Connection conn = DataBase.Connection();
        ResultSet rs;
        PreparedStatement pst;
        Statement stmt;

        
        //int teacherId = Integer.parseInt(teacherIdS);
        
        NewID noweId = new NewID();
        int teacherId = noweId.UstawNumerNauczyciela("Nau_ID","Nauczyciele");
        
        String ajdi = Integer.toString(teacherId);
        idField.setText(ajdi);
        
        //String teacherId = ajdi;
        
        
        String teacherName = nameField.getText();
        String teacherSurname = lastnameFiled.getText();

        Object prz1 = jComboBox10.getSelectedItem();
        String przedmiot1 = prz1.toString();
        Object prz2 = jComboBox11.getSelectedItem();
        String przedmiot2 = prz2.toString();
        Object prz3 = jComboBox12.getSelectedItem();
        String przedmiot3 = prz3.toString();
        Object prz4 = jComboBox13.getSelectedItem();
        String przedmiot4 = prz4.toString();
        Object prz5 = jComboBox14.getSelectedItem();
        String przedmiot5 = prz5.toString();

        String sqlAddTeacher = "insert into NAUCZYCIELE(NAU_ID, NAU_IMIE, NAU_NAZWISKO, PRZ_NAME_1, PRZ_NAME_2, PRZ_NAME_3, PRZ_NAME_4, PRZ_NAME_5) values \n"
                + "('" + teacherId + "','" + teacherName + "','" + teacherSurname + "','" + przedmiot1 + "','" + przedmiot2 + "','" + przedmiot3 + "','" + przedmiot4 + "','" + przedmiot5 + "' );";

        try {
            stmt = conn.createStatement();
            // stmt.execute(CreateTableTeacher());
            stmt.executeUpdate(sqlAddTeacher);
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Dodano rekord do bazy");
            teacherscombo.addItem(teacherName +" "+ teacherSurname);
            comboteacher.addItem(teacherName +" "+ teacherSurname);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        resetId();
        
        nameField.setText("");
        lastnameFiled.setText("");
        jComboBox10.setSelectedIndex(0);
        jComboBox11.setSelectedIndex(0);
        jComboBox12.setSelectedIndex(0);
        jComboBox13.setSelectedIndex(0);
        jComboBox14.setSelectedIndex(0);
    }//GEN-LAST:event_addTeacherButtonActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void jComboBox11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox11ActionPerformed

    private void jComboBox12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox12ActionPerformed

    private void jComboBox13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox13ActionPerformed

    private void jComboBox14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox14ActionPerformed

    private void normalCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_normalCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_normalCheckBoxActionPerformed

    private void classcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classcomboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_classcomboActionPerformed

    private void sub1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sub1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sub1ActionPerformed

    private void sub3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sub3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sub3ActionPerformed

    private void teacherscomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherscomboActionPerformed
        
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherscomboActionPerformed

    private void teacherscomboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherscomboMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_teacherscomboMouseClicked

    private void teacherscomboMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherscomboMousePressed
       
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherscomboMousePressed

    private void teacherscomboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_teacherscomboItemStateChanged
        JComboBox comboBox = (JComboBox) evt.getSource();

                 
                Object item = evt.getItem();
        if (evt.getStateChange() == ItemEvent.SELECTED) {
                     FillEditTeachersItems();
                     
                }
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherscomboItemStateChanged

    private void teacherscomboComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_teacherscomboComponentShown
      
// TODO add your handling code here:
    }//GEN-LAST:event_teacherscomboComponentShown

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
         

                String nameteacher = nametext.getText();
                String Id = idtextfield.getText();
                int id = Integer.parseInt(Id);
                String surnameteacher = surnametext.getText();
                String subname1 = (String)sub1.getSelectedItem();
                String subname2 = (String)sub2.getSelectedItem();
                String subname3 = (String)sub3.getSelectedItem();
                String subname4 = (String)sub4.getSelectedItem();
                String subname5 = (String)sub5.getSelectedItem();
             
            

              try {
                  String sql = "update nauczyciele set Nau_Imie = '"+nameteacher+"', Nau_Nazwisko = '"+surnameteacher+"', Prz_Name_1 = '"+subname1+"', Prz_Name_2 = '"+subname2+"', Prz_Name_3 = '"+subname3+"', Prz_Name_4 = '"+subname4+"', Prz_Name_5 = '"+subname5+"' where Nau_Id = '"+id+"'";
                  Statement stmt = conn.createStatement();
                  stmt.execute(sql);
                  stmt.close();

                  JOptionPane.showMessageDialog(null, "Edytowano ! ");
                  int index = teacherscombo.getSelectedIndex();
                  
                  
                  
                  String sql1 = "select * from nauczyciele where nau_id="+Id+"";
                  pst = conn.prepareStatement(sql1);
                  rs = pst.executeQuery(); 
                  while(rs.next())
                  {
                  String nazwisko = rs.getString("nau_nazwisko");
                  String imie = rs.getString("nau_imie");
                  String imieNazwisko = nazwisko + " " + imie;
                  teacherscombo.addItem(imieNazwisko);
                  comboteacher.addItem(imieNazwisko);
                  }
                  
                 
                  teacherscombo.removeItemAt(index); 
                  comboteacher.removeItemAt(index); 
                  CleanEditTeachersItems();
                 pst.close();
                 rs.close();
          
            
        } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, "Uzupełnij odpowiednie dane!", "Error", JOptionPane.ERROR_MESSAGE);
        } 
             
            

      
    
    

    }//GEN-LAST:event_saveButtonActionPerformed

    private void idtextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idtextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idtextfieldActionPerformed

    private void anulujbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulujbtnActionPerformed
       CleanEditTeachersItems();
        // TODO add your handling code here:
    }//GEN-LAST:event_anulujbtnActionPerformed

    private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebuttonActionPerformed
        if(!"".equals(idtextfield.getText()))
             {
                int p= JOptionPane.showConfirmDialog(null,"Czy na pewno chcesz usunąć rekord?","usun",JOptionPane.YES_NO_OPTION);
          
                 if(p == 0) 
                    
                    {
             
                      String sql = "delete from nauczyciele where Nau_Id =?";
                            
                        try
                           
                            {
                     
                           pst=conn.prepareStatement(sql);
                           pst.setString(1,idtextfield.getText());
                           pst.execute();
                           JOptionPane.showMessageDialog(null, "Usunięto ! ");
                           int index = teacherscombo.getSelectedIndex();
                            teacherscombo.removeItemAt(index); 
                           CleanEditTeachersItems();
                            
                            }           
                        catch (SQLException e){}
                        finally 
                        {
                                 try
                                 {
                                    rs.close();
                                    pst.close();
                                 }
                                catch(SQLException e){}

                         }
                    }
                 else 
                    {
                    CleanEditTeachersItems();
                    }
            }       
         else
            {
             JOptionPane.showMessageDialog(null, "Wybierz nauczyciela do usunięcia !");
              CleanEditTeachersItems();
             }
                
              

            
    }//GEN-LAST:event_deletebuttonActionPerformed

    private void teacherscomboMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherscomboMouseEntered
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherscomboMouseEntered

    private void anulujbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulujbuttonActionPerformed
        CleanEditGroupItems();        
    }//GEN-LAST:event_anulujbuttonActionPerformed

    private void textidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textidActionPerformed

    private void classcomboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_classcomboItemStateChanged
        JComboBox comboBox = (JComboBox) evt.getSource();

                // The item affected by the event.
                Object item = evt.getItem();
        if (evt.getStateChange() == ItemEvent.SELECTED) {
                     FillEditGroupItems();
                    
                }
    }//GEN-LAST:event_classcomboItemStateChanged

    private void newMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuActionPerformed
      
    }//GEN-LAST:event_newMenuActionPerformed

    private void BwyczyscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BwyczyscActionPerformed
        reset();
        
    }//GEN-LAST:event_BwyczyscActionPerformed

    private void idFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idFieldActionPerformed

    private void CzyscButtonSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CzyscButtonSaleActionPerformed
        classNumberField.setText(" ");
        buttonGroup1.clearSelection();
    }//GEN-LAST:event_CzyscButtonSaleActionPerformed

    private void newMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newMenuMouseClicked
        CleanEditTeachersItems();
        CleanEditGroupItems();
    }//GEN-LAST:event_newMenuMouseClicked

    private void editMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMenuMouseClicked
      CleanEditTeachersItems();
        CleanEditGroupItems();
    }//GEN-LAST:event_editMenuMouseClicked

    private void comboteacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboteacherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboteacherActionPerformed

    private void addTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTimeActionPerformed
        
        
        
        NewID noweId = new NewID();
        int Idgodz = noweId.UstawNumer("ID_godz","GODZINA");
        
        
        
        try {
            
            String godz1 = time1.getText();
        String godz2 = time2.getText();
        String godz3 = time3.getText();
        String godz4 = time4.getText();
        
        String sqlAddTime = "insert into GODZINA(GODZINA1, GODZINA2, GODZINA3, GODZINA4) values \n"
                + "('" + godz1 + "','" + godz2 + "','" + godz3 + "','" + godz4 + "' );";
        Statement stmt = conn.createStatement();
                  stmt.execute(sqlAddTime);
                  stmt.close();
            stmt = conn.createStatement();
            
            stmt.executeUpdate(sqlAddTime);
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Dodano rekord do bazy");
            hourList.addItem(godz1 +":"+ godz2 + "-" + godz3 + ":" + godz4);
            listHour.addItem(godz1 +":"+ godz2 + "-" + godz3 + ":" + godz4);
            timeCombo.addItem(godz1 +":"+ godz2 + "-" + godz3 + ":" + godz4);
            
            CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel,"timePanel");
            pst.close();
            rs.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
         // resetId();
         
          
        time1.setText("");
        time2.setText("");
        time3.setText("");
        time4.setText("");
        
    }//GEN-LAST:event_addTimeActionPerformed

    private void timeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeItemActionPerformed
        if (evt.getSource() == timeItem) {
            CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel, "timePanel");
        }
        reset();
    }//GEN-LAST:event_timeItemActionPerformed

    private void lastnameFiledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastnameFiledActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastnameFiledActionPerformed

    private void TimeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimeItemActionPerformed
        if (evt.getSource() == TimeItem) {
            CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel, "changeTime");
            
        }
        reset();
    }//GEN-LAST:event_TimeItemActionPerformed

    private void deleteTimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTimeButtonActionPerformed
        
                if(!"".equals(idTimeField.getText()))
             {
                int p= JOptionPane.showConfirmDialog(null,"Czy na pewno chcesz usunąć rekord?","usun",JOptionPane.YES_NO_OPTION);
          
                 if(p == 0) 
                    
                    {
             
                      String sql = "delete from Godzina where ID_godz =?";
                            
                        try
                           
                            {
                     
                           pst=conn.prepareStatement(sql);
                           pst.setString(1,idTimeField.getText());
                           pst.execute();
                           JOptionPane.showMessageDialog(null, "Usunięto ! ");
                           int index = timeCombo.getSelectedIndex();
                            timeCombo.removeItemAt(index); 
                            hourList.removeItemAt(index);
                            listHour.removeItemAt(index);
                           CleanEditTime();
                            
                            }           
                        catch (SQLException e){}
                        finally 
                        {
                                 try
                                 {
                                    rs.close();
                                    pst.close();
                                 }
                                catch(SQLException e){}

                         }
                    }
                 else 
                    {
                    CleanEditTime();
                    }
            }       
         else
            {
             JOptionPane.showMessageDialog(null, "Wybierz godzinę do usunięcia !");
              CleanEditTime();
             }
            
    }//GEN-LAST:event_deleteTimeButtonActionPerformed

    private void idTimeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTimeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTimeFieldActionPerformed

    private void surnametextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surnametextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_surnametextActionPerformed

    private void timeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeComboActionPerformed
        
    }//GEN-LAST:event_timeComboActionPerformed

    private void classtextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classtextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_classtextActionPerformed

    private void timeComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_timeComboItemStateChanged
        JComboBox timeCombo = (JComboBox) evt.getSource();

                // The item affected by the event.
                Object item = evt.getItem();
        if (evt.getStateChange() == ItemEvent.SELECTED) {
                     FillEditTimeItems();
                    
                }
    }//GEN-LAST:event_timeComboItemStateChanged

    private void groupListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupListActionPerformed
   

       
        
            // TODO add your handling code here:
    }//GEN-LAST:event_groupListActionPerformed

    private void groupListPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_groupListPopupMenuWillBecomeInvisible
      
        String tmp =(String)groupList.getSelectedItem();
        hourList.removeAllItems();
        classList.removeAllItems();
        teacherList.removeAllItems();
        Fillcombo();
        klasapole.setText("");
        dzien.setText("");
                godzina1.setText("");
                 godzina2.setText("");
                  godzina3.setText("");
                   godzina4.setText("");
                przedmiotpole.setText("");
                imiepole1.setText("");
                salapole.setText("");
        klasapole.setText(tmp);
        
        
        
    }//GEN-LAST:event_groupListPopupMenuWillBecomeInvisible

    private void hourListPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_hourListPopupMenuWillBecomeInvisible
       String tmp =(String)hourList.getSelectedItem();
       int id =  Integer.parseInt(tmp);
        //hourList.removeAllItems();
        classList.removeAllItems();
        teacherList.removeAllItems();
        Fillcombo();
        //klasapole.setText("");
        //dzien.setText("");
                godzina1.setText("");
                 godzina2.setText("");
                  godzina3.setText("");
                   godzina4.setText("");
                przedmiotpole.setText("");
                imiepole1.setText("");
                salapole.setText("");
       
        String sql="select * from Godzina where ID_godz = '"+id+"'";
        try{
                pst=conn.prepareStatement(sql);
                 rs=pst.executeQuery();
                 
                 while(rs.next())
                 {
                    String godzina1a=rs.getString("godzina1");
                     String godzina2a=rs.getString("godzina2");
                      String godzina3a=rs.getString("godzina3");
                       String godzina4a=rs.getString("godzina4");
                       godzina1.setText(godzina1a);
                        godzina2.setText(godzina2a);
                         godzina3.setText(godzina3a);
                          godzina4.setText(godzina4a);
                       
                       
                       
                 }
        }
        catch(Exception e )
        {
            
        }
    }//GEN-LAST:event_hourListPopupMenuWillBecomeInvisible

    private void dzienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dzienActionPerformed
      
    }//GEN-LAST:event_dzienActionPerformed

    private void dzientygodniaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_dzientygodniaPopupMenuWillBecomeInvisible
      String tmp =(String)dzientygodnia.getSelectedItem();
       dzien.setText("");
        
        hourList.removeAllItems();
        classList.removeAllItems();
        teacherList.removeAllItems();
        Fillcombo();
        //klasapole.setText("");
       
                godzina1.setText("");
                 godzina2.setText("");
                  godzina3.setText("");
                   godzina4.setText("");
                przedmiotpole.setText("");
                imiepole1.setText("");
                salapole.setText("");
                dzien.setText(tmp);
        Fillcombogodziny();        // TODO add your handling code here:
    }//GEN-LAST:event_dzientygodniaPopupMenuWillBecomeInvisible

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
       String tmp =(String)jComboBox1.getSelectedItem();
       classList.removeAllItems();
        teacherList.removeAllItems();
        przedmiotpole.setText("");
                imiepole1.setText("");
                salapole.setText("");
        przedmiotpole.setText(tmp);
        Fillcombonauczyciel();
        
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void teacherListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherListActionPerformed

    private void teacherListPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_teacherListPopupMenuWillBecomeInvisible
       String tmp =(String)teacherList.getSelectedItem();
       imiepole1.setText("");
                salapole.setText("");
                classList.removeAllItems();
        imiepole1.setText(tmp);
        Fillcombonauczyciel();
        Fillcombosala();
    }//GEN-LAST:event_teacherListPopupMenuWillBecomeInvisible

    private void classListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classListActionPerformed
     
       
    }//GEN-LAST:event_classListActionPerformed

    private void classListPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_classListPopupMenuWillBecomeInvisible
          String tmp =(String)classList.getSelectedItem();
          int id = Integer.parseInt(tmp);
       try{
           String sql = "Select * from Sale1 where sal_id = '"+id+"'";
           
                pst=conn.prepareStatement(sql);
                 rs=pst.executeQuery();
                 
                
                    String rodzaj=rs.getString("sal_rodzaj");
                     
                       salapole.setText(rodzaj);
                       
       
       }
       catch (Exception e){
           
       }
    }//GEN-LAST:event_classListPopupMenuWillBecomeInvisible

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditWindow().setVisible(true);
               // CreateTablesale();

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bwyczysc;
    private javax.swing.JButton CzyscButtonSale;
    private javax.swing.JMenuItem TimeItem;
    private javax.swing.JButton Zapisz;
    private javax.swing.JTextField a;
    private javax.swing.JButton addClassBtn;
    private javax.swing.JButton addClassButton;
    private javax.swing.JButton addLessonBtn;
    private javax.swing.JButton addTeacherButton;
    private javax.swing.JButton addTime;
    private javax.swing.JButton anulujbtn;
    private javax.swing.JButton anulujbutton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JPanel changeLesson;
    private javax.swing.JPanel changeTeacher;
    private javax.swing.JPanel changeTime;
    private javax.swing.JTextField classField;
    private javax.swing.JMenuItem classItem1;
    private javax.swing.JComboBox<String> classList;
    private javax.swing.JTextField classNumberField;
    private javax.swing.JPanel classPanel;
    private javax.swing.JComboBox<String> classcombo;
    private javax.swing.JTextField classtext;
    private javax.swing.JPanel cleanPanel;
    private javax.swing.JComboBox<String> comboteacher;
    private javax.swing.JButton delete2Button;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton deleteTimeButton;
    private javax.swing.JButton deletebutton;
    private javax.swing.JTextField dzien;
    private javax.swing.JComboBox<String> dzientygodnia;
    private javax.swing.JButton edit2Button;
    private javax.swing.JMenu editMenu;
    private javax.swing.JRadioButton fridayRadioBtn1;
    private javax.swing.JTextField godzina1;
    private javax.swing.JTextField godzina2;
    private javax.swing.JTextField godzina3;
    private javax.swing.JTextField godzina4;
    private javax.swing.JMenuItem groupItem1;
    private javax.swing.JMenuItem groupItem2;
    private javax.swing.JComboBox<String> groupList;
    private javax.swing.JPanel groupPanel;
    private javax.swing.JCheckBox gymCheckBox;
    private javax.swing.JComboBox<String> hourList;
    private javax.swing.JTextField idField;
    private javax.swing.JTextField idTimeField;
    private javax.swing.JPanel idtext;
    private javax.swing.JTextField idtextfield;
    private javax.swing.JTextField imiepole1;
    private javax.swing.JCheckBox itCheckBox;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JComboBox jComboBox12;
    private javax.swing.JComboBox jComboBox13;
    private javax.swing.JComboBox jComboBox14;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField klasapole;
    private javax.swing.JTextField lastnameFiled;
    private javax.swing.JMenuItem lessonItem;
    private javax.swing.JMenuItem lessonItem2;
    private javax.swing.JPanel lessonPanel;
    private javax.swing.JComboBox<String> listHour;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JRadioButton mondayRadioBtn1;
    private javax.swing.JLabel name;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField nametext;
    private javax.swing.JMenu newMenu;
    private javax.swing.JCheckBox normalCheckBox;
    private javax.swing.JPanel previewPanel;
    private javax.swing.JPanel previewPanel1;
    private javax.swing.JTextField przedmiotpole;
    private javax.swing.JTextField salapole;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> sub1;
    private javax.swing.JComboBox<String> sub2;
    private javax.swing.JComboBox<String> sub3;
    private javax.swing.JComboBox<String> sub4;
    private javax.swing.JComboBox<String> sub5;
    private javax.swing.JLabel subject;
    private javax.swing.JLabel surname;
    private javax.swing.JTextField surnametext;
    private javax.swing.JLabel teacher;
    private javax.swing.JComboBox<String> teacherBox;
    private javax.swing.JComboBox<String> teacherList;
    private javax.swing.JPanel teacherPanel;
    private javax.swing.JPanel teacherPanel2;
    private javax.swing.JMenuItem teachersItem1;
    private javax.swing.JMenuItem teachersItem2;
    private javax.swing.JComboBox<String> teacherscombo;
    private javax.swing.JTextField textid;
    private javax.swing.JRadioButton thursdayRadioBtn1;
    private javax.swing.JTextField time1;
    private javax.swing.JTextField time2;
    private javax.swing.JTextField time3;
    private javax.swing.JTextField time4;
    private javax.swing.JComboBox<String> timeCombo;
    private javax.swing.JMenuItem timeItem;
    private javax.swing.JPanel timePanel;
    private javax.swing.JRadioButton tuesdayRadioBtn1;
    private javax.swing.JRadioButton wednesdayRadioBtn1;
    // End of variables declaration//GEN-END:variables

    
}
