
package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author darwin
 */
public class CConexion {
   
    Connection connection = null;
    
   String user = "root";
   String password ="root";
   String bd="dbdar";
   String ip="localhost";
   String port="3306";
   
   String querry = "jdbc:mysql://"+ip+":"+port+"/"+bd;
    
     /**
     * Establece la conexión con la base de datos MySQL.
     * 
     * @return Connection Devuelve el objeto de conexión a la base de datos.
     */
   
   public Connection setConnection(){
   
       try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(querry,user,password);
       //    JOptionPane.showMessageDialog(null,"Connection successful!");
       } catch (Exception e) {
           
           JOptionPane.showMessageDialog(null,"Failed connection," +e.toString());
       }
   return connection;
   
   
   };
   /**
    * Cierra la conexión con la base de datos si está abierta.
    */
  public void closeConnection(){
  
      try {
          if(connection !=null && !connection.isClosed()){
          connection.close();
    //      JOptionPane.showMessageDialog(null, "Connection closed");
          
          }
          
      } catch (Exception e) {
          
          JOptionPane.showMessageDialog(null," We can't close connection," +e.toString());
      }
      
  };
   
}
