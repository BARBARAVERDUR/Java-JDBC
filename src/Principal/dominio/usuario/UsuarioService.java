
package Principal.dominio.usuario;

import Principal.persistencia.UsuarioDAO;
import Principal.dominio.usuario.Usuario;
import java.util.Collection;


public class UsuarioService {
    
    //We create an instance of userDao
    private UsuarioDAO dao;

    public UsuarioService() {
        this.dao = new UsuarioDAO();
    }
    
    
    //Method to create an user
    public void createUser(String correoElectronico, String clave) throws Exception {

        try {
            //Validation
            if (correoElectronico == null || correoElectronico.trim().isEmpty()) {
                throw new Exception("You must indicate the email");
            }
            if (correoElectronico.contains("@") == false) {
                throw new Exception("The email is wrong");
            }
            if (clave == null || clave.trim().isEmpty()) {
                throw new Exception("You must indicate the key");
            }
            if (clave.length() < 8) {
                throw new Exception("The password cannot be less than 8 characters.");
            }
         
            //Create the new user
            Usuario usuario = new Usuario();
            usuario.setCorreoElectronico(correoElectronico);
            usuario.setClave(clave);
            dao.saveUser(usuario);
            
        } catch (Exception e) {
            throw e;
        }
    }

    
    //Method to edit an existing user
    public void editUser(String correoElectronico, String clave, String nuevaClave) throws Exception {

        try {

            //Validation
            if (correoElectronico == null || correoElectronico.trim().isEmpty()) {
                throw new Exception("You must indicate your current email");
            }

            if (clave == null || clave.trim().isEmpty()) {
                throw new Exception("You must indicate the current key");
            }

            if (nuevaClave == null || nuevaClave.trim().isEmpty()) {
                throw new Exception("You must indicate your new key");
            }
            

            //Find the user in the database
           Usuario usuario =  dao.searchByEmail(correoElectronico);
          
            String claveActual = usuario.getClave();

            //Validate
            if (!claveActual.equals(clave) ){
                throw new Exception("The password entered is invalid.");
            }
           
            //Modify the information
            usuario.setClave(nuevaClave);
            dao.modifyUser(usuario);
                       
        } catch (Exception e) {
            throw e;
        }
    }

    
    //Delete an user
    public void deleteUser(Integer id) throws Exception {

        try {
            //Validation 
            if (id == null ) {
                throw new Exception("You must indicate one id");
            }

            dao.deleteUser(id);
            
        } catch (Exception e) {
            throw e;
        }
    }

    
    //Looking for an user by email
    public Usuario lookForEmail(String correoElectronico) throws Exception {

        try {

            //Validation
            if (correoElectronico == null || correoElectronico.trim().isEmpty()) {
                throw new Exception("You must indicate the email");
            }

            Usuario usuario = dao.searchByEmail(correoElectronico);

            return usuario;
        } catch (Exception e) {
            throw e;
        }
    }

    //Looking for an user by id
    public Usuario lookForId(Integer id) throws Exception {
        try {

            //Validation
            if (id == null) {
                throw new Exception("You must indicate the id");
            }
            Usuario usuario = dao.searchById(id);

            return usuario;
        } catch (Exception e) {
            throw e;
        }
    }

    //Users list
    public Collection<Usuario> listUsers() throws Exception {

        try {
            Collection<Usuario> usuarios = dao.listUsers();
            if (usuarios.isEmpty()) {
                throw new Exception("No user found");
                
            } else {
                for (Usuario u : usuarios) {
                    System.out.println(u);
                }
                return usuarios;
            }
                       
        } catch (Exception e) {
            throw e;
        }
    }

}
