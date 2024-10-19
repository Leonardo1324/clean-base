package curso.exception;

public class ExceptionNoHayCoicidencias extends RuntimeException{
    public ExceptionNoHayCoicidencias(String message) {
        super(message);
    }
}
