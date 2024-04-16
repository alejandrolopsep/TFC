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
	private String usuario = "root";
	private String contrasena = "1234";
	    private Connection con;

	    public ProductoDAO() {
	        this.con = conectar();
	    }

	    private Connection conectar() {
	        
	        try {
	            return DriverManager.getConnection(url, usuario, contrasena);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }


	 // Método para obtener productos de una sección específica
	    public List<Producto> getProductos(Seccion seccion) {
	        List<Producto> productos = new ArrayList<>();
	        String sql = "SELECT * FROM productos WHERE seccionId = ?";
	        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	            pstmt.setInt(1, seccion.getCodigo());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                Producto producto = new Producto();
	                producto.setCodigo(rs.getInt("codigo"));
	                producto.setCodigoBarras(rs.getString("codigoBarras"));
	                producto.setNombre(rs.getString("nombre"));
	                producto.setSeccionId(rs.getInt("seccionId"));
	                producto.setProveedorId(rs.getInt("proveedorId"));
	                producto.setPrecio(rs.getDouble("precio"));
	                producto.setImpuesto(rs.getDouble("impuesto"));
	                producto.setCoste(rs.getDouble("coste"));
	              
	                productos.add(producto);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return productos;
	    }
	    
	    
	 // Método para obtener productos de una sección específica
	    public List<Producto> obtenerProductosPorSeccion(Seccion seccion) {
	        List<Producto> productos = new ArrayList<>();
	        String sql = "SELECT * FROM productos WHERE seccionId = ?";
	        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	            pstmt.setInt(1, seccion.getCodigo());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                Producto producto = new Producto();
	                producto.setCodigo(rs.getInt("codigo"));
	                producto.setCodigoBarras(rs.getString("codigoBarras"));
	                producto.setNombre(rs.getString("nombre"));
	                producto.setSeccionId(rs.getInt("seccionId"));
	                producto.setProveedorId(rs.getInt("proveedorId"));
	                producto.setPrecio(rs.getDouble("precio"));
	                producto.setImpuesto(rs.getDouble("impuesto"));
	                producto.setCoste(rs.getDouble("coste"));
	                producto.setColor(rs.getString("color"));
	                productos.add(producto);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return productos;
	    }
	    public List<Producto> obtenerProductosPorProveedor(Proveedor proveedor) {
	        List<Producto> productos = new ArrayList<>();
	        String sql = "SELECT * FROM productos WHERE proveedorId = ?";
	        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	            pstmt.setInt(1, proveedor.getCodigo());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                Producto producto = new Producto();
	                
	                productos.add(producto);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return productos;
	    }
	// Método para insertar un nuevo producto
    public boolean insertarProducto(Producto producto) {
        String sql = "INSERT INTO productos (codigoBarras, nombre, seccionId, proveedorId, precio, impuesto, coste, entrada, salida, stockMinimo, stock, mostrarEnCaja, color, comoSeVende, posicionPantalla) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setString(1, producto.getCodigoBarras());
            pstmt.setString(2, producto.getNombre());
            pstmt.setInt(3, producto.getSeccionId());
            pstmt.setInt(4, producto.getProveedorId());
            pstmt.setDouble(5, producto.getPrecio());
            pstmt.setDouble(6, producto.getImpuesto());
            pstmt.setDouble(7, producto.getCoste());
            pstmt.setTimestamp(8, java.sql.Timestamp.valueOf(producto.getEntrada()));
            pstmt.setTimestamp(9, java.sql.Timestamp.valueOf(producto.getSalida()));
            pstmt.setInt(10, producto.getStockMinimo());
            pstmt.setInt(11, producto.getStock());
            pstmt.setBoolean(12, producto.isMostrarEnCaja());
            pstmt.setString(13, producto.getColor());
            pstmt.setString(14, producto.getVentaPor());
            pstmt.setInt(15, producto.getPosicionPantalla());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un producto existente
    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET codigoBarras=?, nombre=?, seccionId=?, proveedorId=?, precio=?, impuesto=?, coste=?, entrada=?, salida=?, stockMinimo=?, stock=?, mostrarEnCaja=?, color=?, comoSeVende=?, posicionPantalla=? WHERE codigo=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Setear los valores del producto
            pstmt.setString(1, producto.getCodigoBarras());
            
            pstmt.setInt(16, producto.getCodigo());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un producto
    public boolean eliminarProducto(int codigo) {
        String sql = "DELETE FROM productos WHERE codigo=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, codigo);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 // Método para obtener todos los productos
    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setCodigo(rs.getInt("codigo"));
                producto.setCodigoBarras(rs.getString("codigoBarras"));
                producto.setNombre(rs.getString("nombre"));
                producto.setSeccionId(rs.getInt("seccionId"));
                producto.setProveedorId(rs.getInt("proveedorId"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setImpuesto(rs.getDouble("impuesto"));
                producto.setCoste(rs.getDouble("coste"));
                producto.setStockMinimo(rs.getInt("stockMinimo"));
                producto.setStock(rs.getInt("stock"));
                producto.setMostrarEnCaja(rs.getBoolean("mostrarEnCaja"));
                producto.setColor(rs.getString("color"));
                producto.setVentaPor(rs.getString("comoSeVende"));
                producto.setPosicionPantalla(rs.getInt("posicionPantalla"));

                // Convertir Timestamp a LocalDateTime
                producto.setEntrada(rs.getTimestamp("entrada") != null ? rs.getTimestamp("entrada").toLocalDateTime() : null);
                producto.setSalida(rs.getTimestamp("salida") != null ? rs.getTimestamp("salida").toLocalDateTime() : null);

                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public List<Producto> obtenerProductosFiltrados(Seccion seccion, Proveedor proveedor) {
        List<Producto> productosFiltrados = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE ";

        List<String> conditions = new ArrayList<>();
        if (seccion != null) {
            conditions.add("seccionId = " + seccion.getCodigo());
        }
        if (proveedor != null) {
            conditions.add("proveedorId = " + proveedor.getCodigo());
        }

        if (!conditions.isEmpty()) {
            sql += String.join(" AND ", conditions);
        } else {
            // Si no hay condiciones, buscamos todos los productos
            sql = "SELECT * FROM productos";
        }

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setCodigo(rs.getInt("codigo"));
                producto.setCodigoBarras(rs.getString("codigoBarras"));
                producto.setNombre(rs.getString("nombre"));
                
                productosFiltrados.add(producto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return productosFiltrados;
    }


   
    
}
