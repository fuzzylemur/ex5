package filesprocessing;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import filesprocessing.filters.*;

public class DirectoryProcessor {

    private CommandParser myParser;
    private SectionProcessor myProcessor;

    private ArrayList<Section> sectionArray;
    private ArrayList<File> fileArray;

    private String sourceDir;
    private String commandFile;


    private DirectoryProcessor(String sourceDir, String commandFile){

        myParser = new CommandParser();
        myProcessor = SectionProcessor.instance();
        this.sourceDir = sourceDir;
        this.commandFile = commandFile;
        sectionArray = new ArrayList<>();

        try {
            loadFileList();
        }
        catch (NullPointerException e){
            System.exit(0);
        }
        getSectionArray();
    }

    private void loadFileList() throws NullPointerException {

        File source = new File(this.sourceDir);
        File[] fileList = source.listFiles();
        if (fileList == null) {
            throw new NullPointerException();
        }

        Filter dirFilter = new Directory();
        fileArray = new ArrayList<>(Arrays.asList(fileList));

        fileArray = dirFilter.filterFiles(fileArray);
    }

    private void getSectionArray(){
        try {
            sectionArray = myParser.parseCommandFile(commandFile);
        }
        catch (TypeTwoException ex){
            System.err.println(ex.getMessage());
        }
    }

    private void processSections(){

        for (Section section : sectionArray) {
            myProcessor.createSection(section);

            ArrayList<File> filteredList = section.getFilter().filterFiles(fileArray);
            filteredList.sort(section.getOrder());
            printFileList(filteredList, section.getReverseOrder());
        }
    }

    private void printFileList(ArrayList<File> sortedList, boolean reverse) {

        if (reverse)
            Collections.reverse(sortedList);

        for (File file : sortedList)
                System.out.println(file.getName());
    }

    private static void validateArgs(String[] args) throws TypeTwoException{
        if (args.length !=2){
            throw new TypeTwoException("Invalid number of arguments");
        }
    }

    public static void main(String[] args) {
        try {
            validateArgs(args);
        } catch (TypeTwoException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        DirectoryProcessor dirProcessor = new DirectoryProcessor(args[0], args[1]);
        dirProcessor.processSections();

    }

}
