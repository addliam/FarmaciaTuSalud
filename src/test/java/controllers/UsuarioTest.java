package controllers;

import java.util.Random;

import ventanas.model.Usuario;
import backend.controller.usuario.UsuarioCRUDImp;

/**
 *
 * @author codeli4m
 */

public class UsuarioTest {
    public static void main(String[] args) {
        UsuarioCRUDImp dao = new UsuarioCRUDImp();
        Random rand = new Random();
        int rand_int = rand.nextInt(9999);        
        // CREATE
        Usuario usuario = new Usuario();
        usuario.setNombre("User"+rand_int);
        usuario.setNickname("Nickkname"+rand_int);
        usuario.setRol("invitado");
        usuario.setPassword("ventas");
        
//        dao.save(usuario);

        // AUTHENTICATION
        // Como manejar la autenticacion
        // Ejemplo autenticacion fallida
        String username = "invitado";
        String password = "invitadomal";        
        try {
            Usuario user2 = dao.authenticate(username, password);
            System.out.println(user2.getPassword());                    
        } catch (java.lang.Error e) {
            System.out.println("Fallo la autenticacion para "+username+"@"+password);
        }
        // Ejemplo autenticacion correcta
        try {
            Usuario user3 = dao.authenticate("invitado", "invitado");
            System.out.println(user3.getPassword());                    
        } catch (java.lang.Error e) {
            System.out.println("Fallo la autenticacion");
        }        
        
        // 
    }
}