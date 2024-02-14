package entidades;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Producto {
    private long id; 
    private String nombre;
    private double precio;
    private double peso;
    private String categoria;
    private boolean selected; 

    // Constructor con los parámetros mencionados
    public Producto(String nombre, double precio, double peso, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
        this.categoria = categoria;
    }

    public Producto() {
		// TODO Auto-generated constructor stub
	}

	

	// Getters y setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    @Override
    public String toString() {
        return "ID: " + this.id + ", Nombre: " + this.nombre + ", Precio: " + this.precio + ", Peso: " + this.peso + ", Categoría: " + this.categoria;
    }
}
