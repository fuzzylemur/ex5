package filesprocessing.filters;
import java.io.File;

/**
 * Filter to check for a given file suffix
 */
public class Suffix implements Filter {

    /* Data members */
    private boolean not;
    private String value;

    /**
     * Constructor for the Suffix filter
     * @param value wanted file suffix
     * @param not boolean for negative filter
     */
    public Suffix(String value, boolean not) {
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

        return ((file.getName().endsWith(value) != not));
    }
}