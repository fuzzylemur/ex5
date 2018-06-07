package filesprocessing;

import filesprocessing.filters.*;
import filesprocessing.filters.Filter;

/*
 * A singleton class, responisibale for validating and creating filter instances.
 * It's main method, makeFilter(Section section), acts as a filter factory by
 * analyzing the String input and creating the apropriate filter.
 *
 * @authors Gil Adam, Jonathan Zedaka
 */
class FilterMaker {

	//-------------------------Class Constants-------------------------------//
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

	private final static String DELIMITER = "#";
	private final static String YES_STR = "YES";
	private final static String NO_STR = "NO";
	private final static String NOT_STR = "NOT";

	private static FilterMaker myInstance = new FilterMaker();

	/* A private defualt constractor .*/
	private FilterMaker() {}

	/** @return the class one and only instance*/
	public static FilterMaker instance() {return myInstance;}

	/*
	 * This private method is responsibale for cheking whether a given string can be converted to double.
	 * Throws TypeOneException if the conversion fails.
	 *
	 * @param doubleStr a string to validate if it can be converted to double.
	 * @return double if it can, TypeOneException otherwise.
	 */
	private double validateDouble(String doubleStr) throws TypeOneException {

		try {
			double value = Double.parseDouble(doubleStr);
			if (value < 0)
				throw new TypeOneException();
			return value;

		} catch (NumberFormatException exc) { // Throws TypeOneException if the double parse failed.
			throw new TypeOneException();
		}
	}

	/*
	 * Validates that the given array length equals the wantes num of args represent by the numargs integer.
	 * Throws TypeOneException if there is no match.
	 *
	 * @param numargs the expectes length of the array.
	 * @param length the length og the args array.
	 */
	private void validateNumArgs(int numargs, int length) throws TypeOneException {

		if (length != numargs) {
			throw new TypeOneException();
		}
	}

	/*
	 * Validates that the value1 is strictly greater then value2.
	 * Throws TypeOneException otherwise.
	 *
	 * @param value1 The first double value.
	 * @param value2 The seconed double value.
	 */
	private void betweenTest(double value1, double value2)throws TypeOneException{

		if (value1 > value2)
			throw new TypeOneException();
	}

	/*
	 * Checks if the given string value is exactly "YES" or "NO".
	 * Throws TypeOneException otherwise.
	 *
	 * @param value the string to test.
	 */
	private boolean yesNoTest(String value)throws TypeOneException{

		if (value.equals(YES_STR))
			return true;
		else if (value.equals(NO_STR))
			return false;
		else
			throw new TypeOneException();
	}

	/*
	 * Cuts the last cell of the given array.
	 *
	 * @param array the array to cut.
	 * @return the new array without the last cell.
	 */
	private String[] cutLastCell(String[] array) {

		int nLength = array.length - 1;
		String[] nArray = new String[nLength];
		System.arraycopy(array, 0, nArray, 0, nLength);
		return nArray;
	}

	/**
	 * The Main function of this class. Receives a section instance and
	 * process its Filter input data (from the command file) in order to validate
	 * and create the apropriate filter type.
	 * The function throws TypeOneException if the filter data was corrupted in any way.
	 *
	 * @param section The section to create the filter to.
	 * @return a filter instance according to the command file data.
	 */
	public Filter makeFilter(Section section) throws TypeOneException {

		String[] split = section.getFilterInput().split(DELIMITER);
		boolean not = false;
		if (split[split.length - 1].equals(NOT_STR)){ // Cheks if the filter data contains NOT in the last cell.
			if (split.length == 1) throw new TypeOneException(); // In case the filter data is only "NOT".
			not = true;
			split = cutLastCell(split); // Cut the last cell of the data array before contiuing to further testing.
		}

		switch (split[0]) { // Here, we make sure that the filter type is one of the legal constant types.
							// We also make sure there are no extra data like #SMALLER_THAN#8#9#76#7
			case (GREATER_THAN): {
				validateNumArgs(2, split.length);
				double value = validateDouble(split[1]); // validates that the double is a real positive double.
				return new GreaterThan(value, not);
			}
			case (SMALLER_THAN): {
				validateNumArgs(2, split.length);
				double value = validateDouble(split[1]);
				return new SmallerThan(value, not);
			}
			case (BETWEEN): {
				validateNumArgs(3, split.length);
				double value1 = validateDouble(split[1]);
				double value2 = validateDouble(split[2]);
				betweenTest(value1,value2);
				return new Between(value1, value2, not);
			}
			case (FILE): {
				validateNumArgs(2, split.length);
				return new FileName(split[1], not);
			}
			case (CONTAINS): {
				validateNumArgs(2, split.length);
				return new Contains(split[1], not);
			}
			case (PREFIX): {
				validateNumArgs(2, split.length);
				return new Prefix(split[1], not);
			}
			case (SUFFIX): {
				validateNumArgs(2, split.length);
				return new Suffix(split[1], not);
			}
			case (WRITABLE): {
				validateNumArgs(2, split.length);
				boolean value = yesNoTest(split[1]);
				return new Writable(value, not);
			}
			case (EXECUTABLE): {
				validateNumArgs(2, split.length);
				boolean value = yesNoTest(split[1]);
				return new Executable(value, not);
			}
			case (HIDDEN): {
				validateNumArgs(2, split.length);
				boolean value = yesNoTest(split[1]);
				return new Hidden(value, not);
			}
			case (ALL): {
				validateNumArgs(1, split.length);
				return new All(not);
			}
		} // If we didnt fins any match we throw TypeOneException.
		throw new TypeOneException();
	}
}

