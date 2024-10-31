package curso.exception;

public class ExceptionCursoMismoNombre extends RuntimeException {
    public ExceptionCursoMismoNombre(String message) {
        super(message);
    }
}
