package entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ControlHorario {
    private int empleadoId;
    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private String observaciones;
    private String estado;  // Assuming you might need to store 'Entry' or 'Exit' states

    // Constructor for detailed date and time handling
    public ControlHorario(int empleadoId, LocalDate fecha, LocalTime horaEntrada, LocalTime horaSalida, String observaciones) {
        this.empleadoId = empleadoId;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.observaciones = observaciones;
    }

    // Constructor for retrieving records
    public ControlHorario(LocalDate fecha, LocalTime horaEntrada, LocalTime horaSalida, String observaciones) {
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.observaciones = observaciones;
    }
    
    // Additional constructor that your application logic seems to require
    public ControlHorario(String estado, LocalDateTime fechaHora, String observaciones, int empleadoId) {
        this.estado = estado;
        this.fecha = fechaHora.toLocalDate();
        this.horaEntrada = (estado.equals("Entry")) ? fechaHora.toLocalTime() : null;
        this.horaSalida = (estado.equals("Exit")) ? fechaHora.toLocalTime() : null;
        this.observaciones = observaciones;
        this.empleadoId = empleadoId;
    }

    // Getters and Setters as needed
    public int getEmpleadoId() {
        return empleadoId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // ToString for debugging
    @Override
    public String toString() {
        return "ControlHorario{" +
               "estado='" + estado + '\'' +
               ", empleadoId=" + empleadoId +
               ", fecha=" + fecha +
               ", horaEntrada=" + horaEntrada +
               ", horaSalida=" + horaSalida +
               ", observaciones='" + observaciones + '\'' +
               '}';
    }
}


