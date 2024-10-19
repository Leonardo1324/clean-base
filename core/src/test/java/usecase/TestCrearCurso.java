package usecase;

import curso.exception.ExceptionCursoConMismoNombre;
import curso.modelo.Nivel;
import curso.output.Peristence;
import curso.usecase.CrearCurso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class TestCrearCurso {

    @Mock
    Peristence BD;

    @Test
    void CursoYaExiste() {
        Exception e;
        CrearCurso cc = new CrearCurso(BD);
        when(BD.existeCurso("Lengua")).thenReturn(Boolean.TRUE);// existe
        e = Assertions.assertThrows(ExceptionCursoConMismoNombre.class,()->cc.RegsistarCurso(UUID.randomUUID(),"Lengua", LocalDate.MAX, Nivel.INICIAL));
        Assertions.assertEquals("esperado",e.getMessage());
        verify(BD,never()).gurdarCurso(any());
    }
}
