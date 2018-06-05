package filesprocessing.filters;
import java.io.File;

public class Hidden implements Filter {

    private boolean not;
    private boolean value;

    public Hidden(Boolean value, boolean not) {
        this.not = not;
        this.value = value;
    }

    public Boolean applyFilter(File file) {

        return ((file.isHidden() == value) != not);
    }
}
