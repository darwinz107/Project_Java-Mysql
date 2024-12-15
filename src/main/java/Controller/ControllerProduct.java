package Controller;

import java.sql.Statement;
import configuration.CConexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.ModelProduct;

/**
 * Clase que gestiona las operaciones CRUD para la entidad Producto.
 * Permite insertar, visualizar, editar, eliminar y seleccionar productos en una base de datos.
 * 
 * @author darwin
 */
public class ControllerProduct {

    /**
     * Inserta un producto en la base de datos.
     * 
     * @param pname Campo de texto para el nombre del producto.
     * @param pprice Campo de texto para el precio del producto.
     * @param pstock Campo de texto para el stock del producto.
     */
    public void insertProduct(JTextField pname, JTextField pprice, JTextField pstock) {
        configuration.CConexion connection = new CConexion();
        model.ModelProduct model = new ModelProduct();

        String query = "INSERT INTO product(productName,productPrice,stock) values(?,?,?);";

        try {
            // Asignar valores desde los campos de texto al modelo.
            model.setProductName(pname.getText());
            model.setProductPrice(Double.valueOf(pprice.getText()));
            model.setProductStock(Integer.parseInt(pstock.getText()));

            // Preparar y ejecutar la consulta SQL.
            CallableStatement cs = connection.setConnection().prepareCall(query);
            cs.setString(1, model.getProductName());
            cs.setDouble(2, model.getProductPrice());
            cs.setInt(3, model.getProductStock());
            cs.execute();

            // Mostrar mensaje de éxito.
            JOptionPane.showMessageDialog(null, "¡Registro exitoso!");
        } catch (Exception e) {
            // Manejar errores y mostrar un mensaje de error.
            JOptionPane.showMessageDialog(null, "Error al registrar: " + e.toString());
        } finally {
            // Cerrar la conexión a la base de datos.
            connection.closeConnection();
        }
    }

    /**
     * Muestra los productos en una tabla especificada.
     * 
     * @param table Tabla donde se mostrarán los productos.
     */
    public void showProduct(JTable table) {
        configuration.CConexion connection = new CConexion();
        model.ModelProduct mproduct = new ModelProduct();

        String query = "SELECT * FROM dbdar.product";
        DefaultTableModel mtable = new DefaultTableModel();

        // Definir las columnas de la tabla.
        mtable.addColumn("ProductID");
        mtable.addColumn("ProductName");
        mtable.addColumn("Price");
        mtable.addColumn("Stock");

        table.setModel(mtable);

        try {
            // Ejecutar la consulta y obtener los resultados.
            Statement st = connection.setConnection().createStatement();
            ResultSet rs = st.executeQuery(query);

            // Recorrer los resultados y llenar la tabla.
            while (rs.next()) {
                mproduct.setIdProduct(rs.getInt("productID"));
                mproduct.setProductName(rs.getString("productName"));
                mproduct.setProductPrice(rs.getDouble("productPrice"));
                mproduct.setProductStock(rs.getInt("stock"));

                mtable.addRow(new Object[]{
                    mproduct.getIdProduct(), 
                    mproduct.getProductName(), 
                    mproduct.getProductPrice(), 
                    mproduct.getProductStock()
                });
            }
            table.setModel(mtable);
        } catch (Exception e) {
            // Manejar errores al mostrar los productos.
            JOptionPane.showMessageDialog(null, "Error al mostrar productos: " + e.toString());
        } finally {
            // Cerrar la conexión a la base de datos.
            connection.closeConnection();
        }
    }

    /**
     * Selecciona una fila de una tabla y muestra los valores en campos de texto.
     * 
     * @param table Tabla de la que se seleccionará la fila.
     * @param id Campo de texto para el ID del producto.
     * @param pname Campo de texto para el nombre del producto.
     * @param pprice Campo de texto para el precio del producto.
     * @param pstock Campo de texto para el stock del producto.
     */
    public void selectRow(JTable table, JTextField id, JTextField pname, JTextField pprice, JTextField pstock) {
        try {
            // Obtener la fila seleccionada.
            int row = table.getSelectedRow();

            // Si hay una fila seleccionada, asignar sus valores a los campos de texto.
            if (row >= 0) {
                id.setText(table.getValueAt(row, 0).toString());
                pname.setText(table.getValueAt(row, 1).toString());
                pprice.setText(table.getValueAt(row, 2).toString());
                pstock.setText(table.getValueAt(row, 3).toString());
            }
        } catch (Exception e) {
            // Manejar errores al seleccionar la fila.
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    /**
     * Edita un producto existente en la base de datos.
     * 
     * @param id Campo de texto para el ID del producto.
     * @param pname Campo de texto para el nombre del producto.
     * @param price Campo de texto para el precio del producto.
     * @param stock Campo de texto para el stock del producto.
     */
    public void editProduct(JTextField id, JTextField pname, JTextField price, JTextField stock) {
        configuration.CConexion connection = new CConexion();
        model.ModelProduct mproduct = new ModelProduct();

        String query = "UPDATE product SET productName = ?, productPrice = ?, stock = ? WHERE productID = ?;";

        try {
            // Asignar valores desde los campos de texto al modelo.
            mproduct.setIdProduct(Integer.parseInt(id.getText()));
            mproduct.setProductName(pname.getText());
            mproduct.setProductPrice(Double.valueOf(price.getText()));
            mproduct.setProductStock(Integer.parseInt(stock.getText()));

            // Preparar y ejecutar la consulta SQL.
            CallableStatement cs = connection.setConnection().prepareCall(query);
            cs.setString(1, mproduct.getProductName());
            cs.setDouble(2, mproduct.getProductPrice());
            cs.setInt(3, mproduct.getProductStock());
            cs.setInt(4, mproduct.getIdProduct());
            cs.execute();

            // Mostrar mensaje de éxito.
            JOptionPane.showMessageDialog(null, "¡Edición exitosa!");
        } catch (Exception e) {
            // Manejar errores al editar el producto.
            JOptionPane.showMessageDialog(null, "Error al editar: " + e.toString());
        } finally {
            // Cerrar la conexión a la base de datos.
            connection.closeConnection();
        }
    }

    /**
     * Elimina un producto de la base de datos.
     * 
     * @param id Campo de texto para el ID del producto a eliminar.
     */
    public void deleteProduct(JTextField id) {
        configuration.CConexion connection = new CConexion();
        model.ModelProduct mproduct = new ModelProduct();

        String query = "DELETE FROM product WHERE productID = ?;";

        try {
            // Asignar el ID del producto desde el campo de texto.
            mproduct.setIdProduct(Integer.parseInt(id.getText()));

            // Preparar y ejecutar la consulta SQL.
            CallableStatement cs = connection.setConnection().prepareCall(query);
            cs.setInt(1, mproduct.getIdProduct());
            cs.execute();

            // Mostrar mensaje de éxito.
            JOptionPane.showMessageDialog(null, "¡Eliminación exitosa!");
        } catch (Exception e) {
            // Manejar errores al eliminar el producto.
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.toString());
        } finally {
            // Cerrar la conexión a la base de datos.
            connection.closeConnection();
        }
    }

    /**
     * Limpia los campos de texto.
     * 
     * @param id Campo de texto para el ID del producto.
     * @param pname Campo de texto para el nombre del producto.
     * @param price Campo de texto para el precio del producto.
     * @param stock Campo de texto para el stock del producto.
     */
    public void clearText(JTextField id, JTextField pname, JTextField price, JTextField stock) {
        id.setText("");
        pname.setText("");
        price.setText("");
        stock.setText("");
    }
}
