package fileprocessing.filters;
import java.io.File;

public class Directory implements Filter {

    public Directory(){}

    public Boolean applyFilter(File file) {

        return (!file.isDirectory());
    }
}
