package filesprocessing.filters;
import java.io.File;

/**
 * Filter to check if file is a directory.
 */
public class Directory implements Filter {

	/**
	 * Constructor for the Directory filter
	 */
	public Directory(){}

	/**
	 * apply filter to the given File object
	 *
	 * @param file File to check
	 * @return True if file satisfies filter, false otherwise
	 */
	public Boolean applyFilter(File file) {

		return (!file.isDirectory());
	}
}
