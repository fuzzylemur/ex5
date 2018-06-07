package filesprocessing.filters;
import java.io.File;

/**
 * Filter that accepts all files.
 */
public class All implements Filter {

	/* Data members */
	private boolean not;

	/**
	 * Constructor for the All filter
	 *
	 * @param not boolean for negative filter
	 */
	public All(boolean not) {
		this.not = not;
	}

	/**
	 * apply filter to the given File object
	 *
	 * @param file File to check
	 * @return True for all files.
	 */
	public Boolean checkFile(File file) {

		return (!not);
	}
}
