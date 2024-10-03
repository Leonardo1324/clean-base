package usecase;

import curso.exception.ExceptionCursoMismoNombre;
import curso.modelo.Curso;
import curso.modelo.Valores;
import curso.output.Persistencia;
import curso.usecase.CrearCurso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestCrearCurso{

    @Mock
    Persistencia BD;

    // no paso porque el ID no es el mismo para el mock y el aseert
    @Test
    void GuardarCurso() {
        CrearCurso micurso = new CrearCurso(BD);

        Curso mat = Curso.instance(UUID.fromString("mat"),"Matematicas",LocalDate.MAX, Valores.INICIAL);


        when(BD.existeCurso("Matematicas")).thenReturn(false);
        when(BD.guardarCurso(mat)).thenReturn(true);
        Assertions.assertTrue(micurso.crearCurso("Matematicas", LocalDate.of(2025,8,15), Valores.MEDIO));
    }
    @Test
    void CursoConElMismoNombre() {
        CrearCurso micurso = new CrearCurso(BD);
        when(BD.existeCurso("Matematicas")).thenReturn(true);
        Assertions.assertThrows(ExceptionCursoMismoNombre.class,()-> micurso.crearCurso("Matematicas", LocalDate.of(2025,8,15), Valores.MEDIO));
    }

}
