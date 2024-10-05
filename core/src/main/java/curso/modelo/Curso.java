package curso.modelo;

import curso.exception.ExceptionAtributosCursoIncorrectos;

import java.time.LocalDate;
import java.util.UUID;

public class Curso {
    private UUID id;
    private String nombre;
    private LocalDate fecha_cierre_inscripcion;
    private Nivel nivel;

    private Curso(UUID id, String nombre, LocalDate fecha_cierre_inscripcion, Nivel nivel) {
        this.id = id;
        this.nombre = nombre;
        this.fecha_cierre_inscripcion = fecha_cierre_inscripcion;
        this.nivel = nivel;
    }
    public static Curso instance(UUID id, String nombre, LocalDate fecha_cierre, Nivel nivel) {

        if (id == null || nombre == null || fecha_cierre == null || nivel == null){
            throw new ExceptionAtributosCursoIncorrectos("Algun atributo es nulo");
        }
        if (nombre.isEmpty()){
            throw new ExceptionAtributosCursoIncorrectos("La cadena de texto esta vacia");
        }
        if (fecha_cierre.isBefore(LocalDate.now())){
            throw new ExceptionAtributosCursoIncorrectos("La fecha ya paso");
        }
        if( !(nivel.equals(Nivel.INICIAL) || nivel.equals(Nivel.MEDIO) || nivel.equals(Nivel.AVANZADO)) ){
            throw new ExceptionAtributosCursoIncorrectos("El Nivel no coincide con uno permitido");
        }
        return new Curso(id,nombre,fecha_cierre,nivel);
    }
    public String getNombre() {
        return this.nombre;
    }
}