package usecase;

import curso.exception.ExceptionCursoMismoNombre;
import curso.modelo.Curso;
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
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestCrearCurso{
    @Mock
    Persistencia BD;
    @Test
    void CursoConElDistintoNombreSeGuardaElCurso() {
        CrearCurso micurso = new CrearCurso(BD);
        when(BD.existeCurso("Matematicas")).thenReturn(false);// ese curso no existe
        when(BD.guardarCurso(Mockito.any())).thenReturn(true);
        Assertions.assertDoesNotThrow(() -> micurso.crearCurso("Matematicas", LocalDate.MAX, Nivel.MEDIO));
        Assertions.assertTrue(micurso.crearCurso("Matematicas", LocalDate.MAX, Nivel.MEDIO));
    }
    @Test
    void CursoConElDistintoNombreNoSeGuardaElCurso() {
        CrearCurso micurso = new CrearCurso(BD);
        when(BD.existeCurso("Matematicas")).thenReturn(false);// ese curso no existe
        when(BD.guardarCurso(Mockito.any())).thenReturn(false);
        Assertions.assertDoesNotThrow(() -> micurso.crearCurso("Matematicas", LocalDate.MAX, Nivel.MEDIO));
        Assertions.assertFalse(micurso.crearCurso("Matematicas", LocalDate.MAX, Nivel.MEDIO));
    }
    @Test
    void CursoConElMismoNombre() {
        CrearCurso micurso = new CrearCurso(BD);
        Exception e;
        when(BD.existeCurso("Matematicas")).thenReturn(true); // ese curso ya existe
        e = Assertions.assertThrows(ExceptionCursoMismoNombre.class,()-> micurso.crearCurso("Matematicas", LocalDate.MAX, Nivel.MEDIO));
        verify(BD,never()).guardarCurso(Mockito.any());
        Assertions.assertEquals("Ya existe curso con este nombre: Matematicas",e.getMessage());
    }
}
