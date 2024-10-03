package curso.usecase;

import curso.exception.ExceptionCursoMismoNombre;
import curso.modelo.Curso;
import curso.modelo.Valores;
import curso.output.Persistencia;

import java.time.LocalDate;
import java.util.UUID;


//intarfaz de caso de uso input
//clase que implenta la interfaz del caso de uso
//interfaz de persistencia outpot
//utilizar la persistencia en el constructor del caso de uso


public class CrearCurso implements curso.input.CrearCurso {
    private Persistencia myDB;
    public CrearCurso(Persistencia myDB) {
        this.myDB = myDB;
    }

    @Override
    public boolean crearCurso(String nombre, LocalDate fecha, Valores nivel) {
        Curso micurso = Curso.instance(UUID.randomUUID(), "Matematicas", LocalDate.of(2025, 8, 15), nivel);
        if (myDB.existeCurso(micurso.getNombre())){
            throw new ExceptionCursoMismoNombre("Ya existe curso con este nombre: "+micurso.getNombre());
        }
        if (myDB.guardarCurso(micurso)){
            return true;
        }
        return false;
    }
}
