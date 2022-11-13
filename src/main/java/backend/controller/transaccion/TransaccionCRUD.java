package backend.controller.transaccion;

import ventanas.model.Transaccion;
import java.util.List;

/**
 *
 * @author codeli4m
 */
public interface TransaccionCRUD {
    public void save(Transaccion transaccion);
    public void update(Transaccion transaccion);
    public void delete(Transaccion transaccion);
    public Transaccion get(int id);
    public List<Transaccion> list();    
}
