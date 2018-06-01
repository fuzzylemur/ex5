package fileprocessing;

import fileprocessing.filters.All;
import fileprocessing.orders.AlphaOrder;


public class SectionProcessor {

	private static SectionProcessor processor = new SectionProcessor();
	private FilterMaker fMaker;
	private OrderMaker oMaker;

	private SectionProcessor(){
		fMaker = FilterMaker.instance();
		oMaker = OrderMaker.instance();
	}

	public void createSection(Section section){
		try{
			section.setFilter(fMaker.makeFilter(section));
		}
		catch (TypeOneException ex){
			System.err.println(ex.getMessage() + section.getFilterLineNum());
			section.setFilter(new All(false));
		}
		try{
			section.setOrder(oMaker.makeOrder(section));
		}
		catch (TypeOneException ex){
			System.err.println(ex.getMessage() + section.getOrderLineNum());
			section.setOrder(AlphaOrder.instance());
		}

	}



	public static SectionProcessor instance(){
		return processor;
	}
}
