package ex5.filters;
import java.io.File;

public interface FileFilter {

    int B_TO_KB = 1024;

    Boolean applyFilter(File file);
}
