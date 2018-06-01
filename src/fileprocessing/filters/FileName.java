package fileprocessing.filters;
import java.io.File;

public class FileName implements Filter {

    private boolean not;
    private String name;

    public FileName(String name, boolean not) {
        this.not = not;
        this.name = name;
    }

    public Boolean applyFilter(File file) {

        return ((file.getName().equals(name) != not));
    }
}
