package com.shop.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import com.shop.exception.BasketException;

public class ShopBasket {

	private static HashMap<String, Double> storeMap = null;

	public static void main(String[] args) {
		/* Load the Fruit Store map with items and its base price */
		storeMap = new HashMap<String, Double>();

		/* Adding elements to HashMap */
		storeMap.put("APPLE", 10.00);
		storeMap.put("BANANA", 20.00);
		storeMap.put("LEMON", 14.90);
		storeMap.put("ORANGE", 32.50);
		storeMap.put("PEACH", 23.00);

		ShopBasket shopbasket = new ShopBasket();
		shopbasket.displayItems(); // Display the Store Items to customer
		shopbasket.shopItems();

	}

	private void displayItems() {
		Set<String> fruits = storeMap.keySet();
		System.out.println("------Welcome to Fruit Shop. Your Shopping Started-------");
		System.out.println();
		System.out.println("We have the below list of fruits availbe now");
		for (String fruit : fruits) {
			System.out.print(fruit);
			System.out.print(" | ");
		}
		System.out.println();
	}

	public void shopItems() {
		System.out.println();
		System.out.println("Enter the name of fruit --> ");
		Double totalCost = null;
		try {
			Scanner inputScanner = new Scanner(System.in);
			Map<String, Double> customerMap = new LinkedHashMap<String, Double>();
			while (true) {
				shoppingKart(inputScanner, customerMap);
				System.out.println("Press Y to continue shopping else Press F to Finish & Pay");
				String key = inputScanner.next();
				if (key.equalsIgnoreCase("Y")) {
					System.out.println("Enter the name of fruit --> ");
					continue;
				} else if (key.equalsIgnoreCase("F")) {
					break;
				} else {
					break;
				}
			}
			if (customerMap.size() == 0) {
				throw new BasketException("You kart is empty, please enter your items");
			} else {
				totalCost = getTotalCost(customerMap, storeMap);
				System.out.println("Your Purchased items : " + customerMap);
				System.out.println("Total cost of your Purchased items - £" + totalCost);
			}

		} catch (BasketException bException) {
			System.err.println(bException.getMessage());
		} catch (Exception exception) {
			System.err.println("An unexpected error occured, Exception message-" + exception.getMessage());
		}

		System.out.println("Thank you for shopping with us");

	}

	public void shoppingKart(Scanner inItem, Map<String, Double> customerMap) {
		String fruitName = inItem.next();
		fruitName = fruitName.toUpperCase();
		if (!storeMap.keySet().contains(fruitName)) {
			System.err.println("Name of the fruit does not match the list, enter again ");
			shoppingKart(inItem, customerMap);
		} else {
			Double itemCount = getItemCount(inItem, fruitName);
			if (customerMap.keySet().contains(fruitName)) {
				Double existQty = customerMap.get(fruitName);
				customerMap.remove(fruitName);
				customerMap.put(fruitName, (itemCount + existQty));
			} else {
				customerMap.put(fruitName, itemCount);
			}
		}
	}

	private Double getTotalCost(Map<String, Double> customerInMap, Map<String, Double> storeMap) {
		Iterator<String> cusInMapIterator = customerInMap.keySet().iterator();
		Double total = new Double(0);
		while (cusInMapIterator.hasNext()) {
			String fruit = cusInMapIterator.next();
			System.out.println(fruit + " - " + customerInMap.get(fruit) + " * " + storeMap.get(fruit) + " = £"
					+ storeMap.get(fruit) * customerInMap.get(fruit));
			total = total + (storeMap.get(fruit) * customerInMap.get(fruit));
		}
		return total;

	}

	private Double getItemCount(Scanner inItem, String fruitName) {
		System.out.println("Please type the quantity of " + fruitName + " in KG");
		String itemQty = inItem.next();
		Double retQty = null;
		try {
			retQty = Double.parseDouble(itemQty);
		} catch (Exception e) {
			System.err.println("Please provide a valid quantity");
			retQty = getItemCount(inItem, fruitName);
		}
		return retQty;
	}

}
