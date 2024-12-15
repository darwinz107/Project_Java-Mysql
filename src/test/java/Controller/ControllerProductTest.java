/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Controller;

import Controller.ControllerProduct;
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
public class ControllerProductTest {
    
    public ControllerProductTest() {
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
     * Test of insertProduct method, of class ControllerProduct.
     */
    @Test
    public void testInsertProduct() {
        System.out.println("insertProduct");
        JTextField pname = null;
        JTextField pprice = null;
        JTextField pstock = null;
        ControllerProduct instance = new ControllerProduct();
        instance.insertProduct(pname, pprice, pstock);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showProduct method, of class ControllerProduct.
     */
    @Test
    public void testShowProduct() {
        System.out.println("showProduct");
        JTable table = null;
        ControllerProduct instance = new ControllerProduct();
        instance.showProduct(table);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectRow method, of class ControllerProduct.
     */
    @Test
    public void testSelectRow() {
        System.out.println("selectRow");
        JTable table = null;
        JTextField id = null;
        JTextField pname = null;
        JTextField pprice = null;
        JTextField pstock = null;
        ControllerProduct instance = new ControllerProduct();
        instance.selectRow(table, id, pname, pprice, pstock);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editProduct method, of class ControllerProduct.
     */
    @Test
    public void testEditProduct() {
        System.out.println("editProduct");
        JTextField id = null;
        JTextField pname = null;
        JTextField price = null;
        JTextField stock = null;
        ControllerProduct instance = new ControllerProduct();
        instance.editProduct(id, pname, price, stock);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteProduct method, of class ControllerProduct.
     */
    @Test
    public void testDeleteProduct() {
        System.out.println("deleteProduct");
        JTextField id = null;
        ControllerProduct instance = new ControllerProduct();
        instance.deleteProduct(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearText method, of class ControllerProduct.
     */
    @Test
    public void testClearText() {
        System.out.println("clearText");
        JTextField id = null;
        JTextField pname = null;
        JTextField price = null;
        JTextField stock = null;
        ControllerProduct instance = new ControllerProduct();
        instance.clearText(id, pname, price, stock);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
