package fileprocessing.filters;
import java.io.File;

public class SmallerThan implements FileFilter{

    private boolean not;
    private double value;

    public SmallerThan(double value, boolean not) {
        this.not = not;
        this.value = value/B_TO_KB;
    }

    public Boolean applyFilter(File file) {

        return ((file.length() <= value) != not);
    }
}