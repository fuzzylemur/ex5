package fileprocessing;

import java.io.*;
import java.util.ArrayList;

public class CommandParser {

    private static final int NUM_LINES = 2;

    private static CommandParser ourInstance;

    public static CommandParser instance() {
        return ourInstance;
    }

    private CommandParser() {
        ourInstance = new CommandParser();
    }

    public ArrayList<Section> parseCommandFile(String commandFilePath) throws TypeTwoException {

        ArrayList<Section> sectionArray = new ArrayList<>();

        String[] tempLines = new String[NUM_LINES];
        int[] lineNumbers = new int[NUM_LINES];

        File commandFile = new File(commandFilePath);                   //TODO handle bad filepath?

        try (FileReader reader = new FileReader(commandFile);
             BufferedReader buffReader = new BufferedReader(reader);
             LineNumberReader lineReader = new LineNumberReader(buffReader)) {

            String line = lineReader.readLine();

            while (line != null) {

                if (line.equals("FILTER")) {
                    tempLines[0] = lineReader.readLine();           // read next line
                    lineNumbers[0] = lineReader.getLineNumber();
                }
                else
                    throw new TypeTwoException();

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
                    throw new TypeTwoException();

                sectionArray.add(new Section(tempLines, lineNumbers));
                tempLines = new String[NUM_LINES];
                lineNumbers = new int[NUM_LINES];
            }
            return sectionArray;
        }

        catch (IOException | NullPointerException e) {
            throw new TypeTwoException();
        }
    }

    public static void main(String[] args) {
        CommandParser parser = new CommandParser();
        ArrayList<Section> sectionArray = new ArrayList<>();

        try {
            sectionArray = parser.parseCommandFile("/Users/giladam/IdeaProjects/ex5/src/ex5/cmd");
        }
        catch (TypeTwoException e){
            System.out.println("ERROR");
        }
    }
}
