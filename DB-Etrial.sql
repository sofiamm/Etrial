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

CREATE TABLE `etrial`.`usuario` (
`id_usuario` INT NOT NULL AUTO_INCREMENT, 
`username` VARCHAR(50) NOT NULL, 
`password` VARCHAR(200) NOT NULL,
`id_cliente` INT NULL,
PRIMARY KEY (`id_usuario`) )
ENGINE = innoDB;

CREATE TABLE `etrial`.`rol` (
`id_rol` INT NOT NULL AUTO_INCREMENT, 
`nombre` VARCHAR(50) NOT NULL, 
id_usuario int,
PRIMARY KEY (`id_rol`),
foreign key fk_rol_usuario (id_usuario) references usuario(id_usuario))
ENGINE = innoDB;

insert into etrial.usuario (id_usuario, username, password) values (1,'ADMIN','123'),
(2,'VENDEDOR','456'),
(3,'USER','789');

insert into etrial.rol (id_rol, nombre, id_usuario) values
 (1,'ROLE_ADMIN',1),
 (2,'ROLE_VENDEDOR',1),
 (3,'ROLE_USER',1),
 (4,'ROLE_VENDEDOR',2),
 (5,'ROLE_USER',2),
 (6,'ROLE_USER',3);
 
 update etrial.usuario SET `password` = '$2a$10$FJhN/uYhjlBxi3qPt3ZA1Oyi10Jy1sjyPkiPzSh/Wz46W/uKXR.DS' where id_usuario = 1;
update etrial.usuario SET `password` = '$2a$10$gNZPJYEtHSCfZ.BJ4lUlr.ALIZbR6xhKmLxq5uYBWLfeHSVjMIaJG' where id_usuario = 2;
update etrial.usuario SET `password` = '$2a$10$7zESpn5m7vC5Xfll1P8YUulSxQhgxwlpwytAHUNM46ndJ.DbHZNwG' where id_usuario = 3;

CREATE TABLE `etrial`.`carrito` (`id_carrito` INT NOT NULL AUTO_INCREMENT, `id_cliente` INT NOT NULL, PRIMARY KEY (`id_carrito`),
foreign key fk_carrito_cliente (id_cliente) references cliente(id_cliente) )
ENGINE = innoDB;
 
CREATE TABLE `etrial`.`carrito_detalle` (`id_detalle` INT NOT NULL AUTO_INCREMENT, `id_carrito` INT NOT NULL, `id_evento` INT NOT NULL, 
`precio` DOUBLE NOT NULL, `cantidad` INT NOT NULL, PRIMARY KEY (`id_detalle`),
foreign key fk_detalle_carrito (id_carrito) references carrito(id_carrito),
foreign key fk_detalle_evento (id_evento) references evento(id_evento) )
ENGINE = innoDB;

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
  nombre VARCHAR(30) NOT NULL,
  lugar VARCHAR(30) NOT NULL,
  descripcion VARCHAR(200) NOT NULL,  
  activo bool,
  ruta_imagen varchar(300),
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

/*Se insertan eventos */
INSERT INTO etrial.evento (id_evento,nombre,lugar,descripcion,activo,ruta_imagen) VALUES 
('1','Malpais','San José','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.',true,'https://firebasestorage.googleapis.com/v0/b/fidelitas-b13ee.appspot.com/o/Malpais.jpg?alt=media&token=4c7d83eb-ebb5-4e6b-9e24-91a72299d0b7'), 
('2','Manuel Turizo','Alajuela','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.',true,'https://firebasestorage.googleapis.com/v0/b/fidelitas-b13ee.appspot.com/o/ManuelTurizo.jpg?alt=media&token=610b9361-9a4a-41a5-bcc5-2f6dca98315d'),
('3','Red Hot Chilli Peppers','San José','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.',true,'https://firebasestorage.googleapis.com/v0/b/fidelitas-b13ee.appspot.com/o/RedHotChiliPeppers.jpg?alt=media&token=5601831d-df13-4edf-b4fb-9bba8b566bd9'),
('4','RockFest','Cartago','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.',false,'https://firebasestorage.googleapis.com/v0/b/fidelitas-b13ee.appspot.com/o/RockFest.jpg?alt=media&token=8fac40e6-c339-4967-a2a4-31cd5c4402e8'),
('5','Rosario','San José','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.',true,'https://firebasestorage.googleapis.com/v0/b/fidelitas-b13ee.appspot.com/o/Rosario.jpg?alt=media&token=b8377e94-0e50-4f1f-abd4-730320a8d73f'), 
('6','Michael Jackson','Alajuela','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.',true,'https://firebasestorage.googleapis.com/v0/b/fidelitas-b13ee.appspot.com/o/MichaelJackson.jpg?alt=media&token=bcea6925-4e7f-4d04-9ad3-bab538b454b5'),
('7','La Cenicienta','San José','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.',true,'https://firebasestorage.googleapis.com/v0/b/fidelitas-b13ee.appspot.com/o/LaCenicienta.jpg?alt=media&token=8c52c809-e972-4765-a0b5-a31301d54791'),
('8','Comic con','Cartago','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.',false,'https://firebasestorage.googleapis.com/v0/b/fidelitas-b13ee.appspot.com/o/C2CR.jpg?alt=media&token=bed840d3-4a40-41f7-9c66-b8e47350d30d'),
('9','Ara Malikian','Cartago','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.',false,'https://firebasestorage.googleapis.com/v0/b/fidelitas-b13ee.appspot.com/o/AraMalikian.jpg?alt=media&token=8a6432cd-90b3-4131-b257-9c0000357423');

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