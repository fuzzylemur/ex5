package fileprocessing.orders;
import java.io.File;

public class SizeOrder implements FileOrder {

    private static SizeOrder myInstance;

    private SizeOrder(){
        myInstance = new SizeOrder();
    }
    public static SizeOrder instance(){return myInstance;}

    public int compare(File file1, File file2){
        long delta = file1.length() - (file2.length());

        if (delta == 0){
            return file1.getName().compareTo(file2.getName());
        }

        else
            return (int)delta;
    }
}
