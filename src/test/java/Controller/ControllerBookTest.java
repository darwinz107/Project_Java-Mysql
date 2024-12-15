/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Controller;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author darwin
 */
public class ControllerBookTest {
    
    public ControllerBookTest() {
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
     * Test of searchxCustomer method, of class ControllerBook.
     */
    @Test
    public void testSearchxCustomer() {
        System.out.println("searchxCustomer");
        JTable tableCustomer = null;
        JTextField customerName = null;
        ControllerBook instance = new ControllerBook();
        instance.searchxCustomer(tableCustomer, customerName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectRow method, of class ControllerBook.
     */
    @Test
    public void testSelectRow() {
        System.out.println("selectRow");
        JTable table = null;
        JTextField id = null;
        ControllerBook instance = new ControllerBook();
        instance.selectRow(table, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buy method, of class ControllerBook.
     */
    @Test
    public void testBuy() {
        System.out.println("buy");
        JTextField quantityChoose = null;
        JTextField id = null;
        JLabel lberrorQuantity = null;
        ControllerBook instance = new ControllerBook();
        instance.buy(quantityChoose, id, lberrorQuantity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of borrow method, of class ControllerBook.
     */
    @Test
    public void testBorrow() {
        System.out.println("borrow");
        JTextField quantityChoose = null;
        JTextField id = null;
        JLabel lberrorQuantity = null;
        ControllerBook instance = new ControllerBook();
        instance.borrow(quantityChoose, id, lberrorQuantity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchxName method, of class ControllerBook.
     */
    @Test
    public void testSearchxName() {
        System.out.println("searchxName");
        JTable table = null;
        JTextField cusName = null;
        ControllerBook instance = new ControllerBook();
        instance.searchxName(table, cusName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of btnmajor method, of class ControllerBook.
     */
    @Test
    public void testBtnmajor() {
        System.out.println("btnmajor");
        JTextField quantityDisp = null;
        JTextField quantityadquired = null;
        ControllerBook instance = new ControllerBook();
        instance.btnmajor(quantityDisp, quantityadquired);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of btnless method, of class ControllerBook.
     */
    @Test
    public void testBtnless() {
        System.out.println("btnless");
        JTextField quantityDisp = null;
        JTextField quantityadquired = null;
        ControllerBook instance = new ControllerBook();
        instance.btnless(quantityDisp, quantityadquired);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumBook method, of class ControllerBook.
     */
    @Test
    public void testGetNumBook() {
        System.out.println("getNumBook");
        ControllerBook instance = new ControllerBook();
        int expResult = 0;
        int result = instance.getNumBook();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumBook method, of class ControllerBook.
     */
    @Test
    public void testSetNumBook() {
        System.out.println("setNumBook");
        int num = 0;
        ControllerBook instance = new ControllerBook();
        instance.setNumBook(num);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBook1 method, of class ControllerBook.
     */
    @Test
    public void testSetBook1() {
        System.out.println("setBook1");
        JLabel bookName = null;
        JTextField autor = null;
        JTextField type = null;
        JTextField editorial = null;
        JTextField datePublish = null;
        JTextField price = null;
        JTextField quantity = null;
        ControllerBook instance = new ControllerBook();
        instance.setBook1(bookName, autor, type, editorial, datePublish, price, quantity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

 
   
    
}
