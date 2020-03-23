package database.utils;

public class NoPermissionException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoPermissionException() {
		this("You have no permission to this Content!");
	}
	
	public NoPermissionException(String message){
    	super(message);
    }
    
}