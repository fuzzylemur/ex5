package filesprocessing.orders;
import java.io.File;

public class AlphaOrder implements FileOrder {

    private static AlphaOrder myInstance = new AlphaOrder();

    private AlphaOrder(){}
    public static AlphaOrder instance(){return myInstance;}

    public int compare(File file1, File file2){
        return file1.getName().compareTo(file2.getName());
    }
}
