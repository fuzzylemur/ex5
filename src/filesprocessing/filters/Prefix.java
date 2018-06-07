package filesprocessing.filters;
import java.io.File;

/**
 * Filter to check for a given file prefix
 */
public class Prefix implements Filter {

    /* Data members */
    private boolean not;
    private String value;

    /**
     * Constructor for the Prefix filter
     * @param value wanted file prefix
     * @param not boolean for negative filter
     */
    public Prefix(String value, boolean not) {
        this.not = not;
        this.value = value;
    }

    /**
     * Apply filter to the given File object
     *
     * @param file File to check
     * @return True if file satisfies filter, false otherwise
     */
    public Boolean applyFilter(File file) {

        return ((file.getName().startsWith(value) != not));
    }
}