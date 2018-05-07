# Management restaurant orders
System to manage the orders of a restoration business developed in Java, the database used in the project is an Oracle Database 11g Express Edition, for the graphical part the Swing graphic library is used and the project is structured in a MVC pattern
* [How to start](https://github.com/sergio-santiago/management-restaurant-orders#how-to-start)
* [Some information about the project data model](https://github.com/sergio-santiago/management-restaurant-orders/blob/master/README.md#some-information-about-the-project-data-model)
* [Some views](https://github.com/sergio-santiago/management-restaurant-orders/blob/master/README.md#some-views)
## How to start
1. Clone the project from the repository: `$git clone https://github.com/sergio-santiago/management-restaurant-orders.git`
2. The project uses an Oracle Database 11g Express Edition database, you can download it from [here](http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html), once downloaded install it and create an empty database
3. You must create a series of tables in the database, you can find the [creation script here](https://github.com/sergio-santiago/management-restaurant-orders/blob/master/README.md#table-creation-script), you can also make an insert of some test data to fill your tables with  [the insertion script](https://github.com/sergio-santiago/management-restaurant-orders/blob/master/README.md#sample-data-insertion-script)
4. Open the project with your IDE and make sure to add the ``management-restaurant-orders/lib/ojdbc6.jar`` file to the build path, it must be established as a referenced library
5. To configure the connection of the database in the project you must edit the file ``management-restaurant-orders/src/model/database_config.properties`` with the values of your database
```properties
	dbhost=localhost
	dbuser=SYSTEM
	dbpassword=root
	dbname=default_database
```
6. To access administration, the default password is ``1234``, you can change it in the application
7. Finally, go to the class ``management-restaurant-orders/src/controller/StartController.java`` and run the main
## Some information about the project data model
* [Entity–relationship model](https://github.com/sergio-santiago/management-restaurant-orders/blob/master/README.md#entityrelationship-model)
* [Relational model of tables](https://github.com/sergio-santiago/management-restaurant-orders/blob/master/README.md#relational-model-of-tables)
* [Table creation script](https://github.com/sergio-santiago/management-restaurant-orders/blob/master/README.md#table-creation-script)
* [Sample data insertion script](https://github.com/sergio-santiago/management-restaurant-orders/blob/master/README.md#sample-data-insertion-script)
### Entity–relationship model
![model entity relationship](https://image.ibb.co/kaftbx/Modelo_Entidad_Relacion.jpg)
### Relational model of tables
* CATEGORIA(***id***, nombre)
* PRODUCTO(***id***, nombre, precio, *id_categoria*)
* MESA(***id***, nombre)
* PEDIDO(***id***, precio, en_curso, fecha, *id_mesa*)
* COMANDA(***id***, *id_pedido*)
* COMANDA_PRODUCTO(***id_comanda***, ***id_producto***, cantidad)
### Table creation script
```sql
drop table categoria cascade constraints;
create table categoria(
id number primary key,
nombre varchar2(255) not null unique
);

drop table producto cascade constraints;
create table producto(
id number primary key,
nombre varchar2(255) not null unique,
precio number not null,
id_categoria number,
foreign key(id_categoria) references categoria(id) on delete cascade
);

drop table mesa cascade constraints;
create table mesa(
id number primary key,
nombre varchar2(255) not null unique
);

drop table pedido cascade constraints;
create table pedido(
id number primary key,
precio number not null,
en_curso varchar2(5) not null,
fecha date not null,
id_mesa number,
check(en_curso in('true', 'false')),
foreign key(id_mesa) references mesa(id) on delete cascade
);

drop table comanda cascade constraints;
create table comanda(
id number primary key,
id_pedido number not null,
foreign key(id_pedido) references pedido(id) on delete cascade
);

drop table comanda_producto cascade constraints;
create table comanda_producto(
id_comanda number not null,
id_producto number not null,
cantidad number not null,
primary key(id_comanda, id_producto),
foreign key(id_comanda) references comanda(id) on delete cascade,
foreign key(id_producto) references producto(id) on delete cascade
);
```
### Sample data insertion script
```sql
REM ***********************************
REM ************ CATEGORIA ************
REM ***********************************
insert into categoria values (1, 'Refrescos');
insert into categoria values (2, 'Cervezas');
insert into categoria values (3, 'Batidos');
insert into categoria values (4, 'Bocatas');
insert into categoria values (5, 'Hamburguesas');

REM ***********************************
REM ************ PRODUCTO *************
REM ***********************************
insert into producto values (1, 'CocaCola', 1.5, 1);
insert into producto values (2, 'CocaCola Zero', 1.5, 1);
insert into producto values (3, 'Fanta Naranja', 1.5, 1);
insert into producto values (4, 'Fanta Limon', 1.5, 1);
insert into producto values (5, 'Red Bull', 2.5, 1);
insert into producto values (6, 'Mahou Clásica', 1.25, 2);
insert into producto values (7, 'Mahou Cinco Estrellas', 1.25, 2);
insert into producto values (8, 'Mahou Sin', 1.75, 2);
insert into producto values (9, 'Mahou Negra', 2, 2);
insert into producto values (10, 'Batido Fresa', 2, 3);
insert into producto values (11, 'Batido Chocolate', 2, 3);
insert into producto values (12, 'Batido Vainilla', 2, 3);
insert into producto values (13, 'Bocata Jamon Serrano', 5, 4);
insert into producto values (14, 'Bocata Lomo', 3.5, 4);
insert into producto values (15, 'Bocata Panceta', 3.5, 4);
insert into producto values (16, 'Bocata Tortilla', 4, 4);
insert into producto values (17, 'Hamburguesa de la Casa', 5.5, 5);
insert into producto values (18, 'Hamburguesa Especial', 7, 5);

REM ***********************************
REM ************ MESA *****************
REM ***********************************
insert into mesa values (1, 'Mesa 1');
insert into mesa values (2, 'Mesa 2');
insert into mesa values (3, 'Mesa 3');
insert into mesa values (4, 'Mesa 4');
insert into mesa values (5, 'Mesa 5');
insert into mesa values (6, 'Mesa 6');
insert into mesa values (7, 'Mesa 7');
insert into mesa values (8, 'Mesa 8');

REM ***********************************
REM ************ PEIDIDO **************
REM ***********************************
insert into pedido values (1, 20, 'false', '03-30-2018', 1);
insert into pedido values (2, 6.5, 'false', '03-31-2018', 2);
insert into pedido values (3, 9.5, 'true', '04-02-2018', 5);

REM ***********************************
REM ************ COMANDA **************
REM ***********************************
insert into comanda values (1, 1);
insert into comanda values (2, 1);
insert into comanda values (3, 2);
insert into comanda values (4, 3);
insert into comanda values (5, 3);

REM ******************************************
REM ************ COMANDA_PRODUCTO ************
REM ******************************************
insert into comanda_producto values (1, 11, 5);
insert into comanda_producto values (2, 10, 2);
insert into comanda_producto values (2, 12, 2);
insert into comanda_producto values (2, 11, 1);
insert into comanda_producto values (3, 1, 1);
insert into comanda_producto values (3, 13, 1);
insert into comanda_producto values (4, 18, 1);
insert into comanda_producto values (5, 5, 1);
```
## Some views
![add_productos](https://image.ibb.co/fX0j9S/add_productos.png)
![finalizar](https://image.ibb.co/dE4KUS/2018_05_06_19_38_02.png)
![gestion](https://image.ibb.co/bCyt3n/gestion.png)
![historial](https://image.ibb.co/ieKcUS/historial.png)
![mesas](https://image.ibb.co/de9Nw7/mesas.png)
