package ex5;

public class Section {

    private Filter myFilter;
    private Order myOrder;

    Section(Filter filter, Order order) {
        this.myFilter = filter;
        this.myOrder = order;
    }

    private File[] applyFilter(File[] fileList){
       return filter.applyFilter(fileList);
    }

    private String[] applyOrder(File[] filteredList){
        return order.applyOrder(filteredList);
    }

    public void printOutput(File[] fileList){

        for (String line : applyOrder(applyFilter(fileList))) {
            print(line);
        }

    }
}
