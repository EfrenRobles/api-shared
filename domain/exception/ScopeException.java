package api.shared.domain.exception;

public class ScopeException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ScopeException(String exception) {
        super(exception);
    }
}
