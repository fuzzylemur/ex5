package filesprocessing;

import filesprocessing.filters.Filter;
import filesprocessing.orders.*;

/*
 * Section object represent the different processing operations as defined in the command file, with
 * each Section having a desired Filtering operation and an Order for the output list of filtered files.
 * Section object holds the raw String input for this section's filter and order, together with their line
 * numbers (as read by CommandParser), which is then verified and translated to Filter/Order objects by
 * SectionProcessor.
 */
class Section {

	/* Data members */
	private String filterInput;
	private String orderInput;
	private boolean reverseOrder;
	private int[] lineNumbers;

	private Filter myFilter;
	private FileOrder myOrder;

	/*
	 * Constructor for the Section objects, receives input Strings for filter/order and their line numbers.
	 * @param inputLines String array for the requested filter ([0]) and order ([1])
	 * @param lineNumbers int array for the line numbers of the filter ([0]) and order ([1])
	 */
	Section(String[] inputLines, int[] lineNumbers) {

		this.filterInput = inputLines[0];
		this.orderInput = inputLines[1];
		this.lineNumbers = lineNumbers;
	}

	/* Getters */
	Filter getFilter() {return myFilter;}
	FileOrder getOrder() {return myOrder;}

	String getFilterInput() {return filterInput;}
	String getOrderInput() {return orderInput;}

	int getFilterLineNum() {return lineNumbers[0];}
	int getOrderLineNum() {return lineNumbers[1];}

	boolean getReverseOrder() {return reverseOrder;}


	/* Setters */
	void setFilter(Filter filter) {myFilter = filter;}
	void setOrder(FileOrder order) {myOrder = order;}
	void setReverseOrder(boolean value) {reverseOrder = value;}



}
