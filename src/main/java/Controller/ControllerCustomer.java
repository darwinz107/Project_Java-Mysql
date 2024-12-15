
package Controller;
import com.toedter.calendar.JDateChooser;
import java.sql.ResultSet;

import java.sql.Statement;
import java.sql.CallableStatement;
import configuration.CConexion;
import java.sql.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.ModelCsutomer;

/**
 *
 * @author darwin
 */
public class ControllerCustomer {
   /**
     * Muestra la lista de clientes en una tabla.
     * 
     * @param table JTable que muestra la información de los clientes.
     */
    public void showCustomer(JTable table){

        configuration.CConexion connection = new CConexion(); // Establece la conexión con la base de datos
        String querry = ""; // Declaración de la variable para la consulta SQL
        model.ModelCsutomer mCustomer = new model.ModelCsutomer(); // Instancia del modelo de cliente
        
        DefaultTableModel model = new DefaultTableModel(); // Modelo de tabla para mostrar los datos
        
        // Añadir las columnas a la tabla
        model.addColumn("CustomerID");
        model.addColumn("CustomerName");
        model.addColumn("LastNameMom");
        model.addColumn("LastNameDad");
        model.addColumn("Date of born");
        table.setModel(model); // Establece el modelo a la tabla
        
        // Consulta SQL para obtener los datos de la base de datos
        querry = "select customer.customerID, customer.customerName, customer.LastNameMom, customer.LastNameDad, customer.dateBorn from dbdar.customer;";
        
        Statement st;
        try {
            st = connection.setConnection().createStatement(); // Crea la sentencia para ejecutar la consulta
            ResultSet rs = st.executeQuery(querry); // Ejecuta la consulta y obtiene los resultados

            // Itera sobre los resultados y los añade a la tabla
            while(rs.next()){
                mCustomer.setIdCustomer(rs.getInt("customerID"));
                mCustomer.setCustomerName(rs.getString("customerName"));
                mCustomer.setCustomerLnMom(rs.getString("LastNameMom"));
                mCustomer.setCustomerLnDad(rs.getString("LastNameDad"));
                mCustomer.setDateBorn(rs.getDate("dateBorn"));
                model.addRow(new Object[]{mCustomer.getIdCustomer(), mCustomer.getCustomerName(), mCustomer.getCustomerLnMom(), mCustomer.getCustomerLnDad(), mCustomer.getDateBorn()});
            }
            table.setModel(model); // Actualiza el modelo de la tabla con los datos obtenidos

        } catch (Exception e) {
            // Muestra un mensaje si hay un error
            JOptionPane.showMessageDialog(null, "We couldn't show," + e.toString());
        } finally {
            connection.closeConnection(); // Cierra la conexión a la base de datos
        }
    }

