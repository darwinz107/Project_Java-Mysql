/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import configuration.CConexion;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.ModelBook_detail;
import model.ModelCsutomer;



/**
 *
 * @author darwin
 */
public class ControllerBook {
    
     /**
     * Este método busca un cliente en la base de datos utilizando su nombre y muestra los resultados en una tabla.
     *
     * @param tableCustomer JTable donde se mostrarán los resultados de la búsqueda
     * @param customerName JTextField que contiene el nombre del cliente a buscar
     */
    public void searchxCustomer(JTable tableCustomer, JTextField customerName){
        configuration.CConexion connection = new CConexion();
        model.ModelCsutomer mcustomer = new ModelCsutomer();
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("CustomerID");
        model.addColumn("CustomerName");
        model.addColumn("LastNameMom");
        model.addColumn("LastNameDad");
        
        tableCustomer.setModel(model);
        mcustomer.setCustomerName(customerName.getText());
        
        if(mcustomer.getCustomerName().isEmpty()){
            model.setRowCount(0);
        } else {
            String querry = "SELECT * from dbdar.customer WHERE customerName LIKE ?;";
            
            try {
                PreparedStatement ps = connection.setConnection().prepareCall(querry);
                ps.setString(1, mcustomer.getCustomerName() + "%");
                
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    mcustomer.setIdCustomer(Integer.parseInt(rs.getString("customerID")));
                    mcustomer.setCustomerName(rs.getString("customerName"));
                    mcustomer.setCustomerLnMom(rs.getString("LastNameMom"));
                    mcustomer.setCustomerLnDad(rs.getString("LastNameDad"));
                    
                    model.addRow(new Object[]{mcustomer.getIdCustomer(), mcustomer.getCustomerName(), mcustomer.getCustomerLnMom(), mcustomer.getCustomerLnDad()});
                }
                tableCustomer.setModel(model);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Failed show, " + e.toString());
            } finally {
                connection.closeConnection();
            }
            // Deshabilitar la edición de las celdas de la tabla
            for (int column = 0; column < tableCustomer.getColumnCount(); column++) {
                Class<?> columnsClass = tableCustomer.getColumnClass(column);
                tableCustomer.setDefaultEditor(columnsClass, null);
            }
        }
    }

    /**
     * Este método selecciona una fila de la tabla y muestra el ID del cliente en un JTextField.
     *
     * @param table JTable donde se selecciona la fila
     * @param id JTextField donde se mostrará el ID del cliente seleccionado
     */
    public void selectRow(JTable table, JTextField id){
        model.ModelCsutomer mcustoemr = new ModelCsutomer();
        int row = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        try {
            if(row < model.getRowCount()){
                mcustoemr.setIdCustomer(Integer.parseInt(model.getValueAt(row, 0).toString())); 
            }
            id.setText(String.valueOf(mcustoemr.getIdCustomer()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed select row," + e.toString());
        }
    }

    /**
     * Este método procesa la compra de un libro, insertando los datos en la base de datos.
     *
     * @param quantityChoose JTextField que contiene la cantidad de libros elegidos
     * @param id JTextField que contiene el ID del cliente
     * @param lberrorQuantity JLabel donde se mostrarán los errores de cantidad
     */
    public void buy(JTextField quantityChoose, JTextField id, JLabel lberrorQuantity){
        model.ModelBook_detail model = new ModelBook_detail();
        model.setQuantityChoose(Integer.parseInt(quantityChoose.getText()));
        
        if(id.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe elegir al menos un cliente registrado");
        } else if(model.getQuantityChoose() == 0){
            lberrorQuantity.setText("Debe elegir al menos un libro");
        } else {
            lberrorQuantity.setText("");
            configuration.CConexion connection = new CConexion();
            model.ModelCsutomer mcustomer = new ModelCsutomer();
            mcustomer.setIdCustomer(Integer.parseInt(id.getText()));
            
            try {
                model.setBookID(1);
                model.setState(1);
                String querry = """
                             INSERT INTO dbdar.bookdetail(dbdar.bookdetail.fkcustomerID, dbdar.bookdetail.dateChoose, dbdar.bookdetail.quantityChoose, dbdar.bookdetail.fkstateID, dbdar.bookdetail.fkbookID) 
                             values(?, curdate(), ?, ?, ?);""";
                
                CallableStatement cs = connection.setConnection().prepareCall(querry);
                cs.setInt(1, mcustomer.getIdCustomer());
                cs.setInt(2, model.getQuantityChoose());
                cs.setInt(3, model.getState());
                cs.setInt(4, model.getBookID());
                cs.execute();
                
                JOptionPane.showMessageDialog(null, "Buy successful!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Failed buy," + e.getMessage());
            } finally {
                connection.closeConnection();
            }
        }
    }

    /**
     * Este método procesa el préstamo de un libro, validando las condiciones antes de insertar los datos en la base de datos.
     *
     * @param quantityChoose JTextField que contiene la cantidad de libros elegidos
     * @param id JTextField que contiene el ID del cliente
     * @param lberrorQuantity JLabel donde se mostrarán los errores de cantidad
     */
    public void borrow(JTextField quantityChoose, JTextField id, JLabel lberrorQuantity){
        model.ModelCsutomer mcustomer = new ModelCsutomer();
        model.ModelBook_detail model = new ModelBook_detail();
        model.setQuantityChoose(Integer.parseInt(quantityChoose.getText()));
        
        if(id.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe elegir al menos un cliente registrado");
            return;
        }
        if(model.getQuantityChoose() == 0){
            lberrorQuantity.setText("Debe elegir al menos un libro");
        } else if(model.getQuantityChoose() > 5){
            lberrorQuantity.setText("Solo puede prestar 5 libros por día");
        } else {
            mcustomer.setIdCustomer(Integer.parseInt(id.getText()));
            lberrorQuantity.setText("");
            configuration.CConexion connection = new CConexion();
            
            try {
                model.setBookID(1);
                model.setState(2);
                String querry = """
                             INSERT INTO dbdar.bookdetail(dbdar.bookdetail.fkcustomerID, dbdar.bookdetail.dateChoose, dbdar.bookdetail.quantityChoose, dbdar.bookdetail.fkstateID, dbdar.bookdetail.fkbookID) 
                             values(?, curdate(), ?, ?, ?);""";
                
                CallableStatement cs = connection.setConnection().prepareCall(querry);
                cs.setInt(1, mcustomer.getIdCustomer());
                cs.setInt(2, model.getQuantityChoose());
                cs.setInt(3, model.getState());
                cs.setInt(4, model.getBookID());
                cs.execute();
                
                JOptionPane.showMessageDialog(null, "Borrow successful!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Failed buy," + e.getMessage());
            } finally {
                connection.closeConnection();
            }
        }
    }
    
/**
 * Método para buscar a un cliente por su nombre y mostrar la información relacionada
 * en una tabla.
 *
 * @param table La tabla donde se mostrará la información del cliente.
 * @param cusName El campo de texto que contiene el nombre del cliente a buscar.
 */
public void searchxName(JTable table, JTextField cusName) {
    
    configuration.CConexion connection = new CConexion();
    model.ModelCsutomer mcustomer = new ModelCsutomer();
    
    model.ModelBook_detail mbook = new ModelBook_detail();
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("CustomerName");
    model.addColumn("LastNameMom");
    model.addColumn("LastNameDad");
    model.addColumn("BookName");
    model.addColumn("Autor");
    model.addColumn("Type");
    model.addColumn("DatePublish");
    model.addColumn("Date_Acquired");
    model.addColumn("Quantity");
    model.addColumn("Date_expiration");

    table.setModel(model);
    mcustomer.setCustomerName(cusName.getText());
    
    // Si el campo de nombre del cliente está vacío, limpiar la tabla.
    if (mcustomer.getCustomerName().isEmpty()) { 
        model.setRowCount(0);
    } else {
        try {
            // Consulta SQL para obtener los detalles de los libros prestados a un cliente.
            String querry = """
                            SELECT c.customerName, c.LastNameMom, c.LastNameDad, b.bookName, b.bookAutor, b.bookType, 
                            Max(b.datePublish) as datePublish, bd.dateChoose, SUM(bd.quantityChoose) as QuantityChoose, 
                            bd.fkstateID FROM dbdar.bookdetail as bd
                            INNER join dbdar.book as b ON bd.fkbookID = b.bookID
                            INNER join dbdar.customer as c ON bd.fkcustomerID = c.customerID
                            where c.customerName LIKE ? and bd.fkstateID = 2
                            group by c.customerName, c.LastNameMom, c.LastNameDad, bd.dateChoose, b.bookName, b.bookAutor, 
                            b.bookType;
                            """;
            PreparedStatement ps = connection.setConnection().prepareCall(querry);
            ps.setString(1, mcustomer.getCustomerName() + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) { 
                mcustomer.setCustomerName(rs.getString("customerName"));
                mcustomer.setCustomerLnMom(rs.getString("LastNameMom"));
                mcustomer.setCustomerLnDad(rs.getString("LastNameDad"));
                mbook.setDatePrest(rs.getDate("dateChoose"));
                
                LocalDate datePrest = mbook.getDatePrest().toLocalDate();
                LocalDate dueDate = datePrest.plusDays(15);  // Se calcula la fecha de vencimiento

                mbook.setBookName(rs.getString("bookName"));
                mbook.setBookType(rs.getString("bookType"));
                mbook.setDatePublish(rs.getString("datePublish"));
                mbook.setQuantityChoose(rs.getInt("QuantityChoose"));
                mbook.setBookAutor(rs.getString("bookAutor"));

                // Agregar una fila a la tabla con los datos obtenidos
                model.addRow(new Object[]{
                    mcustomer.getCustomerName(),
                    mcustomer.getCustomerLnMom(),
                    mcustomer.getCustomerLnDad(),
                    mbook.getBookName(),
                    mbook.getBookAutor(),
                    mbook.getBookType(),
                    mbook.getDatePublish(),
                    mbook.getDatePrest(),
                    mbook.getQuantityChoose(),
                    dueDate
                });
            }
            table.setModel(model);
            
        } catch (Exception e) {
            // En caso de error, mostrar un mensaje de fallo
            JOptionPane.showMessageDialog(null, "Failed search for name, " + e.toString());
        } finally {
            // Cerrar la conexión con la base de datos
            connection.closeConnection();
        }
    }
}

/**
 * Método para incrementar la cantidad adquirida de un libro.
 *
 * @param quantityDisp El campo de texto que contiene la cantidad disponible del libro.
 * @param quantityadquired El campo de texto que contiene la cantidad adquirida del libro.
 */
public void btnmajor(JTextField quantityDisp, JTextField quantityadquired) {
    model.ModelBook_detail model = new ModelBook_detail();
    model.setQuantityDisp(Integer.parseInt(quantityDisp.getText()));

    int qd = Integer.parseInt(quantityadquired.getText());
    // Incrementar la cantidad adquirida si es menor que la cantidad disponible.
    if (qd < model.getQuantityDisp()) {
        qd += 1;
        quantityadquired.setText(String.valueOf(qd));
    } else {
        JOptionPane.showMessageDialog(null, "There aren't more books");
    }
}

/**
 * Método para decrementar la cantidad adquirida de un libro.
 *
 * @param quantityDisp El campo de texto que contiene la cantidad disponible del libro.
 * @param quantityadquired El campo de texto que contiene la cantidad adquirida del libro.
 */
public void btnless(JTextField quantityDisp, JTextField quantityadquired) {
    model.ModelBook_detail model = new ModelBook_detail();
    model.setQuantityDisp(Integer.parseInt(quantityDisp.getText()));

    int qd = Integer.parseInt(quantityadquired.getText());
    // Decrementar la cantidad adquirida si es mayor que 0.
    if (qd == 0) {
        // No hacer nada si la cantidad adquirida es 0.
    } else {
        qd -= 1;
        quantityadquired.setText(String.valueOf(qd));
    }
}
   
// Variable que guarda el número de libro actual
    private int numBook = 0;

    /**
     * Obtiene el número del libro actual.
     * 
     * @return numBook el número del libro.
     */
    public int getNumBook() {
        return numBook;
    }

    /**
     * Establece el número del libro actual.
     * 
     * @param num el número del libro a establecer.
     */
    public void setNumBook(int num) {
        this.numBook = num;
    }

    // Variable que almacena el valor del número de libro
    int num = getNumBook();

    /**
     * Configura los detalles del libro en los campos de la interfaz gráfica.
     * 
     * @param bookName el campo para mostrar el nombre del libro.
     * @param autor el campo para mostrar el autor del libro.
     * @param type el campo para mostrar el tipo de libro.
     * @param editorial el campo para mostrar la editorial del libro.
     * @param datePublish el campo para mostrar la fecha de publicación del libro.
     * @param price el campo para mostrar el precio del libro.
     * @param quantity el campo para mostrar la cantidad disponible del libro.
     */
    public void setBook1(JLabel bookName, JTextField autor, JTextField type, JTextField editorial, JTextField datePublish, JTextField price, JTextField quantity) {
       
        // Se crea la conexión a la base de datos
        CConexion connection = new CConexion(); 
        
        // Se evalúa el número del libro para ejecutar el caso correspondiente
        switch(numBook) {
        
            // Caso para el libro con ID 1
            case 1:
                try {
                    // Consulta SQL para obtener los detalles del libro con ID 1
                    String querry = "SELECT * FROM dbdar.book WHERE bookID=?";
                    PreparedStatement ps = connection.setConnection().prepareCall(querry);
                    ps.setInt(1, 1); // Se establece el parámetro del ID del libro
                    ResultSet rs = ps.executeQuery(); // Se ejecuta la consulta
                    if (rs.next()) {
                        // Se establece el valor de los campos con los datos obtenidos
                        bookName.setText(rs.getString("bookName"));
                        autor.setText(rs.getString("bookAutor"));
                        type.setText(rs.getString("bookType"));
                        editorial.setText(rs.getString("editorial"));
                        datePublish.setText(rs.getString("datePublish"));
                        price.setText(rs.getString("price"));
                        quantity.setText(rs.getString("quantityDisp"));
                    }
                } catch(Exception e) {
                    // En caso de error, se muestra un mensaje de error
                    JOptionPane.showMessageDialog(null,"Error, "+e.toString());
                } finally {
                    // Se cierra la conexión a la base de datos
                    connection.closeConnection();
                }
                break;
                
            // Casos para los libros con ID 2, 3, 4, 5, 6, 7, 8, 9 y 10
            case 2:
                // Bloque similar al anterior para el libro con ID 2
                try {
                    String querry = "SELECT * FROM dbdar.book WHERE bookID=?";
                    PreparedStatement ps = connection.setConnection().prepareCall(querry);
                    ps.setInt(1, 2);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        bookName.setText(rs.getString("bookName"));
                        autor.setText(rs.getString("bookAutor"));
                        type.setText(rs.getString("bookType"));
                        editorial.setText(rs.getString("editorial"));
                        datePublish.setText(rs.getString("datePublish"));
                        price.setText(rs.getString("price"));
                        quantity.setText(rs.getString("quantityDisp"));
                    }
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(null,"Error, "+e.toString());
                } finally {
                    connection.closeConnection();
                }
                break;

            case 3:
                try {
                    String querry = "SELECT * FROM dbdar.book WHERE bookID=?";
                    PreparedStatement ps = connection.setConnection().prepareCall(querry);
                    ps.setInt(1, 3);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        bookName.setText(rs.getString("bookName"));
                        autor.setText(rs.getString("bookAutor"));
                        type.setText(rs.getString("bookType"));
                        editorial.setText(rs.getString("editorial"));
                        datePublish.setText(rs.getString("datePublish"));
                        price.setText(rs.getString("price"));
                        quantity.setText(rs.getString("quantityDisp"));
                    }
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(null,"Error, "+e.toString());
                } finally {
                    connection.closeConnection();
                }
                break;

            case 4:
                try {
                    String querry = "SELECT * FROM dbdar.book WHERE bookID=?";
                    PreparedStatement ps = connection.setConnection().prepareCall(querry);
                    ps.setInt(1, 4);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        bookName.setText(rs.getString("bookName"));
                        autor.setText(rs.getString("bookAutor"));
                        type.setText(rs.getString("bookType"));
                        editorial.setText(rs.getString("editorial"));
                        datePublish.setText(rs.getString("datePublish"));
                        price.setText(rs.getString("price"));
                        quantity.setText(rs.getString("quantityDisp"));
                    }
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(null,"Error, "+e.toString());
                } finally {
                    connection.closeConnection();
                }
                break;

            case 5:
                try {
                    String querry = "SELECT * FROM dbdar.book WHERE bookID=?";
                    PreparedStatement ps = connection.setConnection().prepareCall(querry);
                    ps.setInt(1, 5);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        bookName.setText(rs.getString("bookName"));
                        autor.setText(rs.getString("bookAutor"));
                        type.setText(rs.getString("bookType"));
                        editorial.setText(rs.getString("editorial"));
                        datePublish.setText(rs.getString("datePublish"));
                        price.setText(rs.getString("price"));
                        quantity.setText(rs.getString("quantityDisp"));
                    }
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(null,"Error, "+e.toString());
                } finally {
                    connection.closeConnection();
                }
                break;

            case 6:
                try {
                    String querry = "SELECT * FROM dbdar.book WHERE bookID=?";
                    PreparedStatement ps = connection.setConnection().prepareCall(querry);
                    ps.setInt(1, 6);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        bookName.setText(rs.getString("bookName"));
                        autor.setText(rs.getString("bookAutor"));
                        type.setText(rs.getString("bookType"));
                        editorial.setText(rs.getString("editorial"));
                        datePublish.setText(rs.getString("datePublish"));
                        price.setText(rs.getString("price"));
                        quantity.setText(rs.getString("quantityDisp"));
                    }
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(null,"Error, "+e.toString());
                } finally {
                    connection.closeConnection();
                }
                break;

            case 7:
                try {
                    String querry = "SELECT * FROM dbdar.book WHERE bookID=?";
                    PreparedStatement ps = connection.setConnection().prepareCall(querry);
                    ps.setInt(1, 7);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        bookName.setText(rs.getString("bookName"));
                        autor.setText(rs.getString("bookAutor"));
                        type.setText(rs.getString("bookType"));
                        editorial.setText(rs.getString("editorial"));
                        datePublish.setText(rs.getString("datePublish"));
                        price.setText(rs.getString("price"));
                        quantity.setText(rs.getString("quantityDisp"));
                    }
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(null,"Error, "+e.toString());
                } finally {
                    connection.closeConnection();
                }
                break;

            case 8:
                try {
                    String querry = "SELECT * FROM dbdar.book WHERE bookID=?";
                    PreparedStatement ps = connection.setConnection().prepareCall(querry);
                    ps.setInt(1, 8);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        bookName.setText(rs.getString("bookName"));
                        autor.setText(rs.getString("bookAutor"));
                        type.setText(rs.getString("bookType"));
                        editorial.setText(rs.getString("editorial"));
                        datePublish.setText(rs.getString("datePublish"));
                        price.setText(rs.getString("price"));
                        quantity.setText(rs.getString("quantityDisp"));
                    }
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(null,"Error, "+e.toString());
                } finally {
                    connection.closeConnection();
                }
                break;

            case 9:
                try {
                    String querry = "SELECT * FROM dbdar.book WHERE bookID=?";
                    PreparedStatement ps = connection.setConnection().prepareCall(querry);
                    ps.setInt(1, 9);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        bookName.setText(rs.getString("bookName"));
                        autor.setText(rs.getString("bookAutor"));
                        type.setText(rs.getString("bookType"));
                        editorial.setText(rs.getString("editorial"));
                        datePublish.setText(rs.getString("datePublish"));
                        price.setText(rs.getString("price"));
                        quantity.setText(rs.getString("quantityDisp"));
                    }
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(null,"Error, "+e.toString());
                } finally {
                    connection.closeConnection();
                }
                break;
                
            case 10:
                try {
                    String querry = "SELECT * FROM dbdar.book WHERE bookID=?";
                    PreparedStatement ps = connection.setConnection().prepareCall(querry);
                    ps.setInt(1, 10);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        bookName.setText(rs.getString("bookName"));
                        autor.setText(rs.getString("bookAutor"));
                        type.setText(rs.getString("bookType"));
                        editorial.setText(rs.getString("editorial"));
                        datePublish.setText(rs.getString("datePublish"));
                        price.setText(rs.getString("price"));
                        quantity.setText(rs.getString("quantityDisp"));
                    }
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(null,"Error, "+e.toString());
                } finally {
                    connection.closeConnection();
                }
                break;
        }
    }
    
}
