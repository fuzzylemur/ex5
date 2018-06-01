package fileprocessing.filters;
import java.io.File;

public class Contains implements FileFilter{

    private boolean not;
    private String value;

    public Contains(String value, boolean not) {
        this.not = not;
        this.value = value;
    }

    public Boolean applyFilter(File file) {

        return ((file.getName().contains(value) != not));
    }
}