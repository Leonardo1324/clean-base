package curso.modelo;

import java.time.LocalDate;
import java.util.UUID;

public class Curso {
    private UUID id;
    private String nombre;
    private LocalDate fecha_cierre_inscripcion;
    private Valores nivel;

    private Curso(UUID id, String nombre, LocalDate fecha_cierre_inscripcion, Valores nivel) {
        this.id = id;
        this.nombre = nombre;
        this.fecha_cierre_inscripcion = fecha_cierre_inscripcion;
        this.nivel = nivel;
    }
    public static Curso instance(UUID id, String nombre, LocalDate fecha_cierre, Valores nivel) {

        if (id == null || nombre == null || fecha_cierre == null || nivel == null){
            return null;
        }
        if (fecha_cierre.isBefore(LocalDate.now())){
            return null;
        }
        if( !(nivel.equals(Valores.INICIAL) || nivel.equals(Valores.MEDIO) || nivel.equals(Valores.AVANZADO)) ){
            return null;
        }
        return new Curso(id,nombre,fecha_cierre,nivel);
    }
    public String getNombre() {
        return this.nombre;
    }
}