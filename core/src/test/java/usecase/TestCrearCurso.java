package usecase;

import curso.exception.ExceptionCursoConMismoNombre;
import curso.modelo.Nivel;
import curso.output.Peristence;
import curso.usecase.CrearCurso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class TestCrearCurso {

    @Mock
    Peristence BD;

    @Test
    void CursoYaExiste() {
        Exception e;
        CrearCurso cc = new CrearCurso(BD);
        when(BD.existeCurso("Lengua")).thenReturn(Boolean.TRUE);// existe
        e = Assertions.assertThrows(ExceptionCursoConMismoNombre.class,()->cc.RegsistarCurso(UUID.randomUUID(),"Lengua", LocalDate.MAX, Nivel.INICIAL));
        Assertions.assertEquals("El curso con el nombre: Lengua ya existe",e.getMessage());
        verify(BD,never()).gurdarCurso(any());
    }
    @Test
    void CursoNOExiste() {
        CrearCurso cc = new CrearCurso(BD);
        when(BD.existeCurso("Lengua")).thenReturn(Boolean.FALSE);// no existe
        when(BD.gurdarCurso(any())).thenReturn(Boolean.TRUE);
        Assertions.assertDoesNotThrow(()->cc.RegsistarCurso(UUID.randomUUID(),"Lengua", LocalDate.MAX, Nivel.INICIAL));
        Assertions.assertTrue(()->cc.RegsistarCurso(UUID.randomUUID(),"Lengua", LocalDate.MAX, Nivel.INICIAL));
    }



}