    /**
     * Inserta un nuevo cliente en la base de datos después de realizar las validaciones correspondientes.
     * 
     * @param name JTextField que contiene el nombre del cliente.
     * @param lastNameMom JTextField que contiene el apellido de la madre del cliente.
     * @param lastNameDad JTextField que contiene el apellido del padre del cliente.
     * @param lberrorName JLabel que muestra el mensaje de error para el campo nombre.
     * @param lberrolnMom JLabel que muestra el mensaje de error para el campo apellido de madre.
     * @param lberrolnDad JLabel que muestra el mensaje de error para el campo apellido de padre.
     * @param dateBorn JDateChooser que permite seleccionar la fecha de nacimiento.
     * @param lberrorDate JLabel que muestra el mensaje de error para el campo fecha de nacimiento.
     */
    public void insertCustomer(JTextField name, JTextField lastNameMom, JTextField lastNameDad, JLabel lberrorName, JLabel lberrolnMom, JLabel lberrolnDad, JDateChooser dateBorn, JLabel lberrorDate){

        configuration.CConexion connection = new CConexion(); // Establece la conexión con la base de datos
        model.ModelCsutomer objtCustomer = new ModelCsutomer(); // Instancia del modelo de cliente
        
        String querry = "INSERT INTO customer(customerName, LastNameMom, LastNameDad, dateBorn) values(?, ?, ?, ?);"; // Consulta SQL para insertar el cliente

        boolean validar = true; // Variable para controlar la validez de los campos

        // Validación del campo de nombre
        if (name.getText().isEmpty()) {
            lberrorName.setText("Campo vacío");
            validar = false;
        } else if (!name.getText().matches("[a-zA-Z ]+")) {
            lberrorName.setText("Solo permitido letras");
            validar = false;
        } else {
            lberrorName.setText(""); 
        }

        // Validación del campo de apellido de madre
        if (lastNameMom.getText().isEmpty()) {
            lberrolnMom.setText("Campo vacío");
            validar = false;
        } else if (!lastNameMom.getText().matches("[a-zA-Z ]+")) {
            lberrolnMom.setText("Solo permitido letras");
            validar = false;
        } else {
            lberrolnMom.setText(""); 
        }

        // Validación del campo de apellido de padre
        if (lastNameDad.getText().isEmpty()) {
            lberrolnDad.setText("Campo vacío");
            validar = false;
        } else if (!lastNameDad.getText().matches("[a-zA-Z ]+")) {
            lberrolnDad.setText("Solo permitido letras");
            validar = false;
        } else {
            lberrolnDad.setText(""); 
        }

        // Validación de la fecha de nacimiento
        if(dateBorn.getDate() == null){
            lberrorDate.setText("Campo vacío");
            validar = false;
        } else {
            java.util.Date dateChoose = dateBorn.getDate();
            java.util.Date dateNow = new java.util.Date();

            if(dateChoose.after(dateNow)){
                lberrorDate.setText("No puede escoger fechas futuras");
                validar = false;
            } else {
                lberrorDate.setText("");
            }
        }

        // Si las validaciones son correctas, se procede a insertar el cliente en la base de datos
        if (validar) {
            try {
                java.util.Date dateBornChose = dateBorn.getDate();
                Date dateBornSql = new Date(dateBornChose.getTime());
                objtCustomer.setCustomerName(name.getText());
                objtCustomer.setCustomerLnMom(lastNameMom.getText());
                objtCustomer.setCustomerLnDad(lastNameDad.getText());
                objtCustomer.setDateBorn(dateBornSql);
                CallableStatement cs = connection.setConnection().prepareCall(querry);
                cs.setString(1, objtCustomer.getCustomerName());
                cs.setString(2, objtCustomer.getCustomerLnMom());
                cs.setString(3, objtCustomer.getCustomerLnDad());
                cs.setDate(4, objtCustomer.getDateBorn());
                cs.execute(); // Ejecuta la consulta de inserción
                
                JOptionPane.showMessageDialog(null, "Register successful!"); // Muestra mensaje de éxito

            } catch (Exception e) {
                // Muestra un mensaje si ocurre un error durante la inserción
                JOptionPane.showMessageDialog(null, "We can't insert customers," + e.toString());
            } finally {
                connection.closeConnection(); // Cierra la conexión a la base de datos
            }
        }
    };


