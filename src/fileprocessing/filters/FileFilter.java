package fileprocessing.filters;
import java.io.File;
import java.util.ArrayList;

public interface FileFilter {

    int B_TO_KB = 1024;

    Boolean applyFilter(File file);

    default ArrayList<File> filterFiles(File[] fileList){

        ArrayList<File> filteredList = new ArrayList<>();

        for (File file : fileList) {
            if (applyFilter(file))
                filteredList.add(file);
        }
        return filteredList;
    }
}
