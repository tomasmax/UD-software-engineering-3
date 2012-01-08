package iso3.pt.dao;

public class UserNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(String message, Throwable reason) {
		super(message, reason);
	}
}