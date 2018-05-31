package fileprocessing.orders;
import java.io.File;

public class TypeOrder implements FileOrder {

    private static TypeOrder myInstance;

    private TypeOrder(){
        myInstance = new TypeOrder();
    }
    public static TypeOrder instance(){return myInstance;}

    public int compare(File file1, File file2){

        String[] temp = file1.getName().split(".");
        String type1 = temp[temp.length-1];
        temp = file2.getName().split(".");
        String type2 = temp[temp.length-1];

        if (type1.equals(type2)){
            return file1.getName().compareTo(file2.getName());
        }

        else
            return type1.compareTo(type2);
    }
}