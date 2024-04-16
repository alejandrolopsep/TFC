package entidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private String url = "jdbc:mysql://localhost:33006/TFC?useSSL=false&serverTimezone=UTC";
    private String usuario = "root";
    private String contrasena = "1234";

    public ClienteDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, contrasena);
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("codigo"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setDniCif(rs.getString("dniCif"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setDescuento(rs.getInt("descuento"));
                cliente.setRecomendadoPor(rs.getString("recomendadoPor"));
                cliente.setAtendidoPor(rs.getString("atendidoPor"));
                cliente.setCumpleanos(rs.getDate("cumpleanos") != null ? rs.getDate("cumpleanos").toLocalDate() : null);
                cliente.setFechaAlta(rs.getDate("fechaAlta").toLocalDate());
                cliente.setUltimaVisita(rs.getDate("ultimaVisita") != null ? rs.getDate("ultimaVisita").toLocalDate() : null);
                cliente.setSexo(rs.getString("sexo"));
                cliente.setEnviarEmail(rs.getBoolean("enviarEmail"));
                cliente.setEnviarWhatsapp(rs.getBoolean("enviarWhatsapp"));
                cliente.setEnviarSms(rs.getBoolean("enviarSms"));
                cliente.setObservaciones(rs.getString("observaciones"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clientes;
    }

    public boolean insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, apellidos, dniCif, telefono, email, direccion, descuento, recomendadoPor, atendidoPor, cumpleanos, fechaAlta, ultimaVisita, sexo, enviarEmail, enviarWhatsapp, enviarSms, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellidos());
            pstmt.setString(3, cliente.getDniCif());
            pstmt.setString(4, cliente.getTelefono());
            pstmt.setString(5, cliente.getEmail());
            pstmt.setString(6, cliente.getDireccion());
            pstmt.setDouble(7, cliente.getDescuento());
            pstmt.setString(8, cliente.getRecomendadoPor());
            pstmt.setString(9, cliente.getAtendidoPor());
            pstmt.setDate(10, cliente.getCumpleanos() != null ? java.sql.Date.valueOf(cliente.getCumpleanos()) : null);
            pstmt.setDate(11, java.sql.Date.valueOf(cliente.getFechaAlta()));
            pstmt.setDate(12, cliente.getUltimaVisita() != null ? java.sql.Date.valueOf(cliente.getUltimaVisita()) : null);
            pstmt.setString(13, cliente.getSexo());
            pstmt.setBoolean(14, cliente.isEnviarEmail());
            pstmt.setBoolean(15, cliente.isEnviarWhatsapp());
            pstmt.setBoolean(16, cliente.isEnviarSms());
            pstmt.setString(17, cliente.getObservaciones());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setCodigo(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating client failed, no ID obtained.");
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
