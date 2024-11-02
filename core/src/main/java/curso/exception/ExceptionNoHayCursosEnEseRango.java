package curso.exception;

public class ExceptionNoHayCursosEnEseRango extends RuntimeException{
    public ExceptionNoHayCursosEnEseRango(String message) {
        super(message);
    }
}
