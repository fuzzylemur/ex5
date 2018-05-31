package fileprocessing;

import fileprocessing.orders.AlphaOrder;

import java.io.File;

public class SectionProcessor {

	private static SectionProcessor processor;
	private FilterMaker fMaker;
	private OrderMaker oMaker;

	private SectionProcessor(){
		fMaker = FilterMaker.instance();
		oMaker = OrderMaker.instance();
		processor = new SectionProcessor();
	}

	public void createSection(Section section){
		try{
			section.setFilter(fMaker.makeFilter(section.getFilterInput()));
		}
		catch (Exception Type1Error){
			printError(Section.);
			section.setFilter(new AllFilter());
		}
		try{
			section.setOrder(oMaker.makeOrder(section);
		}
		catch (Exception Type1Error){
			printError(Section.);
			section.setOrder(AlphaOrder.instance());
		}

	}

	private void printError(int lineNum){
		System.err.println("ERROR in bcklasbcl" + lineNum);
	}

	public static SectionProcessor instance(){
		return processor;
	}
}
