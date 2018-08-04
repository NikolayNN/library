package my.hhorushko.otus.library.exception;

public class LibraryNotFoundException extends RuntimeException {

    public LibraryNotFoundException(String message) {
        super(message);
    }

    public LibraryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
