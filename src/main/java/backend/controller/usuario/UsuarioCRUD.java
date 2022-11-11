package backend.controller.usuario;

import backend.model.Usuario;
import java.util.List;

/**
 *
 * @author codeli4m
 */

public interface UsuarioCRUD {
    public void save(Usuario usuario);
    public void update(Usuario usuario);
    public void delete(Usuario usuario);
    public Usuario authenticate(String username, String password);
    public Usuario get(int id);
    public List<Usuario> list();    
    public boolean isAdmin(Usuario usuario); 
}
