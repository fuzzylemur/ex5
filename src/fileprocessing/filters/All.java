package fileprocessing.filters;
import java.io.File;

public class All implements Filter {

    private boolean not;

    public All(boolean not) {
        this.not = not;
    }

    public Boolean applyFilter(File file) {

        return (!not);
    }
}
