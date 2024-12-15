/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Controller;

import Controller.ControllerSell;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author darwin
 */
public class ControllerSellTest {
    
    public ControllerSellTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of searchxCustomer method, of class ControllerSell.
     */
    @Test
    public void testSearchxCustomer() {
        System.out.println("searchxCustomer");
        JTable tableCustomer = null;
        JTextField id = null;
        JTextField customerName = null;
        JTextField name = null;
        JTextField lnmom = null;
        JTextField lndad = null;
        ControllerSell instance = new ControllerSell();
    //    instance.searchxCustomer(tableCustomer, id, customerName, name, lnmom, lndad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showxProducts method, of class ControllerSell.
     */
    @Test
    public void testShowxProducts() {
        System.out.println("showxProducts");
        JTextField pname = null;
        JTable ptable = null;
        JTextField name = null;
        JTextField id = null;
        JTextField price = null;
        JTextField stock = null;
        ControllerSell instance = new ControllerSell();
     //   instance.showxProducts(pname, ptable, name, id, price, stock);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectRow method, of class ControllerSell.
     */
    @Test
    public void testSelectRow() {
        System.out.println("selectRow");
        JTable table = null;
        JTextField id = null;
        JTextField name = null;
        JTextField lnmom = null;
        JTextField lndad = null;
        ControllerSell instance = new ControllerSell();
        instance.selectRow(table, id, name, lnmom, lndad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectRowProduct method, of class ControllerSell.
     */
    @Test
    public void testSelectRowProduct() {
        System.out.println("selectRowProduct");
        JTable table = null;
        JTextField id = null;
        JTextField name = null;
        JTextField price = null;
        JTextField stock = null;
        JTextField price2 = null;
        ControllerSell instance = new ControllerSell();
        instance.selectRowProduct(table, id, name, price, stock, price2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addProduct method, of class ControllerSell.
     */
    @Test
    public void testAddProductCustomerxProductNotSelected() {
        System.out.println("addProduct_CustomerxProduct_Not_Selected");
        JTable tableaddp = new JTable();
        JTextField customerID = new JTextField();
        JTextField productID = new JTextField();
        JTextField cusname = new JTextField();
        JTextField pname = new JTextField();
        JTextField priceSell = new JTextField();
        JTextField quantitySell = new JTextField();
        JTextField stock = new JTextField();
        JLabel jl16 = new JLabel();
        JLabel errorcusID = new JLabel();
        JLabel errorproID = new JLabel();
        
        productID.setText("");
        pname.setText("");
        
        ControllerSell instance = new ControllerSell();
        instance.addProduct(tableaddp, customerID,productID,cusname, pname, priceSell, quantitySell, stock,jl16,errorcusID,errorproID);
        Assert.assertEquals("*", errorcusID.getText());
        Assert.assertEquals("*", errorproID.getText());
       
    }

    @Test
      public void testAddProductFormatNotValidatePriceSell() {
        System.out.println("addProduct_Price_Sell_Format_Not_Validate");
        JTable tableaddp = new JTable();
        JTextField customerID = new JTextField();
        JTextField productID = new JTextField();
        JTextField cusname = new JTextField();
        JTextField pname = new JTextField();
        JTextField priceSell = new JTextField();
        JTextField quantitySell = new JTextField();
        JTextField stock = new JTextField();
        JLabel jl16 = new JLabel();
        JLabel errorcusID = new JLabel();
        JLabel errorproID = new JLabel();
        
        productID.setText("3");
        pname.setText("1");
        quantitySell.setText("!!jhola");
        
        ControllerSell instance = new ControllerSell();
        instance.addProduct(tableaddp,customerID, productID,cusname, pname, priceSell, quantitySell, stock,jl16,errorcusID,errorproID);
        Assert.assertEquals("Solo permitido números", jl16.getText());
        Assert.assertEquals("", errorcusID.getText());
        Assert.assertEquals("", errorproID.getText());       
    }
    /**
     * Test of deleteSelectedRow method, of class ControllerSell.
     */
    @Test
    public void testDeleteSelectedRow() {
        System.out.println("deleteSelectedRow");
        JTable table = null;
        ControllerSell instance = new ControllerSell();
        instance.deleteSelectedRow(table);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of assignTotal method, of class ControllerSell.
     */
    @Test
    public void testAssignTotal() {
        System.out.println("assignTotal");
        JTable table = null;
        JLabel IVA = null;
        JLabel total = null;
        ControllerSell instance = new ControllerSell();
        instance.assignTotal(table, IVA, total);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateReceipt method, of class ControllerSell.
     */
    @Test
    public void testGenerateReceipt() {
        System.out.println("generateReceipt");
        JTextField customerID = null;
        ControllerSell instance = new ControllerSell();
        instance.generateReceipt(customerID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showLastReceipt method, of class ControllerSell.
     */
    @Test
    public void testShowLastReceipt() {
        System.out.println("showLastReceipt");
        JLabel lastReceipt = null;
        ControllerSell instance = new ControllerSell();
        instance.showLastReceipt(lastReceipt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveDetails method, of class ControllerSell.
     */
    @Test
    public void testSaveDetails() {
        System.out.println("saveDetails");
        JTable table = null;
        JLabel receiptID = null;
        ControllerSell instance = new ControllerSell();
        instance.saveDetails(table, receiptID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
