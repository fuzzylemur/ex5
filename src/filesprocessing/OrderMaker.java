package filesprocessing;

import filesprocessing.orders.*;

import java.io.File;
import java.util.Comparator;

/*
 * A singleton class, responsible for validating and creating order instances.
 * It's main method, makeOrder(Section section), acts as a order factory by
 * analyzing the String input and creating the appropriate order.
 *
 * @authors Gil Adam, Jonathan Zedaka
 */
class OrderMaker {

	//-------------------------Class Constants-------------------------------//
	private static final String ABS = "abs";
	private static final String SIZE = "size";
	private static final String TYPE = "type";

	private final static String DELIMITER = "#";
	private final static String REVERSE_STR = "REVERSE";

	private static OrderMaker myInstance = new OrderMaker();

	/* A private default constructor .*/
	private OrderMaker() {}

	/* @return the class one and only instance */
	static OrderMaker instance() {return myInstance;}

	/*
	 * The Main function of this class. Receives a section instance and
	 * process its order input data (from the command file) in order to validate
	 * and create the appropriate order type.
	 * The function throws TypeOneException if the order data was corrupted in any way.
	 *
	 * @param section The section to create the order to.
	 * @return a FileOrder instance according to the command file data.
	 */
	Comparator<File> makeOrder(Section section) throws TypeOneException {

		if (section.getOrderInput() == null) 			// If no order specified in command file
			return AlphaOrder.instance();

		String[] split = section.getOrderInput().split(DELIMITER);

		// Checks if the order data contains REVERSE in the last cell.
		if (split[split.length - 1].equals(REVERSE_STR)) {
			section.setReverseOrder(true); // Is so, we set the section's reverseOrder variable to true.
		}

		switch (split[0]) { // Here, we make sure that the order type is one of the legal constant types.

			case (ABS): {
				return AlphaOrder.instance();
			}
			case (SIZE): {
				return SizeOrder.instance();
			}
			case (TYPE): {
				return TypeOrder.instance();
			}
		}  // If we didn't find a valid order we will set the section's reverseOrder variable back to false,
        section.setReverseOrder(false); // and throw TypeOneException.
        throw new TypeOneException();
	}
}
