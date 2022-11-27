
package Principal.persistencia;

import Principal.dominio.usuario.Usuario;
import java.util.ArrayList;
import java.util.Collection;



public final class UsuarioDAO extends DAO {
    
    //Save Method
    public void saveUser(Usuario usuario) throws Exception{
        try {
            if (usuario == null) {
                throw new Exception ("You must indicate a user");
            }
            
            String sqlQuery = "INSERT INTO Usuario (correoElectronico, clave) "
                    + "VALUES ( '" + usuario.getCorreoElectronico() + "' , '" + usuario.getClave()+ "' );";           
            inserModifyDelete(sqlQuery);
                       
        } catch (Exception e) {
            throw e;
            
        } finally {
            desconectDataBase();
        }
    }
    
    //Method to modify an existing user
    public void modifyUser(Usuario usuario) throws Exception{
        try {
            if (usuario == null) {
                throw new Exception("You must indicate the user you want to modify");
            }
        
        String sqlQuery = "UPDATE Usuario SET "
                    + "clave = '" + usuario.getClave() + "' WHERE correoElectronico = '" + usuario.getCorreoElectronico() + "'";
            
        inserModifyDelete(sqlQuery);
        
        } catch (Exception e) {
            throw e;
            
        } finally {
            desconectDataBase();
        }
    }
    
    
    //Method to delete an existing user
    public void deleteUser(Integer id) throws Exception{
        try {
            String sqlQuery = "DELETE FROM Usuario WHERE id = '" + id + "'";
       
            inserModifyDelete(sqlQuery);
        } catch (Exception e) {
            throw e;
            
        } finally {
            desconectDataBase();
        }
    }
    
    //Search user by email
    public Usuario searchByEmail(String correoElectronico) throws Exception{
        try {
            
            String sqlQuery = "SELECT * FROM Usuario "
                    + " WHERE correoElectronico = '" + correoElectronico + "'";

            
            readDataBase(sqlQuery);
            
            Usuario usuario = null;
            
            while (resulset.next()) {

                usuario = new Usuario();
                usuario.setId(resulset.getInt(1));
                usuario.setCorreoElectronico(resulset.getString(2));
                usuario.setClave(resulset.getString(3));
            }
            desconectDataBase();
            return usuario;
            
        } catch (Exception e) {
            desconectDataBase();
            throw e;
        }                  
    }
    
     //Search user by id
    public Usuario searchById(Integer id) throws Exception{
        try {
            
            String sqlQuery =  "SELECT * FROM Usuario "
                    + " WHERE id = '" + id + "'";
            
            readDataBase(sqlQuery);
            
            Usuario usuario = null;
            
            while (resulset.next()) {

                usuario = new Usuario();
                usuario.setId(resulset.getInt(1));
                usuario.setCorreoElectronico(resulset.getString(2));
                usuario.setClave(resulset.getString(3));
            }
            desconectDataBase();
            return usuario;
            
        } catch (Exception e) {
            desconectDataBase();
            throw e;
        }                  
    }
    
    //List users
    public Collection<Usuario> listUsers() throws Exception{
        try {
            String sqlQuery = "SELECT correoElectronico, clave FROM Usuario ";
           
            readDataBase(sqlQuery);
            
            Usuario usuario = null;
            Collection<Usuario> usuarios = new ArrayList<>();
            
            while(resulset.next()){
                usuario = new Usuario();
                usuario.setCorreoElectronico(resulset.getString(1));
                usuario.setClave(resulset.getString(2));
                usuarios.add(usuario);
            }
            desconectDataBase();
            return usuarios;
            
        } catch (Exception e) {
            e.printStackTrace();
            desconectDataBase();
            throw new Exception ("System error");
        }
        
        
    }
    
    
}
