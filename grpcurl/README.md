# Uso de gRPCurl

Es una aplicación de línea de comando para realizar consultas
a los servicios gRPC.

## Instalación:

Ver el proyecto en https://github.com/fullstorydev/grpcurl para más información.

En Ubuntu:

Instalando Go:
```
sudo apt install golang-go
```
Instalando grpcurl:

```
go install github.com/fullstorydev/grpcurl/cmd/grpcurl@latest
```
## Ejecutando:

### Listando los servicios

```
~/go/bin/grpcurl -plaintext localhost:50051 list
```

Salida:

![listado_servicios.png](img%2Flistado_servicios.png)

### Verificando los servicios disponibles

```
~/go/bin/grpcurl -plaintext localhost:50051 describe estudiantern.EstudianteRn
```

![descripcion.png](img%2Fdescripcion.png)

### Tipo de datos de entrada o salida

```
~/go/bin/grpcurl -plaintext localhost:50051 describe .estudiantern.EstudianteResponse
```
![dto.png](img%2Fdto.png)

### Crear Estudiante

```
~/go/bin/grpcurl -plaintext -d '{"matricula": 1, "nombre": "Estudiante 1", "carrera": "ICC"}' localhost:50051 estudiantern.EstudianteRn.crearEstudiante
```
![crear_estudiante.png](img%2Fcrear_estudiante.png)

### Listar Estudiantes

```
~/go/bin/grpcurl -plaintext localhost:50051 estudiantern.EstudianteRn.listaEstudiante
```
![listado_estudiantes.png](img%2Flistado_estudiantes.png)



