package ex5;
import ex5.filters.GreaterThan;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class DirectoryProcessor {

    private static final String GREATER_THAN = "greater_than";
    private static final String SMALLER_THAN = "smaller_than";
    private static final String BETWEEN = "between";
    private static final String FILE = "file";
    private static final String CONTAINS = "contains";
    private static final String PREFIX = "prefix";
    private static final String SUFFIX = "suffix";
    private static final String WRITABLE = "writable";
    private static final String EXECUTABLE = "executable";
    private static final String HIDDEN = "hidden";
    private static final String ALL = "all";

    private File[] fileList;
    private ArrayList<Section> sectionList = new ArrayList<>();

    DirectoryProcessor(String sourceDirPath, String commandFilePath) {

        File source = new File(sourceDirPath);
        fileList = source.listFiles();
        parseCommandFile(commandFilePath);
    }

    private void parseCommandFile(String commandFilePath) throws TypeTwoException {
        ArrayList<String[]> linesForFactory= new ArrayList<>();

        File commandFile = new File(commandFilePath);

        try (FileReader reader = new FileReader(commandFile);
             BufferedReader buffReader = new BufferedReader(reader)) {

            int counter = 0;
            String[] tempLines = new String[4];
            while (buffReader.readLine() != null) {
                tempLines[counter] = buffReader.readLine();
                counter ++;
                if (counter == 4) {
                   validateSection(tempLines);
                   linesForFactory.add(new String[]{tempLines[1], tempLines[3]});
                   counter = 0;
                   tempLines = new String[4];
                }
            }
        }

        catch (IOException ioe) {
            throw new TypeTwoException();
        }
    }

    private void validateSection(String[] lines) throws TypeTwoException {

        if (lines[0] == "FILTER" && lines[2] == "ORDER") {

            validateFilter(lines[1]);
        }
    }

    public static void main(String[] args) {

        String sourceDir = args[0];
        String commandFile = args[1];

        DirectoryProcessor processor = new DirectoryProcessor(sourceDir, commandFile);
    }
}
