package com.shop.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.shop.main.ShopBasket;

public class ShopBasketTest {

	ShopBasket shopBasket = new ShopBasket();
	Map<String, Double> testShop = null;
	Map<String, Integer> testBasket = null;

	@Before
	public void loadStore() {
		testShop = new HashMap<String, Double>();
		testShop.put("APPLE", 10.00);
		testShop.put("BANANA", 20.00);
		testShop.put("LEMON", 14.90);
		testShop.put("ORANGE", 32.00);
		testShop.put("PEACH", 23.00);
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public final void testShoppingKart() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

}
