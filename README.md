# Apklis4Devs API
Accede a la plataforma Apklis-Admin de forma programática.
Esta API es actualmente desarrollada por Javier Alejadro González Casellas.

> *Proyecto original*: https://github.com/karelantonio/Apklis-Admin-Api.

***

### ¿Cómo funciona esta API?
La API se comunica con el servidor mediante un servicio web del tipo JSON Rest, en la cual se transfieren datos de una forma limpia, entendible para la computadora y las personas (para depuración)
### ¿Para qué es útil?
A través de esta API podrás acceder a los datos de tu cuenta de desarrollador de Apklis de una manera fácil y rápida
### Funcionalidades
- Acceso a todos los datos de las aplicaciones desarrolladas por el usuario
- Acceso a todos los datos del usuario desarrollador
- Acceso a todos los datos del usuario como vendedor
- Acceso a todos datos globales de la plataforma
- Acceso a notificaciones del usuario Apklis
- Acceso a registro de ventas
## Dependencias
```
gson >= 2.8.6
json >= 20180813
okhttp >= 4.9.0
okio-jvm >= 2.8.0
```
> *NOTA*: Debes incluir a Kotlin en el proyecto, pues es requerido por la dependencia *okhttp*

### Uso
Puedes encontrar ejemplos prácticos en el fichero [Examples.java](cu/jalexcode/apklis4devs/api/Examples.java)
```java
// Todos los datos estan contenidos en la clase Session
// Primeramente iniciamos sesion y obtenemos sus cookies
Session s = new Session(user, pass);
// Luego recolectamos toda la información relacionada con nuestra cuenta
s.updateCache()
```
Los métodos que puedes utilizar son los siguientes:
```java
// Obtener lista de aplicaciones
ArrayList<Application> apps = s.getOwnApps(true);
// Obtener información personal
UserInfo userInfo = s.getUserInfo();
// en proximos PUSH se agregaran mas ejemplos :-)
```

### ¿Este proyecto tiene licencia?
Si, esta bajo la *Creative Commons Zero v1.0 Universal*, puedes revisarla en: [LICENCIA](https://github.com/JalexCode/Apklis4Devs-API/blob/master/LICENSE). *Recuerda agregar la línea: "Utilizando el proyecto de código abierto Apklis4Devs-API, disponible en https://github.com/JalexCode/Apklis4Devs-API"* para que la comunidad de Devs pueda hacer uso de este proyecto, así como acceder a su mantenimiento.

### Etiquetas:
[JAVA] [CODIGO ABIRTO] [APKLIS] [APKLIS ADMIN] [API] [REST] [JSON REST]
