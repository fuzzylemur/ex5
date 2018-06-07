package filesprocessing;

import filesprocessing.filters.*;
import filesprocessing.orders.*;

import java.io.File;
import java.util.Comparator;

/*
 * A singleton class, responsible for processing and creating a section instance.
 * This single object holds a FilterMaker and OrderMaker objects and use them in order to
 * validate and create the section's Filter and order.
 * The SectionProcessor is also responsible for handling all type one Exceptions.
 *
 * @authors Gil Adam, Jonathan Zedaka
 */
class SectionProcessor {

	//-------------------------Class Constants-------------------------------//
	private static final Filter DEFAULT_FILTER = new All(false);
	private static final Comparator<File> DEFAULT_ORDER = AlphaOrder.instance();
	private static SectionProcessor myInstance = new SectionProcessor();

	//-------------------------Class Members--------------------------------//
	private FilterMaker fMaker;
	private OrderMaker oMaker;

	/*
	 * A private constructor creating the Filter and order makers when first called.
	 */
	private SectionProcessor(){

		fMaker = FilterMaker.instance();
		oMaker = OrderMaker.instance();
	}

	/* @return the class one and only instance */
	static SectionProcessor instance() {return myInstance;}

	/*
	 * The class's single method, responsible on running the Filter and Order makers in order to validate
	 * and create the section's Filter and order. The method receives a Section instance with only String
	 * values representing the Filter and order data from the command file and process the info into
	 * a ready to use section.
	 * the function also handles all kinds of type one Exceptions thrown from the makers objects.
	 *
	 * @param section the section instance to process.
	 */
	void createSection(Section section){

		// Using the FilterMaker instance in order to validate and create the filter instance.
		try {
			section.setFilter(fMaker.makeFilter(section));
		}
		// Print error to the screen in case of TypeOneException and set default filter.
		catch (TypeOneException ex){
			System.err.println(ex.getMessage() + section.getFilterLineNum());
			section.setFilter(DEFAULT_FILTER);
		}

		// Using the OrderMaker instance in order to validate and create the order instance.
		try {
			section.setOrder(oMaker.makeOrder(section));
		}
		// Print error to the screen in case of TypeOneException and set default order.
		catch (TypeOneException ex){
			System.err.println(ex.getMessage() + section.getOrderLineNum());
			section.setOrder(DEFAULT_ORDER);
		}

	}
}
