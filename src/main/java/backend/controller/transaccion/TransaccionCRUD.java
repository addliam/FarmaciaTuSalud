package backend.controller.transaccion;

import ventanas.model.Transaccion;
import java.util.List;

/**
 *
 * @author codeli4m
 */
public interface TransaccionCRUD {
    public void save(Transaccion transacion);
    public void update(Transaccion transacion);
    public void delete(Transaccion transacion);
    public Transaccion get(int id);
    public List<Transaccion> list();    
}
