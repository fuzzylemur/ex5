package fileprocessing.filters;
import java.io.File;

public class Writable implements Filter {

    private boolean not;
    private boolean value;

    public Writable(Boolean value, boolean not) {
        this.not = not;
        this.value = value;
    }

    public Boolean applyFilter(File file) {

        return ((file.canWrite() == value) != not);
    }
}


