package filesprocessing;

import java.io.*;
import java.util.ArrayList;

/**
 * CommandParser object receives the path to the command file and parses it to get the desired
 * filter and order for each section to be processed by DirectoryProcessor. If there is a problem
 * with the command file (bad format, no access) a TypeTwoException will be thrown from the method
 * parseCommandFile.
 */
public class CommandParser {

	/* Class Constants */
	private static final int NUM_LINES = 2;
	private static final String FILTER_LINE = "FILTER";
	private static final String ORDER_LINE = "ORDER";
	private static final String MSG_SUB_FORMAT = "Bad sub-section format";
	private static final String MSG_EMPTY = "Empty command file";
	private static final String MSG_ERR_ACCESS = "Error accessing command file";

	/* Singleton instance */
	private static CommandParser myInstance = new CommandParser();

	/* Default singleton constructor */
	private CommandParser() {}

	/**
	 * singleton method for getting the instance of this object
	 * @return CommandParser instance
	 */
	public static CommandParser instance() { return myInstance; }

	/**
	 * The method for parsing the command file in the given path, to an ArrayList of Section
	 * objects representing the desired processing operations. Utilises java's LineNumberReader
	 * to read the command file.
	 *
	 * @param commandFilePath String path to command file
	 * @return ArrayList of Section objects parsed from the command file
	 * @throws TypeTwoException in case of bad file format, empty or inaccessible command file
	 */
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

				sectionArray.add(new Section(tempLines, lineNumbers));  // create new Section object
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
