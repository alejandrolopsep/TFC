package entidades;

import java.time.LocalDate;

public class Proveedor {
    private int codigo;
    private String nombre;
    private String email;
    private String nif;
    private String telefono;
    private String url;
    private String direccion;
    private String personaContacto;
    private String formaPago;
    private double impuesto;
    private double recargoEquiv;
    private double descuento;
    private LocalDate diaPago;
    private LocalDate vencimiento;
    private String banco;
    private String numeroCuenta;
    private String portes;
    private String transporte;
    private String color;
    private int posicionPantalla;
    private String observaciones;

    // Constructor vacío
    public Proveedor() {
    }

    // Constructor con parámetros
    public Proveedor(int codigo, String nombre, String email, String nif, String telefono, String url, String direccion, String personaContacto, String formaPago, double impuesto, double recargoEquiv, double descuento, LocalDate diaPago, LocalDate vencimiento, String banco, String numeroCuenta, String portes, String transporte, String color, int posicionPantalla, String observaciones) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.email = email;
        this.nif = nif;
        this.telefono = telefono;
        this.url = url;
        this.direccion = direccion;
        this.personaContacto = personaContacto;
        this.formaPago = formaPago;
        this.impuesto = impuesto;
        this.recargoEquiv = recargoEquiv;
        this.descuento = descuento;
        this.diaPago = diaPago;
        this.vencimiento = vencimiento;
        this.banco = banco;
        this.numeroCuenta = numeroCuenta;
        this.portes = portes;
        this.transporte = transporte;
        this.color = color;
        this.posicionPantalla = posicionPantalla;
        this.observaciones = observaciones;
    }

    public Proveedor(int codigo, String nombre) {
    	this.codigo = codigo;
        this.nombre = nombre;
	}

	// Getters y setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getRecargoEquiv() {
        return recargoEquiv;
    }

    public void setRecargoEquiv(double recargoEquiv) {
        this.recargoEquiv = recargoEquiv;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public LocalDate getDiaPago() {
        return diaPago;
    }

    public void setDiaPago(LocalDate diaPago) {
        this.diaPago = diaPago;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }



	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getPortes() {
		return portes;
	}

	public void setPortes(String portes) {
		this.portes = portes;
	}

	public String getTransporte() {
		return transporte;
	}

	public void setTransporte(String transporte) {
		this.transporte = transporte;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPosicionPantalla() {
		return posicionPantalla;
	}

	public void setPosicionPantalla(int posicionPantalla) {
		this.posicionPantalla = posicionPantalla;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}