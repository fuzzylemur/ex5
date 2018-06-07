package filesprocessing.orders;
import java.io.File;
import java.util.Comparator;

/**
 * A singleton class, implements java's Comparator<File> interface.
 * This singleton has one method - compare - which can be use to sort a
 * Data structure according to the type of the files in it.
 *
 * @authors Gil Adam, Jonathan Zedaka
 */
public class TypeOrder implements Comparator<File> {

	//-------------------------Class Constants-------------------------------//
	private static TypeOrder myInstance = new TypeOrder();

	/* A private default constructor .*/
	private TypeOrder(){}

	/** @return the class one and only instance*/
	public static TypeOrder instance(){return myInstance;}

	/**
	 * Compares the give 2 files according to their type.
	 * If the type is equal we will compare according to ABS order.
	 *
	 * @param file1 the first file to compare.
	 * @param file2 the second file to compare.
	 * @return A negative integer, zero, or a positive integer as the first argument
	 *         is less than, equal to, or greater than the second.
	 */
	public int compare(File file1, File file2){

		String[] temp = file1.getName().split("\\.");
		String type1 = temp[temp.length-1];
		temp = file2.getName().split("\\.");
		String type2 = temp[temp.length-1];

		if (type1.equals(type2)){
			return file1.getName().compareTo(file2.getName());
		}

		else
			return type1.compareTo(type2);
	}
}