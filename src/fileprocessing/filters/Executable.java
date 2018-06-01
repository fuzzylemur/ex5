package fileprocessing.filters;
import java.io.File;

public class Executable implements FileFilter{

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
