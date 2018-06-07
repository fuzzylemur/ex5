package filesprocessing.filters;
import java.io.File;

/**
 * Filter to check if File is smaller then a certain size
 */
public class SmallerThan implements Filter {

    /* Data members */
    private boolean not;
    private double value;

    /**
     * Constructor for the SmallerThan filter
     * @param value maximal file size in KB (not inclusive)
     * @param not boolean for negative filter
     */
    public SmallerThan(double value, boolean not) {
        this.not = not;
        this.value = value * B_TO_KB;
    }

    /**
     * Apply filter to the given File object
     *
     * @param file File to check
     * @return True if file satisfies filter, false otherwise
     */
    public Boolean applyFilter(File file) {

        return ((file.length() < value) != not);
    }
}