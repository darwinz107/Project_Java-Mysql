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
public class ControllerReceiptTest {
    
    public ControllerReceiptTest() {
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
     * Test of searchxNumReceipt method, of class ControllerReceipt.
     */
    @Test
    public void testSearchxNumReceipt() {
        System.out.println("searchxNumReceipt");
        JTable table = null;
        JTextField numreceipt = null;
        JLabel lblnumReceipt = null;
        JLabel lblDate = null;
        JLabel cusName = null;
        JLabel lnMom = null;
        JLabel lnDad = null;
        JLabel iva = null;
        JLabel total = null;
        JLabel lberror = null;
        ControllerReceipt instance = new ControllerReceipt();
        instance.searchxNumReceipt(table, numreceipt, lblnumReceipt, lblDate, cusName, lnMom, lnDad, iva, total, lberror);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
