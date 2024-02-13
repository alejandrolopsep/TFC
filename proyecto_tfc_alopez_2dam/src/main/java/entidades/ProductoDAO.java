package entidades;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private String url = "jdbc:mysql://localhost:33006/TFC?useSSL=false&serverTimezone=UTC";
    private String user = "root";
    private String password = "1234";

    public ProductoDAO() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void añadirProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, precio, peso, categoria) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecio());
            pstmt.setDouble(3, producto.getPeso());
            pstmt.setString(4, producto.getCategoria());
            pstmt.executeUpdate();
            System.out.println("Producto añadido exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al añadir el producto: " + e.getMessage());
        }
    }

    public void eliminarProducto(long id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setFloat(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Producto eliminado exitosamente.");
            } else {
                System.out.println("No se encontró el producto con id: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }
    }
    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getDouble("peso"),
                        rs.getString("categoria"));
                // Asegúrate de establecer el ID del producto aquí
                producto.setId(rs.getInt("id"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los productos: " + e.getMessage());
        }
        return productos;
    }
}
