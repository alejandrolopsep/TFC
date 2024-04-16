package entidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormaDePagoDAO {
	private String url = "jdbc:mysql://localhost:33006/TFC?useSSL=false&serverTimezone=UTC";
	private String usuario = "root";
	private String contrasena = "1234";

    public FormaDePagoDAO() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection conectar() {
        // Establece la conexión con la base de datos
        try {
            return DriverManager.getConnection(url, usuario, contrasena);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertarFormaDePago(FormaDePago formaDePago) {
        // SQL para insertar una nueva forma de pago
        String sql = "INSERT INTO formasPago (nombre, color) VALUES (?, ?)";
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, formaDePago.getNombre());
            pstmt.setString(2, formaDePago.getColor());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<FormaDePago> obtenerFormasDePago() {
        // SQL para obtener todas las formas de pago
        String sql = "SELECT * FROM formasPago";
        List<FormaDePago> formasDePago = new ArrayList<>();
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                FormaDePago formaDePago = new FormaDePago();
                formaDePago.setCodigo(rs.getInt("codigo"));
                formaDePago.setNombre(rs.getString("nombre"));
                formaDePago.setColor(rs.getString("color"));
                formasDePago.add(formaDePago);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formasDePago;
    }

    
}

