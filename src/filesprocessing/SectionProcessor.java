package filesprocessing;

import filesprocessing.filters.*;
import filesprocessing.orders.*;

public class SectionProcessor {

	private static final Filter DEFAULT_FILTER = new All(false);
	private static final FileOrder DEFAULT_ORDER = AlphaOrder.instance();

	private static SectionProcessor myInstance = new SectionProcessor();
	public static SectionProcessor instance() {return myInstance;}

	private FilterMaker fMaker;
	private OrderMaker oMaker;

	private SectionProcessor(){

		fMaker = FilterMaker.instance();
		oMaker = OrderMaker.instance();
	}

	public void createSection(Section section){

		try {
			section.setFilter(fMaker.makeFilter(section));
		}
		catch (TypeOneException ex){
			System.err.println(ex.getMessage() + section.getFilterLineNum());
			section.setFilter(DEFAULT_FILTER);
		}

		try {
			section.setOrder(oMaker.makeOrder(section));
		}
		catch (TypeOneException ex){
			System.err.println(ex.getMessage() + section.getOrderLineNum());
			section.setOrder(DEFAULT_ORDER);
		}

	}
}
