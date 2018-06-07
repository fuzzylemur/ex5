package filesprocessing;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import filesprocessing.filters.*;

/**
 * Main object for the file processing program.
 * Receives the user input and handles the program's inner objects to process the required directory
 * with the operations defined in the command file, and print the output. Utilizes java.io.File for
 * referencing the files in the directory.
 */
public class DirectoryProcessor {

    /* Class Constants */
    private static final String MSG_NUM_ARGS = "Invalid number of arguments";

    /* Data members */
    private CommandParser myParser;
    private SectionProcessor myProcessor;

    private ArrayList<Section> sectionArray;
    private ArrayList<File> fileArray;

    private String sourceDir;
    private String commandFile;

    /**
     * Constructor, receives the input paths for the directory to be processed and the command file.
     * @param sourceDir String path to directory
     * @param commandFile String path to command file
     */
    private DirectoryProcessor(String sourceDir, String commandFile){

        myParser = CommandParser.instance();
        myProcessor = SectionProcessor.instance();
        this.sourceDir = sourceDir;
        this.commandFile = commandFile;
        sectionArray = new ArrayList<>();

        loadFileList();
    }

    /*
     * Private method for loading file list from desired the directory
     */
    private void loadFileList() {

        File source = new File(this.sourceDir);
        File[] fileList = source.listFiles();

        if (fileList == null) {
            System.exit(0);             // in case of no files in directory
        }

        Filter dirFilter = new Directory();                     // get only the files in the directory
        fileArray = new ArrayList<>(Arrays.asList(fileList));
        fileArray = dirFilter.filterFiles(fileArray);
    }

    /*
     * Private method for parsing the command file and loading the ArrayList of Sections (by calling
     * the CommandParser object)
     *
     * @throws TypeTwoException if there is a problem with the command file
     */
    private void getSectionArray() throws TypeTwoException{

        sectionArray = myParser.parseCommandFile(commandFile);
    }

    /*
     * Private method for processing each section in the ArrayList of Sections (setting the Filter/Order
     * objects, applying them to the file array and printing the output)
     */
    private void processSections(){

        for (Section section : sectionArray) {
            myProcessor.createSection(section);

            ArrayList<File> filteredList = section.getFilter().filterFiles(fileArray);
            filteredList.sort(section.getOrder());
            printFileList(filteredList, section.getReverseOrder());
        }
    }

	/*
	 * Private method for printing the output file list (filtered and ordered)
	 *
	 * @param sortedList The sorted ArrayList of files to be printed
	 * @param reverse true if order needs to be reversed
	 */
	private void printFileList(ArrayList<File> sortedList, boolean reverse) {

        if (reverse)
            Collections.reverse(sortedList);

        for (File file : sortedList)
                System.out.println(file.getName());
    }

	/*
	 * Private method for validating the user input for directory and command file.
	 *
	 * @param args user input
	 * @throws TypeTwoException if there are more than two args
	 */
	private static void validateArgs(String[] args) throws TypeTwoException{

        if (args.length !=2){
            throw new TypeTwoException(MSG_NUM_ARGS);
        }
    }

	/**
	 * Main method for DirectoryProcessor.
	 * @param args path to directory ([0]) and path to command file ([1])
	 */
	public static void main(String[] args) {

        try {
            validateArgs(args);
        } catch (TypeTwoException ex){
            System.err.println(ex.getMessage());
            return;
        }
        DirectoryProcessor dirProcessor = new DirectoryProcessor(args[0], args[1]);

        try {
            dirProcessor.getSectionArray();
        } catch (TypeTwoException ex) {
            System.err.println(ex.getMessage());
            return;
        }
        dirProcessor.processSections();
    }

}
