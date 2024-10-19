package curso.exception;

public class ExceptionCursoConMismoNombre extends RuntimeException{
    public ExceptionCursoConMismoNombre(String message) {
        super(message);
    }
}
