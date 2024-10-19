package model;

import curso.exception.ExceptionNombreVacio;
import curso.modelo.Curso;
import curso.modelo.Nivel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class TestCurso {

    @Test
    void nombreVacio() {
        Exception e;
        e = Assertions.assertThrows(ExceptionNombreVacio.class,()-> Curso.Instance(UUID.randomUUID(),"", LocalDate.MAX, Nivel.INICIAL));
        Assertions.assertEquals("El nombre no puede ser vacio",e.getMessage());
    }
}
