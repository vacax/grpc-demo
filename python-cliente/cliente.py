import grpc
import EstudianteRn_pb2
import EstudianteRn_pb2_grpc


def llamada_servicio():
    # Abriendo el canal para el servidor
    channel = grpc.insecure_channel('localhost:50051')
    # Obteniendo la referencia del servicio.
    stub = EstudianteRn_pb2_grpc.EstudianteRnStub(channel)

    respuesta = stub.getEstudiante(EstudianteRn_pb2.EstudianteRequest(matricula=20011136))
    print("Información del cliente: ", respuesta)
    

llamada_servicio()
