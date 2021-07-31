package org.jconfdominicana.js.services;


import org.jconfdominicana.js.Excepciones.NoExisteEstudianteException;
import org.jconfdominicana.js.entidades.Estudiante;

import java.util.ArrayList;
import java.util.List;


/**
 * Ejemplo de servicio patron Singleton simulando el CRUD de un Objeto.
 */
public class EstudianteFakeServices {

    private static EstudianteFakeServices instancia;
    private final List<Estudiante> listaEstudiante = new ArrayList<>();

    /**
     * Constructor privado.
     */
    private EstudianteFakeServices(){
        //añadiendo los estudiantes.
        listaEstudiante.add(new Estudiante(20011136, "Carlos Camacho", "ITT"));
    }

    public static EstudianteFakeServices getInstancia(){
        if(instancia==null){
            instancia = new EstudianteFakeServices();
        }
        return instancia;
    }


    public List<Estudiante> listarEstudiante(){
        return listaEstudiante;
    }

    public Estudiante getEstudiantePorMatricula(int matricula){
        return listaEstudiante.stream().filter(e -> e.getMatricula() == matricula).findFirst().orElse(null);
    }

    public Estudiante crearEstudiante(Estudiante estudiante){
        if(getEstudiantePorMatricula(estudiante.getMatricula())!=null){
            System.out.println("Estudiante registrado...");
            return null; //generar una excepcion...
        }
        listaEstudiante.add(estudiante);
        return estudiante;
    }

    public Estudiante actualizarEstudiante(Estudiante estudiante){
        Estudiante tmp = getEstudiantePorMatricula(estudiante.getMatricula());
        if(tmp == null){//no existe, no puede se actualizado
            throw new NoExisteEstudianteException("No Existe el estudiante: "+estudiante.getMatricula());
        }
        tmp.mezclar(estudiante);
        return tmp;
    }

    public boolean eliminandoEstudiante(int matricula){
        Estudiante tmp = new Estudiante();
        tmp.setMatricula(matricula);
        return listaEstudiante.remove(tmp);
    }

}
