package filesprocessing.filters;
import java.io.File;

public class GreaterThan implements Filter {

	/* Data members */
	private boolean not;
    private double value;

    /**
     * Constructor for the GreaterThan filter
     * @param value minimal file size in KB (not inclusive)
     * @param not boolean for negative filter
     */
    public GreaterThan(double value, boolean not) {
        this.not = not;
        this.value = value * B_TO_KB;
    }

    /**
     * Apply filter to the given File object
     *
     * @param file File to check
     * @return True if file satisfies filter, false otherwise
     */
    public Boolean checkFile(File file) {

        return ((file.length() > value) != not);
    }
}
