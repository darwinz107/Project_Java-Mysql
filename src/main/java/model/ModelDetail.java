/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Clase que representa los detalles de un producto o transacción.
 * Contiene información sobre el ID del detalle, la cantidad y el precio de venta.
 * 
 * @author darwin
 */
public class ModelDetail {
    
    // ID único del detalle
    int detailID;
    
    // Cantidad de productos o ítems en el detalle
    int quantity;
    
    // Precio de venta del producto en el detalle
    double priceSale;

    /**
     * Obtiene el ID del detalle.
     * 
     * @return El ID del detalle.
     */
    public int getDetailID() {
        return detailID;
    }

    /**
     * Establece el ID del detalle.
     * 
     * @param detailID El ID del detalle.
     */
    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    /**
     * Obtiene la cantidad de productos o ítems en el detalle.
     * 
     * @return La cantidad del detalle.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad de productos o ítems en el detalle.
     * 
     * @param quantity La cantidad del detalle.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Obtiene el precio de venta del producto en el detalle.
     * 
     * @return El precio de venta del producto.
     */
    public double getPriceSale() {
        return priceSale;
    }

    /**
     * Establece el precio de venta del producto en el detalle.
     * 
     * @param priceSale El precio de venta del producto.
     */
    public void setPriceSale(double priceSale) {
        this.priceSale = priceSale;
    }
}
