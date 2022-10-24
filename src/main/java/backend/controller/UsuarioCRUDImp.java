package backend.controller;
// import producto model
import backend.model.Producto;
// import database config
import backend.FarmaciaDb;
import backend.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 *
 * @author codeli4m
 */
public class UsuarioCRUDImp implements UsuarioCRUD {

    @Override
    public void save(Usuario usuario) {
        String hashedPassword = Util.convertSHA256(usuario.getPassword());
        try {
            Connection con = new FarmaciaDb().getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Usuarios(Usuario_nombre,Usuario_nickname,Usuario_contrasena,Usuario_rol) VALUES(?,?,?,?)");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getNickname());
            ps.setString(3, hashedPassword);
            ps.setString(4, usuario.getRol());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void delete(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario get(int id) {
        // you wont get password jejeje
        Usuario usuario = new Usuario();
        try {
            Connection con = new FarmaciaDb().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Usuarios WHERE Usuario_id=?");
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                usuario.setId(rs.getInt("Producto_id"));      
                usuario.setNombre(rs.getString("Usuario_nombre"));
                usuario.setNickname(rs.getString("Usuario_nickname"));
                usuario.setRol(rs.getString("Usuario_rol"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<Usuario> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isAdmin(Usuario usuario) {
        return (usuario.getRol().equalsIgnoreCase("admin"));
    }

    @Override
    public Usuario authenticate(String username, String password) {
        String hashedPassword = Util.convertSHA256(password);        
        Usuario usuario = new Usuario();
        try {
            Connection con = new FarmaciaDb().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Usuarios WHERE Usuario_nickname = ? and Usuario_contrasena = ?");
            ps.setString(1, username);
            ps.setString(2, hashedPassword);  
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                usuario.setId(rs.getInt("Usuario_id"));      
                usuario.setNombre(rs.getString("Usuario_nombre"));
                usuario.setNickname(rs.getString("Usuario_nickname"));
                usuario.setPassword(rs.getString("Usuario_contrasena"));
                usuario.setRol(rs.getString("Usuario_rol"));                
            }else{
                throw new Error("Credenciales invalidas");
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return usuario;
    }
    
}
