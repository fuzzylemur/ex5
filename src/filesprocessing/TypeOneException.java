package filesprocessing;

/*
 * TypeOneExceptions are thrown when the requested filter/order in the command file are invalid
 * (bad name, invalid parameters)
 */
class TypeOneException extends Exception {

	/* Class Constants */
    private static final String MSG = "Warning in line ";
    private static final long serialVersionUID = 1L;

	/**
	 * Get the message to print for this exception
	 * @return the message to print
	 */
	public String getMessage(){return MSG;}

}
