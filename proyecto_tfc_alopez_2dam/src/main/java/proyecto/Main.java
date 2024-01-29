package proyecto;

public class Main {

	public static void main(String[] args) {
		
		String nombreUsuario = "admin";
        String contrasena = "admin";

        UsuarioServicio.insertarUsuario(nombreUsuario, contrasena);
	}

}
