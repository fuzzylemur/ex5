package filesprocessing.filters;
import java.io.File;

/**
 * Filter to check if file name contains the given value(String).
 */
public class Contains implements Filter {

	/* Data members */
	private boolean not;
	private String value;

	/**
	 * Constructor for the Contains filter
	 * @param value the value to look for in the file name.
	 * @param not boolean for negative filter
	 */
	public Contains(String value, boolean not) {
		this.not = not;
		this.value = value;
	}

	/**
	 * apply filter to the given File object
	 *
	 * @param file File to check
	 * @return True if file satisfies filter, false otherwise
	 */
	public Boolean checkFile(File file) {

		return ((file.getName().contains(value) != not));
	}
}