package fileprocessing;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import fileprocessing.filters.*;

public class DirectoryProcessor {

    private CommandParser myParser;
    private SectionProcessor myProccessor;

    private ArrayList<Section> sectionArray;
    private ArrayList<File> fileArray;

    private String sourceDir;
    private String commandFile;


    private DirectoryProcessor(String sourceDir, String commandFile){

        myParser = CommandParser.instance();
        myProccessor = SectionProcessor.instance();
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
        catch (TypeTwoException e){
            System.err.println(e.getMessage());
        }
    }

    private void processSections(){

        for (Section section : sectionArray) {
            myProccessor.createSection(section);

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

    public static void main(String[] args) {

        // validate args

        DirectoryProcessor dirProcessor = new DirectoryProcessor(args[0], args[1]);
        dirProcessor.processSections();

    }

}
