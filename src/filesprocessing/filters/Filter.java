package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;

/**
 * Interface for the different Filters to be applied to the files.
 */
public interface Filter {

	/* Class Constants */
    int B_TO_KB = 1024;

	/**
	 * method to check if a File satisfies a Filter
	 * @param file File to check
	 * @return True if satisfies, false otherwise
	 */
	Boolean checkFile(File file);

	/**
	 * Default method for applying the filter to an ArrayList of Files, getting a filtered ArrayList
	 * of the Files that satisfied the filter.
	 * @param fileArray Files to check
	 * @return filtered ArrayList of files
	 */
    default ArrayList<File> filterFiles(ArrayList<File> fileArray){

        ArrayList<File> filteredList = new ArrayList<>();

        for (File file : fileArray) {
            if (checkFile(file))
                filteredList.add(file);
        }
        return filteredList;
    }
}
