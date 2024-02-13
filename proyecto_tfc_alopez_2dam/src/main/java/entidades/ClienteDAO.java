package entidades;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private String url = "jdbc:mysql://localhost:33006/TFC?useSSL=false&serverTimezone=UTC";
    private String user = "root";
    private String password = "1234";

    public ClienteDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void añadirCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, apellidos, telefono, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellidos());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getEmail());
            pstmt.executeUpdate();
            System.out.println("Cliente añadido exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al añadir el cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(long id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Cliente eliminado exitosamente.");
            } else {
                System.out.println("No se encontró el cliente con id: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
        }
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("email"));
                cliente.setId(rs.getLong("id"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los clientes: " + e.getMessage());
        }
        return clientes;
    }
}

