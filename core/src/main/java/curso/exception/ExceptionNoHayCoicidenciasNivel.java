package curso.exception;

public class ExceptionNoHayCoicidenciasNivel extends RuntimeException{
    public ExceptionNoHayCoicidenciasNivel(String message) {
        super(message);
    }
}
