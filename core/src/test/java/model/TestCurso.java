package model;

import curso.modelo.Curso;
import curso.modelo.Nivel;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCurso {
    @Test
    @Order(1)
    void atributos_obligatorios() {
        Curso micurso = Curso.instance(UUID.randomUUID(),"Matematicas",LocalDate.MAX, Nivel.INICIAL);
        assertNotNull(micurso);
    }
    @Test
    @Order(2)
    void atributos_nulos() {
        Curso micurso = Curso.instance(UUID.randomUUID(),null,LocalDate.MAX, Nivel.INICIAL);
        assertEquals(null,micurso);
    }
    @Test
    @Order(3)
    void Nombre_Cadena_Vacia() {
        Curso micurso = Curso.instance(UUID.randomUUID(),"",LocalDate.MAX, Nivel.INICIAL);
        assertEquals(null,micurso);
    }

    @Test
    @Order(4)
    void fecha_anterior_a_hoy() {
        Curso micurso = Curso.instance(UUID.randomUUID(),"Matematicas",LocalDate.of(2000,8,15), Nivel.INICIAL);
        assertEquals(null,micurso);
    }
    @Test
    @Order(5)
    void fecha_posterior_a_hoy() {
        Curso micurso = Curso.instance(UUID.randomUUID(),"Matematicas",LocalDate.MAX, Nivel.INICIAL);
        assertNotNull(micurso);
    }
    @Test
    @Order(6)
    void enum_diferente() {
        Curso micurso = Curso.instance(UUID.randomUUID(),"Matematicas",LocalDate.MAX, Nivel.PRO);
        assertEquals(null,micurso);
    }
}
