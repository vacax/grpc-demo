# Cliente gRPC basado en Python3

Para configurar es necesario instalar los siguientes paquetes:

` pip3 install grpcio grpcio-tools --upgrade protobuf `

Para generar el código desde el archivo .proto:

`
python3 -m grpc_tools.protoc -I. --python_out=. --grpc_python_out=. EstudianteRn.proto
`

Para ejecutar:

`
python3 cliente.py
`