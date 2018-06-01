package fileprocessing.filters;
import java.io.File;

public class Executable implements Filter {

    private boolean not;
    private boolean value;

    public Executable(Boolean value, boolean not) {
        this.not = not;
        this.value = value;
    }

    public Boolean applyFilter(File file) {

        return ((file.canExecute() == value) != not);
    }
}
