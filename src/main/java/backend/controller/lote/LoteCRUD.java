package backend.controller.lote;

import ventanas.model.Lote;
import java.util.List;

/**
 *
 * @author codeli4m
 */
public interface LoteCRUD {
    public void save(Lote producto);
    public void update(Lote producto);
    public void delete(Lote producto);
    public Lote get(int id);
    public List<Lote> list();    
}
