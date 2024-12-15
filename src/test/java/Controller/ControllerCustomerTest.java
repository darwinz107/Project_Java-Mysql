/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Controller;
import com.toedter.calendar.JDateChooser;
import configuration.CConexion;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;

import javax.swing.JTextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author darwin
 */
public class ControllerCustomerTest {
    
    
    public ControllerCustomerTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        
   CConexion connection = new CConexion();
   connection.setConnection();
  
    
    }
    
    
    @AfterClass
    public static void tearDownClass() throws Exception {
        CConexion connection = new CConexion();
        connection.closeConnection();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    
    @Test
    public void testValidateEmptyField(){
        System.out.println("Validate_Empty_Field");
     JTextField name = new JTextField();
        JTextField LastNameMom = new JTextField();
        JTextField LastNameDad = new JTextField();
    JDateChooser dateBorn = new JDateChooser();
    JLabel errorName = new JLabel();
    JLabel erroLnMom = new JLabel();
    JLabel errorLnDad = new JLabel();
    JLabel errorDateBorn = new JLabel();
   
        name.setText("");
        LastNameMom.setText("");
        LastNameDad.setText("");
        dateBorn.setDate(null);
        
       
      
      Controller.ControllerCustomer controlc = new ControllerCustomer();
     
      controlc.insertCustomer(name, LastNameMom, LastNameDad,errorName,erroLnMom,errorLnDad,dateBorn,errorDateBorn);
    
        Assert.assertEquals("Campo vacío", errorName.getText());
        Assert.assertEquals("Campo vacío", erroLnMom.getText());
        Assert.assertEquals("Campo vacío", errorLnDad.getText());
        Assert.assertEquals("Campo vacío", errorDateBorn.getText());
    
    } 
     @Test
    public void testValidateEmptyNotEmpty(){
        System.out.println("Not_Validate_Empty_Field");
     JTextField name = new JTextField();
        JTextField LastNameMom = new JTextField();
        JTextField LastNameDad = new JTextField();
    JDateChooser dateBorn = new JDateChooser();
    JLabel errorName = new JLabel();
    JLabel erroLnMom = new JLabel();
    JLabel errorLnDad = new JLabel();
    JLabel errorDateBorn = new JLabel();
   
        name.setText("Darwin");
        LastNameMom.setText("Muruzumbay");
        LastNameDad.setText("Zambrano");
        
         Calendar date = Calendar.getInstance();
         date.set(2000, 05, 12);
        dateBorn.setDate(date.getTime());
        
       
      
      Controller.ControllerCustomer controlc = new ControllerCustomer();
     
      controlc.insertCustomer(name, LastNameMom, LastNameDad,errorName,erroLnMom,errorLnDad,dateBorn,errorDateBorn);
    
        Assert.assertEquals("", errorName.getText());
        Assert.assertEquals("", erroLnMom.getText());
        Assert.assertEquals("", errorLnDad.getText());
        Assert.assertEquals("", errorDateBorn.getText());
    
    } 
    
    @Test
           
    public void testFormatNotValidate(){
        System.out.println("Not_Validate_Format");
     JTextField name = new JTextField();
     JTextField LastNameMom = new JTextField();
     JTextField LastNameDad = new JTextField();
    JDateChooser dateBorn = new JDateChooser();
    JLabel errorName = new JLabel();
    JLabel erroLnMom = new JLabel();
    JLabel errorLnDad = new JLabel();
    JLabel errorDateBorn = new JLabel();
   
        name.setText("21515");
        LastNameMom.setText("[][]**");
        LastNameDad.setText("!!515");
        Calendar date = Calendar.getInstance();
        date.set(3000, 01, 02);
        dateBorn.setDate(date.getTime());
        
       
      
      Controller.ControllerCustomer controlc = new ControllerCustomer();
     
      controlc.insertCustomer(name, LastNameMom, LastNameDad,errorName,erroLnMom,errorLnDad,dateBorn,errorDateBorn);
    
        Assert.assertEquals("Solo permitido letras", errorName.getText());
        Assert.assertEquals("Solo permitido letras", erroLnMom.getText());
        Assert.assertEquals("Solo permitido letras", errorLnDad.getText());
        Assert.assertEquals("No puede escoger fechas futuras", errorDateBorn.getText());
    
    }
    
    @Test
       public void testFormatValidate(){
        System.out.println("Validate_Format");
     JTextField name = new JTextField();
     JTextField LastNameMom = new JTextField();
     JTextField LastNameDad = new JTextField();
    JDateChooser dateBorn = new JDateChooser();
    JLabel errorName = new JLabel();
    JLabel erroLnMom = new JLabel();
    JLabel errorLnDad = new JLabel();
    JLabel errorDateBorn = new JLabel();
   
        name.setText("Jose");
        LastNameMom.setText("Sanchez");
        LastNameDad.setText("Sarmiento");
        Calendar date = Calendar.getInstance();
        date.set(2001, 01, 02);
        dateBorn.setDate(date.getTime());
        
       
      
      Controller.ControllerCustomer controlc = new ControllerCustomer();
     
      controlc.insertCustomer(name, LastNameMom, LastNameDad,errorName,erroLnMom,errorLnDad,dateBorn,errorDateBorn);
    
        Assert.assertEquals("", errorName.getText());
        Assert.assertEquals("", erroLnMom.getText());
        Assert.assertEquals("", errorLnDad.getText());
        Assert.assertEquals("", errorDateBorn.getText());
    
    }
    /**
     * Test of showCustomer method, of class ControllerCustomer.
     */
    @Test
    public void testShowCustomer() throws Exception {
        System.out.println("showCustomer");
        CConexion connection = new CConexion();
        JTextField name = new JTextField();
        JTextField LastNameMom = new JTextField();
        JTextField LastNameDad = new JTextField();
   /*      JLabel errorName= new JLabel();
         JLabel errorLnMom= new JLabel();
        JLabel errorLnDad= new JLabel();
        
        JLabel errorDate= new JLabel();
       */ JDateChooser dateBorn = new JDateChooser();
        Date dateNow= new Date();
        
        name.setText("Darwin");
        LastNameMom.setText("Muruzumbay");
        LastNameDad.setText("Zambrano");
       dateBorn.setDate(dateNow);
      java.sql.Date dateTest = new java.sql.Date(dateNow.getTime());
        //Controller.ControllerCustomer controlc = new ControllerCustomer();
    //    controlc.insertCustomer(name, LastNameMom, LastNameDad,errorName,errorLnMom,errorLnDad,dateBorn,errorDate);
       
        String querry = "SELECT * FROM customer WHERE customerName='Darwin' AND LastNameMom='Muruzumbay' AND LastNameDad='Zambrano' AND dateBorn ='2024-11-30';";
        Statement st = connection.setConnection().createStatement();
      //  st.execute(querry);
        ResultSet rs = st.executeQuery(querry);
        
        Assert.assertTrue("We can't insert customers,", rs.next());
        Assert.assertEquals("Darwin",rs.getString("customerName"));
        Assert.assertEquals("Muruzumbay", rs.getString("LastNameMom"));
        Assert.assertEquals("Zambrano", rs.getString("LastNameDad"));
        Assert.assertEquals(dateTest.toString(), rs.getDate("dateBorn").toString());
       
      //  fail("The test case is a prototype.");
    }

    @Test
    public void testErrorShowCustomer() throws Exception{
        System.out.println("Error show customer");
        JTextField cname = new JTextField();
        JTextField lnmom = new JTextField();
        JTextField lndad = new JTextField();
        
        cname.setText("Ana");
        lnmom.setText("Paredes");
        lndad.setText("Zamora"); 
      
        Date dateNow = new Date();
        
        
        java.sql.Date dateTest = new java.sql.Date(dateNow.getTime());
        LocalDate date2 = dateTest.toLocalDate();
        LocalDate datePlus = date2.plusDays(11);
        configuration.CConexion connection = new configuration.CConexion();
        
        
        String querry = "SELECT * FROM dbdar.customer WHERE customerName='Darwin' AND LastNameMom='Muruzumbay' AND LastNameDad='Zambrano' AND dateBorn='2024-11-30';";
        
        Statement st = connection.setConnection().createStatement();
        ResultSet rs = st.executeQuery(querry);
        rs.next();
        Assert.assertNotSame(cname,rs.getString("customerName"));
        Assert.assertNotSame(lnmom,rs.getString("LastNameMom"));
        Assert.assertNotSame(lndad,rs.getString("LastNameDad"));
        Assert.assertNotSame(datePlus, rs.getDate("dateBorn"));
    }
    
    
    
    /**
     * Test of insertCustomer method, of class ControllerCustomer.
     */
  /*  @Test
    public void testInsertCustomer() {
        System.out.println("insertCustomer");
        JTextField name = null;
        JTextField lastNameMom = null;
        JTextField lastNameDad = null;
        name.setText("dar");
        lastNameMom.setText("mur");
        lastNameDad.setText("zam");
        ControllerCustomer instance = new ControllerCustomer();
        instance.insertCustomer(name, lastNameMom, lastNameDad);
       
       
    //    fail("The test case is a prototype.");
    }

    /**
     * Test of selectRow method, of class ControllerCustomer.
     */
    /*
    @Test
    public void testSelectRow() {
        System.out.println("selectRow");
        JTable table = null;
        JTextField id = null;
        JTextField name = null;
        JTextField lastNameMom = null;
        JTextField lastNameDad = null;
        ControllerCustomer instance = new ControllerCustomer();
        instance.selectRow(table, id, name, lastNameMom, lastNameDad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editCustomer method, of class ControllerCustomer.
     */
    /*
    @Test
    public void testEditCustomer() {
        System.out.println("editCustomer");
        JTextField id = null;
        JTextField name = null;
        JTextField lastNameMom = null;
        JTextField lastNameDad = null;
        ControllerCustomer instance = new ControllerCustomer();
        instance.editCustomer(id, name, lastNameMom, lastNameDad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCustomer method, of class ControllerCustomer.
     */
    /*
    @Test
    public void testDeleteCustomer() throws Exception{
        System.out.println("deleteCustomer");
        JTextField id = new JTextField();
        id.setText(String.valueOf(1));
        
        ControllerCustomer instance = new ControllerCustomer();
        instance.deleteCustomer(id);
        configuration.CConexion connection = new CConexion();
        String querry = "DELETE FROM dbdar.customer WHERE customerID=?";
        CallableStatement cs = connection.setConnection().prepareCall(querry);
        cs.setInt(1, Integer.parseInt(id.getText()));
        connection.closeConnection();
       String querry2 = "SELECT * FROM dbdar.customer WHERE customerID=1";
        Statement st = connection.setConnection().createStatement();
        ResultSet rs = st.executeQuery(querry2);
        Assert.assertTrue("There is anything",rs.next());
        Assert.assertEquals("", rs.getString("customerID"));
        
        
    
    }

    /**
     * Test of clearText method, of class ControllerCustomer.
     */
    /*
    @Test
    public void testClearText() {
        System.out.println("clearText");
        JTextField id = null;
        JTextField name = null;
        JTextField lastNameMom = null;
        JTextField lastNameDad = null;
        ControllerCustomer instance = new ControllerCustomer();
        instance.clearText(id, name, lastNameMom, lastNameDad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
