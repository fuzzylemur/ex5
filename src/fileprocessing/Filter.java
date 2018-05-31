package fileprocessing;
import java.util.Iterator;

public enum Filter {


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

    Filter(String stringRep) {
        this.stringRep = stringRep;
    }

    String stringRep(){
        return stringRep;
    }

}
