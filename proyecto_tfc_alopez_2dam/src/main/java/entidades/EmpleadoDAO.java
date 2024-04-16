package entidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    private String url = "jdbc:mysql://localhost:33006/TFC?useSSL=false&serverTimezone=UTC";
    private String usuario = "root";
    private String contrasena = "1234";

    public EmpleadoDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, contrasena);
    }

    public List<Empleado> obtenerEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setCodigo(rs.getInt("codigo"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellidos(rs.getString("apellidos"));
                empleado.setDireccion(rs.getString("direccion"));
                empleado.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                empleado.setFechaAlta(rs.getDate("fechaAlta").toLocalDate());
                empleado.setCifNif(rs.getString("cifNif"));
                empleado.setTelefono(rs.getString("telefono"));
                empleado.setNumeroSS(rs.getString("numeroSS"));
                empleado.setCategoria(rs.getString("categoria"));
                empleado.setSexo(rs.getString("sexo"));
                empleado.setApareceEnAgenda(rs.getBoolean("apareceEnAgenda"));
                empleado.setApareceEnCaja(rs.getBoolean("apareceEnCaja"));
                empleado.setColor(rs.getString("color"));
                empleado.setObservaciones(rs.getString("observaciones"));
                // La contraseña no se recupera por razones de seguridad
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return empleados;
    }

    public boolean insertarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleados (nombre, apellidos, direccion, fechaNacimiento, fechaAlta, cifNif, telefono, numeroSS, categoria, sexo, apareceEnAgenda, apareceEnCaja, color, observaciones, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getApellidos());
            pstmt.setString(3, empleado.getDireccion());
            pstmt.setDate(4, Date.valueOf(empleado.getFechaNacimiento()));
            pstmt.setDate(5, Date.valueOf(empleado.getFechaAlta()));
            pstmt.setString(6, empleado.getCifNif());
            pstmt.setString(7, empleado.getTelefono());
            pstmt.setString(8, empleado.getNumeroSS());
            pstmt.setString(9, empleado.getCategoria());
            pstmt.setString(10, empleado.getSexo());
            pstmt.setBoolean(11, empleado.isApareceEnAgenda());
            pstmt.setBoolean(12, empleado.isApareceEnCaja());
            pstmt.setString(13, empleado.getColor());
            pstmt.setString(14, empleado.getObservaciones());
            pstmt.setString(15, empleado.getContrasena()); // Incluye la contraseña

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    empleado.setCodigo(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating employee failed, no ID obtained.");
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
