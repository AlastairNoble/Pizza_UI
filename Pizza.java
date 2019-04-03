package application;
import java.io.Serializable;
/**
 * <h1> a class to store pizza information for a single pizza</h1>
 * <p> 
 * size, cheese and toppings (ham, pineapple, green pepper) are recorded
 * this class was created for assignment 3 of COMP 212
 * @author Alastair Noble
 * @version 2
 */

public class Pizza implements Serializable {

	private static final long serialVersionUID = 6280923299164568881L;
	private	String size;
	private	String cheese;
	private	String ham;
	private	String pineapple;
	private	String greenPepper;
	
	/**
	 * full parameter constructor
	 * @param sz
	 * @param chz
	 * @param pin
	 * @param pep
	 * @param hm
	 * @throws IllegalPizza
	 */
	public Pizza(String sz, String chz, String pin, String pep, String hm) throws IllegalPizza {
		setSize(sz);
		setCheese(chz);
		setHam(hm);
		setPineapple(pin);
		setGreenPepper(pep);
	}
	/**
	 * no parameter constructor
	 * @throws IllegalPizza
	 */
	public Pizza() throws IllegalPizza {
		this("Small", "Single", "None", "None", "Single");
	}
	/**
	 * sets the size of the pizza
	 * @param size
	 * @throws IllegalPizza
	 */
	public void setSize(String size) throws IllegalPizza{
		if(size == null || !(size.equalsIgnoreCase("Small") 
			|| size.equalsIgnoreCase("Large") 
			|| size.equalsIgnoreCase("Medium"))
		)
			throw new IllegalPizza("illegal size: " + size);
		this.size=size;
	}
	/**
	 * sets the amount of cheese on the pizza
	 * @param cheese
	 * @throws IllegalPizza
	 */
	public void setCheese(String cheese) throws IllegalPizza{
		if(cheese == null || !(cheese.equalsIgnoreCase("Single")
			||cheese.equalsIgnoreCase("Double")
			||cheese.equalsIgnoreCase("Triple"))
		)
			throw new IllegalPizza("illegal cheese: " + cheese);
		this.cheese=cheese;
	}
	/**
	 * sets topping ham
	 * @param ham
	 * @throws IllegalPizza
	 */
	public void setHam(String ham) throws IllegalPizza{
		if(ham == null 
			|| !(ham.equalsIgnoreCase("Single")
			||ham.equalsIgnoreCase("None"))
		)
			throw new IllegalPizza("illegal ham: " + ham);
		this.ham=ham;
	}
	/**
	 * sets pineapple topping
	 * @param pineapple
	 * @throws IllegalPizza
	 */
	public void setPineapple(String pineapple) throws IllegalPizza{
		if(pineapple==null 
			|| !(pineapple.equalsIgnoreCase("Single")
			||pineapple.equalsIgnoreCase("None"))
		)
			throw new IllegalPizza("illegal pineapple: " + pineapple);
		else if (pineapple.equalsIgnoreCase("Single")
				&& this.ham.equalsIgnoreCase("None")
		)
			throw new IllegalPizza("illegal pineapple without ham");
		this.pineapple=pineapple;
	}
	/**
	 * sets greenPepper topping
	 * @param greenPepper
	 * @throws IllegalPizza
	 */
	public void setGreenPepper(String greenPepper) throws IllegalPizza{
		if(greenPepper == null 
			|| !(greenPepper.equalsIgnoreCase("Single")
			||greenPepper.equalsIgnoreCase("None"))
		)
			throw new IllegalPizza("illegal greenPepper: " + greenPepper);
		else if (greenPepper.equalsIgnoreCase("Single")
				&& this.ham.equalsIgnoreCase("None")
		)
			throw new IllegalPizza("illegal pepper without ham");
		this.greenPepper=greenPepper;
	}

	private double numToppings() {
		double tops = 0;
		if (this.cheese.equalsIgnoreCase("Double"))
			tops+=1;
		else if (this.cheese.equalsIgnoreCase("Triple"))
			tops+=2;
		if (this.pineapple.equalsIgnoreCase("Single"))
			tops+=1;
		if (this.greenPepper.equalsIgnoreCase("Single"))
			tops+=1;
		if (this.ham.equalsIgnoreCase("Single"))
			tops+=1;	
		return tops;
	}
	/**
	 * returns the cost of one pizza
	 * @return the total cost of the pizza
	 */
	public double getCost() {
		double cost=0;
		if(this.size.equalsIgnoreCase("Small"))
			cost = 7;
		else if (this.size.equalsIgnoreCase("Medium"))
			cost = 9;
		else cost = 11;
		double numTops = this.numToppings();
		cost = cost + numTops*1.5;
		cost = (double)Math.round(cost*100)/100;
		return cost;
		
	}
	/**
	 * a string representation of the current object
	 * @return sentence including all attributes
	 */
	@Override
	public String toString() {
		String toppings= "";
		if (this.pineapple.equalsIgnoreCase("Single"))
			toppings +=  ", pineapple";
		if (this.greenPepper.equalsIgnoreCase("Single"))
			toppings+=  ", green pepper";
		if (this.ham.equalsIgnoreCase("Single"))
			toppings+= ", ham";
		
		String price = "Cost: $"+ getCost()+"0" + " each.";
		return "" + this.size + " pizza, " + this.cheese + " cheese" + toppings + ". " + price;
	}
	/**
	 * returns a deep copy of the current object
	 * @return a copy of the pizza
	 */
	@Override
	public Pizza clone() {
		Pizza pizzaCopy = null;
		try {
			pizzaCopy = new Pizza(size, cheese, pineapple, greenPepper, ham);
		} catch (IllegalPizza e) {
			System.out.println(e.getMessage());
			return null;
		}
		return pizzaCopy;
	}
	/**
	 * compares current pizza to input pizza based on cost
	 * @return cost difference between current pizza and input pizza
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (otherObject instanceof Pizza) {
			
			Pizza otherPizza = (Pizza)otherObject;
			if (otherPizza.cheese.equalsIgnoreCase(this.cheese)
				&& otherPizza.greenPepper.equalsIgnoreCase(this.greenPepper)
				&& otherPizza.ham.equalsIgnoreCase(this.ham) 
				&& otherPizza.pineapple.equalsIgnoreCase(this.pineapple)
				&& otherPizza.size.equalsIgnoreCase(this.size))
				return true;
		}
		return false;
	}
}