package curso.output;

import curso.modelo.Curso;

import java.util.ArrayList;

public interface PersistenceBuscar {
    Curso RecuperarCurso(String name);

    ArrayList<Curso> RecuperarCursos(String name);
}
