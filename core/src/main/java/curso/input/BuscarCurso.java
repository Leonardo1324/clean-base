package curso.input;

import curso.modelo.Curso;
import curso.modelo.Nivel;

import java.util.ArrayList;

public interface BuscarCurso {
    ArrayList<Curso> BuscarCursosPorNivel(Nivel lvl);
    ArrayList<Curso> BuscarCursosPorParteDelNombre(String name);
}
