package model;

import curso.modelo.Curso;
import curso.modelo.Valores;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCurso {
    @Test
    void atributos_obligatorios() {
        Curso micurso = Curso.instance(UUID.randomUUID(),"Matematicas",LocalDate.of(2025,8,15), Valores.INICIAL);
        assertNotNull(micurso);
    }
    @Test
    void atributos_faltantes() {
        Curso micurso = Curso.instance(UUID.randomUUID(),null,LocalDate.of(2000,8,15), Valores.INICIAL);
        assertEquals(null,micurso);
    }

    @Test
    void fecha_anterior_a_hoy() {
        Curso micurso = Curso.instance(UUID.randomUUID(),"Matematicas",LocalDate.of(2000,8,15), Valores.INICIAL);
        assertEquals(null,micurso);
    }
    @Test
    void enum_diferente() {
        Curso micurso = Curso.instance(UUID.randomUUID(),"Matematicas",LocalDate.of(2000,8,15), Valores.PRO);
        assertEquals(null,micurso);
    }

}
