# Api de Usuarios

Aplicación que permite registrar usuarios, editarlos y eliminarlos.

### Tecnologias implementadas:
- Spring Framework 3.
- H2 Database.
- Java 17.
- Spring Security (JWT).

## Documentación

Para ver los diagramas [haz click aqui](https://drive.google.com/drive/folders/1VgjkeIwZupgsehhBHyFuk-TEteReP0DR?usp=sharing)

## Guía de uso

Una vez clonado el repositorio se debe iniciar la app dando lugar a que se inserten los datos del usuario maestro, las credenciales del usuario son:

- User: admin
- Pass: 123

Si se necesita ver las tablas de la base de datos [hazme click](http://localhost:8080/h2-console) , las credenciales son las siguientes:

- JDBC URL: jdbc:h2:mem:usersdb
- User Name: admin
- Pass: sa

Para iniciar sesión se debe hacer una petición **POST** a ``` http://localhost:8080/login ``` , junto a un JSON como el siguiente: 

```
{
    "email": "admin@correo.cl",
    "password": "123"
}
```

Esto retornará un token JWT necesario para acceder a los siguientes endpoints:

## Listado de Usuarios

- Para registrar usuarios, tipo petición **GET** : ``` http://localhost:8080/users ```, se devuelve un listado de usuarios en formato JSON.

Para acceder a los siguientes endpoints es necesario enviar el token con el header **Authorization** y el valor **Bearer tokenRecibido**

(Ejemplo)[https://drive.google.com/file/d/1an9RsXiYvs0GBFzHZpDMEngxt-nCgJrE/view?usp=drive_link]

## Registro de Usuarios

- Para registrar usuarios, tipo petición **POST** : ``` http://localhost:8080/users ```

Ejemplo dato entrada:

```
{
    "name": "Usuario 1",
    "email": "user1@correo.cl",
    "phoneList": [
        {
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "57"
        },
        {
            "number": "1234567",
            "cityCode": "2",
            "countryCode": "57"
        }
    ],
    "password": "123"
}
```

## Editar Usuarios

- Para editar usuarios, tipo petición **PUT** , se requiere enviar por url el id del usuario a editar: ``` http://localhost:8080/users/id ```

Ejemplo dato entrada:

```
{
    "name": "Usuario 1",
    "email": "user1@correo.cl",
    "phoneList": [
        {
            "number": "123467",
            "cityCode": "1",
            "countryCode": "57"
        },
        {
            "number": "1234567",
            "cityCode": "2",
            "countryCode": "57"
        },
        {
            "number": "1234567",
            "cityCode": "3",
            "countryCode": "57"
        }
    ]
}
```

## Eliminar Usuarios
- Para eliminar usuarios, tipo petición **DELETE**, se requirere enviar por url el id del usuario a eliminar: ``` http://localhost:8080/users/id ``` , este solo devuelve un status 200.
