--crea el esquema
CREATE SCHEMA Donaciones;

--Usa el esquema recien creado
SET search_path TO Donaciones;

CREATE TABLE Padrino(
	Dni INTEGER NOT NULL PRIMARY KEY,
	nombre varchar(50) NOT NULL,
	apellido varchar(50),
	email varchar(60),
	fecha_nac DATE,
	facebook varchar(70),
	cod_postal INTEGER,
	direccion varchar(50)
);

CREATE TABLE Telefono(
	telefono varchar(50),
	Dni INTEGER NOT NULL,
	CONSTRAINT pktelefono PRIMARY KEY (telefono, Dni),
	CONSTRAINT fkDni FOREIGN KEY (Dni) REFERENCES Padrino ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Donante(
	Dni INTEGER NOT NULL PRIMARY KEY,
	cuil varchar(50),
	ocupacion varchar(100),
	CONSTRAINT fkDni FOREIGN KEY (Dni) REFERENCES Padrino ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Contacto(
	Dni INTEGER NOT NULL PRIMARY KEY,
	estado varchar(100) CHECK (estado IN ('Sin llamar','ERROR', 'En gestion', 'Adherido', 'Amigo', 'No acepta', 'Baja','Voluntario')),
	fecha_primer_contacto DATE,
	fecha_alta	DATE,
	fecha_baja	DATE,
	fecha_rechazo DATE,
	CONSTRAINT fkDni FOREIGN KEY (Dni) REFERENCES Padrino ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Programa(
	nombre_programa varchar(100) NOT NULL PRIMARY KEY,
	descripcion TEXT
);

CREATE TABLE Medio_de_Pago(
	nro_pago SERIAL PRIMARY KEY,
	nom_titular varchar(50)
);

CREATE TYPE Tfrecuencia AS ENUM(
'SEMANAL', 'MENSUAL'
);

CREATE TABLE Aporte(
	Dni INTEGER NOT NULL,
	nombre_programa varchar(100) NOT NULL,
	monto Decimal (10,2) NOT NULL,
	frecuencia Tfrecuencia,
	nro_pago INTEGER,
	CONSTRAINT pkAporte PRIMARY KEY (Dni, nombre_programa),
	CONSTRAINT fkDni FOREIGN KEY (Dni) REFERENCES Donante ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fknombre_programa FOREIGN KEY (nombre_programa) REFERENCES Programa ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT fknro_pago FOREIGN KEY (nro_pago) REFERENCES Medio_de_Pago ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE Tarjeta(
		nro_pago SERIAL PRIMARY KEY,
		nro_tarjeta varchar(50),
		fecha_Venc DATE,
		nom_tarjeta varchar(50),
		CONSTRAINT fknro_pago FOREIGN KEY (nro_pago) REFERENCES Medio_de_Pago ON DELETE CASCADE ON UPDATE CASCADE
		
);

CREATE TABLE Banco(
	sucursal_banco varchar(50) NOT NULL PRIMARY KEY,
	nom_banco varchar(50)

);


CREATE TABLE Debito_Trans(
	nro_pago SERIAL PRIMARY KEY,
	nro_cuenta SERIAL,
	cbu varchar(50) NOT NULL,
	tipo_cuenta varchar (50),
	sucursal_banco varchar(50),
	CONSTRAINT fknro_pago FOREIGN KEY (nro_pago) REFERENCES Medio_de_Pago ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fKsucursal_banco FOREIGN KEY (sucursal_banco) REFERENCES Banco ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Auditoria_Eliminacion(
	Id_auditoria SERIAL PRIMARY KEY,
	Dni INTEGER, 
	fecha_eliminacion DATE,
	usuario varchar(50)
	
);

CREATE OR REPLACE FUNCTION registrar_eliminacion()
RETURNS TRIGGER AS $$
BEGIN
	INSERT INTO Auditoria_Eliminacion (Dni, fecha_eliminacion, usuario)
	VALUES (OLD.Dni, CURRENT_DATE, 'sistema');
	RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_eliminar_padrino
BEFORE DELETE ON Padrino
FOR EACH ROW
EXECUTE FUNCTION registrar_eliminacion();

