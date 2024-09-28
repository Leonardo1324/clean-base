package usecase;

import curso.exception.ExceptionCursoMismoNombre;
import curso.modelo.Valores;
import curso.output.Persistencia;
import curso.usecase.CrearCurso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestCrearCurso{

    @Mock
    Persistencia BD;
    @Test
    void CursoConElMismoNombre() {
        CrearCurso micurso = new CrearCurso(BD);
        when(BD.existeCurso("Matematicas")).thenReturn(true);
        Assertions.assertThrows(ExceptionCursoMismoNombre.class,()-> micurso.crearCurso("Matematicas", LocalDate.of(2025,8,15), Valores.MEDIO));
    }
}
