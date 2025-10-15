# **Documentación Backend – Proyecto Bargaming**

---

## Tecnologías principales

- **Java 17+**
- **Spring Boot** (Maven)
- **JPA / Hibernate** con **Oracle Database**
- **REST API** con formato **JSON**
- **Spring Security (JWT)** para autenticación y roles
- Arquitectura modular (`modules/` + `common/`)

---

## Estructura general

---

## 1. Autenticación y Usuarios (`auth`)

Módulo responsable del registro, login y control de roles (**CLIENTE**, **VENDEDOR**, **ADMIN**).

### Endpoints principales

| Método | Ruta                 | Descripción                                        | Rol      |
| ------ | -------------------- | -------------------------------------------------- | -------- |
| POST   | `/api/auth/register` | Registrar nuevo usuario (rol por defecto: CLIENTE) | Público  |
| POST   | `/api/auth/login`    | Iniciar sesión y obtener JWT                       | Público  |
| GET    | `/api/client/demo`   | Prueba de acceso CLIENTE                           | CLIENTE  |
| GET    | `/api/seller/demo`   | Prueba de acceso VENDEDOR                          | VENDEDOR |
| GET    | `/api/admin/demo`    | Prueba de acceso ADMIN                             | ADMIN    |
| GET    | `/api/admin/users`   | Listar todos los usuarios                          | ADMIN    |

> Los usuarios **ADMIN** se crean manualmente o desde `DataInitializer`.
> Todos los nuevos registros obtienen **rol CLIENTE por defecto**.

---

## 2. Productos (`products`)

Módulo para publicación y administración de productos.

### Endpoints

| Método | Ruta                           | Descripción                                                 | Rol      |
| ------ | ------------------------------ | ----------------------------------------------------------- | -------- |
| POST   | `/api/productos`               | Crear producto                                              | VENDEDOR |
| GET    | `/api/productos`               | Listar productos (con filtros por categoría, marca o texto) | Público  |
| GET    | `/api/productos/{id}`          | Ver detalle de producto                                     | Público  |
| PUT    | `/api/productos/{id}`          | Editar producto (solo propietario)                          | VENDEDOR |
| DELETE | `/api/productos/{id}`          | Eliminar producto                                           | VENDEDOR |
| POST   | `/api/productos/{id}/imagenes` | Subir imágenes del producto                                 | VENDEDOR |
| GET    | `/api/productos/{id}/imagenes` | Listar imágenes asociadas                                   | Público  |

---

## 3. Ofertas (`offers`)

Permite a los clientes enviar ofertas por productos que las acepten.

| Método | Ruta                                 | Descripción                                  | Rol              |
| ------ | ------------------------------------ | -------------------------------------------- | ---------------- |
| POST   | `/api/ofertas`                       | Crear oferta (si el producto acepta ofertas) | CLIENTE          |
| GET    | `/api/ofertas/vendedor/{idVendedor}` | Listar ofertas recibidas                     | VENDEDOR         |
| PUT    | `/api/ofertas/{id}/aceptar`          | Aceptar oferta                               | VENDEDOR         |
| PUT    | `/api/ofertas/{id}/rechazar`         | Rechazar oferta                              | VENDEDOR         |
| GET    | `/api/ofertas/{id}`                  | Ver detalle de una oferta                    | CLIENTE/VENDEDOR |

---

## 4. Carrito (`cart`)

Módulo para manejar el carrito temporal de un cliente.

| Método | Ruta                                             | Descripción                 | Rol     |
| ------ | ------------------------------------------------ | --------------------------- | ------- |
| POST   | `/api/carrito`                                   | Agregar producto al carrito | CLIENTE |
| GET    | `/api/carrito/{idCliente}`                       | Ver contenido del carrito   | CLIENTE |
| DELETE | `/api/carrito/{idCliente}/producto/{idProducto}` | Quitar producto             | CLIENTE |
| DELETE | `/api/carrito/{idCliente}/vaciar`                | Vaciar carrito              | CLIENTE |

---

## 5. Ventas (`sales`)

Maneja las compras directas y las generadas a partir de ofertas aceptadas.

| Método | Ruta                                | Descripción                                               | Rol              |
| ------ | ----------------------------------- | --------------------------------------------------------- | ---------------- |
| POST   | `/api/ventas`                       | Registrar nueva venta                                     | CLIENTE          |
| GET    | `/api/ventas/cliente/{idCliente}`   | Listar compras del cliente                                | CLIENTE          |
| GET    | `/api/ventas/vendedor/{idVendedor}` | Listar ventas del vendedor                                | VENDEDOR         |
| GET    | `/api/ventas/{id}`                  | Ver detalle de venta                                      | CLIENTE/VENDEDOR |
| PUT    | `/api/ventas/{id}/estado`           | Actualizar estado (PAGADA, ENVIADA, ENTREGADA, CANCELADA) | VENDEDOR/ADMIN   |

---

## 6. Administración (`admin`)

Gestión global del sistema y estadísticas.

| Método | Ruta                    | Descripción                        |
| ------ | ----------------------- | ---------------------------------- |
| GET    | `/api/admin/users`      | Listar usuarios                    |
| DELETE | `/api/admin/users/{id}` | Eliminar usuario                   |
| GET    | `/api/admin/ventas`     | Estadísticas de ventas             |
| GET    | `/api/admin/productos`  | Listar productos activos/inactivos |
| GET    | `/api/admin/reportes`   | Generar reportes del sistema       |

---

## Flujo principal de negocio

1. **Publicación:**
   Vendedor publica un producto (`POST /api/productos`).
2. **Compra directa:**
   Cliente lo agrega al carrito y genera la venta (`POST /api/ventas`).
3. **Compra por oferta:**
   Cliente crea oferta → vendedor acepta → se genera una venta pendiente.
4. **Pago confirmado:**
   `VENTAS.estado_venta = PAGADA`
5. **Entrega confirmada:**
   `VENTAS.estado_venta = ENTREGADA`

---

## Roles y permisos

| Rol          | Acceso                                                       |
| ------------ | ------------------------------------------------------------ |
| **CLIENTE**  | Navegar, ofertar, comprar, usar carrito                      |
| **VENDEDOR** | Publicar y administrar productos, gestionar ofertas y ventas |
| **ADMIN**    | Control total: usuarios, productos y reportes                |

---

## Diagrama ERD

![ERD](./diagrams/ERD.png)

---

## Estado actual (Octubre 2025)

OK / Módulo `auth` completo y funcional
OK / Roles y seguridad por JWT configurados
OK / Integración con Oracle DB
OK / Estructura modular lista para expansión
NOTOK / En desarrollo: módulos `productos`, `ofertas`, `carrito`, `ventas`

---
