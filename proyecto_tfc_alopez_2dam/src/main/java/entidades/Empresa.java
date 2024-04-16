package entidades;

public class Empresa {
    private String nombre;
    private String direccion;
    private String poblacion;
    private String provincia;
    private String codigoPostal;
    private String cifNif;
    private String telefono;
    private String email;
    private String logoPath;

    public Empresa(String nombre, String direccion, String poblacion, String provincia,
                   String codigoPostal, String cifNif, String telefono, String email, String logoPath) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
        this.cifNif = cifNif;
        this.telefono = telefono;
        this.email = email;
        this.logoPath = logoPath;
    }

    // Getters aquí
    public String getNombre() { return nombre; }
    public String getDireccionCompleta() {
        return direccion + ", " + poblacion + ", " + provincia + ", CP: " + codigoPostal;
    }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public String getLogoPath() { return logoPath; }

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getCifNif() {
		return cifNif;
	}

	public void setCifNif(String cifNif) {
		this.cifNif = cifNif;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
}
