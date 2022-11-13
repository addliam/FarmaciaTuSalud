package backend.controller.transaccion;
// import producto model
import ventanas.model.Producto;
// import database config
import backend.FarmaciaDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import ventanas.model.Transaccion;
/**
 *
 * @author codeli4m
 */

public class TransaccionCRUDImp implements TransaccionCRUD {
    
    @Override
    public void save(Transaccion transaccion){
        try {
            Connection con = new FarmaciaDb().getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Transacciones(Transaccion_tipo , Lote_id , Transaccion_cantidad , Usuario_id) VALUES (?,?,?,?,?,?,?)");
            pstmt.setString(1, transaccion.getTipoTransaccion());
            pstmt.setString(2, transaccion.getLoteId());
            pstmt.setInt(3, transaccion.getCantidad());
            pstmt.setString(4, transaccion.getUsuarioId());
            pstmt.executeUpdate();
            // TODO: usando funciones y triggers sql automatizar agregar y disminiur stock de cierto Lote
            PreparedStatement pstUpdateStock = con.prepareStatement("UPDATE Lotes SET Lote_stock = Lote_stock + ? WHERE Lote_id = ?");
            pstUpdateStock.setInt(1, transaccion.getCantidad());
            pstUpdateStock.setString(2, transaccion.getLoteId());            
        } catch (Exception e) {
            // TODO: lanzar un error si el Usuario_id es incorrecto o Lote_id no existe
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(Transaccion transaccion){
        throw new UnsupportedOperationException("Metodo no esta soportado.");
    }
    
    @Override
    public void delete(Transaccion transaccion){
        try {
            Connection con = new FarmaciaDb().getConnection();
            // No eliminamos de la base de datos para evitar eliminacion en cascada
            PreparedStatement pstmt = con.prepareStatement("UPDATE Transacciones SET Transaccion_activo = 0 WHERE Transaccion_id = ?");
            pstmt.setString(1, transaccion.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
    @Override
    public Transaccion get(int id) {
        Transaccion transa = new Transaccion();
        try {
            Connection con = new FarmaciaDb().getConnection();           
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Transacciones WHERE Transaccion_id = ?");
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                transa.setId(rs.getString("Transaccion_id"));
                transa.setTipoTransaccion(rs.getString("Transaccion_tipo"));
                transa.setLoteId(rs.getString("Lote_id"));
                transa.setCantidad(rs.getInt("Transaccion_cantidad"));
                transa.setUsuarioId(rs.getString("Usuario_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();            
        }
        return transa;
    }

    @Override
    public List<Transaccion> list() {
        List<Transaccion> list = new ArrayList<Transaccion>();
        try {
            Connection con = new FarmaciaDb().getConnection();                       
            String sql = "SELECT * FROM Transacciones ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Transaccion transa = new Transaccion();
                transa.setId(rs.getString("Transaccion_id"));
                transa.setTipoTransaccion(rs.getString("Transaccion_tipo"));
                transa.setLoteId(rs.getString("Lote_id"));
                transa.setCantidad(rs.getInt("Transaccion_cantidad"));
                transa.setUsuarioId(rs.getString("Usuario_id"));
                // TODO: modificar modelo para agregar Transaccion_fecha y Transaccion_activo 
                list.add(transa);
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