 /**
 * Este método se utiliza para seleccionar una fila de una tabla y
 * mostrar los valores correspondientes en los campos de texto y el 
 * selector de fecha. 
 *
 * @param table JTable que contiene los datos.
 * @param id Campo de texto para mostrar el ID del cliente.
 * @param name Campo de texto para mostrar el nombre del cliente.
 * @param lastNameMom Campo de texto para mostrar el apellido materno del cliente.
 * @param lastNameDad Campo de texto para mostrar el apellido paterno del cliente.
 * @param dateBorn Selector de fecha para mostrar la fecha de nacimiento del cliente.
 */
public void selectRow(JTable table, JTextField id, JTextField name, JTextField lastNameMom, JTextField lastNameDad, JDateChooser dateBorn) {
    try {
        // Obtener la fila seleccionada
        int row = table.getSelectedRow();
        
        // Verificar que la fila seleccionada sea válida
        if (row >= 0) {
            // Mostrar los datos correspondientes de la fila seleccionada en los campos
            id.setText(table.getValueAt(row, 0).toString());
            name.setText(table.getValueAt(row, 1).toString());
            lastNameMom.setText(table.getValueAt(row, 2).toString());
            lastNameDad.setText(table.getValueAt(row, 3).toString());
            
            // Convertir la fecha de la tabla a un formato adecuado
            Object date = table.getValueAt(row, 4);
            Date dateSql = (Date) date;
            java.util.Date dateUtil = new java.util.Date(dateSql.getTime());
            dateBorn.setDate(dateUtil);
        }      
        
    } catch (Exception e) {
        // En caso de error, mostrar un mensaje
        JOptionPane.showMessageDialog(null, "Error al seleccionar: " + e.toString());
    }
}

/**
 * Este método permite editar los datos de un cliente. Realiza la validación
 * de los campos antes de actualizar los datos en la base de datos.
 *
 * @param table JTable que contiene los datos.
 * @param id Campo de texto para el ID del cliente.
 * @param name Campo de texto para el nombre del cliente.
 * @param lastNameMom Campo de texto para el apellido materno del cliente.
 * @param lastNameDad Campo de texto para el apellido paterno del cliente.
 * @param dateBorn Selector de fecha para la fecha de nacimiento del cliente.
 * @param lberrorName Etiqueta para mostrar errores de validación en el nombre.
 * @param lberrolnMom Etiqueta para mostrar errores de validación en el apellido materno.
 * @param lberrolnDad Etiqueta para mostrar errores de validación en el apellido paterno.
 */
public void editCustomer(JTable table, JTextField id, JTextField name, JTextField lastNameMom, JTextField lastNameDad, JDateChooser dateBorn, JLabel lberrorName, JLabel lberrolnMom, JLabel lberrolnDad) {
    // Crear una nueva conexión a la base de datos
    configuration.CConexion connection = new CConexion();
    model.ModelCsutomer mc = new ModelCsutomer();
    
    // Consulta SQL para actualizar los datos del cliente
    String querry = "UPDATE customer SET customerName=?, LastNameMom=?, LastNameDad=?, dateBorn=? WHERE customerID=?";
    
    // Verificar si se ha seleccionado una fila en la tabla
    if (table.getSelectedRow() < 0) {
        JOptionPane.showMessageDialog(null, "Debe escoger al menos un campo en la tabla");
        // Limpiar los mensajes de error
        lberrorName.setText("");
        lberrolnMom.setText("");
        lberrolnDad.setText("");
    } else {
        // Validar los campos antes de realizar la actualización
        boolean validar = true;

        // Validar el campo de nombre
        if (name.getText().isEmpty()) {
            lberrorName.setText("Campo vacío");
            validar = false;
        } else if (!name.getText().matches("[a-zA-Z ]+")) {
            lberrorName.setText("Solo permitido letras");
            validar = false;
        } else {
            lberrorName.setText(""); 
        }

        // Validar el campo de apellido materno
        if (lastNameMom.getText().isEmpty()) {
            lberrolnMom.setText("Campo vacío");
            validar = false;
        } else if (!lastNameMom.getText().matches("[a-zA-Z ]+")) {
            lberrolnMom.setText("Solo permitido letras");
            validar = false;
        } else {
            lberrolnMom.setText(""); 
        }

        // Validar el campo de apellido paterno
        if (lastNameDad.getText().isEmpty()) {
            lberrolnDad.setText("Campo vacío");
            validar = false;
        } else if (!lastNameDad.getText().matches("[a-zA-Z ]+")) {
            lberrolnDad.setText("Solo permitido letras");
            validar = false;
        } else {
            lberrolnDad.setText(""); 
        }

        // Si la validación fue exitosa, proceder con la actualización
        if (validar) {
            // Obtener la fecha seleccionada en el JDateChooser
            java.util.Date dateChose = dateBorn.getDate();
            Date dateChoseSql = new Date(dateChose.getTime());

            try {
                // Establecer los valores en el modelo de cliente
                mc.setIdCustomer(Integer.parseInt(id.getText()));
                mc.setCustomerName(name.getText());
                mc.setCustomerLnMom(lastNameMom.getText());
                mc.setCustomerLnDad(lastNameDad.getText());
                mc.setDateBorn(dateChoseSql);
                
                // Preparar la llamada al procedimiento almacenado
                CallableStatement cs = connection.setConnection().prepareCall(querry);
                cs.setString(1, mc.getCustomerName());
                cs.setString(2, mc.getCustomerLnMom());
                cs.setString(3, mc.getCustomerLnDad());
                cs.setDate(4, mc.getDateBorn());
                cs.setInt(5, mc.getIdCustomer());
                
                // Ejecutar la consulta
                cs.execute();
                
                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(null, "¡Actualización exitosa!");
                
            } catch (Exception e) {
                // En caso de error, mostrar mensaje
                JOptionPane.showMessageDialog(null, "No pudimos actualizar: " + e.toString());
            } finally {
                // Cerrar la conexión a la base de datos
                connection.closeConnection();
            }
        } else {
            // Si no pasa la validación, no hacer nada
        }
    }
}
    /**
     * Elimina un cliente de la base de datos utilizando el ID seleccionado de la tabla.
     * Valida que los campos del nombre, apellido materno y apellido paterno no estén vacíos
     * y que contengan solo letras. Si los campos son válidos, se ejecuta la consulta para eliminar el cliente.
     * 
     * @param table Tabla que contiene los datos del cliente.
     * @param id Campo de texto que contiene el ID del cliente a eliminar.
     * @param name Campo de texto que contiene el nombre del cliente.
     * @param lastNameMom Campo de texto que contiene el apellido materno del cliente.
     * @param lastNameDad Campo de texto que contiene el apellido paterno del cliente.
     * @param lberrorName Etiqueta para mostrar mensajes de error relacionados con el nombre.
     * @param lberrolnMom Etiqueta para mostrar mensajes de error relacionados con el apellido materno.
     * @param lberrolnDad Etiqueta para mostrar mensajes de error relacionados con el apellido paterno.
     */
    public void deleteCustomer(JTable table, JTextField id, JTextField name, JTextField lastNameMom, JTextField lastNameDad, JLabel lberrorName, JLabel lberrolnMom, JLabel lberrolnDad) {

        // Crear una nueva conexión a la base de datos
        configuration.CConexion connection = new CConexion();
        model.ModelCsutomer model = new ModelCsutomer();

        // Consulta SQL para eliminar un cliente por su ID
        String querry = "DELETE FROM customer WHERE customerID=?";

        // Verificar si se ha seleccionado una fila en la tabla
        if (table.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Debe escoger al menos un campo en la tabla");
        } else {
            // Variable de validación para los campos de texto
            boolean validar = true;

            // Validación para el campo nombre
            if (name.getText().isEmpty()) {
                lberrorName.setText("Campo vacío");
                validar = false;
            } else if (!name.getText().matches("[a-zA-Z ]+")) {
                lberrorName.setText("Solo permitido letras");
                validar = false;
            } else {
                lberrorName.setText("");
            }

            // Validación para el campo apellido materno
            if (lastNameMom.getText().isEmpty()) {
                lberrolnMom.setText("Campo vacío");
                validar = false;
            } else if (!lastNameMom.getText().matches("[a-zA-Z ]+")) {
                lberrolnMom.setText("Solo permitido letras");
                validar = false;
            } else {
                lberrolnMom.setText("");
            }

            // Validación para el campo apellido paterno
            if (lastNameDad.getText().isEmpty()) {
                lberrolnDad.setText("Campo vacío");
                validar = false;
            } else if (!lastNameDad.getText().matches("[a-zA-Z ]+")) {
                lberrolnDad.setText("Solo permitido letras");
                validar = false;
            } else {
                lberrolnDad.setText("");
            }

            // Si los campos son válidos, se procede a ejecutar la consulta de eliminación
            if (validar) {
                try {
                    model.setIdCustomer(Integer.parseInt(id.getText()));
                    // Preparar y ejecutar la consulta
                    CallableStatement st = connection.setConnection().prepareCall(querry);
                    st.setInt(1, model.getIdCustomer());
                    st.execute();
                    JOptionPane.showMessageDialog(null, "Delete successful!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                } finally {
                    connection.closeConnection();
                }
            }
        }
    }

    /**
     * Limpia todos los campos de texto y el campo de selección de fecha.
     * 
     * @param id Campo de texto que contiene el ID del cliente.
     * @param name Campo de texto que contiene el nombre del cliente.
     * @param lastNameMom Campo de texto que contiene el apellido materno del cliente.
     * @param lastNameDad Campo de texto que contiene el apellido paterno del cliente.
     * @param dateBorn Campo de selección de fecha del cliente.
     */
    public void clearText(JTextField id, JTextField name, JTextField lastNameMom, JTextField lastNameDad, JDateChooser dateBorn) {
        // Limpiar los campos de texto
        id.setText("");
        name.setText("");
        lastNameMom.setText("");
        lastNameDad.setText("");
        dateBorn.setDate(null);
    }

    
    
}
