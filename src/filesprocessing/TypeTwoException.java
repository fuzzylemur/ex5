package filesprocessing;

public class TypeTwoException extends Exception {

    private static final String MSG = "ERROR: ";
    private String msgDetails;

    private static final long serialVersionUID = 1L;

    TypeTwoException(String msgDetails){
        this.msgDetails = msgDetails;
    }

    public String getMessage(){
        return MSG + msgDetails + "\n";
    }

}
