package fileprocessing;

import fileprocessing.orders.*;

public class OrderMaker {

	private static final String ABC = "abc";
	private static final String SIZE = "size";
	private static final String TYPE = "type";

	private final static String DELIMITER = "#";

	private static OrderMaker maker;

	private OrderMaker() {
		maker = new OrderMaker();
	}

	public static OrderMaker instance() {
		return maker;
	}

	public FileOrder makeOrder(Section section) throws TypeOneException {

		String[] split = section.getOrderInput().split(DELIMITER);
		if (split[split.length - 1].equals("REVERSE")) {
			section.setReverseOrder(true);
		}

		switch (split[0]) {

			case (ABC): {
				return AlphaOrder.instance();
			}
			case (SIZE): {
				return SizeOrder.instance();
			}
			case (TYPE): {
				return TypeOrder.instance();
			}
		}
		throw new TypeOneException();
	}
}
