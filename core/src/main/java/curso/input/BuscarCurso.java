package curso.input;

import curso.modelo.Curso;
import curso.modelo.Nivel;

import java.time.LocalDate;
import java.util.ArrayList;

public interface BuscarCurso {
    ArrayList<Curso> BuscarCursosPorNivel(Nivel lvl);
    ArrayList<Curso> BuscarCursosPorParteDelNombre(String name);
    ArrayList<Curso> BuscarCursosPorRangoDeFechas(LocalDate fechaInicio,LocalDate fechaFin);
}
