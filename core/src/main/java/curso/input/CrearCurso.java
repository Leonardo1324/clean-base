package curso.input;

import curso.modelo.Nivel;

import java.time.LocalDate;
import java.util.UUID;

public interface CrearCurso {
    boolean RegsistarCurso(UUID id, String name, LocalDate date, Nivel lvl);
}
