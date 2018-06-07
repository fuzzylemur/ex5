package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;

public interface Filter {

    int B_TO_KB = 1024;

    Boolean applyFilter(File file);

    default ArrayList<File> filterFiles(ArrayList<File> fileArray){

        ArrayList<File> filteredList = new ArrayList<>();

        for (File file : fileArray) {
            if (applyFilter(file))
                filteredList.add(file);
        }
        return filteredList;
    }
}
