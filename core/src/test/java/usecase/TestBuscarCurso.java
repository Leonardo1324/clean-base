package usecase;

import curso.exception.ExceptionNoHayCoicidencias;
import curso.modelo.Curso;
import curso.modelo.Nivel;
import curso.output.PersistenceBuscar;
import curso.usecase.BuscarCurso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestBuscarCurso {

    @Mock
    PersistenceBuscar BD;

    @Test
    void BuscarCursoExistente() {
        Curso c = Curso.Instance(UUID.randomUUID(),"Lengua", LocalDate.MAX, Nivel.INICIAL);
        BuscarCurso bc = new BuscarCurso(BD);
        when(BD.RecuperarCurso("Lengua")).thenReturn(c);
        Assertions.assertNotNull(bc.BuscarCurso("Lengua"));
    }
    @Test
    void BuscarCursoNoExistente() {
        Curso c = Curso.Instance(UUID.randomUUID(),"Lengua", LocalDate.MAX, Nivel.INICIAL);
        BuscarCurso bc = new BuscarCurso(BD);
        when(BD.RecuperarCurso("Lengua")).thenReturn(null);
        Assertions.assertNull(bc.BuscarCurso("Lengua"));
    }

    @Test
    void BuscarCursoParteDelNombre() {
        Curso c2 = Curso.Instance(UUID.randomUUID(),"Matematica", LocalDate.MAX, Nivel.INICIAL);
        Curso c3 = Curso.Instance(UUID.randomUUID(),"Agromatica", LocalDate.MAX, Nivel.INICIAL);
        Curso c4 = Curso.Instance(UUID.randomUUID(),"Materiales", LocalDate.MAX, Nivel.INICIAL);

        ArrayList<Curso> misCursosCorrecto = new ArrayList<Curso>();

        misCursosCorrecto.add(c2);
        misCursosCorrecto.add(c3);
        misCursosCorrecto.add(c4);

        BuscarCurso bc = new BuscarCurso(BD);
        when(BD.RecuperarCursos("mat")).thenReturn(misCursosCorrecto);
        Assertions.assertDoesNotThrow(()->bc.BuscarCursos("mat"));
        Assertions.assertEquals(misCursosCorrecto,bc.BuscarCursos("mat"));
    }

    @Test
    void BuscarCursoParteDelNombreIncorrecto() {
        Exception e;
        BuscarCurso bc = new BuscarCurso(BD);
        when(BD.RecuperarCursos("pan")).thenReturn(null);
        e = Assertions.assertThrows(ExceptionNoHayCoicidencias.class,()->bc.BuscarCursos("pan"));
        Assertions.assertEquals("No hay cursos con ese nombre",e.getMessage());
    }
}
