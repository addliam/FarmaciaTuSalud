package backend.controller.categoria;

import backend.FarmaciaDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author codeli4m
 */

public class CategoriaTemp {
    public String getCategoryName (int id){
        String catName = "";
        try {
            Connection con = new FarmaciaDb().getConnection();
            PreparedStatement ps = con.prepareStatement("select Categoria_nombre from Categorias where Categoria_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                catName = rs.getString("Categoria_nombre");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return catName;
    }
    
    public List<String[]> getCategoryNameIdPairs(){
        List<String[]> categoryPairs = new ArrayList<>();        
        try {
            Connection con = new FarmaciaDb().getConnection();
            PreparedStatement ps = con.prepareStatement("select  Categoria_nombre, Categoria_id from Categorias");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String[] pair = new String[2];
                String id = String.valueOf(rs.getInt("Categoria_id")); 
                String categoria = rs.getString("Categoria_nombre");
                pair[0] = id;
                pair[1] = categoria;
                categoryPairs.add(pair);
            }
        } catch (Exception e) {
        }
        return categoryPairs;
    }
}
