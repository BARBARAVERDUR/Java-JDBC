
package Principal.persistencia;

import Principal.dominio.mascota.Mascota;
import Principal.dominio.usuario.Usuario;
import Principal.dominio.usuario.UsuarioService;
import java.util.ArrayList;
import java.util.Collection;



public final class MascotaDAO extends DAO {
    
      private final UsuarioService usuarioService;

    public MascotaDAO() {
        this.usuarioService = new UsuarioService();
    }

    //Save new pet
    public void savePet(Mascota mascota) throws Exception {
        try {
            if (mascota == null) {
                throw new Exception("You must indicate a pet");
            }
            String sqlQuery = "INSERT INTO Mascota (apodo, raza, idUsuario) "
                    + "VALUES ( '" + mascota.getApodo() + "' , '" + mascota.getRaza() + "' ," + mascota.getUsuario().getId() + " );";
            
            inserModifyDelete(sqlQuery);
            
        } catch (Exception e) {
            throw e;
            
        } finally {
            desconectDataBase();
        }
    }

    //Edit existing pet
    public void modifyPet(Mascota mascota) throws Exception {
        try {
            if (mascota == null) {
                throw new Exception("You must indicate the pet you want to modify");
            }
            String sqlQuery = "UPDATE Mascota SET "
                    + " apodo = '" + mascota.getApodo() + "' , raza = '" + mascota.getRaza() + "' , idUsuario = " + mascota.getUsuario().getId()
                    + " WHERE id = '" + mascota.getId() + "'";
            
            inserModifyDelete(sqlQuery);
            
        } catch (Exception e) {
            throw e;
            
        } finally {
            desconectDataBase();
        }
    }

    //Delete pet
    public void deletePet(int id) throws Exception {
        try {
            String sqlQuery = "DELETE FROM Mascota WHERE id = " + id + "";
            inserModifyDelete(sqlQuery);
            
        } catch (Exception e) {
            throw e;
            
        } finally {
            desconectDataBase();
        }
    }

    
    //Look for a pet by id
    public Mascota lookById(int id) throws Exception {
        try {
            String sqlQuery = "SELECT * FROM Mascota WHERE id = " + id + "";
            
            readDataBase(sqlQuery);
            
            Mascota mascota = null;
            
            while (resulset.next()) {
                mascota = new Mascota();
                
                mascota.setId(resulset.getInt(1));
                mascota.setApodo(resulset.getString(2));
                mascota.setRaza(resulset.getString(3));
                Integer idUsuario = resulset.getInt(4);
                
                Usuario usuario = usuarioService.lookForId(idUsuario);
                mascota.setUsuario(usuario);
            }
            
            desconectDataBase();
            
            return mascota;
            
        } catch (Exception e) {
           desconectDataBase();
           
            throw e;
        }
    }

    //Pet list
    public Collection<Mascota> listPet() throws Exception {
        try {
            String sqlQuery = "SELECT * FROM Mascota ";
            readDataBase(sqlQuery);
            
            Mascota mascota = null;
            
            Collection<Mascota> mascotas = new ArrayList<>();
            
            while (resulset.next()) {
                mascota = new Mascota();
                mascota.setId(resulset.getInt(1));
                mascota.setApodo(resulset.getString(2));
                mascota.setRaza(resulset.getString(3));
                Integer idUsuario = resulset.getInt(4);
                
                Usuario usuario = usuarioService.lookForId(idUsuario);
                mascota.setUsuario(usuario);
                
                mascotas.add(mascota);
            }
            
            desconectDataBase();
            
            return mascotas;
            
        } catch (Exception e) {                       
            
            desconectDataBase();
            throw e;
        }
    }
    
}
