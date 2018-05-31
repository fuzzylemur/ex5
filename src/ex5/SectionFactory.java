package ex5;


import java.util.ArrayList;

public class SectionFactory {

    private ArrayList<Section> sectionList = new ArrayList<>();

    public SectionFactory(String[][] lines){

        for (String[] section: lines) {
            Filter filter = createFilter(section[0]);
            Order order = createOrder(section[1]);
            sectionList.add(new Section(filter,order));
        }
    }

    private double validateDouble(String[] split) throws TypeTwoException {       // TODO verify stuff
        try {
            double value = Double.parseDouble(split[1]);
            return value;
        } catch (NumberFormatException exc) {
            throw new TypeTwoException();
        }
    }

    private void validateNumArgs(int num, int length) throws TypeTwoException {
        if (length != num) {throw new TypeTwoException();}

    }

    private String validateString(String string){}

    private Boolean validateBoolean(String string){}

    private createOrder(String orderStr) throws TypeTwoException {

        // check reverse
        // slice array, check for length = 1
        // cases
    }

    private createFilter(String filterStr) throws TypeTwoException {

        String[] split = filterStr.split("#");
        boolean not = false;
        if(split[split.length-1].equals("NOT")){
            not = true;                                 // TODO slice list, choeck if only not
        }

        switch(split[0]) {

            case (GREATER_THAN): {
                validateNumArgs(2,split.length);
                double value = validateDouble(split);
                return new GreaterThan(value, not);
            }
            case (SMALLER_THAN): {
                validateNumArgs(2,split.length);
                double value = validateDouble(split);
                return new SmallerThan(value, not);
            }
            case (BETWEEN): {
                validateNumArgs(3,split.length);
                double value1 = validateDouble(split[1]);
                double value2 = validateDouble(split[2]);
                return new Between(value1, value2, not);
            }
            case (FILE): {
                validateNumArgs(2,split.length);
                String value = validateString(split[1]);
                return new File(value, not);
            }
            case (CONTAINS): {
                validateNumArgs(2,split.length);
                String value = validateString(split[1]);
                return new Contains(value, not);
            }
            case (PREFIX): {
                validateNumArgs(2,split.length);
                String value = validateString(split[1]);
                return new Prefix(value, not);
            }
            case (SUFFIX): {
                validateNumArgs(2,split.length);
                String value = validateString(split[1]);
                return new Suffix(value, not);
            }
            case (WRITABLE): {
                validateNumArgs(2,split.length);
                boolean value = validateBoolean(split[1]);
                return new Writable(value, not);
            }
            case (EXECUTABLE): {
                validateNumArgs(2,split.length);
                boolean value = validateBoolean(split[1]);
                return new Exectuable(value, not);
            }
            case (HIDDEN): {
                validateNumArgs(2,split.length);
                boolean value = validateBoolean(split[1]);
                return new Hidden(value, not);
            }
            case (ALL): {                                           // TODO not?
                validateNumArgs(1,split.length);
                return new All(not);
            }
            throw new TypeTwoException();
        }
    }
}
