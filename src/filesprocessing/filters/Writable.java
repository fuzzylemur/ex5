package filesprocessing.filters;
import java.io.File;

/**
 * Filter to check if file is writable
 */
public class Writable implements Filter {

	/* Data members */
    private boolean not;
    private boolean value;

	/**
	 * Constructor for the Writable filter
	 * @param value true to keep the writable files, false otherwise
	 * @param not boolean for negative filter
	 */
	public Writable(Boolean value, boolean not) {
        this.not = not;
        this.value = value;
    }

	/**
	 * Apply filter to the given File object
	 *
	 * @param file File to check
	 * @return True if file satisfies filter, false otherwise
	 */
	public Boolean checkFile(File file) {

        return ((file.canWrite() == value) != not);
    }
}


