package fileprocessing;

import java.io.File;
import java.util.ArrayList;

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

        File source = new File(this.sourceDir);
        fileList = source.listFiles();

        getSectionArray();

    }

    private void getSectionArray(){
        try {
            sectionArray = myParser.parseCommandFile(sourceDir);
        }
        catch (TypeTwoException e){

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

    private void printFileList(ArrayList<File> filteredList, boolean reverse) {

        if (!reverse) {
            for (File file : filteredList) {
                System.out.println(file.getName());
            }

        } else {
            for (int i = filteredList.size()-1; i>=0; i--){
                System.out.println(fileList[i].getName());
            }
        }
    }

    public static void main(String[] args) {

        DirectoryProcessor dirProcessor = new DirectoryProcessor(args[0], args[1]);
        dirProcessor.processSections();

    }

}
