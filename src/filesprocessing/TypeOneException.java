package filesprocessing;

public class TypeOneException extends Exception {

    private static final String MSG = "Warning in line ";
    private static final long serialVersionUID = 1L;

    public String getMessage(){
        return MSG;
    }

}
