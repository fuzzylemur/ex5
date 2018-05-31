package fileprocessing.orders;
import java.io.File;

public class AlphaOrder implements FileOrder {

    public int compare(File file1, File file2){
        return file1.getName().compareTo(file2.getName());
    }
}
