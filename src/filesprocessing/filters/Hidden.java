package filesprocessing.filters;
import java.io.File;

/**
 * Filter to check if File is bigger then a certain size
 */
public class Hidden implements Filter {

	/* Data members */
	private boolean not;
    private boolean value;

    /**
     * Constructor for the Hidden filter
     * @param value true to keep the hidden files, false otherwise
     * @param not boolean for negative filter
     */
    public Hidden(Boolean value, boolean not) {
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

        return ((file.isHidden() == value) != not);
    }
}
