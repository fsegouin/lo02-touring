package fr.lo02.model.exception;

public class UnknownPlayerNameException extends Exception {

	public UnknownPlayerNameException() {
		// TODO Auto-generated constructor stub
		System.out.println("Ce joueur n'existe pas.");
	}

	public UnknownPlayerNameException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public UnknownPlayerNameException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public UnknownPlayerNameException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
