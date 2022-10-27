package backend.controller;

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
