package backend.controller.producto;
// import producto model
import ventanas.model.Producto;
// import database config
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

public class ProductoCRUDImp implements ProductoCRUD {
    
    @Override
    public void save(Producto producto){
        try {
            Connection con = new FarmaciaDb().getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Productos(Producto_nombre,Producto_descripcion, Producto_precio_compra, Categoria_id, Producto_stock_actual, Producto_unidad_medida, Producto_cantidad_unidades) VALUES (?,?,?,?,?,?,?)");
            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setFloat(3, producto.getPrecioCompra());
            pstmt.setInt(4, producto.getCategoriaId());
            pstmt.setInt(5, producto.getStockActual());
            pstmt.setString(6, producto.getUnidadMedida());
            pstmt.setInt(7, producto.getCantidadMedida());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(Producto producto){
        try {
            Connection con = new FarmaciaDb().getConnection();      
        PreparedStatement pstmt = con.prepareStatement("UPDATE Productos SET Producto_nombre=?, Producto_descripcion=?,  Producto_precio_compra=?, Categoria_id=?, Producto_stock_actual=?, Producto_unidad_medida=?, Producto_cantidad_unidades=? WHERE Producto_id = ?");
            pstmt.setString(1,producto.getNombre());       
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setFloat(3, producto.getPrecioCompra());
            pstmt.setInt(4, producto.getCategoriaId());
            pstmt.setInt(5, producto.getStockActual());
            pstmt.setString(6, producto.getUnidadMedida());
            pstmt.setInt(7, producto.getCantidadMedida());
            pstmt.setInt(8, producto.getId());
            pstmt.executeUpdate();
            
            }
        catch (Exception e) {
            System.out.println("ID: "+producto.getId());
            System.out.println("NOMBRE: "+producto.getNombre());
            System.out.println("DESCR: "+producto.getDescripcion());
            System.out.println("PRECIO: "+producto.getPrecioCompra());
            System.out.println("CATEG ID: "+producto.getCategoriaId());
            System.out.println("STOCK: "+producto.getStockActual());
            System.out.println("U.MEDIDA: "+producto.getUnidadMedida());
            System.out.println("CANT MEDIDA: "+producto.getCantidadMedida());
            e.printStackTrace();
        }        
    }
    
    @Override
    public void delete(Producto producto){
        try {
            Connection con = new FarmaciaDb().getConnection();
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM Productos WHERE Producto_id=?");
            pstmt.setInt(1, producto.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    public List<String[]> getIdNamePairs(){
        // TODO: sort alphabetically
        List<String[]> pairs = new ArrayList<>();
        try {
            Connection con = new FarmaciaDb().getConnection();           
            PreparedStatement ps = con.prepareStatement("select Producto_id, Producto_nombre FROM Productos");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String[] pair = {rs.getString("Producto_id"),rs.getString("Producto_nombre")};
                pairs.add(pair);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pairs;
    }
    
    @Override
    public Producto get(int id) {
        Producto prod = new Producto();
        try {
            Connection con = new FarmaciaDb().getConnection();           
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Productos WHERE Producto_id=?");
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                prod.setId(rs.getInt("Producto_id"));
                prod.setNombre(rs.getString("Producto_nombre"));
                prod.setDescripcion(rs.getString    ("Producto_descripcion"));
                prod.setPrecioCompra(rs.getFloat("Producto_precio_compra"));
                prod.setCategoriaId(rs.getInt("Categoria_id"));
                prod.setStockActual(rs.getInt("Producto_stock_actual"));
                prod.setUnidadMedida(rs.getString("Producto_unidad_medida"));
                prod.setCantidadMedida(rs.getInt("Producto_cantidad_unidades"));
                prod.setIsActive(rs.getBoolean("Producto_activo"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();            
        }
        return prod;
    }

    @Override
    public List<Producto> list() {
        List<Producto> list = new ArrayList<>();
        try {
            Connection con = new FarmaciaDb().getConnection();                       
            String sql = "SELECT * FROM Productos ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Producto prod = new Producto();

                prod.setId(rs.getInt("Producto_id"));
                prod.setNombre(rs.getString("Producto_nombre"));
                prod.setDescripcion(rs.getString    ("Producto_descripcion"));
                prod.setPrecioCompra(rs.getFloat("Producto_precio_compra"));
                prod.setCategoriaId(rs.getInt("Categoria_id"));
                prod.setStockActual(rs.getInt("Producto_stock_actual"));
                prod.setUnidadMedida(rs.getString("Producto_unidad_medida"));
                prod.setCantidadMedida(rs.getInt("Producto_cantidad_unidades"));
                prod.setIsActive(rs.getBoolean("Producto_activo"));                
 
                list.add(prod);
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
