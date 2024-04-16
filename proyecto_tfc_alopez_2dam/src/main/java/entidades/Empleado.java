package entidades;

import java.time.LocalDate;

public class Empleado {
    private int codigo;
    private String nombre;
    private String apellidos;
    private String direccion;
    private LocalDate fechaNacimiento;
    private LocalDate fechaAlta;
    private String cifNif;
    private String telefono;
    private String numeroSS;
    private String categoria;
    private String sexo;
    private boolean apareceEnAgenda;
    private boolean apareceEnCaja;
    private String color;
    private String observaciones;
    private String contrasena; // Nuevo campo para la contraseña

    // Constructor vacío
    public Empleado() {
    }

    // Constructor con todos los atributos, incluyendo la nueva contraseña
    public Empleado(int codigo, String nombre, String apellidos, String direccion, LocalDate fechaNacimiento,
                    LocalDate fechaAlta, String cifNif, String telefono, String numeroSS, String categoria,
                    String sexo, boolean apareceEnAgenda, boolean apareceEnCaja, String color, String observaciones,
                    String contrasena) { // Añade contrasena como parámetro
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaAlta = fechaAlta;
        this.cifNif = cifNif;
        this.telefono = telefono;
        this.numeroSS = numeroSS;
        this.categoria = categoria;
        this.sexo = sexo;
        this.apareceEnAgenda = apareceEnAgenda;
        this.apareceEnCaja = apareceEnCaja;
        this.color = color;
        this.observaciones = observaciones;
        this.contrasena = contrasena; // Inicializa el campo contrasena
    }

    
    

    public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getCifNif() {
		return cifNif;
	}

	public void setCifNif(String cifNif) {
		this.cifNif = cifNif;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNumeroSS() {
		return numeroSS;
	}

	public void setNumeroSS(String numeroSS) {
		this.numeroSS = numeroSS;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public boolean isApareceEnAgenda() {
		return apareceEnAgenda;
	}

	public void setApareceEnAgenda(boolean apareceEnAgenda) {
		this.apareceEnAgenda = apareceEnAgenda;
	}

	public boolean isApareceEnCaja() {
		return apareceEnCaja;
	}

	public void setApareceEnCaja(boolean apareceEnCaja) {
		this.apareceEnCaja = apareceEnCaja;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
    public String toString() {
        return "Empleado{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaAlta=" + fechaAlta +
                ", cifNif='" + cifNif + '\'' +
                ", telefono='" + telefono + '\'' +
                ", numeroSS='" + numeroSS + '\'' +
                ", categoria='" + categoria + '\'' +
                ", sexo='" + sexo + '\'' +
                ", apareceEnAgenda=" + apareceEnAgenda +
                ", apareceEnCaja=" + apareceEnCaja +
                ", color='" + color + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", contrasena='" + contrasena + '\'' + // Incluye contrasena en toString
                '}';
    }
}
