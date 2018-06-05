package filesprocessing.filters;
import java.io.File;

public class Prefix implements Filter {

    private boolean not;
    private String value;

    public Prefix(String value, boolean not) {
        this.not = not;
        this.value = value;
    }

    public Boolean applyFilter(File file) {

        return ((file.getName().startsWith(value) != not));
    }
}