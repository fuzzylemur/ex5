package filesprocessing;

/*
 * TypeTwoExceptions are thrown for IO problems with the command file, its format or invalid args.
 */
class TypeTwoException extends Exception {

	/* Class Constants */
	private static final String MSG = "ERROR: ";
    private String msgDetails;
    private static final long serialVersionUID = 1L;

	/*
	 * Constructor, receives a string with details about the specific error
	 * @param msgDetails error details
	 */
	TypeTwoException(String msgDetails){
        this.msgDetails = msgDetails;
    }

	/**
	 * Get the message to print for this exception
	 * @return the message to print
	 */
    public String getMessage(){
        return MSG + msgDetails + "\n";
    }

}
