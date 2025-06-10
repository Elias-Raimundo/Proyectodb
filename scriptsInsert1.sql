SET search_path TO Donaciones;

-- Insertar datos en PADRINO
INSERT INTO Donaciones.Padrino (dni, nombre, apellido, email, fecha_nac, facebook, cod_postal, direccion) VALUES
('19870996', 'Juan', 'Pérez', 'juan.perez@email.com', '1980-05-10', 'facebook.com/juanperez', '1000', 'Buenos Aires 123'),
('13567880', 'María', 'González', 'maria.gonzalez@email.com', '1975-11-20', 'facebook.com/mariagonzalez', '1010', 'San Martin 742');

-- Insertar datos en TELEFONO
INSERT INTO Donaciones.Telefono (telefono, dni) VALUES
('3585678993', '19870996'),
('3582560870', '13567880');

-- Insertar datos en DONANTE
INSERT INTO Donaciones.Donante (dni, cuil, ocupacion) VALUES
('19870996', '20198709969', 'Ingeniero'),
('13567880', '27135678803', 'Doctora');

-- Insertar datos en CONTACTO
INSERT INTO Donaciones.Contacto (dni, estado, fecha_primer_contacto, fecha_alta, fecha_baja, fecha_rechazo) VALUES
('19870996', 'En gestion', '2023-01-15', '2023-02-01', NULL, NULL),
('13567880', 'Baja', '2022-08-10', '2022-09-01', '2023-01-01', '2023-01-10');

-- Insertar datos en PROGRAMA
INSERT INTO Donaciones.Programa (nombre_programa, descripcion) VALUES
('Programa Salud', 'Aporte para programas de salud infantil'),
('Programa Educación', 'Aporte para becas escolares');

-- Insertar datos en MEDIO DE PAGO
INSERT INTO Donaciones.Medio_de_Pago (nom_titular) VALUES
('Juan Pérez'),
('María González');

-- Insertar datos en APORTE
INSERT INTO Donaciones.Aporte (dni, nombre_programa, monto, frecuencia, nro_pago) VALUES
('19870996', 'Programa Salud', 1000.00, 'MENSUAL', 1),
('13567880', 'Programa Educación', 1500.00, 'SEMANAL', 2);

-- Insertar datos en TARJETA
INSERT INTO Donaciones.Tarjeta (nro_pago, nro_tarjeta, fecha_Venc, nom_tarjeta) VALUES
(1, '4111111111111111', '2026-12-31', 'Visa'),
(2, '5500000000000004', '2025-06-30', 'MasterCard');

-- Insertar datos en BANCO
INSERT INTO Donaciones.Banco (sucursal_banco, nom_banco) VALUES
('001', 'Banco Nación'),
('002', 'Banco Provincia');

-- Insertar datos en DEBITO/TRANS
INSERT INTO Donaciones.Debito_Trans (nro_pago, nro_cuenta, cbu, tipo_cuenta, sucursal_banco) VALUES
(1, '1234567890', '0720000123456789012345', 'Caja de Ahorro', '001'),
(2, '0987654321', '0140000098765432109876', 'Cuenta Corriente', '002');