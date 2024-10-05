package model;

import curso.exception.ExceptionAtributosCursoIncorrectos;
import curso.modelo.Curso;
import curso.modelo.Nivel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCurso {
    @Test
    @Order(1)
    void atributos_obligatorios_correctos() {
        Curso micurso = Curso.instance(UUID.randomUUID(),"Matematicas",LocalDate.MAX, Nivel.INICIAL);
        assertNotNull(micurso);
    }
    @Test
    @Order(2)
    void atributos_nulos() {
        Curso micurso;
        Exception e;
        e = Assertions.assertThrows(ExceptionAtributosCursoIncorrectos.class,()-> Curso.instance(UUID.randomUUID(),null,LocalDate.MAX, Nivel.INICIAL));
        Assertions.assertEquals("Algun atributo es nulo",e.getMessage());
    }
    @Test
    @Order(3)
    void Nombre_Cadena_Vacia() {
        Curso micurso;
        Exception e;
        e = Assertions.assertThrows(ExceptionAtributosCursoIncorrectos.class,()-> Curso.instance(UUID.randomUUID(),"",LocalDate.MAX, Nivel.INICIAL));
        Assertions.assertEquals("La cadena de texto esta vacia",e.getMessage());
    }
    @Test
    @Order(4)
    void fecha_anterior_a_hoy() {
        Curso micurso;
        Exception e;
        e = Assertions.assertThrows(ExceptionAtributosCursoIncorrectos.class,()-> Curso.instance(UUID.randomUUID(),"Matematicas",LocalDate.MIN, Nivel.INICIAL));
        Assertions.assertEquals("La fecha ya paso",e.getMessage());
    }
    @Test
    @Order(5)
    void fecha_posterior_a_hoy() {
        Curso micurso;
        Assertions.assertDoesNotThrow(()-> Curso.instance(UUID.randomUUID(),"Matematicas",LocalDate.MAX, Nivel.INICIAL));
    }
    @Test
    @Order(6)
    void enum_diferente() {
        Curso micurso;
        Exception e;
        e = Assertions.assertThrows(ExceptionAtributosCursoIncorrectos.class,()-> Curso.instance(UUID.randomUUID(),"Matematicas",LocalDate.MAX, Nivel.PRO));
        Assertions.assertEquals("El Nivel no coincide con uno permitido",e.getMessage());
    }
}