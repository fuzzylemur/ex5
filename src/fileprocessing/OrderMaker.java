package fileprocessing;

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

	public FileOrder makeOrder(String orderData) throws TypeOneException {

		String[] split = orderData.split(DELIMITER);
		boolean reverse = false;
		if (split[split.length - 1].equals("NOT")) {
			reverse = true;
		}

		switch (split[0]) {

			case (ABC): {
				validateNumArgs(2, split.length);
				return Abc.inctace();
			}
			case (SIZE): {
				validateNumArgs(2, split.length);
				double value = validateDouble(split[1]);
				return new SmallerThan(value, not);
			}
			case (TYPE): {
				validateNumArgs(3, split.length);
				double value1 = validateDouble(split[1]);
				double value2 = validateDouble(split[2]);
				betweenTest(value1,value2);
				return new Between(value1, value2, not);
			}

	}
}
