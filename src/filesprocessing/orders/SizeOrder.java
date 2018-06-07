package filesprocessing.orders;
import java.io.File;
import java.util.Comparator;

/**
 * A singleton class, implements java's Comparator interface.
 * this Singleton has one method - compare - which can be use to sort a
 * Data structure according to the size of the files in it.
 *
 * @author Gil Adam, Jonathan Zedaka
 */
public class SizeOrder implements Comparator<File> {

	//-------------------------Class Constants-------------------------------//
	private static SizeOrder myInstance = new SizeOrder();

	/* A private default constructor .*/
	private SizeOrder(){}

	/** @return the class one and only instance*/
	public static SizeOrder instance(){return myInstance;}

	/**
	 * Compares the give 2 files according to their size.
	 * If the size is equal we will compare according to ABS order.
	 *
	 * @param file1 the first file to compare.
	 * @param file2 the second file to compare.
	 * @return A negative integer, zero, or a positive integer as the first argument
	 *         is less than, equal to, or greater than the second.
	 */
	public int compare(File file1, File file2){

		int delta = (int)(file1.length() - file2.length());

		if (delta == 0){
			return file1.getName().compareTo(file2.getName());
		}

		else
			return delta;
	}
}
