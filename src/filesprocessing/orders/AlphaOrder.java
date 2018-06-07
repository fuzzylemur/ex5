package filesprocessing.orders;
import java.io.File;
import java.util.Comparator;

/**
 * A singleton class, implements java's Comparator<File> interface.
 * this singleton has one method - compare - which can be use to sort a
 * Data structure in a ABS order.
 *
 * @authors Gil Adam, Jonathan Zedaka
 */
public class AlphaOrder implements Comparator<File> {

	//-------------------------Class Constants-------------------------------//
	private static AlphaOrder myInstance = new AlphaOrder();

	/* A private default constructor .*/
	private AlphaOrder(){}

	/** @return the class one and only instance*/
	public static AlphaOrder instance(){return myInstance;}

	/**
	 * Compares the give 2 files according to their String name in ABS order.
	 *
	 * @param file1 the first file to compare.
	 * @param file2 the second file to compare.
	 * @return A negative integer, zero, or a positive integer as the first argument
	 *         is less than, equal to, or greater than the second.
	 */
	public int compare(File file1, File file2){
		return file1.getName().compareTo(file2.getName());
	}
}
