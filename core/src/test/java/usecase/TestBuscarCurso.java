package usecase;

import curso.exception.ExceptionFechaInvalida;
import curso.exception.ExceptionNoHayCoicidencias;
import curso.exception.ExceptionNoHayCoicidenciasNivel;
import curso.modelo.Curso;
import curso.modelo.Nivel;
import curso.output.PersistenceBuscar;
import curso.usecase.BuscarCurso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestBuscarCurso {

    @Mock
    PersistenceBuscar BD;

    @Test
    void BuscarCursoParteDelNombreIndividual() {
        Curso c2 = Curso.instance(UUID.randomUUID(),"Matematica", LocalDate.MAX, Nivel.INICIAL);

        ArrayList<Curso> misCursosCorrecto = new ArrayList<Curso>();

        misCursosCorrecto.add(c2);

        BuscarCurso bc = new BuscarCurso(BD);
        when(bc.BuscarCursosPorParteDelNombre("Matematica")).thenReturn(misCursosCorrecto);
        Assertions.assertDoesNotThrow(()->bc.BuscarCursosPorParteDelNombre("Matematica"));
        Assertions.assertEquals(misCursosCorrecto,bc.BuscarCursosPorParteDelNombre("Matematica"));
    }

    @Test
    void BuscarCursoParteDelNombreMinusculas() {
        Curso c2 = Curso.instance(UUID.randomUUID(),"Matematica", LocalDate.MAX, Nivel.INICIAL);
        Curso c3 = Curso.instance(UUID.randomUUID(),"Agromatica", LocalDate.MAX, Nivel.INICIAL);
        Curso c4 = Curso.instance(UUID.randomUUID(),"Materiales", LocalDate.MAX, Nivel.INICIAL);

        ArrayList<Curso> misCursosCorrecto = new ArrayList<Curso>();

        misCursosCorrecto.add(c2);
        misCursosCorrecto.add(c3);
        misCursosCorrecto.add(c4);

        BuscarCurso bc = new BuscarCurso(BD);
        when(bc.BuscarCursosPorParteDelNombre("mat")).thenReturn(misCursosCorrecto);
        Assertions.assertDoesNotThrow(()->bc.BuscarCursosPorParteDelNombre("mat"));
        Assertions.assertEquals(misCursosCorrecto,bc.BuscarCursosPorParteDelNombre("mat"));
    }

    @Test
    void BuscarCursoParteDelNombreSinAciertos() {
        Exception e;
        BuscarCurso bc = new BuscarCurso(BD);
        when(bc.BuscarCursosPorParteDelNombre("pan")).thenReturn(null);
        e = Assertions.assertThrows(ExceptionNoHayCoicidencias.class,()->bc.BuscarCursosPorParteDelNombre("pan"));
        Assertions.assertEquals("No hay ningun curso con ese nombre",e.getMessage());
    }

    @Test
    void BuscarCursosPorNivel() {
        Curso c2 = Curso.instance(UUID.randomUUID(),"Matematica", LocalDate.MAX, Nivel.INICIAL);
        Curso c3 = Curso.instance(UUID.randomUUID(),"Agromatica", LocalDate.MAX, Nivel.INICIAL);
        Curso c4 = Curso.instance(UUID.randomUUID(),"Materiales", LocalDate.MAX, Nivel.INICIAL);

        ArrayList<Curso> misCursosCorrecto = new ArrayList<Curso>();

        misCursosCorrecto.add(c2);
        misCursosCorrecto.add(c3);
        misCursosCorrecto.add(c4);

        BuscarCurso bc = new BuscarCurso(BD);
        when(bc.BuscarCursosPorNivel(Nivel.INICIAL)).thenReturn(misCursosCorrecto);

        Assertions.assertDoesNotThrow(()->bc.BuscarCursosPorNivel(Nivel.INICIAL));
        Assertions.assertEquals(misCursosCorrecto,bc.BuscarCursosPorNivel(Nivel.INICIAL));
    }

    @Test
    void BuscarCursoPorNivelSinAciertos() {
        Exception e;
        BuscarCurso bc = new BuscarCurso(BD);
        when(bc.BuscarCursosPorNivel(Nivel.MEDIO)).thenReturn(null);
        e = Assertions.assertThrows(ExceptionNoHayCoicidenciasNivel.class,()->bc.BuscarCursosPorNivel(Nivel.MEDIO));
        Assertions.assertEquals("No hay cursos con ese nivel",e.getMessage());
    }

    @Test
    void BuscarCursoPorRangoDeFechas() {
        Curso c3 = Curso.instance(UUID.randomUUID(),"Agromatica", LocalDate.now().plusYears(1), Nivel.INICIAL);

        ArrayList<Curso> misCursosCorrecto = new ArrayList<Curso>();

        misCursosCorrecto.add(c3);

        BuscarCurso bc = new BuscarCurso(BD);
        when(bc.BuscarCursosPorRangoDeFechas(LocalDate.now(),
                LocalDate.now().plusYears(2))).thenReturn(misCursosCorrecto);

        Assertions.assertDoesNotThrow(()->bc.BuscarCursosPorRangoDeFechas
                (LocalDate.now(),
                LocalDate.now().plusYears(2)));

        Assertions.assertEquals(misCursosCorrecto,bc.BuscarCursosPorRangoDeFechas
                (LocalDate.now(),
                        LocalDate.now().plusYears(2)));
    }

    @Test
    void BuscarCursoPorRangoDeFechasIncorrectas() {

        Exception e;
        BuscarCurso bc = new BuscarCurso(BD);

        e = Assertions.assertThrows(ExceptionFechaInvalida.class,()->bc.BuscarCursosPorRangoDeFechas
                (LocalDate.now().minusYears(1),
                        LocalDate.now().plusYears(2)));
        verify(BD,never()).RecuperarCursosPorFechas(Mockito.any(),Mockito.any());

        Assertions.assertEquals(e.getMessage(),"Las fechas ingresadas no son correctas");
    }
}
