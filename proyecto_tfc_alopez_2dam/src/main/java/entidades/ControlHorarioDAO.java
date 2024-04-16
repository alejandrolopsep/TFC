package entidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ControlHorarioDAO {
    private String url = "jdbc:mysql://localhost:33006/TFC?useSSL=false&serverTimezone=UTC";
    private String usuario = "root";
    private String contrasena = "1234";

    /**
     * Método para registrar o actualizar el horario de entrada/salida
     * @param controlHorario Objeto que contiene los datos del horario a registrar o actualizar
     */
    public void registrarActualizarHorario(ControlHorario controlHorario) throws SQLException {
        // Verificar si ya existe un registro para el empleado en la fecha especificada
        String selectSql = "SELECT id, horaEntrada FROM RegistrosHorarios WHERE empleadoId = ? AND fecha = ?";
        String insertSql = "INSERT INTO RegistrosHorarios (empleadoId, fecha, horaEntrada, observaciones) VALUES (?, ?, ?, ?)";
        String updateSql = "UPDATE RegistrosHorarios SET horaSalida = ?, observaciones = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena);
             PreparedStatement selectStmt = conn.prepareStatement(selectSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            selectStmt.setInt(1, controlHorario.getEmpleadoId());
            selectStmt.setDate(2, java.sql.Date.valueOf(controlHorario.getFecha()));
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                // Registro existe, actualizar con hora de salida
                int registroId = rs.getInt("id");
                updateStmt.setTime(1, (controlHorario.getHoraSalida() != null) ? java.sql.Time.valueOf(controlHorario.getHoraSalida()) : null);
                updateStmt.setString(2, controlHorario.getObservaciones());
                updateStmt.setInt(3, registroId);
                updateStmt.executeUpdate();
            } else {
                // No existe registro, insertar nuevo
                insertStmt.setInt(1, controlHorario.getEmpleadoId());
                insertStmt.setDate(2, java.sql.Date.valueOf(controlHorario.getFecha()));
                insertStmt.setTime(3, (controlHorario.getHoraEntrada() != null) ? java.sql.Time.valueOf(controlHorario.getHoraEntrada()) : null);
                insertStmt.setString(4, controlHorario.getObservaciones());
                insertStmt.executeUpdate();
            }
        }
    }

    
    public int obtenerEmpleadoIdPorContrasena(String password) throws SQLException {
        String sql = "SELECT codigo FROM empleados WHERE contrasena = ?";
        try (Connection conn = DriverManager.getConnection(url, usuario, this.contrasena);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("codigo");
                } else {
                    return -1;  // employee not found or password incorrect
                }
            }
        }
    }
    
    public List<ControlHorario> obtenerRegistrosPorEmpleado(int empleadoId) throws SQLException {
        List<ControlHorario> listaHorarios = new ArrayList<>();
        String sql = "SELECT fecha, horaEntrada, horaSalida, observaciones FROM RegistrosHorarios WHERE empleadoId = ?";
        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empleadoId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                LocalTime horaEntrada = (rs.getTime("horaEntrada") != null) ? rs.getTime("horaEntrada").toLocalTime() : null;
                LocalTime horaSalida = (rs.getTime("horaSalida") != null) ? rs.getTime("horaSalida").toLocalTime() : null;
                String observaciones = rs.getString("observaciones");
                listaHorarios.add(new ControlHorario(fecha, horaEntrada, horaSalida, observaciones));
            }
        }
        return listaHorarios;
    }


    
    
    public List<ControlHorario> obtenerTodos() throws SQLException {
        List<ControlHorario> listaHorarios = new ArrayList<>();
        String sql = "SELECT empleadoId, fecha, horaEntrada, horaSalida, observaciones FROM RegistrosHorarios";
        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int empleadoId = rs.getInt("empleadoId");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                LocalTime horaEntrada = rs.getTime("horaEntrada") != null ? rs.getTime("horaEntrada").toLocalTime() : null;
                LocalTime horaSalida = rs.getTime("horaSalida") != null ? rs.getTime("horaSalida").toLocalTime() : null;
                String observaciones = rs.getString("observaciones");
                ControlHorario horario = new ControlHorario(empleadoId, fecha, horaEntrada, horaSalida, observaciones);
                listaHorarios.add(horario);
            }
        }
        return listaHorarios;
    }
}

