
package Principal.dominio.mascota;

import Principal.dominio.usuario.Usuario;
import Principal.persistencia.MascotaDAO;
import java.util.Collection;


public class MascotaService {
    
    //We create an instance of MascotaDao
    private MascotaDAO dao;

    public MascotaService() {
        this.dao = new MascotaDAO();
    }

    //Method to create a pet
    public void createPet(String apodo, String raza, Usuario usuario) throws Exception {
        try {
            //Validation
            if (apodo == null || apodo.trim().isEmpty()) {
                throw new Exception("You must indicate the nickname");
            }

            if (raza == null || raza.trim().isEmpty()) {
                throw new Exception("You must indicate the `raza`");
            }

            if (usuario == null) {
                throw new Exception("You must indicate the User");
            }

            //Create the new pet
            Mascota mascota = new Mascota();
            mascota.setApodo(apodo);
            mascota.setRaza(raza);
            mascota.setUsuario(usuario);

            dao.savePet(mascota);

        } catch (Exception e) {
            throw e;
        }
    }

    //Method to edit a pet
    public void modifyPet(int id, String apodo, String raza, int idUsuario) throws Exception {
        try {
            //Validation
            if (id <= 0) {
                throw new Exception("You must indicate the id number");
            }

            if (apodo == null || apodo.trim().isEmpty()) {
                throw new Exception("You must indicate the `apodo`");
            }

            if (raza == null || raza.trim().isEmpty()) {
                throw new Exception("You must indicate the `raza`");
            }

            if (idUsuario <= 0) {
                throw new Exception("You must indicate the User id");
            }

            //Find the pet in the database
            Mascota mascota = dao.lookById(id);
            dao.modifyPet(mascota);
            
        } catch (Exception e) {
            throw e;
        }
    }

    //Delete pet
    public void deletePet(int id) throws Exception {
        try {
            //Validation 
            if (id <= 0) {
                throw new Exception("You must indicate the id number");
            }
            dao.deletePet(id);
            
        } catch (Exception e) {
            throw e;
        }
    }

    //Find the pet in the database
    public Mascota lookPetById(int id) throws Exception {
        try {
            //Validation
            if (id <= 0) {
                throw new Exception("You must indicate the id number");
            }
            
            Mascota mascota = dao.lookById(id);
            
            //Validation 
            if (mascota == null) {
                throw new Exception("No pet found");
            }
            return mascota;
            
        } catch (Exception e) {
            throw e;
        }
    }

    //Pet list
    public Collection<Mascota> listPet() throws Exception {
        try {

            Collection<Mascota> mascotas = dao.listPet();
            
            if (mascotas.isEmpty()) {
                throw new Exception("No pet found");
                
            } else {      
                for (Mascota m : mascotas) {
                    System.out.println(m);
                }
                return mascotas;
            }
                       
        } catch (Exception e) {
            throw e;
        }
    }

}
