# Management restaurant orders
System to manage the orders of a restoration business developed in Java, final project of 1ºDAW.
* The database used in the project is an [Oracle Database 11g Express Edition](http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html)
* For the graphical part the Swing graphic library is used
* The project is structured in a MVC pattern
## Some information about the project data model
* [Entity–relationship model](https://github.com/sergio-santiago/management-restaurant-orders/blob/master/README.md#entityrelationship-model)
* [Relational model of tables](https://github.com/sergio-santiago/management-restaurant-orders/blob/master/README.md#relational-model-of-tables)
### Entity–relationship model
![model entity relationship](https://image.ibb.co/kaftbx/Modelo_Entidad_Relacion.jpg)
### Relational model of tables
* CATEGORIA(***id***, nombre)
* PRODUCTO(***id***, nombre, precio, *id_categoria*)
* MESA(***id***, nombre)
* PEDIDO(***id***, precio, en_curso, fecha, *id_mesa*)
* COMANDA(***id***, *id_pedido*)
* COMANDA_PRODUCTO(***id_comanda***, ***id_producto***, cantidad)
