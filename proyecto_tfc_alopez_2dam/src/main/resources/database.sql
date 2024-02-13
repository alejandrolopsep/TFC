CREATE DATABASE IF NOT EXISTS TFC;
USE TFC;

CREATE TABLE IF NOT EXISTS usuarios (
    usuario VARCHAR(50) PRIMARY KEY,
    contrasena VARCHAR(100) NOT NULL
);

INSERT INTO usuarios (usuario, contrasena) VALUES
('a','a');

CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS compras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    fecha_compra DATE NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    peso DECIMAL(10, 2) NOT NULL,
    categoria VARCHAR(255) NOT NULL
);

INSERT INTO productos (nombre, precio, peso, categoria) VALUES
('Manzanas', 1.50, 0.5, 'Frutas'),
('Pan integral', 2.00, 0.3, 'Panadería'),
('Leche', 0.90, 1.0, 'Lácteos'),
('Tomates', 2.50, 0.5, 'Verduras'),
('Pollo', 5.00, 1.0, 'Carnes');

