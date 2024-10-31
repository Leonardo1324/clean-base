package curso.usecase;

import curso.exception.ExceptionNoHayCoicidencias;
import curso.exception.ExceptionNoHayCoicidenciasNivel;
import curso.modelo.Curso;
import curso.modelo.Nivel;
import curso.output.PersistenceBuscar;

import java.util.ArrayList;

public class BuscarCurso implements curso.input.BuscarCurso {
    private PersistenceBuscar myBD;

    public BuscarCurso(PersistenceBuscar myBD) {
        this.myBD = myBD;
    }

//    public Curso BuscarCurso(String name) {
//        if (myBD.RecuperarCursoNombre(name)==null){
//            throw new ExceptionCursoNoEncontrado("No se encontro curso con ese nombre");
//        }
//        return myBD.RecuperarCursoNombre(name);
//    }

//    public ArrayList<Curso> BuscarCursos(String name) {
//        if (myBD.RecuperarCursosNombre(name) == null ) {
//            throw new ExceptionNoHayCoicidencias("No hay cursos con ese nombre");
//        }
//        return myBD.RecuperarCursosNombre(name);
//    }


    @Override
    public ArrayList<Curso> BuscarCursosPorParteDelNombre(String name) {
        if (myBD.RecuperarCursosNombre(name)==null){
            throw new ExceptionNoHayCoicidencias("No hay ningun curso con ese nombre");
        }
        return myBD.RecuperarCursosNombre(name);
    }

    @Override
    public ArrayList<Curso> BuscarCursosPorNivel(Nivel lvl) {
        if (myBD.RecuperCursosNivel(lvl)==null){
            throw new ExceptionNoHayCoicidenciasNivel("No hay cursos con ese nivel");
        }
        return myBD.RecuperCursosNivel(lvl);
    }


}
