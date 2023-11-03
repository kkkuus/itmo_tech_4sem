package tools;

/**
 * Класс, описывающий кастомный exception, наследуется от RuntimeException
 */

public class BanksException extends RuntimeException {
    public BanksException() {
    }

    public BanksException(String message) {
        super(message);
    }

    public BanksException(String message, Exception inner) {
        super(message, inner);
    }
}
