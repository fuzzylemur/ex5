package filesprocessing;

import java.io.*;
import java.util.ArrayList;

public class CommandParser {

    private final int NUM_LINES = 2;

    //private static CommandParser myInstance = new CommandParser();
    //public static CommandParser instance() { return myInstance; }
    public CommandParser() {}

    public ArrayList<Section> parseCommandFile(String commandFilePath) throws TypeTwoException {

        ArrayList<Section> sectionArray = new ArrayList<>();

        String[] tempLines = new String[NUM_LINES];
        int[] lineNumbers = new int[NUM_LINES];

        File commandFile = new File(commandFilePath);

        try (FileReader reader = new FileReader(commandFile);
             BufferedReader buffReader = new BufferedReader(reader);
             LineNumberReader lineReader = new LineNumberReader(buffReader)) {

            String line = lineReader.readLine();

            while (line != null) {

                if (line.equals("FILTER")) {
                    tempLines[0] = lineReader.readLine();
                    lineNumbers[0] = lineReader.getLineNumber();
                }
                else
                    throw new TypeTwoException("Bad sub-section format");

                line = lineReader.readLine();
                if (line.equals("ORDER")) {
                    line = lineReader.readLine();
                    if (line == null ||line.equals("FILTER"))
                        tempLines[1] = null;
                    else {
                        tempLines[1] = line;
                        lineNumbers[1] = lineReader.getLineNumber();
                        line = lineReader.readLine();
                    }
                } else
                    throw new TypeTwoException("Bad sub-section format");

                sectionArray.add(new Section(tempLines, lineNumbers));
                tempLines = new String[NUM_LINES];
                lineNumbers = new int[NUM_LINES];
            }
            return sectionArray;
        }

        catch (IOException e) {
            throw new TypeTwoException("Error accessing command file");
        }
        catch (NullPointerException e) {
            throw new TypeTwoException("Bad command file format");
        }
    }
}
