package Controller;

import java.sql.ResultSet;
import configuration.CConexion;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.ModelBook_detail;
import model.ModelCsutomer;
import model.ModelDetail;
import model.ModelProduct;
import model.ModelReceipt;

/**
 * Clase ControllerReceipt.
 * Controlador para gestionar las operaciones relacionadas con los recibos.
 * Se encarga de buscar y mostrar información del recibo en una tabla y etiquetas.
 */
public class ControllerReceipt {

    /**
     * Método para buscar un recibo por su número y mostrar la información en una tabla.
     * @param table JTable donde se mostrarán los datos.
     * @param numreceipt JTextField que contiene el número del recibo a buscar.
     * @param lblnumReceipt JLabel para mostrar el número del recibo.
     * @param lblDate JLabel para mostrar la fecha del recibo.
     * @param cusName JLabel para mostrar el nombre del cliente.
     * @param lnMom JLabel para mostrar el apellido materno del cliente.
     * @param lnDad JLabel para mostrar el apellido paterno del cliente.
     * @param iva JLabel para mostrar el IVA calculado.
     * @param total JLabel para mostrar el total calculado.
     * @param lberror JLabel para mostrar mensajes de error.
     */
    public void searchxNumReceipt(JTable table, JTextField numreceipt, JLabel lblnumReceipt, JLabel lblDate,
                                  JLabel cusName, JLabel lnMom, JLabel lnDad, JLabel iva, JLabel total, JLabel lberror) {

        // Validación inicial de los datos ingresados en el campo de texto.
        boolean validar = true;
        if (numreceipt.getText().isEmpty()) {
            lberror.setText("Campo vacío");
            validar = false;
            return;
        } else {
            validar = true;
        }

        if (!numreceipt.getText().matches("\\d+")) {
            lberror.setText("Solo permitido valor numérico");
            validar = false;
            return;
        } else {
            validar = true;
        }

        if (validar) {
            configuration.CConexion connection = new CConexion();
            model.ModelCsutomer mcustomer = new ModelCsutomer();
            model.ModelProduct mproduct = new ModelProduct();
            model.ModelReceipt mreceipt = new ModelReceipt();
            model.ModelDetail mdetail = new ModelDetail();
            model.ModelBook_detail mbook = new ModelBook_detail();
            DefaultTableModel model = new DefaultTableModel();

            // Definición de las columnas de la tabla.
            model.addColumn("ProductName");
            model.addColumn("Quantity");
            model.addColumn("Price");
            model.addColumn("BookName");
            model.addColumn("Autor");
            model.addColumn("Type");
            model.addColumn("DatePublish");
            model.addColumn("Quantity");
            model.addColumn("Price");

            table.setModel(model);

            try {
                // Configuración del ID del recibo en el modelo.
                mreceipt.setReceiptID(Integer.parseInt(numreceipt.getText()));

                // Consulta SQL para buscar los datos del recibo.
                String querry = """
                    SELECT 
                        d.fkreceipt,
                        r.receiptDate,
                        c.customerName,
                        c.LastNameMom,
                        c.LastNameDad,
                        p.productID,
                        p.productName,
                        p.productPrice,
                        SUM(d.quantity) AS QuantityProduct,
                        b.bookName,
                        b.bookAutor,
                        b.bookType,
                        b.datePublish,
                        b.price AS bookPrice,
                        SUM(bd.quantityChoose) AS QuantityBook,
                        MAX(bd.dateChoose) AS lastDateChoose
                    FROM dbdar.customer AS c
                    INNER JOIN dbdar.bookdetail AS bd ON c.customerID = bd.fkcustomerID
                    INNER JOIN dbdar.receipt AS r ON c.customerID = r.fkcustomerID
                    INNER JOIN dbdar.detail AS d ON r.receiptID = d.fkreceipt
                    INNER JOIN dbdar.book AS b ON bd.fkbookID = b.bookID
                    INNER JOIN dbdar.state AS s ON bd.fkstateID = s.stateID
                    INNER JOIN dbdar.product AS p ON d.fkproduct = p.productID
                    WHERE bd.fkstateID = 1 AND d.fkreceipt = ?
                    GROUP BY 
                        d.fkreceipt,
                        c.customerID,
                        p.productID,
                        p.productName,
                        p.productPrice,
                        b.bookName,
                        b.bookType,
                        b.datePublish,
                        b.bookAutor,
                        b.price;
                    """;

                // Preparación de la consulta y asignación de parámetros.
                PreparedStatement ps = connection.setConnection().prepareCall(querry);
                ps.setInt(1, mreceipt.getReceiptID());
                ResultSet rs = ps.executeQuery();

                // Procesamiento de los resultados de la consulta.
                while (rs.next()) {
                    mreceipt.setReceiptID(rs.getInt("fkreceipt"));
                    mreceipt.setReceiptDate(rs.getDate("receiptDate"));
                    mcustomer.setCustomerName(rs.getString("customerName"));
                    mcustomer.setCustomerLnMom(rs.getString("LastNameMom"));
                    mcustomer.setCustomerLnDad(rs.getString("LastNameDad"));
                    mproduct.setProductName(rs.getString("productName"));
                    mdetail.setQuantity(rs.getInt("QuantityProduct"));
                    mdetail.setPriceSale(rs.getDouble("productPrice"));
                    mbook.setBookName(rs.getString("bookName"));
                    mbook.setBookType(rs.getString("bookType"));
                    mbook.setDatePublish(rs.getString("datePublish"));
                    mbook.setQuantityChoose(rs.getInt("QuantityBook"));
                    mbook.setPrice(rs.getDouble("bookPrice"));
                    mbook.setBookAutor(rs.getString("bookAutor"));

                    model.addRow(new Object[] { mproduct.getProductName(), mdetail.getQuantity(), mdetail.getPriceSale(),
                            mbook.getBookName(), mbook.getBookAutor(), mbook.getBookType(), mbook.getDatePublish(),
                            mbook.getQuantityChoose(), mbook.getPrice() });
                }

                // Verificación si no se encontró el recibo.
                if (mreceipt.getReceiptDate() == null) {
                    JOptionPane.showMessageDialog(null, "Recibo no encontrado");
                    return;
                }

                table.setModel(model);

                // Actualización de etiquetas con información del recibo.
                lblnumReceipt.setText(String.valueOf(mreceipt.getReceiptID()));
                lblDate.setText(String.valueOf(mreceipt.getReceiptDate()));
                cusName.setText(mcustomer.getCustomerName());
                lnMom.setText(mcustomer.getCustomerLnMom());
                lnDad.setText(mcustomer.getCustomerLnDad());

                // Cálculo del IVA y total final.
                double Iva = 0.15;
                double totalIva = 0;
                double Total = 0;
                double Totalbook = 0;
                double PriceUnit;
                double PriceUnitBook;
                double TotalFinal = 0;
                DecimalFormat format = new DecimalFormat("#.##");

                for (int i = 0; i < model.getRowCount(); i++) {
                    PriceUnit = Double.parseDouble(model.getValueAt(i, 2).toString());
                    PriceUnitBook = Double.parseDouble(model.getValueAt(i, 8).toString());
                    int quantity = Integer.parseInt(model.getValueAt(i, 1).toString());
                    int quantityBook = Integer.parseInt(model.getValueAt(i, 7).toString());
                    Total += quantity * PriceUnit;
                    Totalbook += quantityBook * PriceUnitBook;
                    TotalFinal = Total + Totalbook;
                    totalIva = TotalFinal * Iva;
                }

                total.setText(String.valueOf(format.format(TotalFinal)));
                iva.setText(String.valueOf(format.format(totalIva)));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al buscar el recibo: " + e.toString());
            } finally {
                connection.closeConnection();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe digitar los valores correctos");
        }
    }
}
