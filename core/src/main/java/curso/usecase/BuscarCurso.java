package curso.usecase;

import curso.exception.ExceptionFechaInvalida;
import curso.exception.ExceptionNoHayCoicidencias;
import curso.exception.ExceptionNoHayCoicidenciasNivel;
import curso.exception.ExceptionNoHayCursosEnEseRango;
import curso.modelo.Curso;
import curso.modelo.Nivel;
import curso.output.PersistenceBuscar;

import java.time.LocalDate;
import java.util.ArrayList;

public class BuscarCurso implements curso.input.BuscarCurso {
    private PersistenceBuscar myBD;

    public BuscarCurso(PersistenceBuscar myBD) {
        this.myBD = myBD;
    }

    @Override
    public ArrayList<Curso> BuscarCursosPorParteDelNombre(String name) {
        if (myBD.RecuperarCursosNombre(name)==null){
            throw new ExceptionNoHayCoicidencias("No hay ningun curso con ese nombre");
        }
        return myBD.RecuperarCursosNombre(name);
    }

    @Override
    public ArrayList<Curso> BuscarCursosPorRangoDeFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio.isBefore(LocalDate.now()) || fechaFin.isBefore(LocalDate.now()) || fechaInicio.isEqual(fechaFin)){
            throw new ExceptionFechaInvalida("Las fechas ingresadas no son correctas");
        }
        if (myBD.RecuperarCursosPorFechas(fechaInicio,fechaFin)==null){
            throw new ExceptionNoHayCursosEnEseRango("No hay cursos en ese rango de fecahs");
        }
        return myBD.RecuperarCursosPorFechas(fechaInicio,fechaFin);
    }

    @Override
    public ArrayList<Curso> BuscarCursosPorNivel(Nivel lvl) {
        if (myBD.RecuperarCursosNivel(lvl)==null){
            throw new ExceptionNoHayCoicidenciasNivel("No hay cursos con ese nivel");
        }
        return myBD.RecuperarCursosNivel(lvl);
    }

}
