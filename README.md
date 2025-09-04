# 📚 ClubLectura

**ClubLectura** es una aplicación web desarrollada en **Java EE / Jakarta EE** con **JSF** que permite la gestión de libros, ediciones, favoritos, críticas y usuarios en un club de lectura.  
La aplicación utiliza **JPA (`EntityManager`)** para la persistencia de datos y sigue un patrón de **controladores + DAO**, separando la lógica de negocio de la capa de datos.

---

## 🚀 Características principales

- Gestión de **libros**: creación, búsqueda por ISBN, borrado y listado.  
- Gestión de **ediciones**: creación, validación, listado total y por libro, borrado y obtención de detalles.  
- Gestión de **favoritos**: los usuarios pueden añadir y eliminar libros favoritos, y consultar sus favoritos.  
- Gestión de **críticas**: creación, borrado y listado por libro.  
- Gestión de **usuarios/clientes**: registro de nuevos clientes, borrado y listado.  

---

## ⚙️ Tecnologías utilizadas

- **Java EE / Jakarta EE**  
- **JSF (JavaServer Faces)**  
- **CDI / EJB / JPA**  
- **EntityManager** para persistencia con `@Transactional`  
- **PostgreSQL / MySQL** (u otra BD relacional compatible)  
- **Maven**  

---

## 📡 Controladores principales

**LibrosController**  
- Crear, buscar, listar y borrar libros.  

**EdicionController**  
- Gestiona ediciones: crear, validar ISBN, listar total y por ISBN, borrar, obtener título, URL y descripción.  

**FavoritosController**  
- Añadir, eliminar y consultar libros favoritos de un usuario.  

**CriticaController**  
- Crear, borrar y listar críticas por ISBN.  

**ClientesController**  
- Registrar y gestionar clientes usando `ClientesDAOJPA` con persistencia en JPA.  

---

## 💾 Persistencia de datos

- Se usa **JPA (`EntityManager`)** con unidad de persistencia `notesPU` definida en `persistence.xml`.  
- Los DAO están anotados con `@Transactional` para gestionar transacciones automáticamente.  
- Operaciones típicas en `ClientesDAOJPA`:  
  - `inserta(Cliente c)` → crea un cliente.  
  - `guarda(Cliente c)` → actualiza un cliente.  
  - `elimina(String user)` → elimina un cliente (solo si no es administrador).  
  - `obtenerTodos()` → devuelve todos los clientes registrados.  

---

## ▶️ Ejecución

Clonar el repositorio y ejecutar con Maven:  
```bash
git clone https://github.com/tuusuario/ClubLectura.git
cd ClubLectura
mvn clean install
mvn wildfly:run   # o mvn glassfish:run según el servidor
```

Finalmente accede a: http://localhost:8080/ClubLectura

## 🧪 Ejemplos de uso
- Crear un libro:
    ```
    POST /clublectura/libros
    Content-Type: application/json

    {
        "isbn": "978-1234567890",
        "titulo": "Cien Años de Soledad",
        "autor": "Gabriel García Márquez"
    }
    ```
    <br>

- Obtener detalles de un libro:
    ```
    GET /clublectura/libros/978-1234567890
    ```
    <br>

- Crear una edición:
    ```
    POST /clublectura/ediciones
    Content-Type: application/json

    {
        "isbn": "978-1234567890",
        "numero": 1,
        "descripcion": "Primera edición en tapa dura",
        "url": "http://ejemplo.com/edicion/1"
    }
    <br>

- Añadir un libro a favoritos:
    ```
    POST /clublectura/favoritos/usuario123/978-1234567890
    ```
    <br>

- Consultar favoritos de un usuario:
    ```
    GET /clublectura/favoritos/usuario123
    ```
    <br>

- Enviar una crítica:
    ```
    POST /clublectura/criticas/978-1234567890/usuario123
    Content-Type: application/json

    {
        "texto": "Una obra maestra de la literatura universal.",
        "puntuacion": 5
    }
    ```
    <br>

- Registrar un nuevo cliente:
    ```
    POST /clublectura/clientes
    Content-Type: application/json

    {
        "usuario": "usuario123",
        "nombre": "Juan Pérez",
        "pwd": "secreta",
        "rol": "USUARIOS"
    }
    ```
    <br>
