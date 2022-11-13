// TODO: mover carpeta ventanas.model a backend.models
package ventanas.model;

/**
 *
 * @author codeli4m
 */
public class Transaccion {
    public static final String TRANSACCION_ENTRADA = "entrada";
    public static final String TRANSACCION_SALIDA = "salida";    
    private String id;
    private String tipoTransaccion;
    private String loteId;
    private int cantidad;
    private String usuarioId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) throws Exception {
        if (tipoTransaccion.contentEquals(TRANSACCION_ENTRADA) || tipoTransaccion.contentEquals(TRANSACCION_SALIDA)){
            this.tipoTransaccion = tipoTransaccion;            
        }else{
            throw new Exception("Tipo de transaccion desconocida");            
        }
    }

    public String getLoteId() {
        return loteId;
    }

    public void setLoteId(String loteId) {
        this.loteId = loteId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    
}
