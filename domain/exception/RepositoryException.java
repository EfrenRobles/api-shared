package api.shared.domain.exception;

public class RepositoryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RepositoryException(String exception) {
        super(exception);
    }
}
