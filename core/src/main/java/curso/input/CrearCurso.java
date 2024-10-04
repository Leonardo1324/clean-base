package curso.input;

import curso.modelo.Valores;

import java.time.LocalDate;
import java.util.UUID;

public interface CrearCurso {
    boolean crearCurso(String nombre, LocalDate fecha, Valores nivel);

}
