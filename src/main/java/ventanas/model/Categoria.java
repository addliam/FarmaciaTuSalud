package ventanas.model;

public class Categoria {

    private int id;
    private String nombre;
    private String desc;
    private boolean activado = true;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setActivado(boolean activado) {this.activado = activado;}
}
