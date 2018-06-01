package fileprocessing.filters;
import java.io.File;

public class Between implements FileFilter{

    private boolean not;
    private double value_low;
    private double value_high;

    public Between(double value1, double value2, boolean not) {
        this.not = not;
        value_low = value1*B_TO_KB;
        value_high = value2*B_TO_KB;
    }

    public Boolean applyFilter(File file) {

        return (((file.length() >= value_low)&&(file.length() <= value_high)) != not);
    }
}
