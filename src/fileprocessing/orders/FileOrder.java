package fileprocessing.orders;
import java.io.File;
import java.util.Comparator;

public interface FileOrder extends Comparator<File> {

    int compare(File file1, File file2);
}
