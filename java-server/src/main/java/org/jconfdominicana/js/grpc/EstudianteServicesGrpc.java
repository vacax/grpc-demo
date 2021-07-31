package org.jconfdominicana.js.grpc;

import estudiantern.EstudianteRnGrpc;
import estudiantern.EstudianteRnOuterClass;
import io.grpc.stub.StreamObserver;
import org.jconfdominicana.js.Excepciones.NoExisteEstudianteException;
import org.jconfdominicana.js.entidades.Estudiante;
import org.jconfdominicana.js.services.EstudianteFakeServices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Representación de
 */
public class EstudianteServicesGrpc extends EstudianteRnGrpc.EstudianteRnImplBase {

    //Instancia del servicio.
    private final EstudianteFakeServices estudianteFakeServices = EstudianteFakeServices.getInstancia();

    /**
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void getEstudiante(EstudianteRnOuterClass.EstudianteRequest request, StreamObserver<EstudianteRnOuterClass.EstudianteResponse> responseObserver) {
        //Recuperando el parametro de entrada.
        Estudiante estudiantePorMatricula = estudianteFakeServices.getEstudiantePorMatricula(request.getMatricula());
        if(estudiantePorMatricula!=null){
            responseObserver.onNext(convertir(estudiantePorMatricula));
            responseObserver.onCompleted(); //terminando la petición por el servidor.
        }else{
            responseObserver.onError(new NoExisteEstudianteException("No existe el estudiante: "+request.getMatricula()));
        }
    }

    @Override
    public void crearEstudiante(EstudianteRnOuterClass.EstudianteResponse request, StreamObserver<EstudianteRnOuterClass.EstudianteResponse> responseObserver) {
        Estudiante e = estudianteFakeServices.crearEstudiante(convertir(request));
        responseObserver.onNext(convertir(e));
        responseObserver.onCompleted();
    }

    @Override
    public void actualizarEstudiante(EstudianteRnOuterClass.EstudianteResponse request, StreamObserver<EstudianteRnOuterClass.EstudianteResponse> responseObserver) {
        Estudiante e = estudianteFakeServices.actualizarEstudiante(convertir(request));
        responseObserver.onNext(convertir(e));
        responseObserver.onCompleted();
    }

    @Override
    public void eliminarEstudiante(EstudianteRnOuterClass.EstudianteRequest request, StreamObserver<EstudianteRnOuterClass.EstudianteBorrado> responseObserver) {
        boolean borrado = estudianteFakeServices.eliminandoEstudiante(request.getMatricula());
        responseObserver.onNext(EstudianteRnOuterClass.EstudianteBorrado.newBuilder().setOk(borrado).build());
        responseObserver.onCompleted();
    }

    @Override
    public void listaEstudiante(EstudianteRnOuterClass.Empty request, StreamObserver<EstudianteRnOuterClass.ListaEstudiante> responseObserver) {
        List<Estudiante> estudiantes = estudianteFakeServices.listarEstudiante();
        List<EstudianteRnOuterClass.EstudianteResponse> estudianteResponseList = new ArrayList<>();
        for(Estudiante e : estudiantes){
            estudianteResponseList.add(convertir(e));
        }
        EstudianteRnOuterClass.ListaEstudiante build = EstudianteRnOuterClass.ListaEstudiante.newBuilder().addAllEstudiante(estudianteResponseList).build();
        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }

    /**
     * Implementar Streams bidireccional entre el cliente y servidor.
     * @param responseObserver
     * @return
     */
    @Override
    public StreamObserver<EstudianteRnOuterClass.MensajeChat> chatEntreEstudiante(StreamObserver<EstudianteRnOuterClass.MensajeChat> responseObserver) {

        //Flujo del servidor al cliente
        for (int i = 0; i < 10; i++) {
            responseObserver.onNext(EstudianteRnOuterClass.MensajeChat.newBuilder()
                    .setUsuario("servidor")
                    .setMensaje(String.format("Mensaje %d", i))
                    .setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()))
                    .build());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        //Flujo desde el cliente al servidor
        return new StreamObserver<>() {

            int cantidadMensajes = 0;

            @Override
            public void onNext(EstudianteRnOuterClass.MensajeChat value) {
                //recibiendo la información y presentando por salida estadar.
                System.out.println("Mensaje Recibido: " + value.toString());
                cantidadMensajes++;
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Ocurrió un error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Fueron recibidos del cliente: " + cantidadMensajes);
            }
        };
    }

    /**
     * Convierte la clase del modelo para enviar por el servicio gRPC.
     * @param estudiante
     * @return
     */
    private EstudianteRnOuterClass.EstudianteResponse convertir(Estudiante estudiante){
        return EstudianteRnOuterClass.EstudianteResponse.newBuilder()
                .setMatricula(estudiante.getMatricula())
                .setNombre(estudiante.getNombre())
                .setCarrera(estudiante.getCarrera())
                .build();
    }

    /**
     *
     * @param e
     * @return
     */
    private Estudiante convertir(EstudianteRnOuterClass.EstudianteResponse e){
        return new Estudiante(e.getMatricula(), e.getNombre(), e.getCarrera());
    }
}
