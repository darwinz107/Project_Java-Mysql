package model;

import java.sql.Date;

/**
 * Clase que representa los detalles de un libro.
 * Contiene información sobre el libro, como su ID, nombre, autor, tipo,
 * editorial, fecha de publicación, precio, cantidad disponible, cantidad elegida,
 * fecha de préstamo y estado.
 * 
 * @author darwin
 */
public class ModelBook_detail {
    
    // ID único del libro
    int bookID;
    
    // Nombre del libro
    String bookName;
    
    // Autor del libro
    String bookAutor;
    
    // Tipo o género del libro
    String bookType;
    
    // Editorial del libro
    String editorial;
    
    // Fecha de publicación del libro
    String datePublish;
    
    // Precio del libro
    double price;
    
    // Cantidad disponible del libro
    int quantityDisp;
    
    // Cantidad de libros elegidos por el usuario
    int quantityChoose;
    
    // Fecha del préstamo del libro
    Date datePrest;
    
    // Estado del libro (por ejemplo, disponible, prestado, etc.)
    int state;

    /**
     * Obtiene el estado del libro.
     * 
     * @return El estado del libro.
     */
    public int getState() {
        return state;
    }

    /**
     * Establece el estado del libro.
     * 
     * @param state El estado del libro.
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * Obtiene el ID del libro.
     * 
     * @return El ID del libro.
     */
    public int getBookID() {
        return bookID;
    }

    /**
     * Establece el ID del libro.
     * 
     * @param bookID El ID del libro.
     */
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    /**
     * Obtiene el nombre del libro.
     * 
     * @return El nombre del libro.
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * Establece el nombre del libro.
     * 
     * @param bookName El nombre del libro.
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * Obtiene el autor del libro.
     * 
     * @return El autor del libro.
     */
    public String getBookAutor() {
        return bookAutor;
    }

    /**
     * Establece el autor del libro.
     * 
     * @param bookAutor El autor del libro.
     */
    public void setBookAutor(String bookAutor) {
        this.bookAutor = bookAutor;
    }

    /**
     * Obtiene el tipo o género del libro.
     * 
     * @return El tipo o género del libro.
     */
    public String getBookType() {
        return bookType;
    }

    /**
     * Establece el tipo o género del libro.
     * 
     * @param bookType El tipo o género del libro.
     */
    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    /**
     * Obtiene la editorial del libro.
     * 
     * @return La editorial del libro.
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * Establece la editorial del libro.
     * 
     * @param editorial La editorial del libro.
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * Obtiene la fecha de publicación del libro.
     * 
     * @return La fecha de publicación del libro.
     */
    public String getDatePublish() {
        return datePublish;
    }

    /**
     * Establece la fecha de publicación del libro.
     * 
     * @param datePublish La fecha de publicación del libro.
     */
    public void setDatePublish(String datePublish) {
        this.datePublish = datePublish;
    }

    /**
     * Obtiene el precio del libro.
     * 
     * @return El precio del libro.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Establece el precio del libro.
     * 
     * @param price El precio del libro.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Obtiene la cantidad disponible del libro.
     * 
     * @return La cantidad disponible del libro.
     */
    public int getQuantityDisp() {
        return quantityDisp;
    }

    /**
     * Establece la cantidad disponible del libro.
     * 
     * @param quantityDisp La cantidad disponible del libro.
     */
    public void setQuantityDisp(int quantityDisp) {
        this.quantityDisp = quantityDisp;
    }

    /**
     * Obtiene la cantidad de libros elegidos por el usuario.
     * 
     * @return La cantidad de libros elegidos por el usuario.
     */
    public int getQuantityChoose() {
        return quantityChoose;
    }

    /**
     * Establece la cantidad de libros elegidos por el usuario.
     * 
     * @param quantityChoose La cantidad de libros elegidos por el usuario.
     */
    public void setQuantityChoose(int quantityChoose) {
        this.quantityChoose = quantityChoose;
    }

    /**
     * Obtiene la fecha del préstamo del libro.
     * 
     * @return La fecha del préstamo del libro.
     */
    public Date getDatePrest() {
        return datePrest;
    }

    /**
     * Establece la fecha del préstamo del libro.
     * 
     * @param datePrest La fecha del préstamo del libro.
     */
    public void setDatePrest(Date datePrest) {
        this.datePrest = datePrest;
    }
}
