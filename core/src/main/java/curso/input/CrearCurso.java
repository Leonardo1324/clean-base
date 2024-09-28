package curso.input;

import curso.modelo.Valores;

import java.time.LocalDate;

public interface CrearCurso {
    void crearCurso(String nombre, LocalDate fecha, Valores nivel);

}
