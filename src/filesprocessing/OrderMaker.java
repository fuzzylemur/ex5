package filesprocessing;

import filesprocessing.orders.*;

public class OrderMaker {

	private static final String ABS = "abs";
	private static final String SIZE = "size";
	private static final String TYPE = "type";

	private final static String DELIMITER = "#";

	private static OrderMaker maker = new OrderMaker();

	private OrderMaker() {
	}

	public static OrderMaker instance() {
		return maker;
	}

	public FileOrder makeOrder(Section section) throws TypeOneException {

		if (section.getOrderInput() == null) return AlphaOrder.instance(); // when there is no order at all

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
