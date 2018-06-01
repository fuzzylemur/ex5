package fileprocessing;
import fileprocessing.filters.*;

public class FilterMaker {

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

	private static FilterMaker maker = new FilterMaker();

	private FilterMaker() {}

	public static FilterMaker instance() {
		return maker;
	}

	private double validateDouble(String doubleStr) throws TypeOneException {
		try {
			double value = Double.parseDouble(doubleStr);
			if (value < 0) throw new TypeOneException();
			return value;
		} catch (NumberFormatException exc) {
			throw new TypeOneException();
		}
	}

	private void validateNumArgs(int num, int length) throws TypeOneException {
		if (length != num) {
			throw new TypeOneException();
		}
	}

	private void betweenTest(double value1, double value2)throws TypeOneException{
		if (value1 > value2)
			throw new TypeOneException();
	}

	private boolean yesNoTest(String value)throws TypeOneException{
		if (value.equals("YES"))
			return true;
		else if (value.equals("NO"))
			return false;
		else
			throw new TypeOneException();
	}

	private String[] cutLastCell(String[] array) {

		int nLength = array.length - 1;
		String[] nArray = new String[nLength];
		System.arraycopy(array, 0, nArray, 0, nLength);
		return nArray;
	}

	public FileFilter makeFilter(Section section) throws TypeOneException {

		String[] split = section.getFilterInput().split(DELIMITER);
		boolean not = false;
		if (split[split.length - 1].equals("NOT")){
			if (split.length == 1) throw new TypeOneException();
			not = true;
			split = cutLastCell(split);
		}

		switch (split[0]) {

			case (GREATER_THAN): {
				validateNumArgs(2, split.length);
				double value = validateDouble(split[1]);
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
		}
		throw new TypeOneException();
	}
}

