package bargamingBackend.bargaming.common.enums;

public enum EstadoProducto {
    DISPONIBLE("DISPONIBLE", 1), VENDIDO("VENDIDO", 2);

    private String nombre;
    private int codigo;

    EstadoProducto(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }
}