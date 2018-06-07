package filesprocessing.filters;
import java.io.File;

/**
 * Filter to check if file length is between the 2 given values.
 */
public class Between implements Filter {

	/* Data members */
	private boolean not;
	private double value_low;
	private double value_high;

	/**
	 * Constructor for the Between filter
	 * @param value1 The lower bound of the filter.
	 * @param value2 The upper bound of the filter.
	 * @param not boolean for negative filter
	 */
	public Between(double value1, double value2, boolean not) {
		this.not = not;
		value_low = value1*B_TO_KB;
		value_high = value2*B_TO_KB;
	}

	/**
	 * apply filter to the given File object
	 *
	 * @param file File to check
	 * @return True if file satisfies filter, false otherwise
	 */
	public Boolean checkFile(File file) {

		return (((file.length() >= value_low)&&(file.length() <= value_high)) != not);
	}
}
