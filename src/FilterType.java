
public enum FilterType {


    GREATER_THAN ("greater_than"),
    SMALLER_THAN ("smaller_than"),
    BETWEEN ("between"),
    FILE ("file"),
    CONTAINS ("contains"),
    PREFIX ("prefix"),
    SUFFIX ("suffix"),
    WRITABLE ("writable"),
    EXECUTABLE ("executable"),
    HIDDEN ("hidden"),
    ALL ("all");

    String stringRep;

    FilterType(String stringRep) {
        this.stringRep = stringRep;
    }

    public String stringRep(){
        return stringRep;
    }

}
