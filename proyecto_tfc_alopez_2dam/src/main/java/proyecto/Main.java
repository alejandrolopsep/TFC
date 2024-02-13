package proyecto;


public class Main {

    public static void main(String[] args) {
        // Datos del usuario de prueba
        String nombreUsuario = "a";
        String contrasena = "a";

        // Insertar el usuario en la base de datos
        UsuarioServicio.insertarUsuario(nombreUsuario, contrasena);

        // Mensaje de confirmación
        System.out.println("Usuario insertado con éxito para pruebas.");
    }
}
