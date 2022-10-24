package backend;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author codeli4m
 */
public class FarmaciaDb {
    
    // Busca el driver aqui - MySql 8.0.31-1ubuntu18.04
    // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j/8.0.31
    
    // Descarga el jar dotenv-java-2.2.4.jar desde aqui
    // https://github.com/cdimascio/dotenv-java/packages/404788
    
    Connection con;
    final String DB_URL = "jdbc:mysql://147.189.173.157:3306/inventario_farmacia";
    final String DB_DRV = "com.mysql.cj.jdbc.Driver";
    Dotenv dotenv = null;
    String DB_USER;
    String DB_PASSWD; 
    
    public void loadVariables(){
        try {
            dotenv = Dotenv.configure().load();
            DB_USER = dotenv.get("MYSQL_USER");
            DB_PASSWD = dotenv.get("MYSQL_PASSWORD"); 
        } catch (DotenvException e) {
            System.out.println("DOT ENV package error"+e);
        }
    }
    
    public Connection getConnection() throws SQLException, ClassNotFoundException{
        loadVariables();
        if (con == null){
            try {
                Class.forName(DB_DRV);
                con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);                
         } catch (SQLException ex) {
                System.out.println("Ocurrió un error al conectar la base de datos. ");
                System.out.println(ex);
         }
        }
        return con;
    }
}
