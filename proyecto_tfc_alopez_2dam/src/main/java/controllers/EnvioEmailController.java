package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.Properties;

public class EnvioEmailController {

    @FXML
    private DatePicker filtroAnoNacimiento;
    @FXML
    private ListView<String> listaClientesEmails; 
    @FXML
    private TextField txtAsunto;
    @FXML
    private TextArea txtContenido;

    public void filtrarClientesPorAno() {
        LocalDate selectedDate = filtroAnoNacimiento.getValue();
        if (selectedDate != null) {
            int year = selectedDate.getYear();
            
        } else {
            mostrarAlerta("Error", "Por favor, seleccione un año.");
        }
    }

    public void enviarEmail() {
        String asunto = txtAsunto.getText();
        String contenido = txtContenido.getText();
        // Concatena los correos de listaClientesEmails para crear la cadena de destinatarios
        String destinatarios = String.join(",", listaClientesEmails.getItems());

        // Configura los detalles del remitente y servidor SMTP aquí
        String remitente = "x";
        String clave = "x";

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatarios));
            message.setSubject(asunto);
            message.setText(contenido);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            mostrarAlerta("Éxito", "Correo enviado exitosamente.");
        } catch (MessagingException me) {
            mostrarAlerta("Error", "Error al enviar el correo: " + me.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}

