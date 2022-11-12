package backend.controller.producto;

import ventanas.model.Producto;
import java.util.List;

/**
 *
 * @author codeli4m
 */
public interface ProductoCRUD {
    public void save(Producto producto);
    public void update(Producto producto);
    public void delete(Producto producto);
    public Producto get(int id);
    public List<Producto> list();    
}
