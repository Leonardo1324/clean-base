package curso.usecase;

import curso.exception.ExceptionNoHayCoicidencias;
import curso.modelo.Curso;
import curso.output.PersistenceBuscar;

import java.util.ArrayList;

public class BuscarCurso implements curso.input.BuscarCurso {
    private PersistenceBuscar myBD;

    public BuscarCurso(PersistenceBuscar myBD) {
        this.myBD = myBD;
    }

    @Override
    public Curso BuscarCurso(String name) {
        return myBD.RecuperarCurso(name);
    }

    public ArrayList<Curso> BuscarCursos(String name) {
        if (myBD.RecuperarCursos(name) == null) {
            throw new ExceptionNoHayCoicidencias("No hay cursos con ese nombre");
        }
        return myBD.RecuperarCursos(name);
    }
}
