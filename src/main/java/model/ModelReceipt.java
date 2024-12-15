package model;

import java.sql.Date;

/**
 * Clase que representa un recibo o comprobante de compra.
 * Contiene información sobre el ID del recibo, la fecha del recibo 
 * y el ID del cliente asociado.
 * 
 * @author darwin
 */
public class ModelReceipt {
    
    // ID único del recibo
    int receiptID;
    
    // Fecha del recibo
    Date receiptDate;
    
    // ID del cliente asociado al recibo
    int customerID;

    /**
     * Obtiene el ID del recibo.
     * 
     * @return El ID del recibo.
     */
    public int getReceiptID() {
        return receiptID;
    }

    /**
     * Establece el ID del recibo.
     * 
     * @param receiptID El ID del recibo.
     */
    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    /**
     * Obtiene la fecha del recibo.
     * 
     * @return La fecha del recibo.
     */
    public Date getReceiptDate() {
        return receiptDate;
    }

    /**
     * Establece la fecha del recibo.
     * 
     * @param receiptDate La fecha del recibo.
     */
    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    /**
     * Obtiene el ID del cliente asociado al recibo.
     * 
     * @return El ID del cliente.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Establece el ID del cliente asociado al recibo.
     * 
     * @param customerID El ID del cliente.
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
