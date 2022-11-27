
package Principal.persistencia;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public abstract class DAO {
    
    //Variables for connection
    protected Connection connection = null;
    protected ResultSet resulset = null;
    protected Statement statement = null;
    
    //Constants for connection
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "ejemplojdbc";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    
    
    //Connection Method
    protected void connectDataBase() throws ClassNotFoundException, SQLException{
        
        try{
            Class.forName(DRIVER);
            String url ="jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false";
            connection = DriverManager.getConnection(url, USER, PASSWORD);
        }catch(ClassNotFoundException | SQLException ex){
            throw ex;
        }
    }
    
    //Desconnection Method
    protected void desconectDataBase() throws Exception{
        
        try {
            
            if (resulset != null ) {
                resulset.close();
            }
            if (statement != null) {
                statement.close();
                
            }if (connection != null) {
                connection.close();
            }

        }catch(Exception e){
            throw e;
        }
    }
    
    
    //Transactional Method
    protected void inserModifyDelete(String sqlQuery) throws Exception{
        
        try {
            connectDataBase();
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            
        } catch (SQLException ex) {                 
            throw ex;
            
        }finally{
            desconectDataBase();
        }
    }
    
    //Read Method
    protected void readDataBase(String sqlQuery) throws Exception{
        try {
            connectDataBase();
            statement = connection.createStatement();
            resulset = statement.executeQuery(sqlQuery);
            
        } catch (Exception ex) {
            throw ex;
        }
        
    }
}
