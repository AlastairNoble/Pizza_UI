package application;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.text.DecimalFormat;


public class PizzaController {
	
	@FXML
	private ToggleGroup RadioSize;
	@FXML
	private ToggleGroup RadioCheese;
	@FXML
	private ToggleGroup RadioPineapple;
	@FXML
	private ToggleGroup RadioPepper;
	@FXML
	private ToggleGroup RadioHam;
	@FXML 
	private Label pizza1;
	@FXML 
	private Label IllegalMessage;
	@FXML
	private Button addPizza;
	@FXML 
	private TextField numPizzas;
	@FXML
	private Label orderCost;
	@FXML
	private Label currentPizzaCost;
	@FXML 
	private RadioButton Small;
	@FXML 
	private RadioButton Single;
	@FXML 
	private RadioButton NonePepper;
	@FXML 
	private RadioButton NonePineapple;
	@FXML 
	private RadioButton NoneHam;
	
	
	private ArrayList<LineItem> pizzas = new ArrayList<>(); 
	private int numLineItems=0;
	private double totalCost=0;
	@FXML
	private VBox sidebar;
//	@FXML 
//	private anchorPane anchorPane;
	
	@FXML
	public void initialize() {
		Small.setSelected(true);
		Single.setSelected(true);
		NonePineapple.setSelected(true);
		NonePepper.setSelected(true);
		NoneHam.setSelected(true);
		numPizzas.setText("1");
	}
	
	
	@FXML void currentPizza(ActionEvent event){
		String size = ((RadioButton)RadioSize.getSelectedToggle()).getId().toString();
		String cheese = ((RadioButton)RadioCheese.getSelectedToggle()).getId().toString();
		String pine = ((RadioButton)RadioPineapple.getSelectedToggle()).getId().toString();
		String pepper = ((RadioButton)RadioPepper.getSelectedToggle()).getId().toString();
		String ham = ((RadioButton)RadioHam.getSelectedToggle()).getId().toString();
		
		int num;
		try {
			num = Integer.parseInt(numPizzas.getText());
			LineItem temp = new LineItem(num , new Pizza(size, cheese, pine, pepper, ham));
			currentPizzaCost.setText("Cost of this Pizza: $" + temp.getCost() + "0");
			currentPizzaCost.setStyle("-fx-text-fill: black;");


		} catch (IllegalPizza e) {
			currentPizzaCost.setText(e.getMessage()+ ", cannont display cost");
			currentPizzaCost.setStyle("-fx-text-fill: red;");

		}
	}

	
	@FXML
	void addNewPizza(ActionEvent event) {
		String size = ((RadioButton)RadioSize.getSelectedToggle()).getId().toString();
		String cheese = ((RadioButton)RadioCheese.getSelectedToggle()).getId().toString();
		String pine = ((RadioButton)RadioPineapple.getSelectedToggle()).getId().toString();
		String pepper = ((RadioButton)RadioPepper.getSelectedToggle()).getId().toString();
		String ham = ((RadioButton)RadioHam.getSelectedToggle()).getId().toString();
		
		int num;
		try {
			num = Integer.parseInt(numPizzas.getText());

			addItem(num , new Pizza(size, cheese, pine, pepper, ham));	
						
			totalCost = 0;
			sidebar.getChildren().clear();
			for(int i=0; i<pizzas.size();i++) {
				Label toAdd = new Label();
				toAdd.setText(pizzas.get(i).toString());
				sidebar.getChildren().add(toAdd);
				totalCost+=pizzas.get(i).getCost();
			}
			DecimalFormat df = new DecimalFormat("####.##");
			orderCost.setText("Total Order Cost: $" + df.format(totalCost));
			
			IllegalMessage.setText("");

			
		} catch (IllegalPizza e) {
			IllegalMessage.setText(e.getMessage());

		}
	}	
	
	private int searchOrders(Pizza pizza) {
		for (int line = 0; line < pizzas.size(); line++)
			if (pizzas.get(line).getPizza().equals(pizza))
				return line;
		return -1;
	} // end searchOrders

	private boolean addItem (int number, Pizza pizza) {
		int orderLocation = searchOrders(pizza);
		if (orderLocation < 0)
			try {
				pizzas.add(new LineItem(number, pizza));
				numLineItems++;
				
				totalCost+=(double)pizzas.get(numLineItems-1).getCost();
				IllegalMessage.setText("");
				
			} catch (IllegalPizza e) {
				IllegalMessage.setText(e.getMessage());
				return false;
			}
		else {
			LineItem item = pizzas.get(orderLocation);
			try {
				double tempCost = pizzas.get(orderLocation).getCost();
				item.setNumber(item.getNumber() + number);
				IllegalMessage.setText("");
				
				totalCost+=(double)pizzas.get(orderLocation).getCost()-tempCost;
			} catch (IllegalPizza e) {
				IllegalMessage.setText(e.getMessage());

				return false;				
			}
		}
		pizzas.trimToSize();
		System.out.println(pizzas);
		return true;
	} // end addItem
}
