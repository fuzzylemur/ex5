package filesprocessing;

import filesprocessing.filters.*;
import filesprocessing.orders.*;

import java.io.File;
import java.util.Comparator;

/**
 * A singelton class, responisibale for processing and creating a section instance.
 * This single object holds a FilterMaker and OrderMaker objects and use them in order to
 * validate and create the section's Filter and order.
 * The SectionProcessor is also responsibale for handeling all type one Exceptions.
 *
 * @authors Gil Adam, Jonathan Zedaka
 */
public class SectionProcessor {

	//-------------------------Class Constants-------------------------------//
	private static final Filter DEFAULT_FILTER = new All(false);
	private static final Comparator<File> DEFAULT_ORDER = AlphaOrder.instance();

	private static SectionProcessor myInstance = new SectionProcessor();

	//-------------------------Class Members--------------------------------//
	private FilterMaker fMaker;
	private OrderMaker oMaker;

	/*
	 * A private constractor creating the Filter and order makers when first called.
	 */
	private SectionProcessor(){

		fMaker = FilterMaker.instance();
		oMaker = OrderMaker.instance();
	}

	/** @return the class one and only instance*/
	public static SectionProcessor instance() {return myInstance;}

	/**
	 * The class's single method, responsibale on running the Filter and Order makers in order to validate
	 * and create the section's Filter and order. The method receives a Section instance with only String
	 * values representing the Filter and order data from the command file and process the info into
	 * a ready to use section.
	 * the function also handels all kinds of type one Exceptions thorwn from the makers objects.
	 *
	 * @param section the section instance to process.
	 */
	public void createSection(Section section){

		try { // Using the FilterMaker instance in order to validate and create the filter instance.
			section.setFilter(fMaker.makeFilter(section));
		}
		catch (TypeOneException ex){ // Print error to the screen in case of TypeOneException and set defualt filter.
			System.err.println(ex.getMessage() + section.getFilterLineNum());
			section.setFilter(DEFAULT_FILTER);
		}

		try { // Using the OrderMaker instance in order to validate and create the order instance.
			section.setOrder(oMaker.makeOrder(section));
		}
		catch (TypeOneException ex){ // Print error to the screen in case of TypeOneException and set defualt order.
			System.err.println(ex.getMessage() + section.getOrderLineNum());
			section.setOrder(DEFAULT_ORDER);
		}

	}
}
