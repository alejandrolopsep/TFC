package entidades;


import java.time.LocalDateTime;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Producto {
    private int codigo;
    private String codigoBarras;
    private String nombre;
    private int seccionId;
    private int proveedorId;
    private double precio;
    private double impuesto;
    private double coste;
    private double margenComercial; 
    private LocalDateTime entrada;
    private LocalDateTime salida;
    private int stockMinimo;
    private int stock;
    private boolean mostrarEnCaja;
    
    private int cantidad;
    private double total;
    

    
    
    private String color;
    private String ventaPor; // "Unidades" o "Peso"
    
    private int posicionPantalla;

    // Constructor vacío
    public Producto() {
    }
    
 // Constructor que acepta nombre y color
    public Producto(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
    }

    // Constructor con todos los parámetros
    public Producto(int codigo, String codigoBarras, String nombre, int seccionId, int proveedorId, double precio, double impuesto, double coste, LocalDateTime entrada, LocalDateTime salida, int stockMinimo, int stock, boolean mostrarEnCaja, String color, String ventaPor, int posicionPantalla) {
        this.codigo = codigo;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.seccionId = seccionId;
        this.proveedorId = proveedorId;
        this.precio = precio;
        this.impuesto = impuesto;
        this.coste = coste;
        this.entrada = entrada;
        this.salida = salida;
        this.stockMinimo = stockMinimo;
        this.stock = stock;
        this.mostrarEnCaja = mostrarEnCaja;
        this.color = color;
        this.ventaPor = ventaPor;
        this.posicionPantalla = posicionPantalla;
        this.margenComercial = precio - coste; // Calcula el margen comercial
    }
    
    public Producto(String nombre, double precio, double impuesto, int cantidad, String codigoBarras) {
        this.nombre = nombre;
        this.precio = precio;
        this.impuesto = impuesto;
        this.cantidad = cantidad;
        this.total = total;
        this.codigoBarras = codigoBarras;
    }
    
    public Producto(String nombre, double precio, double impuesto, int cantidad, double total, String codigoBarras) {
        this.nombre = nombre;
        this.precio = precio;
        this.impuesto = impuesto;
        this.cantidad = cantidad;
        this.total = total;
        this.codigoBarras = codigoBarras;
    }

	public double getMargenComercial() {
        return precio - coste;
    }

    
    public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		 this.cantidad = cantidad;
	        this.total = (precio + precio * impuesto) * cantidad;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setMargenComercial(double margenComercial) {
		this.margenComercial = margenComercial;
	}

	public String getVentaPor() {
        return ventaPor;
    }

    public void setVentaPor(String ventaPor) {
        this.ventaPor = ventaPor;
    }
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSeccionId() {
		return seccionId;
	}

	public void setSeccionId(int seccionId) {
		this.seccionId = seccionId;
	}

	public int getProveedorId() {
		return proveedorId;
	}

	public void setProveedorId(int proveedorId) {
		this.proveedorId = proveedorId;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}

	public double getCoste() {
		return coste;
	}

	public void setCoste(double coste) {
		this.coste = coste;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSalida() {
		return salida;
	}

	public void setSalida(LocalDateTime salida) {
		this.salida = salida;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isMostrarEnCaja() {
		return mostrarEnCaja;
	}

	public void setMostrarEnCaja(boolean mostrarEnCaja) {
		this.mostrarEnCaja = mostrarEnCaja;
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

    
}