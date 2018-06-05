package filesprocessing.filters;
import java.io.File;

public class Suffix implements Filter {

    private boolean not;
    private String value;

    public Suffix(String value, boolean not) {
        this.not = not;
        this.value = value;
    }

    public Boolean applyFilter(File file) {

        return ((file.getName().endsWith(value) != not));
    }
}