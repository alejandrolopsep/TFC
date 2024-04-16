package entidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeccionDAO {

	 private String url = "jdbc:mysql://localhost:33006/TFC?useSSL=false&serverTimezone=UTC";
	    private String usuario = "root";
	    private String contrasena = "1234";

	  

    public void agregarSeccion(Seccion seccion) {
        String sql = "INSERT INTO secciones (nombre, color, posicion) VALUES (?, ?, ?)";
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, seccion.getNombre());
            pstmt.setString(2, seccion.getColor());
            pstmt.setInt(3, seccion.getPosicion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    public void eliminarSeccion(int id) {
        String sql = "DELETE FROM secciones WHERE codigo = ?";
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    public void actualizarSeccion(Seccion seccion) {
        String sql = "UPDATE secciones SET nombre = ?, color = ?, posicion = ? WHERE codigo = ?";
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, seccion.getNombre());
            pstmt.setString(2, seccion.getColor());
            pstmt.setInt(3, seccion.getPosicion());
            pstmt.setInt(4, seccion.getCodigo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    

    public List<Seccion> obtenerSecciones() {
        List<Seccion> secciones = new ArrayList<>();
        String sql = "SELECT * FROM secciones ORDER BY posicion";
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Seccion seccion = new Seccion();
                seccion.setCodigo(rs.getInt("codigo"));
                seccion.setNombre(rs.getString("nombre"));
                seccion.setColor(rs.getString("color"));
                seccion.setPosicion(rs.getInt("posicion"));
                secciones.add(seccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return secciones;
    }

	    
    public SeccionDAO() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, contrasena);
    }

    

    
}
