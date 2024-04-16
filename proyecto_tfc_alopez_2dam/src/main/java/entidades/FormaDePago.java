package entidades;

public class FormaDePago {
    private int codigo; 
    private String nombre;
    private String color; 
    // Constructor sin argumentos
    public FormaDePago() {
    }

    // Constructor con argumentos
    public FormaDePago(int codigo, String nombre, String color) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.color = color;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    
    @Override
    public String toString() {
        return "FormaDePago{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
