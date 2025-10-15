package bargamingBackend.bargaming.common.enums;

public enum EstadoVenta {
    PAGADA("PAGADA", 1),
    ENVIADA("ENVIADA", 2),
    ENTREGADA("ENTREGADA", 3),
    CANCELADA("CANCELADA", 4);

    private final String nombre;
    private final int codigo;

    EstadoVenta(String nombre, int codigo) {
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
