package iso3.pt.util;

public class IncorrectPasswordException extends Exception {
	private static final long serialVersionUID = 1L;

	public IncorrectPasswordException(String message) {
		super(message);
	}

	public IncorrectPasswordException(String message, Throwable reason) {
		super(message, reason);
	}
}
