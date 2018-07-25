DROP TABLE DEMO.CLIENTE;

CREATE TABLE DEMO.CLIENTE (
  id bigint auto_increment,
  nombre varchar(50) not null,
  apellido varchar(50),
  email varchar(255) not null,
  telefono varchar(255)
  primary key (id));


INSERT INTO DEMO.CLIENTE (ID,NOMBRE,APELLIDO,EMAIL,TELEFONO) VALUES (1,'Geovanny','Mendoza','geovanny@mendoza.com','437689');
