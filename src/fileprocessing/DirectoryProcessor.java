package fileprocessing;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class DirectoryProcessor {

    CommandParser myParser;
    SectionProcessor myProccessor;
    ArrayList<Section> sectionArray;
    File[] fileList;
    String sourceDir;
    String commandFile;


    DirectoryProcessor(String sourceDir, String commandFile){
        myParser = CommandParser.instance();
        myProccessor = SectionProcessor.instance();
        this.sourceDir = sourceDir;
        this.commandFile = commandFile;
        sectionArray = new ArrayList<>();

        File source = new File(this.sourceDir);
        fileList = source.listFiles();

        getSectionArray();


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

            ArrayList<File> filteredList = section.getFilter().filterFiles(fileList);
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
