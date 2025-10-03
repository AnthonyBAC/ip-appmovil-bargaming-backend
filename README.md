# Documentación Backend – API REST

# Tecnologías

---

- Java 17+
- Spring Boot (Maven)
- JPA/Hibernate con Oracle
- REST API con JSON
- Spring Security (JWT) para login/roles

---

## Módulos de la API

### 1. Auth & Usuarios (CLIENTES)

#### Maneja registro, login, y roles (CLIENTE, VENDEDOR, ADMIN).

#### Endpoints:

- POST /api/auth/register → registrar usuario
- POST /api/auth/login → login con JWT
- GET /api/usuarios/{id} → obtener perfil
- PUT /api/usuarios/{id} → actualizar perfil
- DELETE /api/usuarios/{id} → eliminar cuenta

---

2. Productos (PRODUCTOS + IMAGENES_PRODUCTO)

CRUD de productos y gestión de imágenes.

Endpoints:

POST /api/productos → publicar producto (rol: VENDEDOR)

GET /api/productos → listar productos (con filtros: categoría, marca, búsqueda)

GET /api/productos/{id} → ver detalle de un producto

PUT /api/productos/{id} → editar producto (rol: VENDEDOR propietario)

DELETE /api/productos/{id} → eliminar producto

POST /api/productos/{id}/imagenes → subir imagen al producto

GET /api/productos/{id}/imagenes → listar imágenes

3. Ofertas (OFERTAS)

Gestión de ofertas entre compradores y vendedores.

Endpoints:

POST /api/ofertas → crear oferta (cliente sobre producto con recibe_ofertas = true)

GET /api/ofertas/vendedor/{idVendedor} → listar ofertas recibidas

PUT /api/ofertas/{id}/aceptar → aceptar oferta (rol: VENDEDOR)

PUT /api/ofertas/{id}/rechazar → rechazar oferta

GET /api/ofertas/{id} → ver detalle de oferta

4. Carrito (CARRITO)

Carrito temporal de un cliente.

Endpoints:

POST /api/carrito → agregar producto al carrito

GET /api/carrito/{idCliente} → ver carrito

DELETE /api/carrito/{idCliente}/producto/{idProducto} → quitar producto del carrito

DELETE /api/carrito/{idCliente}/vaciar → vaciar carrito

5. Ventas (VENTAS + DETALLE_VENTAS)

Manejo de compras directas y ventas por oferta.

Endpoints:

POST /api/ventas → registrar venta (desde carrito o desde oferta aceptada)

GET /api/ventas/cliente/{idCliente} → listar compras de un cliente

GET /api/ventas/vendedor/{idVendedor} → listar ventas de un vendedor

GET /api/ventas/{id} → ver detalle de venta

PUT /api/ventas/{id}/estado → actualizar estado de venta (PAGADA, ENVIADA, ENTREGADA, CANCELADA)

6. Administrador (rol = ADMIN)

Gestión y control del sistema.

Endpoints:

GET /api/admin/usuarios → listar usuarios

DELETE /api/admin/usuarios/{id} → eliminar usuario

GET /api/admin/ventas → estadísticas de ventas

GET /api/admin/productos → listar productos activos/inactivos

GET /api/admin/reportes → generar informes

🔄 Flujo de negocio principal

Publicación: vendedor publica producto (POST /productos).

Compra directa: cliente agrega al carrito y paga (POST /ventas).

Compra por oferta: cliente hace oferta (POST /ofertas) → vendedor acepta (PUT /ofertas/{id}/aceptar) → se genera la venta pendiente.

Pago confirmado: se actualiza VENTAS.estado_venta = PAGADA.

Entrega confirmada: VENTAS.estado_venta = ENTREGADA.
