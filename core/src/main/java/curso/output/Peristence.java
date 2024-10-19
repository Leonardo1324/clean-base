package curso.output;

import curso.modelo.Curso;

public interface Peristence {
    boolean existeCurso (String name);
    boolean gurdarCurso (Curso c1);
}
