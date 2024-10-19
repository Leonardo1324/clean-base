package curso.modelo;

import curso.exception.ExceptionNombreVacio;

import java.time.LocalDate;
import java.util.UUID;


public class Curso {
    private UUID id;
    private String name;
    private LocalDate date;
    private Nivel lvl;

    public Curso(UUID id, String name, LocalDate date, Nivel lvl) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.lvl = lvl;
    }

    public static Curso Instance(UUID id, String name, LocalDate date, Nivel lvl){
        if (name.isEmpty()){
            throw new ExceptionNombreVacio("El nombre no puede ser vacio");
        }
        return new Curso(id,name,date,lvl);
    }
}
