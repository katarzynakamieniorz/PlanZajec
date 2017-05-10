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
        
        Fillgroup();        
    }
    
    // Funkcja czyszcząca pola w trybie tworzenia nowego nauczyciela
    public void reset(){
      
        nameField.setText("");
        lastnameFiled.setText("");
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
                comboteacher.addItem(imieNazwisko);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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
        teacherBox = new javax.swing.JComboBox<String>();
        addClassButton = new javax.swing.JButton();
        cleanPanel = new javax.swing.JPanel();
        teacherPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lessonPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        groupList = new javax.swing.JComboBox<String>();
        jLabel12 = new javax.swing.JLabel();
        classList = new javax.swing.JComboBox<String>();
        jLabel13 = new javax.swing.JLabel();
        teacherList = new javax.swing.JComboBox<String>();
        jLabel14 = new javax.swing.JLabel();
        mondayRadioBtn = new javax.swing.JRadioButton();
        tuesdayRadioBtn = new javax.swing.JRadioButton();
        wednesdayRadioBtn = new javax.swing.JRadioButton();
        thursdayRadioBtn = new javax.swing.JRadioButton();
        fridayRadioBtn = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        hourList = new javax.swing.JComboBox<String>();
        previewPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        addLessonBtn = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<String>();
        classPanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        classNumberField = new javax.swing.JTextField();
        gymCheckBox = new javax.swing.JCheckBox();
        itCheckBox = new javax.swing.JCheckBox();
        addClassBtn = new javax.swing.JButton();
        normalCheckBox = new javax.swing.JCheckBox();
        idtext = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        classcombo = new javax.swing.JComboBox<String>();
        deleteButton = new javax.swing.JButton();
        Zapisz = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        textid = new javax.swing.JTextField();
        anulujbutton = new javax.swing.JButton();
        classtext = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        comboteacher = new javax.swing.JComboBox<String>();
        changeTeacher = new javax.swing.JPanel();
        teacher = new javax.swing.JLabel();
        teacherscombo = new javax.swing.JComboBox<String>();
        deletebutton = new javax.swing.JButton();
        subject = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        sub1 = new javax.swing.JComboBox<String>();
        sub2 = new javax.swing.JComboBox<String>();
        sub3 = new javax.swing.JComboBox<String>();
        sub4 = new javax.swing.JComboBox<String>();
        sub5 = new javax.swing.JComboBox<String>();
        nametext = new javax.swing.JTextField();
        surnametext = new javax.swing.JTextField();
        name = new javax.swing.JLabel();
        surname = new javax.swing.JLabel();
        anulujbtn = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        idtextfield = new javax.swing.JTextField();
        changeLesson = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<String>();
        jLabel23 = new javax.swing.JLabel();
        mondayRadioBtn1 = new javax.swing.JRadioButton();
        tuesdayRadioBtn1 = new javax.swing.JRadioButton();
        wednesdayRadioBtn1 = new javax.swing.JRadioButton();
        thursdayRadioBtn1 = new javax.swing.JRadioButton();
        fridayRadioBtn1 = new javax.swing.JRadioButton();
        jLabel24 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<String>();
        jLabel25 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<String>();
        jLabel26 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<String>();
        edit2Button = new javax.swing.JButton();
        delete2Button = new javax.swing.JButton();
        previewPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<String>();
        jMenuBar1 = new javax.swing.JMenuBar();
        newMenu = new javax.swing.JMenu();
        groupItem1 = new javax.swing.JMenuItem();
        teachersItem1 = new javax.swing.JMenuItem();
        classItem1 = new javax.swing.JMenuItem();
        lessonItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        groupItem2 = new javax.swing.JMenuItem();
        teachersItem2 = new javax.swing.JMenuItem();
        lessonItem2 = new javax.swing.JMenuItem();

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
                .addContainerGap(561, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
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
                .addContainerGap(212, Short.MAX_VALUE))
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
                .addContainerGap(573, Short.MAX_VALUE))
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
                .addContainerGap(383, Short.MAX_VALUE))
        );

        mainPanel.add(groupPanel, "groupPanel");

        javax.swing.GroupLayout cleanPanelLayout = new javax.swing.GroupLayout(cleanPanel);
        cleanPanel.setLayout(cleanPanelLayout);
        cleanPanelLayout.setHorizontalGroup(
            cleanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 994, Short.MAX_VALUE)
        );
        cleanPanelLayout.setVerticalGroup(
            cleanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
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
                .addContainerGap(735, Short.MAX_VALUE))
        );
        teacherPanel2Layout.setVerticalGroup(
            teacherPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teacherPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(463, Short.MAX_VALUE))
        );

        mainPanel.add(teacherPanel2, "teacherPanel2");

        jLabel10.setText("Dodaj nową lekcję");

        jLabel11.setText("Klasa");

        groupList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("Sala");

        classList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setText("Nauczyciel");

        teacherList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setText("Dzień");

        mondayRadioBtn.setText("Poniedziałek");
        mondayRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mondayRadioBtnActionPerformed(evt);
            }
        });

        tuesdayRadioBtn.setText("Wtorek");

        wednesdayRadioBtn.setText("Środa");
        wednesdayRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wednesdayRadioBtnActionPerformed(evt);
            }
        });

        thursdayRadioBtn.setText("Czwartek");
        thursdayRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thursdayRadioBtnActionPerformed(evt);
            }
        });

        fridayRadioBtn.setText("Piątek");

        jLabel15.setText("Godzina");

        hourList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        previewPanelLayout.setVerticalGroup(
            previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(previewPanelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Wybierz--------" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lessonPanelLayout = new javax.swing.GroupLayout(lessonPanel);
        lessonPanel.setLayout(lessonPanelLayout);
        lessonPanelLayout.setHorizontalGroup(
            lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lessonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lessonPanelLayout.createSequentialGroup()
                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(lessonPanelLayout.createSequentialGroup()
                                .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(lessonPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(lessonPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tuesdayRadioBtn)
                                            .addComponent(mondayRadioBtn)
                                            .addComponent(wednesdayRadioBtn)
                                            .addComponent(thursdayRadioBtn)
                                            .addComponent(fridayRadioBtn)))
                                    .addGroup(lessonPanelLayout.createSequentialGroup()
                                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(groupList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(classList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(teacherList, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(lessonPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(33, 33, 33)
                                        .addComponent(hourList, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 232, Short.MAX_VALUE)))
                        .addGap(42, 42, 42)
                        .addComponent(previewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addLessonBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        lessonPanelLayout.setVerticalGroup(
            lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lessonPanelLayout.createSequentialGroup()
                .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(lessonPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(groupList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(classList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(teacherList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mondayRadioBtn)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tuesdayRadioBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wednesdayRadioBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thursdayRadioBtn)
                        .addGap(6, 6, 6)
                        .addComponent(fridayRadioBtn)
                        .addGap(18, 18, 18)
                        .addGroup(lessonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(hourList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addComponent(previewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(addLessonBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 109, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(addClassBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(418, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(normalCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gymCheckBox)
                .addGap(7, 7, 7)
                .addComponent(itCheckBox)
                .addContainerGap(317, Short.MAX_VALUE))
        );

        mainPanel.add(classPanel, "classPanel");

        jLabel18.setText("Wybierz klasę:");

        classcombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Klasa" }));
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

        jLabel21.setText("ID klasy");

        jLabel29.setText("Klasa");

        comboteacher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nauczyciel" }));

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
                .addContainerGap(567, Short.MAX_VALUE))
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
                .addContainerGap(189, Short.MAX_VALUE))
        );

        mainPanel.add(idtext, "changeGroup");

        teacher.setText("Wybierz nauczyciela:");

        teacherscombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nauczyciel" }));
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
                                .addGap(0, 143, Short.MAX_VALUE))
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
                        .addContainerGap(287, Short.MAX_VALUE))))
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
                .addContainerGap(163, Short.MAX_VALUE))
        );

        mainPanel.add(changeTeacher, "changeTeacher");

        jLabel22.setText("Klasa:");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel25.setText("Sala:");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });

        jLabel26.setText("Nauczyciel:");

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Wybierz--------" }));
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
                                .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(changeLessonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGap(0, 99, Short.MAX_VALUE))
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
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        newMenu.setText("Nowy");
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

        jMenuBar1.add(newMenu);

        editMenu.setText("Edytuj");

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
    }//GEN-LAST:event_groupItem2ActionPerformed

    private void mondayRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mondayRadioBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mondayRadioBtnActionPerformed

    private void wednesdayRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wednesdayRadioBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wednesdayRadioBtnActionPerformed

    private void addLessonBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLessonBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addLessonBtnActionPerformed

    private void lessonItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessonItemActionPerformed
        if (evt.getSource() == lessonItem) {
            CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel, "lessonPanel");

        }
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
       
        //String ClassNumber = classNumberField.getText();
        
        NewID noweId = new NewID();
        int Idsali = noweId.UstawNumer("SAL_ID","SALE");
        
        String ClassTyp = " ";
        if(gymCheckBox.isSelected()){
            ClassTyp = "WF";
        }
        else if (itCheckBox.isSelected()){
            ClassTyp = "IT";
        }
        else if (normalCheckBox.isSelected())
        
            ClassTyp = "N";
        
        
        else 
        {ClassTyp = " ";}
          
        
                    //String sqlAddClass = "insert into SALE(SAL_ID, SAL_NUMER, SAL_RODZAJ) values \n"
                    //+ "('" + Idsali + "','" + ClassNumber + "','" + ClassTyp + "' );";
                    if(normalCheckBox.isSelected()||itCheckBox.isSelected()||gymCheckBox.isSelected())
                    {
        try {
            String ClassNumber = classNumberField.getText();
        String sqlAddClass = "insert into SALE(SAL_ID, SAL_NUMER, SAL_RODZAJ) values \n"
                    + "('" + Idsali + "','" + ClassNumber + "','" + ClassTyp + "' );";
            stmt = conn.createStatement();

            stmt.executeUpdate(sqlAddClass);
            stmt.close();
         
            
                    JOptionPane.showMessageDialog(null, "Dodano rekord do bazy");
                            
                    classNumberField.setText(" ");
                    
                    CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel, "classPanel");
              
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Uzupełnij odpowiednie dane!", "Error", JOptionPane.ERROR_MESSAGE);
        }
                    }
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Wybierz typ sali!", "Error", JOptionPane.ERROR_MESSAGE);

                    }

        
    }//GEN-LAST:event_addClassBtnActionPerformed

    private void classItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classItem1ActionPerformed
        if (evt.getSource() == classItem1) {
            CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel, "classPanel");
            
        }
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

    private void thursdayRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thursdayRadioBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thursdayRadioBtnActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void teachersItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teachersItem2ActionPerformed
        if (evt.getSource() == teachersItem2) {
            CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel, "changeTeacher");
         
                   
            
        }
    }//GEN-LAST:event_teachersItem2ActionPerformed

    private void lessonItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessonItem2ActionPerformed
        if (evt.getSource() == lessonItem2) {
            CardLayout card = (CardLayout) mainPanel.getLayout();
            card.show(mainPanel, "changeLesson");
            
        }
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
        jComboBox10.setSelectedIndex(0);
        jComboBox11.setSelectedIndex(0);
        jComboBox12.setSelectedIndex(0);
        jComboBox13.setSelectedIndex(0);
        jComboBox14.setSelectedIndex(0);
    }//GEN-LAST:event_BwyczyscActionPerformed

    private void idFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idFieldActionPerformed

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
    private javax.swing.JButton Zapisz;
    private javax.swing.JButton addClassBtn;
    private javax.swing.JButton addClassButton;
    private javax.swing.JButton addLessonBtn;
    private javax.swing.JButton addTeacherButton;
    private javax.swing.JButton anulujbtn;
    private javax.swing.JButton anulujbutton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel changeLesson;
    private javax.swing.JPanel changeTeacher;
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
    private javax.swing.JButton deletebutton;
    private javax.swing.JButton edit2Button;
    private javax.swing.JMenu editMenu;
    private javax.swing.JRadioButton fridayRadioBtn;
    private javax.swing.JRadioButton fridayRadioBtn1;
    private javax.swing.JMenuItem groupItem1;
    private javax.swing.JMenuItem groupItem2;
    private javax.swing.JComboBox<String> groupList;
    private javax.swing.JPanel groupPanel;
    private javax.swing.JCheckBox gymCheckBox;
    private javax.swing.JComboBox<String> hourList;
    private javax.swing.JTextField idField;
    private javax.swing.JPanel idtext;
    private javax.swing.JTextField idtextfield;
    private javax.swing.JCheckBox itCheckBox;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JComboBox jComboBox12;
    private javax.swing.JComboBox jComboBox13;
    private javax.swing.JComboBox jComboBox14;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
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
    private javax.swing.JTextField lastnameFiled;
    private javax.swing.JMenuItem lessonItem;
    private javax.swing.JMenuItem lessonItem2;
    private javax.swing.JPanel lessonPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JRadioButton mondayRadioBtn;
    private javax.swing.JRadioButton mondayRadioBtn1;
    private javax.swing.JLabel name;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField nametext;
    private javax.swing.JMenu newMenu;
    private javax.swing.JCheckBox normalCheckBox;
    private javax.swing.JPanel previewPanel;
    private javax.swing.JPanel previewPanel1;
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
    private javax.swing.JRadioButton thursdayRadioBtn;
    private javax.swing.JRadioButton thursdayRadioBtn1;
    private javax.swing.JRadioButton tuesdayRadioBtn;
    private javax.swing.JRadioButton tuesdayRadioBtn1;
    private javax.swing.JRadioButton wednesdayRadioBtn;
    private javax.swing.JRadioButton wednesdayRadioBtn1;
    // End of variables declaration//GEN-END:variables

    
}
