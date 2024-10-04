package usecase;

import curso.exception.ExceptionCursoMismoNombre;
import curso.modelo.Nivel;
import curso.output.Persistencia;
import curso.usecase.CrearCurso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestCrearCurso{
    @Mock
    Persistencia BD;
    @Test
    void GuardarCurso() {
        CrearCurso micurso = new CrearCurso(BD);
        when(BD.guardarCurso(Mockito.any())).thenReturn(true);
        Assertions.assertTrue(micurso.crearCurso("Matematicas",LocalDate.of(2025,8,15), Nivel.MEDIO));
    }
    @Test
    void NoSePuedeGuardarCurso() {
        CrearCurso micurso = new CrearCurso(BD);
        when(BD.guardarCurso(Mockito.any())).thenReturn(false);
        Assertions.assertFalse(micurso.crearCurso("Matematicas",LocalDate.of(2025,8,15), Nivel.MEDIO));
    }
    @Test
    void CursoConElMismoNombre() {
        CrearCurso micurso = new CrearCurso(BD);
        when(BD.existeCurso("Matematicas")).thenReturn(true);
        Assertions.assertThrows(ExceptionCursoMismoNombre.class,()-> micurso.crearCurso("Matematicas", LocalDate.of(2025,8,15), Nivel.MEDIO));
    }
    @Test
    void CursoConElDistintoNombre() {
        CrearCurso micurso = new CrearCurso(BD);
        when(BD.existeCurso("Matematicas")).thenReturn(false);
        Assertions.assertDoesNotThrow(() -> micurso.crearCurso("Matematicas", LocalDate.MAX, Nivel.MEDIO));
    }

}
