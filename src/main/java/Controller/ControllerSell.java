
package Controller;

import configuration.CConexion;
import java.sql.CallableStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.ModelCsutomer;

import javax.swing.JOptionPane;
import model.ModelProduct;
import model.ModelReceipt;

/**
 *
 * @author darwin
 */
public class ControllerSell {
    
     /**
     * Busca un cliente en la base de datos y muestra los resultados en la tabla.
     * Si el nombre del cliente no está vacío, realiza una búsqueda utilizando una consulta SQL 
     * y muestra los resultados en la tabla correspondiente.
     * 
     * @param tableCustomer Tabla donde se mostrarán los resultados de la búsqueda de clientes.
     * @param id Campo de texto que contiene el ID del cliente.
     * @param customerName Campo de texto que contiene el nombre del cliente para la búsqueda.
     * @param name Campo de texto para mostrar el nombre del cliente.
     * @param lnmom Campo de texto para mostrar el apellido materno del cliente.
     * @param lndad Campo de texto para mostrar el apellido paterno del cliente.
     */
    public void searchxCustomer(JLabel errorCus,JTable tableCustomer, JTextField id, JTextField customerName, JTextField name, JTextField lnmom, JTextField lndad) {
    
        // Crear una nueva conexión a la base de datos
        configuration.CConexion connection = new configuration.CConexion();
        model.ModelCsutomer mcustomer = new ModelCsutomer();
        DefaultTableModel tbmodel = new DefaultTableModel();
        
        // Agregar las columnas a la tabla
        tbmodel.addColumn("CustomerID");
        tbmodel.addColumn("CustomerName");
        tbmodel.addColumn("LastNameMom");
        tbmodel.addColumn("LastNameDad");
        
        // Asignar el modelo a la tabla
        tableCustomer.setModel(tbmodel);
        
        // Consulta SQL para buscar clientes por nombre
        String querry = "SELECT * from dbdar.customer WHERE customerName LIKE ?;";
        
        try {
            mcustomer.setCustomerName(customerName.getText());
            // Si el nombre del cliente está vacío, se limpian los campos de texto
            if (mcustomer.getCustomerName().isEmpty()) {
                id.setText("");
                name.setText("");
                lnmom.setText("");
                lndad.setText("");
                errorCus.setText("");
                
            } else if(!mcustomer.getCustomerName().matches("[a-zA-Z ]+")){
                errorCus.setText("Solo permitido letras");
                id.setText("");
                name.setText("");
                lnmom.setText("");
                lndad.setText("");
                
            }else{
                errorCus.setText("");
                // Si el nombre no está vacío, ejecutar la consulta
                PreparedStatement ps = connection.setConnection().prepareStatement(querry);
                ps.setString(1, mcustomer.getCustomerName() + "%");

                ResultSet rs = ps.executeQuery();

                // Rellenar la tabla con los resultados de la consulta
                while (rs.next()) {
                    mcustomer.setIdCustomer(rs.getInt("customerID"));
                    mcustomer.setCustomerName(rs.getString("customerName"));
                    mcustomer.setCustomerLnMom(rs.getString("LastNameMom"));
                    mcustomer.setCustomerLnDad(rs.getString("LastNameDad"));
                    
                    tbmodel.addRow(new Object[]{mcustomer.getIdCustomer(), mcustomer.getCustomerName(), mcustomer.getCustomerLnMom(), mcustomer.getCustomerLnDad()});
                }
                tableCustomer.setModel(tbmodel);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed show, " + e.getMessage());
        } finally {
            connection.closeConnection();
        }
        
        // Deshabilitar la edición de las celdas de la tabla
        for (int column = 0; column < tableCustomer.getColumnCount(); column++) {
            Class<?> columnsClass = tableCustomer.getColumnClass(column);
            tableCustomer.setDefaultEditor(columnsClass, null);
        }
    }
    
    /**
     * Muestra los productos que coinciden con el nombre ingresado en el campo de búsqueda.
     * Si el nombre del producto no está vacío, se realiza una búsqueda y los resultados se muestran
     * en la tabla correspondiente.
     * 
     * @param pname Campo de texto que contiene el nombre del producto para la búsqueda.
     * @param ptable Tabla donde se mostrarán los resultados de la búsqueda de productos.
     * @param name Campo de texto para mostrar el nombre del producto.
     * @param id Campo de texto para mostrar el ID del producto.
     * @param price Campo de texto para mostrar el precio del producto.
     * @param stock Campo de texto para mostrar el stock del producto.
     */
    public void showxProducts(JLabel errorProduct,JTextField pname, JTable ptable, JTextField name, JTextField id, JTextField price, JTextField stock) {
    
        // Crear una nueva conexión a la base de datos
        configuration.CConexion connection = new CConexion();
        model.ModelProduct mproduct = new ModelProduct();
        DefaultTableModel mtable = new DefaultTableModel();
        
        // Agregar las columnas a la tabla
        mtable.addColumn("ProductID");
        mtable.addColumn("ProductName");
        mtable.addColumn("Price");
        mtable.addColumn("Stock");
        
        // Asignar el modelo a la tabla
        ptable.setModel(mtable);
        
        // Consulta SQL para buscar productos por nombre
        String querry = "SELECT * FROM dbdar.product WHERE productName LIKE ?;";
        
        try {
            mproduct.setProductName(pname.getText());
            // Si el nombre del producto está vacío, se limpian los campos de texto
            if (mproduct.getProductName().isEmpty()) {
                id.setText("");
                name.setText("");
                price.setText("");
                stock.setText("");
                errorProduct.setText("");
            } else if(!mproduct.getProductName().matches("[a-zA-Z ]+")){
            errorProduct.setText("Solo permitido letras");
            id.setText("");
                name.setText("");
                price.setText("");
                stock.setText("");
                
            }
            else {
                errorProduct.setText("");
                // Si el nombre no está vacío, ejecutar la consulta
                PreparedStatement ps = connection.setConnection().prepareCall(querry);
                ps.setString(1, mproduct.getProductName() + "%");

                ResultSet rs = ps.executeQuery();

                // Rellenar la tabla con los resultados de la consulta
                while (rs.next()) {
                    mproduct.setIdProduct(rs.getInt("productID"));
                    mproduct.setProductName(rs.getString("productName"));
                    mproduct.setProductPrice(rs.getDouble("productPrice"));
                    mproduct.setProductStock(rs.getInt("stock"));

                    mtable.addRow(new Object[]{mproduct.getIdProduct(), mproduct.getProductName(), mproduct.getProductPrice(), mproduct.getProductStock()});
                }
                ptable.setModel(mtable);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed show, " + e.toString());
        } finally {
            connection.closeConnection();
        }
        
        // Deshabilitar la edición de las celdas de la tabla
        for (int column = 0; column < ptable.getColumnCount(); column++) {
            Class<?> columnClass = ptable.getColumnClass(column);
            ptable.setDefaultEditor(columnClass, null);
        }
    }
    
    /**
     * Selecciona una fila de la tabla y muestra los detalles del cliente en los campos de texto.
     * 
     * @param table Tabla desde la cual se selecciona la fila.
     * @param id Campo de texto para mostrar el ID del cliente.
     * @param name Campo de texto para mostrar el nombre del cliente.
     * @param lnmom Campo de texto para mostrar el apellido materno del cliente.
     * @param lndad Campo de texto para mostrar el apellido paterno del cliente.
     */
    public void selectRow(JTable table, JTextField id, JTextField name, JTextField lnmom, JTextField lndad) {
    
        try {
            int row = table.getSelectedRow();
            
            if (row >= 0) {
                id.setText(table.getValueAt(row, 0).toString());
                name.setText(table.getValueAt(row, 1).toString());
                lnmom.setText(table.getValueAt(row, 2).toString());
                lndad.setText(table.getValueAt(row, 3).toString());
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed select, " + e.toString());
        }
    }

     
   /**
 * Método que selecciona una fila de un JTable y asigna sus valores a los campos de texto.
 *
 * @param table  La tabla de productos de la que se selecciona la fila.
 * @param id     Campo de texto donde se muestra el ID del producto.
 * @param name   Campo de texto donde se muestra el nombre del producto.
 * @param price  Campo de texto donde se muestra el precio del producto.
 * @param stock  Campo de texto donde se muestra el stock disponible del producto.
 * @param price2 Campo de texto donde se muestra nuevamente el precio del producto.
 */
public void selectRowProduct(JTable table, JTextField id, JTextField name, JTextField price, JTextField stock, JTextField price2) {
    try {
        int row = table.getSelectedRow();

        if (row >= 0) {
            id.setText(table.getValueAt(row, 0).toString());
            name.setText(table.getValueAt(row, 1).toString());
            price.setText(table.getValueAt(row, 2).toString());
            stock.setText(table.getValueAt(row, 3).toString());
            price2.setText(table.getValueAt(row, 2).toString());
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Failed select, " + e.toString());
    }
}

/**
 * Valida si la cantidad ingresada es un número entero.
 *
 * @param quantity Cantidad a validar.
 * @return true si la cantidad es válida (es un número entero), false en caso contrario.
 */
private boolean isValidQuantity(String quantity) {
    try {
        Integer.parseInt(quantity);
        
        return false;
    } catch (NumberFormatException e) {
        return true;
    }
}

/**
 * Método que agrega un producto a la tabla de ventas después de validaciones.
 *
 * @param tableaddp   Tabla en la que se agregarán los productos vendidos.
 * @param customerID   Campo de texto con el ID del cliente.
 * @param productID   Campo de texto con el ID del producto.
 * @param cusname     Campo de texto con el nombre del cliente.
 * @param pname       Campo de texto con el nombre del producto.
 * @param priceSell   Campo de texto con el precio de venta del producto.
 * @param quantitySell Campo de texto con la cantidad a vender.
 * @param stock       Campo de texto con el stock disponible del producto.
 * @param jl16        Etiqueta donde se muestra el mensaje de error de cantidad.
 * @param errorcusID  Etiqueta de error para el ID del cliente.
 * @param errorproID  Etiqueta de error para el ID del producto.
 */
public void addProduct(JTable tableaddp,JTextField customerID, JTextField productID, JTextField cusname, JTextField pname, JTextField priceSell, JTextField quantitySell, JTextField stock, JLabel jl16, JLabel errorcusID, JLabel errorproID) {
    DefaultTableModel model = (DefaultTableModel) tableaddp.getModel();

    boolean validar = true;
    if (productID.getText().isEmpty() || cusname.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Debe elegir almenos un cliente y un producto");
       // errorcusID.setText("Campo vacio");
       // errorproID.setText("Campo vacio");
        jl16.setText("");
        validar = false;
    }  else {
        validar = true;
        jl16.setText("");
        errorcusID.setText("");
        errorproID.setText("");
    }
    if (isValidQuantity(quantitySell.getText())) {
        jl16.setText("Solo permitido números");
        errorcusID.setText("");
        errorproID.setText("");
        validar = false;
    }else{
        jl16.setText("");
    validar = true;
    }
    if (quantitySell.getText().isEmpty()) {
        jl16.setText("Campo vacio");
        validar = false;
    } else {
        jl16.setText("");
        validar = true;
    }

    if (validar) {
        DecimalFormat format = new DecimalFormat("#.##");
        int ProductID = Integer.parseInt(productID.getText());
        int IDcustomer = Integer.parseInt(customerID.getText());

        for (int i = 0; i < tableaddp.getRowCount(); i++) {
           
            int customerRegister =Integer.parseInt(tableaddp.getValueAt(i, 0).toString()) ;
            int ProductRegister = Integer.parseInt(tableaddp.getValueAt(i, 2).toString());

            if (ProductRegister == ProductID && customerRegister==IDcustomer) {
                JOptionPane.showMessageDialog(null, "Usted ya ha registrado este producto");
                return;
            }
        }

        int availableStock = Integer.parseInt(stock.getText());
        int QuantitySell = Integer.parseInt(quantitySell.getText());

        if (QuantitySell > availableStock) {
            JOptionPane.showMessageDialog(null, "We don't have that stock quantity");
            return;
        }

        String customerName = cusname.getText();
        String Pname = pname.getText();
        double PriceUnit = Double.parseDouble(priceSell.getText());
        double subTotal = PriceUnit * QuantitySell;

        model.addRow(new Object[]{IDcustomer,customerName, ProductID, Pname, PriceUnit, QuantitySell, subTotal});
    } else {
        // JOptionPane.showMessageDialog(null,"Debe llenar los campos correctamente");
    }
}

/**
 * Método que elimina la fila seleccionada de la tabla de ventas.
 *
 * @param table Tabla en la que se desea eliminar la fila seleccionada.
 */
public void deleteSelectedRow(JTable table) {
    int itemSelected = table.getSelectedRow();
    DefaultTableModel model = (DefaultTableModel) table.getModel();

    if (itemSelected != -1) {
        model.removeRow(itemSelected);
    } else {
        JOptionPane.showMessageDialog(null, "Debe elegir almenos una columna a eliminar");
    }
}

/**
 * Método que calcula el total de la venta, incluyendo el IVA, y lo asigna a las etiquetas correspondientes.
 *
 * @param table La tabla de ventas cuyos productos se sumarán para calcular el total.
 * @param IVA   Etiqueta donde se mostrará el valor del IVA.
 * @param total Etiqueta donde se mostrará el total con IVA.
 */
public void assignTotal(JTable table, JLabel IVA, JLabel total) {
    double Total = 0;
    double iva = 0.15;
    double totalIva = 0;

    DefaultTableModel model = (DefaultTableModel) table.getModel();
    DecimalFormat format = new DecimalFormat("#.##");

    for (int i = 0; i < model.getRowCount(); i++) {
        double value = Double.parseDouble(model.getValueAt(i, 4).toString());
        Total = Total + value;
        totalIva = iva * Total;
    }

    // JOptionPane.showMessageDialog(null,Total );
    if (model.getRowCount() == 0) {
        total.setText("----");
        IVA.setText("----");
    } else {
        total.setText(String.valueOf(format.format(Total + totalIva)));
        IVA.setText(String.valueOf(format.format(totalIva)));
    }
}

   /**
    * Este método genera un recibo para un cliente insertando el ID del cliente 
    * y la fecha actual en la base de datos.
    *
    * @param customerID JTextField que contiene el ID del cliente
    */
   public void generateReceipt(JTextField customerID){
        configuration.CConexion connexion = new CConexion();
        model.ModelCsutomer mcustomer = new ModelCsutomer();
        String querry  ="INSERT INTO dbdar.receipt(receiptDate,fkcustomerID) values(curdate(),?)";
    
        if(customerID.getText().isEmpty()){
            // No hacer nada si el ID del cliente está vacío
        } else {
            try {
                mcustomer.setIdCustomer(Integer.parseInt(customerID.getText()));
                if(mcustomer.getIdCustomer() == 0){
                    JOptionPane.showMessageDialog(null,"No se ha seleccionado ninguna persona");
                    return;
                }
                
                // Prepara y ejecuta la consulta para insertar el recibo en la base de datos
                CallableStatement cs = connexion.setConnection().prepareCall(querry);
                cs.setInt(1, mcustomer.getIdCustomer());
                cs.execute();
                

                JOptionPane.showMessageDialog(null,"¡Registro exitoso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Fallo en el registro: "+ e.toString());
            } finally {
                connexion.closeConnection();
            }
        }
    }
    
    /**
     * Este método muestra el último ID de recibo en el JLabel proporcionado.
     *
     * @param lastReceipt JLabel para mostrar el último ID de recibo
     */
    public void showLastReceipt(JLabel lastReceipt){
        configuration.CConexion connection = new CConexion();
          
        try{      
            String querry2 = "SELECT receiptID FROM dbdar.receipt order by receiptID desc limit 1;";
            Statement statement = connection.setConnection().createStatement();
            ResultSet rs = statement.executeQuery(querry2);
           
            if(rs.next()){
                lastReceipt.setText(rs.getString("receiptID"));
            } else {
                lastReceipt.setText("----");
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Fallo al mostrar el último recibo: " + e.toString());
        } finally {
            connection.closeConnection();
        }
    }

    /**
     * Este método guarda los detalles del recibo en la base de datos insertando 
     * la información del producto y las cantidades de la tabla en la tabla 'detail'.
     *
     * @param table JTable que contiene los detalles del recibo
     * @param receiptID JLabel que contiene el ID del recibo para vincular los detalles
     */
    public void saveDetails(JTable table, JLabel receiptID){
        configuration.CConexion connection = new CConexion();
        model.ModelReceipt mreceipt = new ModelReceipt();
    
        DefaultTableModel model = (DefaultTableModel) table.getModel();
    
        if(table.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(null,"Debe seleccionar al menos una columna en la tabla");
        } else {
            try {
                mreceipt.setReceiptID(Integer.parseInt(receiptID.getText()));
                
                // Insertar los detalles de cada producto en la tabla 'detail'
                for (int i = 0; i < model.getRowCount(); i++) {
                    String querry = "insert into dbdar.detail(fkreceipt,fkproduct,quantity,priceSale) values(?,?,?,?);";    
                    CallableStatement cs = connection.setConnection().prepareCall(querry);
                    
                    cs.setInt(1, mreceipt.getReceiptID());
                    cs.setInt(2, Integer.parseInt(model.getValueAt(i, 2).toString()));
                    cs.setInt(3, Integer.parseInt(model.getValueAt(i, 5).toString()));
                    cs.setDouble(4, Double.parseDouble(model.getValueAt(i, 4).toString()));
                    cs.execute();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Fallo al guardar los detalles: "+ e.toString());
            } finally {
                connection.closeConnection();
            }
        }
    }

   
}
