package filesprocessing;

import filesprocessing.orders.*;

public class OrderMaker {

	private static final String ABS = "abs";
	private static final String SIZE = "size";
	private static final String TYPE = "type";

	private final static String DELIMITER = "#";

	private static OrderMaker myInstance = new OrderMaker();
	public static OrderMaker instance() {return myInstance;}

	private OrderMaker() {}


	public FileOrder makeOrder(Section section) throws TypeOneException {

		if (section.getOrderInput() == null) 			// no order specified in command file
			return AlphaOrder.instance();

		String[] split = section.getOrderInput().split(DELIMITER);
		if (split[split.length - 1].equals("REVERSE")) {
			section.setReverseOrder(true);
		}

		switch (split[0]) {

			case (ABS): {
				return AlphaOrder.instance();
			}
			case (SIZE): {
				return SizeOrder.instance();
			}
			case (TYPE): {
				return TypeOrder.instance();
			}
		}
        section.setReverseOrder(false);
        throw new TypeOneException();
	}
}
