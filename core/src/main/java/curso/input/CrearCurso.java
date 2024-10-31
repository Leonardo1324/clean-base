package curso.input;

import curso.modelo.Nivel;

import java.time.LocalDate;

public interface CrearCurso {
    boolean crearCurso(String nombre, LocalDate fecha, Nivel nivel);

}
