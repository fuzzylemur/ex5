package filesprocessing;

import java.io.*;
import java.util.ArrayList;

public class CommandParser {

    private static final int NUM_LINES = 2;

    private static final String FILTER_LINE = "FILTER";
    private static final String ORDER_LINE = "ORDER";

    private static final String MSG_SUB_FORMAT = "Bad sub-section format";
    private static final String MSG_EMPTY = "Empty command file";
    private static final String MSG_ERR_ACCESS = "Error accessing command file";

    private static CommandParser myInstance = new CommandParser();
    public static CommandParser instance() { return myInstance; }
    private CommandParser() {}

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

                if (line.equals(FILTER_LINE)) {
                    tempLines[0] = lineReader.readLine();
                    lineNumbers[0] = lineReader.getLineNumber();
                }
                else
                    throw new TypeTwoException(MSG_SUB_FORMAT);         // no FILTER at section start

                line = lineReader.readLine();
                if (line.equals(ORDER_LINE)) {
                    line = lineReader.readLine();
                    if (line == null ||line.equals(FILTER_LINE))
                        tempLines[1] = null;
                    else {
                        tempLines[1] = line;
                        lineNumbers[1] = lineReader.getLineNumber();
                        line = lineReader.readLine();
                    }
                } else
                    throw new TypeTwoException(MSG_SUB_FORMAT);         // no ORDER sub-section

                sectionArray.add(new Section(tempLines, lineNumbers));
                tempLines = new String[NUM_LINES];
                lineNumbers = new int[NUM_LINES];
            }
            return sectionArray;
        }

        catch (IOException e) {                             // inaccessible command file
            throw new TypeTwoException(MSG_ERR_ACCESS);
        }
        catch (NullPointerException e) {                    // empty command file
            throw new TypeTwoException(MSG_EMPTY);
        }
    }
}
