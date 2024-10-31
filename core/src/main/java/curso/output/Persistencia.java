package curso.output;

import curso.modelo.Curso;

public interface Persistencia {

    boolean existeCurso(String nombre);
    boolean guardarCurso(Curso micurso);
}
