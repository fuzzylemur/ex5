package fileprocessing;
import fileprocessing.filters.*;
import fileprocessing.orders.*;

public class Section {

    private String filterInput;
    private String orderInput;
    private boolean reverseOrder;
    private int[] lineNumbers;

    private FileFilter myFilter;
    private FileOrder myOrder;


    Section(String[] inputLines, int[] lineNumbers) {
        this.filterInput = inputLines[0];
        this.orderInput = inputLines[1];
        this.lineNumbers = lineNumbers;
    }

    public FileFilter getFilter() {
        return myFilter;
    }

    public FileOrder getOrder() {
        return myOrder;
    }

    public void setFilter(FileFilter filter) {
        myFilter = filter;
    }

    public void setOrder(FileOrder order) {
        myOrder = order;
    }

    public void setReverseOrder(boolean value) {reverseOrder = value;}

    public int getFilterLineNum() {
        return lineNumbers[0];
    }

    public int getOrderLineNum() {
        return lineNumbers[1];
    }

    public boolean getReverseOrder() { return reverseOrder; }

    public String getFilterInput() {
        return filterInput;
    }

    public String getOrderInput() {
        return orderInput;
    }
}
