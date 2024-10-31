package curso.modelo;

import curso.exception.ExceptionAtributosCursoIncorrectos;

import java.time.LocalDate;
import java.util.UUID;

public class Curso {
    private UUID id;
    private String nombre;
    private LocalDate fechaCierreInscripcion;
    private Nivel nivel;

    private Curso(UUID id, String nombre, LocalDate fechaCierreInscripcion, Nivel nivel) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCierreInscripcion = fechaCierreInscripcion;
        this.nivel = nivel;
    }
    public static Curso instance(UUID id, String nombre, LocalDate fechaCierre, Nivel nivel) {

        if (id == null || nombre == null || fechaCierre == null || nivel == null){
            throw new ExceptionAtributosCursoIncorrectos("Algun atributo es nulo");
        }
        if (nombre.isEmpty()){
            throw new ExceptionAtributosCursoIncorrectos("La cadena de texto esta vacia");
        }
        if (fechaCierre.isBefore(LocalDate.now())){
            throw new ExceptionAtributosCursoIncorrectos("La fecha ya paso");
        }
        if( !(nivel.equals(Nivel.INICIAL) || nivel.equals(Nivel.MEDIO) || nivel.equals(Nivel.AVANZADO)) ){
            throw new ExceptionAtributosCursoIncorrectos("El Nivel no coincide con uno permitido");
        }
        return new Curso(id,nombre,fechaCierre,nivel);
    }
    public String getNombre() {
        return this.nombre;
    }
}