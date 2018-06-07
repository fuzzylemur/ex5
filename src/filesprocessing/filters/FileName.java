package filesprocessing.filters;
import java.io.File;

/**
 * Filter to check for a given file name
 */
public class FileName implements Filter {

    /* Data members */
    private boolean not;
    private String name;

    /**
     * Constructor for the FileName filter
     * @param value file name to check for
     * @param not boolean for negative filter
     */
    public FileName(String value, boolean not) {
        this.not = not;
        this.name = value;
    }

    /**
     * Apply filter to the given File object
     *
     * @param file File to check
     * @return True if file satisfies filter, false otherwise
     */
    public Boolean applyFilter(File file) {

        return ((file.getName().equals(name) != not));
    }
}
