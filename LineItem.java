package application;
import java.io.Serializable;

public class LineItem implements Comparable<LineItem>, Serializable{
	
	private static final long serialVersionUID = -7378845849805246696L;
	private Pizza pizzas;
	private int numPizzas;
	/**
	 * full parameter constructor
	 * @param pizzaNum
	 * @param za
	 * @throws IllegalPizza
	 */
	public LineItem(int pizzaNum, Pizza za) throws IllegalPizza {
			setPizza(za);
			setNumber(pizzaNum);
	}
	/**
	 * single parameter constructor
	 * @param za
	 * @throws IllegalPizza
	 */
	public LineItem(Pizza za) throws IllegalPizza{ // default
		this(1,za);
	}

	private void setPizza(Pizza za) throws IllegalPizza{
		if(!(za instanceof Pizza)) {
			throw new IllegalPizza("Not a Pizza");
		}
		this.pizzas = za;
	}
	/**
	 * sets the number of this type of pizza ordered
	 * @param number
	 * @throws IllegalPizza
	 */
	public void setNumber(int number) throws IllegalPizza {
		if(number >100 || number <1) {
			throw new IllegalPizza("too many pizzas!!");
		}
		 this.numPizzas = number;
	}
	/**
	 * accessor for number of this type of pizza
	 * @return number of pizzas
	 */
	public int getNumber() {
		return this.numPizzas;
	}
	/**
	 * returns cost of this lineitem
	 * @return total cost 
	 */
	public double getCost() {
		double oneCost = this.pizzas.getCost();
		double totalCost = oneCost*this.numPizzas;
		if(this.numPizzas>=20)
			return totalCost*.9;
		else if (this.numPizzas>=10)
			return totalCost*.95;
		else return totalCost;
	}
	/**
	 * pizza accessor
	 * @return this pizza
	 */
	public Pizza getPizza() {
		return this.pizzas;
	}
	/**
	 * returns lineitem as a string
	 * @return string describing order
	 */
	@Override
	public String toString() {
		if (this.numPizzas<10)
			return " " + this.getNumber() + " " + pizzas.toString();
		else return "" + this.getNumber() + " " + pizzas.toString();
	}
	/**
	 * compares lineitems based on cost 
	 * @return difference between this lineitem and another
	 */
	@Override
	public int compareTo(LineItem o) {
		double difference = o.getCost()-this.getCost();
		if (difference<1 && difference >-1)
			return 0;
		return (int)Math.round(difference);
	}
//	@Override
//	public boolean equals(Object otherObject) {
//		
//		if(otherObject instanceof LineItem) {
//			LineItem otherLineItem = (LineItem)otherObject;
//			if (otherLineItem.numPizzas == this.numPizzas && this.pizzas.equals(otherLineItem.pizzas))
//				return true;
//		}
//	return false;
//	}
}
