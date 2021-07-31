package org.jconfdominicana.jc;

import com.google.protobuf.StringValue;
import estudiantern.EstudianteRnGrpc;
import estudiantern.EstudianteRnOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        //
        String host = "localhost";
        int puerto = 50051;

        //Abriendo la conectividad
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext() //configuración para la conexión.
                .build();
        // Abriendo el servicio.
        EstudianteRnGrpc.EstudianteRnBlockingStub estudianteInterfaz = EstudianteRnGrpc.newBlockingStub(channel);

        //
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while (!salir) {

            System.out.println("Cliente gRPC en Java - Opciones de Operaciones");
            System.out.println("1. Consultar Estudiante");
            System.out.println("2. Listar Estudiantes");
            System.out.println("3. Cantidad de Estudiantes");
            System.out.println("4. Crear Estudiantes");
            System.out.println("5. Actualizar Estudiantes");
            System.out.println("6. Eliminar Estudiantes");
            System.out.println("7. Ejemplo Bidireccional");
            System.out.println("8. Salir");

            try {

                System.out.println("Escribe una de las opciones:");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Consultar de Estudiante por Matrícula\n");
                        System.out.print("Matrícula:");
                        int m = sn.nextInt();
                        //consultando el servicio.
                        EstudianteRnOuterClass.EstudianteResponse estudianteResponse = estudianteInterfaz
                                .getEstudiante(
                                        EstudianteRnOuterClass
                                                .EstudianteRequest
                                                .newBuilder()
                                                .setMatricula(m)
                                                .build()
                                );
                        //mostrando...
                        imprimirEstudiante(estudianteResponse);
                        break;
                    case 2:
                        System.out.println("Listar Estudiantes vía gRpc:");
                        EstudianteRnOuterClass.ListaEstudiante listaEstudiante = estudianteInterfaz.listaEstudiante(EstudianteRnOuterClass.Empty.newBuilder().build());
                        System.out.println("La cantidad de estudiantes: " + listaEstudiante.getEstudianteCount());
                        for (EstudianteRnOuterClass.EstudianteResponse e : listaEstudiante.getEstudianteList()) {
                            imprimirEstudiante(e);
                        }
                        break;
                    case 3:
                        System.out.println("La Cantidad de Estudiantes: " + estudianteInterfaz.listaEstudiante(EstudianteRnOuterClass.Empty.newBuilder().build()).getEstudianteCount());
                        break;
                    case 4:
                        System.out.println("Creación de Estudiante\n");
                        System.out.print("Matrícula:");
                        int matricula = sn.nextInt();
                        System.out.print("Nombre:");
                        String nombre = sn.next();
                        System.out.print("Carrera:");
                        String carrera = sn.next();
                        estudianteInterfaz.crearEstudiante(EstudianteRnOuterClass.EstudianteResponse.newBuilder()
                                .setMatricula(matricula)
                                .setNombre(nombre)
                                .setCarrera(carrera)
                                .build());
                        break;
                    case 5:
                        System.out.println("Actualizar Estudiante\n");
                        System.out.print("Matrícula:");
                        int matriculaA = sn.nextInt();
                        System.out.print("Nombre:");
                        String nombreA = sn.next();
                        System.out.print("Carrera:");
                        String carreraA = sn.next();
                        estudianteInterfaz.actualizarEstudiante(EstudianteRnOuterClass.EstudianteResponse.newBuilder()
                                .setMatricula(matriculaA)
                                .setNombre(nombreA)
                                .setCarrera(carreraA)
                                .build());
                        break;
                    case 6:
                        System.out.println("Eliminar un Estudiante por Matrícula\n");
                        System.out.print("Matrícula:");
                        int d = sn.nextInt();
                        //consultando el servicio.
                        EstudianteRnOuterClass.EstudianteBorrado estudianteBorrado = estudianteInterfaz.eliminarEstudiante(
                                EstudianteRnOuterClass
                                        .EstudianteRequest
                                        .newBuilder()
                                        .setMatricula(d)
                                        .build()
                        );
                        System.out.println("El estudiante fue borrado: "+estudianteBorrado.getOk());
                        break;
                    case 7:
                        procesoBiDireccional(channel);
                        break;
                    case 8:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 8");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }

    /**
     *
     * @param e
     */
    private static void imprimirEstudiante(EstudianteRnOuterClass.EstudianteResponse e){
        System.out.printf("======== %d ========\n", e.getMatricula());
        System.out.printf("\t Nombre: %s \n", e.getNombre());
        System.out.printf("\t Carrera: %s\n", e.getCarrera());
        System.out.println("===========================");
    }

    /**
     * Ejemplo de servicio stream bidreccional.
     * @param channel
     */
    private static void procesoBiDireccional(ManagedChannel channel){
        System.out.println("Proceso de Comunicación Bidireccional - gRPC");
        EstudianteRnGrpc.EstudianteRnStub asynCliente = EstudianteRnGrpc.newStub(channel);
        final CountDownLatch finishLatch = new CountDownLatch(1);

        //
        StreamObserver<EstudianteRnOuterClass.MensajeChat> responseStream = asynCliente.chatEntreEstudiante(
                //Flujo información recibida por el servidor.
                new StreamObserver<>() {
                    @Override
                    public void onNext(EstudianteRnOuterClass.MensajeChat value) {
                        System.out.println("Mensaje desde el servidor: "+value.toString());
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("Error: " + t.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Información enviada pro el servidor completada.");
                        finishLatch.countDown();
                    }
                });

        //Enviando la data desde el cliente al servidor.
        responseStream.onNext(EstudianteRnOuterClass.MensajeChat.newBuilder().setUsuario("cliente").setMensaje("Desde el cliente 1").setFecha("31/07/2021 13:30:30").build());
        responseStream.onNext(EstudianteRnOuterClass.MensajeChat.newBuilder().setUsuario("cliente").setMensaje("Desde el cliente 2").setFecha("31/07/2021 13:30:30").build());
        responseStream.onNext(EstudianteRnOuterClass.MensajeChat.newBuilder().setUsuario("cliente").setMensaje("Desde el cliente 3").setFecha("31/07/2021 13:30:30").build());


        if (finishLatch.getCount() == 0) {
            System.out.println("RPC completed or errored before we finished sending.");
            return;
        }

        responseStream.onCompleted(); //enviando a cerrar desde el cliente

        // Receiving happens asynchronously
        try {
            if (!finishLatch.await(20, TimeUnit.SECONDS)) {
                System.out.println("Falló, no completo en el tiempo indicado");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
