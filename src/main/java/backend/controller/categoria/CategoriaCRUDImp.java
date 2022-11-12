package backend.controller.categoria;

import backend.FarmaciaDb;
import ventanas.model.Categoria;
import ventanas.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaCRUDImp implements CategoriaCRUD {

    @Override
    public void save(Categoria categoria) {
        try {
            Connection con = new FarmaciaDb().getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Categorias(Categoria_nombre,Categoria_descripcion, Categoria_id) VALUES (?,?,?)");
            pstmt.setInt(1, categoria.getId());
            pstmt.setString(2, categoria.getNombre());
            pstmt.setString(3, categoria.getDesc());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Categoria categoria) {
        try {
            Connection con = new FarmaciaDb().getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE Categorias SET Categoria_nombre=?, Categoria_descripcion=?, Categoria_id=?");
            pstmt.setString(1,categoria.getNombre());
            pstmt.setString(2, categoria.getDesc());
            pstmt.setInt(3, categoria.getId());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Categoria categoria) {
        try {
            Connection con = new FarmaciaDb().getConnection();
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM Categorias WHERE Categoria_id=?");
            pstmt.setInt(1, categoria.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //   ESPERAR A QUE LA VENTANA DE CATEGORIA ESTE FINALIZADO!!
        // public List<String[]> getIdNamePairs(){
        //    // TODO: sort alphabetically
        //    List<String[]> pairs = new ArrayList<>();
        //    try {
        //         Connection con = new FarmaciaDb().getConnection();
        //      PreparedStatement ps = con.prepareStatement("select Categoria_id, Categoria_nombre FROM Productos");
        //        ResultSet rs = ps.executeQuery();
        //           while (rs.next()){
        //            String[] pair = {rs.getString("Categoria_id"),rs.getString("Categoria_nombre")};
        //              pairs.add(pair);
        //        }
        //      } catch (Exception e) {
        //          e.printStackTrace();
        //     }
        //      return pairs;

    }

    @Override
    public Categoria get(int id) {

        Categoria categoria = new Categoria();
        try {
            Connection con = new FarmaciaDb().getConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Categorias WHERE Categoria_id=?");
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                categoria.setId(rs.getInt("Categoria_id"));
                categoria.setNombre(rs.getString("Categoria_nombre"));
                categoria.setDesc(rs.getString("Categoria_descripcion"));
                categoria.setActivado(rs.getBoolean("Categoria_activo"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoria;
    }

    @Override
    public List<Categoria> list() {
        List<Categoria> list = new ArrayList<>();
        try {
            Connection con = new FarmaciaDb().getConnection();
            String sql = "SELECT * FROM Categorias ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Categoria categoria = new Categoria();

                categoria.setId(rs.getInt("Categoria_id"));
                categoria.setNombre(rs.getString("Categoria_nombre"));
                categoria.setDesc(rs.getString    ("Categoria_descripcion"));
                categoria.setActivado(rs.getBoolean("Categoria_activo"));

                list.add(categoria);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}