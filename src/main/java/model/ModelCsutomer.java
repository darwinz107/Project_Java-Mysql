package model;

import java.sql.Date;

/**
 * Clase que representa a un cliente.
 * Contiene información sobre el cliente, como su ID, nombre, apellidos de la madre y el padre, y la fecha de nacimiento.
 * 
 * @author darwin
 */
public class ModelCsutomer {

    // ID único del cliente
    private int idCustomer;

    // Fecha de nacimiento del cliente
    private Date dateBorn;

    // Nombre del cliente
    private String customerName;

    // Apellido de la madre del cliente
    private String customerLnMom;

    // Apellido del padre del cliente
    private String customerLnDad;

    /**
     * Constructor vacío para la clase ModelCsutomer.
     */
    public ModelCsutomer() {
    }

    /**
     * Constructor para inicializar un cliente con sus datos.
     * 
     * @param idCustomer El ID del cliente.
     * @param customerName El nombre del cliente.
     * @param customerLnMom El apellido de la madre del cliente.
     * @param customerLnDad El apellido del padre del cliente.
     */
    public ModelCsutomer(int idCustomer, String customerName, String customerLnMom, String customerLnDad) {
        this.idCustomer = idCustomer;
        this.customerName = customerName;
        this.customerLnMom = customerLnMom;
        this.customerLnDad = customerLnDad;
    }

    /**
     * Obtiene el ID del cliente.
     * 
     * @return El ID del cliente.
     */
    public int getIdCustomer() {
        return idCustomer;
    }

    /**
     * Establece el ID del cliente.
     * 
     * @param idCustomer El ID del cliente.
     */
    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    /**
     * Obtiene el nombre del cliente.
     * 
     * @return El nombre del cliente.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Establece el nombre del cliente.
     * 
     * @param customerName El nombre del cliente.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Obtiene el apellido de la madre del cliente.
     * 
     * @return El apellido de la madre del cliente.
     */
    public String getCustomerLnMom() {
        return customerLnMom;
    }

    /**
     * Establece el apellido de la madre del cliente.
     * 
     * @param customerLnMom El apellido de la madre del cliente.
     */
    public void setCustomerLnMom(String customerLnMom) {
        this.customerLnMom = customerLnMom;
    }

    /**
     * Obtiene el apellido del padre del cliente.
     * 
     * @return El apellido del padre del cliente.
     */
    public String getCustomerLnDad() {
        return customerLnDad;
    }

    /**
     * Establece el apellido del padre del cliente.
     * 
     * @param customerLnDad El apellido del padre del cliente.
     */
    public void setCustomerLnDad(String customerLnDad) {
        this.customerLnDad = customerLnDad;
    }

    /**
     * Obtiene la fecha de nacimiento del cliente.
     * 
     * @return La fecha de nacimiento del cliente.
     */
    public Date getDateBorn() {
        return dateBorn;
    }

    /**
     * Establece la fecha de nacimiento del cliente.
     * 
     * @param dateBorn La fecha de nacimiento del cliente.
     */
    public void setDateBorn(Date dateBorn) {
        this.dateBorn = dateBorn;
    }
}
