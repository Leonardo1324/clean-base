package curso.modelo;

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
            return null; // E valor nulo
        }
        if (nombre.isEmpty()){
            return null; // E Cadeba vacia
        }
        if (fecha_cierre.isBefore(LocalDate.now())){
            return null; //E fecha anteriro a la de hoy
        }
        if( !(nivel.equals(Nivel.INICIAL) || nivel.equals(Nivel.MEDIO) || nivel.equals(Nivel.AVANZADO)) ){
            return null; // valor diferente a los permitidos
        }
        return new Curso(id,nombre,fecha_cierre,nivel);
    }
    public String getNombre() {
        return this.nombre;
    }
}