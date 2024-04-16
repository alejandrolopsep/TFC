package entidades;

import java.awt.TextArea;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;

public class ProovedorDAO {
	private String url = "jdbc:mysql://localhost:33006/TFC?useSSL=false&serverTimezone=UTC";
	private String usuario = "root";
	private String contrasena = "1234";

	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtNif;
	@FXML
	private TextField txtTelefono;
	@FXML
	private TextField txtUrl;
	@FXML
	private TextField txtDireccion;
	@FXML
	private TextField txtPersonaContacto;
	@FXML
	private TextField txtFormaPago;
	@FXML
	private TextField txtImpuesto;
	@FXML
	private TextField txtRecargo;
	@FXML
	private TextField txtDescuento;
	@FXML
	private TextField txtDiaPago;
	@FXML
	private TextField txtVencimiento;
	@FXML
	private TextField txtBanco;
	@FXML
	private TextField txtNumeroCuenta;
	@FXML
	private ComboBox<String> cbPortes;
	@FXML
	private TextField txtTransporte;
	@FXML
	private ColorPicker colorPicker;
	@FXML
	private Spinner<Integer> spinnerPosicion;
	@FXML
	private TextArea txtObservaciones;

	

	// Constructor para cargar el driver
	public ProovedorDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Método para obtener conexión
	private Connection conectar() throws SQLException {
		return DriverManager.getConnection(url, usuario, contrasena);
	}

