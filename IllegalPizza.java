package application;
/**
 * <h2>Illegal Pizza exeption</h2>
 * <p>
 * an exception thrown by the Pizza object if an illegal parameter is given.
 * also thrown by the LineItem Object if an illegal parameter is given.
 * </p>
 * @author Alastair Noble
 * @version 2.0
 *
 */
public class IllegalPizza extends Exception {
	
	private static final long serialVersionUID = 4593713417540203652L;
/**
 * specifies illegality 
 * @param message 
 */
	public IllegalPizza(String message) {
		super(message);
	}
}
