
package Principal;

import Principal.dominio.mascota.MascotaService;
import Principal.dominio.usuario.Usuario;
import Principal.dominio.usuario.UsuarioService;



public class MainClass {

    
    public static void main(String[] args) { 
        
        //Create an object
        UsuarioService usuarioService = new UsuarioService();
        MascotaService mascotaService = new MascotaService();

        
        try {
            //Create new user
            usuarioService.createUser("usuario1@hotmail.com", "claveUsuario1");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("System error for \n" + e.getMessage());
        }
        
        
        
        try {
            //Modify an user
            usuarioService.editUser("usuario1@hotmail.com", "claveUsuario1", "nuevaClaveUsuario1");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("System error for \n" + e.getMessage());
        }
        
        
        try {
            //Delete an user
            usuarioService.deleteUser(1);
            usuarioService.listUsers();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("System error for \n" + e.getMessage());
        }
        
       
        try {
            //Looking for an user by email
            Usuario usuario = usuarioService.lookForEmail("usuario1@hotmail.com");
            //We create pet with the previous User
            mascotaService.createPet("Mascota1", "Labrador", usuario);

            //Show pet and user
            mascotaService.listPet();
            
        } catch (Exception e) {
            
            e.printStackTrace();
            System.out.println("System error for \n" + e.getMessage());
        }
        
    }
    
}
