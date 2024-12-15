package model;

/**
 * Clase que representa un producto en un inventario o base de datos.
 * Contiene información sobre el ID del producto, el nombre, el stock disponible y el precio.
 * 
 * @author darwin
 */
public class ModelProduct {
    
    // ID único del producto
    private int idProduct;
    
    // Cantidad de stock disponible del producto
    private int productStock;
    
    // Nombre del producto
    private String productName;
    
    // Precio del producto
    private Double productPrice;

    /**
     * Obtiene el ID del producto.
     * 
     * @return El ID del producto.
     */
    public int getIdProduct() {
        return idProduct;
    }

    /**
     * Establece el ID del producto.
     * 
     * @param idProduct El ID del producto.
     */
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * Obtiene el stock disponible del producto.
     * 
     * @return La cantidad de stock del producto.
     */
    public int getProductStock() {
        return productStock;
    }

    /**
     * Establece la cantidad de stock disponible del producto.
     * 
     * @param productStock La cantidad de stock del producto.
     */
    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param productName El nombre del producto.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return El precio del producto.
     */
    public Double getProductPrice() {
        return productPrice;
    }

    /**
     * Establece el precio del producto.
     * 
     * @param productPrice El precio del producto.
     */
    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