	public boolean agregarProveedor(Proveedor proveedor) {
	    String sql = "INSERT INTO proveedores (nombre, email, nif, telefono, url, direccion, personaContacto, formaPago, impuesto, recargoEquiv, descuento, diaPago, vencimiento, banco, numeroCuenta, portes, transporte, color, posicionPantalla, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = this.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, proveedor.getNombre());
	        pstmt.setString(2, proveedor.getEmail());
	        pstmt.setString(3, proveedor.getNif());
	        pstmt.setString(4, proveedor.getTelefono());
	        pstmt.setString(5, proveedor.getUrl());
	        pstmt.setString(6, proveedor.getDireccion());
	        pstmt.setString(7, proveedor.getPersonaContacto());
	        pstmt.setString(8, proveedor.getFormaPago());
	        pstmt.setDouble(9, proveedor.getImpuesto());
	        pstmt.setDouble(10, proveedor.getRecargoEquiv());
	        pstmt.setDouble(11, proveedor.getDescuento());
	        
	        // Aquí convertimos LocalDate a SQL Date
	        pstmt.setDate(12, java.sql.Date.valueOf(proveedor.getDiaPago()));
	        pstmt.setDate(13, java.sql.Date.valueOf(proveedor.getVencimiento()));
	        
	        pstmt.setString(14, proveedor.getBanco());
	        pstmt.setString(15, proveedor.getNumeroCuenta());
	        pstmt.setString(16, proveedor.getPortes());
	        pstmt.setString(17, proveedor.getTransporte());
	        pstmt.setString(18, proveedor.getColor());
	        pstmt.setInt(19, proveedor.getPosicionPantalla());
	        pstmt.setString(20, proveedor.getObservaciones());

	        pstmt.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	public boolean actualizarProveedor(Proveedor proveedor) {
	    String sql = "UPDATE proveedores SET nombre=?, email=?, nif=?, telefono=?, url=?, direccion=?, personaContacto=?, formaPago=?, impuesto=?, recargoEquiv=?, descuento=?, diaPago=?, vencimiento=?, banco=?, numeroCuenta=?, portes=?, transporte=?, color=?, posicionPantalla=?, observaciones=? WHERE codigo=?";

	    try (Connection conn = this.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, proveedor.getNombre());
	        pstmt.setString(2, proveedor.getEmail());
	        pstmt.setString(3, proveedor.getNif());
	        pstmt.setString(4, proveedor.getTelefono());
	        pstmt.setString(5, proveedor.getUrl());
	        pstmt.setString(6, proveedor.getDireccion());
	        pstmt.setString(7, proveedor.getPersonaContacto());
	        pstmt.setString(8, proveedor.getFormaPago());
	        pstmt.setDouble(9, proveedor.getImpuesto());
	        pstmt.setDouble(10, proveedor.getRecargoEquiv());
	        pstmt.setDouble(11, proveedor.getDescuento());

	        // Convertir LocalDate a SQL Date
	        pstmt.setDate(12, java.sql.Date.valueOf(proveedor.getDiaPago()));
	        pstmt.setDate(13, java.sql.Date.valueOf(proveedor.getVencimiento()));

	        pstmt.setString(14, proveedor.getBanco());
	        pstmt.setString(15, proveedor.getNumeroCuenta());
	        pstmt.setString(16, proveedor.getPortes());
	        pstmt.setString(17, proveedor.getTransporte());
	        pstmt.setString(18, proveedor.getColor());
	        pstmt.setInt(19, proveedor.getPosicionPantalla());
	        pstmt.setString(20, proveedor.getObservaciones());
	        pstmt.setInt(21, proveedor.getCodigo());

	        pstmt.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	public List<Proveedor> obtenerProveedores() {
	    List<Proveedor> proveedores = new ArrayList<>();
	    String sql = "SELECT * FROM proveedores";

	    try (Connection conn = this.conectar();
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            ResultSet rs = pstmt.executeQuery()) {

	        while (rs.next()) {
	            Proveedor proveedor = new Proveedor();
	            proveedor.setCodigo(rs.getInt("codigo"));
	            proveedor.setNombre(rs.getString("nombre"));
	            proveedor.setEmail(rs.getString("email"));
	            proveedor.setNif(rs.getString("nif"));
	            proveedor.setTelefono(rs.getString("telefono"));
	            proveedor.setUrl(rs.getString("url"));
	            proveedor.setDireccion(rs.getString("direccion"));
	            proveedor.setPersonaContacto(rs.getString("personaContacto"));
	            proveedor.setFormaPago(rs.getString("formaPago"));
	            proveedor.setImpuesto(rs.getDouble("impuesto"));
	            proveedor.setRecargoEquiv(rs.getDouble("recargoEquiv"));
	            proveedor.setDescuento(rs.getDouble("descuento"));

	            // Convertir SQL Date a LocalDate
	            proveedor.setDiaPago(rs.getDate("diaPago") != null ? rs.getDate("diaPago").toLocalDate() : null);
	            proveedor.setVencimiento(rs.getDate("vencimiento") != null ? rs.getDate("vencimiento").toLocalDate() : null);

	            proveedor.setBanco(rs.getString("banco"));
	            proveedor.setNumeroCuenta(rs.getString("numeroCuenta"));
	            proveedor.setPortes(rs.getString("portes"));
	            proveedor.setTransporte(rs.getString("transporte"));
	            proveedor.setColor(rs.getString("color"));
	            proveedor.setPosicionPantalla(rs.getInt("posicionPantalla"));
	            proveedor.setObservaciones(rs.getString("observaciones"));
	            proveedores.add(proveedor);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return proveedores;
	}


	public boolean insertarProveedor(Proveedor proveedor) {
	    String sql = "INSERT INTO proveedores (nombre, email, nif, telefono, url, direccion, personaContacto, formaPago, impuesto, recargoEquiv, descuento, diaPago, vencimiento, banco, numeroCuenta, portes, transporte, color, posicionPantalla, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = this.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, proveedor.getNombre());
	        pstmt.setString(2, proveedor.getEmail());
	        pstmt.setString(3, proveedor.getNif());
	        pstmt.setString(4, proveedor.getTelefono());
	        pstmt.setString(5, proveedor.getUrl());
	        pstmt.setString(6, proveedor.getDireccion());
	        pstmt.setString(7, proveedor.getPersonaContacto());
	        pstmt.setString(8, proveedor.getFormaPago());
	        pstmt.setDouble(9, proveedor.getImpuesto());
	        pstmt.setDouble(10, proveedor.getRecargoEquiv());
	        pstmt.setDouble(11, proveedor.getDescuento());

	        // Convertir LocalDate a java.sql.Date
	        pstmt.setDate(12, proveedor.getDiaPago() == null ? null : java.sql.Date.valueOf(proveedor.getDiaPago()));
	        pstmt.setDate(13, proveedor.getVencimiento() == null ? null : java.sql.Date.valueOf(proveedor.getVencimiento()));

	        pstmt.setString(14, proveedor.getBanco());
	        pstmt.setString(15, proveedor.getNumeroCuenta());
	        pstmt.setString(16, proveedor.getPortes());
	        pstmt.setString(17, proveedor.getTransporte());
	        pstmt.setString(18, proveedor.getColor());
	        pstmt.setInt(19, proveedor.getPosicionPantalla());
	        pstmt.setString(20, proveedor.getObservaciones());

	        pstmt.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	

}
