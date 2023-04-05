/*Las líneas 2 y 3 están en comentarios, para usarse así la primera vez... luego de eso se quitan los comentarios para que todo funcione bien, en las siguientes ejecuciones */
/* drop schema etrial ;
drop user usuario_proyecto;*/

/*Se crea la base de datos */
CREATE SCHEMA etrial ;
/*Se crea un usuario para la base de datos llamado "usuario_proyecto" y tiene la contraseña "Clave123"*/
create user 'usuario_proyecto'@'%' identified by 'Clave123';
/*Se asignan los prvilegios sobr ela base de datos etrial al usuario creado */
grant all privileges on etrial.* to 'usuario_proyecto'@'%';
flush privileges;

create table etrial.credito (
  id_credito INT NOT NULL AUTO_INCREMENT,
  limite double,
  PRIMARY KEY (id_credito)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_eo_0900_ai_ci;

/*Se crea la tabla de clientes llamada cliente... igual que la clase Cliente */
CREATE TABLE etrial.cliente (
  id_cliente INT NOT NULL AUTO_INCREMENT,
  id_credito INT NOT NULL,
  nombre VARCHAR(20) NOT NULL,
  apellidos VARCHAR(30) NOT NULL,
  correo VARCHAR(60) NULL,
  telefono VARCHAR(15) NULL,
  PRIMARY KEY (`id_cliente`),
  foreign key fk_cliente_credito (id_credito) references credito(id_credito)  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_eo_0900_ai_ci;

create table etrial.evento (
  id_evento INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,  
  activo bool,
  PRIMARY KEY (id_evento))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_eo_0900_ai_ci;

create table etrial.entrada (
  id_entrada INT NOT NULL AUTO_INCREMENT,
  id_evento INT NOT NULL,
  descripcion VARCHAR(30) NOT NULL,
  id_cliente INT NOT NULL,
  precio double,
  existencias int,
  activo bool,
  PRIMARY KEY (id_entrada),
  foreign key fk_entrada_caregoria (id_evento) references evento(id_evento), 
  foreign key fk_factura_cliente (id_cliente) references cliente(id_cliente)  
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_eo_0900_ai_ci;

create table etrial.factura (
  id_factura INT NOT NULL AUTO_INCREMENT,
  id_cliente INT NOT NULL,
  fecha date,  
  total double,
  estado int,
  PRIMARY KEY (id_factura),
  foreign key fk_factura_cliente (id_cliente) references cliente(id_cliente)  
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_eo_0900_ai_ci;

create table etrial.venta (
  id_venta INT NOT NULL AUTO_INCREMENT,
  id_factura INT NOT NULL,
  id_entrada INT NOT NULL,
  precio double, 
  cantidad int,
  PRIMARY KEY (id_venta),
  foreign key fk_ventas_factura (id_factura) references factura(id_factura),
  foreign key fk_ventas_entrada (id_entrada) references entrada(id_entrada) 
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_eo_0900_ai_ci;

INSERT INTO etrial.credito (id_credito,limite) values
(1,500000),
(2,1000000),
(3,1500000);

/*Se insertan registros en la tabla cliente*/
INSERT INTO etrial.cliente (id_cliente, id_credito, nombre, apellidos, correo, telefono) VALUES 
(1, 1,'Sofía', 'Mora Muñoz', 'smora00640@ufide.ac.cr', '8456-8267'),
(2, 2,'Daniel', 'Roda Solano', 'danielroda2406@gmail.com', '8415-9808'),
(3, 3,'Aaron', 'Salamanca Carmona', 'aaronsalamanca100@gmail.com', '8801-7197');

/*Se insertan eventos de entradas */
INSERT INTO etrial.evento (id_evento,descripcion,activo) VALUES 
('1','Concierto 1',true), 
('2','Concierto 2',true),
('3','Concierto 3',true),
('4','Concierto 4',false);

/*Se insertan entradas por evento */
INSERT INTO etrial.entrada (id_entrada,id_evento,descripcion,id_cliente,precio,existencias,activo) VALUES
(1,1,'Platea','1',23000,5,true),
(2,1,'Golden','1',27000,2,true),
(3,1,'VIP','1',24000,5,true),
(4,1,'Gradería Sur','2',27600,2,true),
(5,2,'Gradería Este','2',45000,5,true),
(6,2,'Palco','3',57000,2,true),
(7,2,'Palco','3',25000,5,true),
(8,2,'Sombra','3',27600,2,true);

/*Se crean 6 facturas */   /*'Activa','Pagada','Anulada')*/
INSERT INTO etrial.factura (id_factura,id_cliente,fecha,total,estado) VALUES
(1,1,'2022-01-05',211560,2),
(2,2,'2022-01-07',554340,2),
(3,3,'2022-01-07',871000,2),
(4,1,'2022-01-15',244140,1),
(5,2,'2022-01-17',414800,1),
(6,3,'2022-01-21',420000,1);

INSERT INTO etrial.venta (id_venta,id_factura,id_entrada,precio,cantidad) values
(1,1,5,45000,3),
(2,2,3,15780,2),
(3,3,8,15000,3),
(4,1,5,45000,1),
(5,2,6,154000,3),
(6,3,2,15780,3);