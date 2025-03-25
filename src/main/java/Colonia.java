public class Colonia {
    private Integer id;
    private String nombre;
    private String cp;
    private Municipio municipio;

    public Colonia() {
    }

    public Colonia(Integer id, String nombre, Municipio municipio, String cp) {
        this.id = id;
        this.nombre = nombre;
        this.municipio = municipio;
        this.cp = cp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
}
