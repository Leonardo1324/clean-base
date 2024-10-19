package curso.usecase;

import curso.exception.ExceptionCursoConMismoNombre;
import curso.modelo.Curso;
import curso.modelo.Nivel;
import curso.output.Peristence;

import java.time.LocalDate;
import java.util.UUID;

public class CrearCurso implements curso.input.CrearCurso {
    private Peristence myBD;

    public CrearCurso(Peristence myBD) {
        this.myBD = myBD;
    }

    @Override
    public boolean RegsistarCurso(UUID id, String name, LocalDate date, Nivel lvl) {
        if (myBD.existeCurso(name)) {
            throw new ExceptionCursoConMismoNombre("El curso con el nombre: "+name+" ya existe");
        }
        return myBD.gurdarCurso(Curso.Instance(id,name,date,lvl));
    }
}
