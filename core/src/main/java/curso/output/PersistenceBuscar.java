package curso.output;

import curso.modelo.Curso;
import curso.modelo.Nivel;

import java.util.ArrayList;

public interface PersistenceBuscar {
//    Curso RecuperarCursoNombre(String name);
//    Curso RecuperarCursoNivel(Nivel lvl);

    ArrayList<Curso> RecuperarCursosNombre(String name);

    ArrayList<Curso> RecuperCursosNivel(Nivel lvl);
}
