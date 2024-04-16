package entidades;

import java.time.LocalDate;

public class Cliente {
    private int codigo;
    private String nombre;
    private String apellidos;
    private String dniCif;
    private String telefono;
    private String email;
    private String direccion;
    private double descuento;
    private String recomendadoPor;
    private String atendidoPor;
    private LocalDate cumpleanos;
    private LocalDate fechaAlta;
    private LocalDate ultimaVisita;
    private String sexo;
    private boolean enviarEmail;
    private boolean enviarWhatsapp;
    private boolean enviarSms;
    private String observaciones;

    
    public Cliente() {
    }

    
    public Cliente(int codigo, String nombre, String apellidos, String dniCif, String telefono, String email, String direccion, double descuento, String recomendadoPor, String atendidoPor, LocalDate cumpleanos, LocalDate fechaAlta, LocalDate ultimaVisita, String sexo, boolean enviarEmail, boolean enviarWhatsapp, boolean enviarSms, String observaciones) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dniCif = dniCif;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.descuento = descuento;
        this.recomendadoPor = recomendadoPor;
        this.atendidoPor = atendidoPor;
        this.cumpleanos = cumpleanos;
        this.fechaAlta = fechaAlta;
        this.ultimaVisita = ultimaVisita;
        this.sexo = sexo;
        this.enviarEmail = enviarEmail;
        this.enviarWhatsapp = enviarWhatsapp;
        this.enviarSms = enviarSms;
        this.observaciones = observaciones;
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

	public String getDniCif() {
		return dniCif;
	}

	public void setDniCif(String dniCif) {
		this.dniCif = dniCif;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public String getRecomendadoPor() {
		return recomendadoPor;
	}

	public void setRecomendadoPor(String recomendadoPor) {
		this.recomendadoPor = recomendadoPor;
	}

	public String getAtendidoPor() {
		return atendidoPor;
	}

	public void setAtendidoPor(String atendidoPor) {
		this.atendidoPor = atendidoPor;
	}

	public LocalDate getCumpleanos() {
		return cumpleanos;
	}

	public void setCumpleanos(LocalDate cumpleanos) {
		this.cumpleanos = cumpleanos;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public LocalDate getUltimaVisita() {
		return ultimaVisita;
	}

	public void setUltimaVisita(LocalDate ultimaVisita) {
		this.ultimaVisita = ultimaVisita;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public boolean isEnviarEmail() {
		return enviarEmail;
	}

	public void setEnviarEmail(boolean enviarEmail) {
		this.enviarEmail = enviarEmail;
	}

	public boolean isEnviarWhatsapp() {
		return enviarWhatsapp;
	}

	public void setEnviarWhatsapp(boolean enviarWhatsapp) {
		this.enviarWhatsapp = enviarWhatsapp;
	}

	public boolean isEnviarSms() {
		return enviarSms;
	}

	public void setEnviarSms(boolean enviarSms) {
		this.enviarSms = enviarSms;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
    public String toString() {
        return "Cliente{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dniCif='" + dniCif + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", descuento=" + descuento +
                ", recomendadoPor='" + recomendadoPor + '\'' +
                ", atendidoPor='" + atendidoPor + '\'' +
                ", cumpleanos=" + cumpleanos +
                ", fechaAlta=" + fechaAlta +
                ", ultimaVisita=" + ultimaVisita +
                ", sexo='" + sexo + '\'' +
                ", enviarEmail=" + enviarEmail +
                ", enviarWhatsapp=" + enviarWhatsapp +
                ", enviarSms=" + enviarSms +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}

