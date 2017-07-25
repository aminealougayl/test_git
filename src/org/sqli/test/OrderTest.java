package org.sqli.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.verification.Times;
import org.mockito.runners.MockitoJUnitRunner;
import org.sqli.Order;
import org.sqli.Printer;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class OrderTest {

	@Spy
	private Printer printer = new Printer();
	private Order order;
	
	@Captor
	private ArgumentCaptor<String> messageCaptor;
	
	@Before
	public void before(){
		
	}
	
	@Test
	public void canHasDiscountWhenIsNotBlank() {
		order = new Order("Ord1", 2, "Promo_17");
		assertTrue(order.hasDiscount());
	}
	
	@Test
	public void canNotHasDiscountWhenIsBlank() {
		order = new Order("Ord1", 2, null);
		assertFalse(order.hasDiscount());
	}
	
	@Test
	public void canPrintUsingWhenHasDiscount() {
		order = new Order("Ord1", 2, "Promo_17");
		order.printUsing(printer);
		Mockito.verify(printer, times(1)).print(messageCaptor.capture());
		String output = messageCaptor.getValue();
		String input = "Product Code : Ord1, Quantity : 2, Promotion Code : Promo_17";
		assertEquals(output,input);
	}
	
	@Test
	public void canPrintUsingWhenHasNoDiscount() {
		order = new Order("Ord1", 2, null);
		order.printUsing(printer);
		Mockito.verify(printer, times(1)).print(messageCaptor.capture());
		String output = messageCaptor.getValue();
		String input = "Product Code : Ord1, Quantity : 2";
		assertEquals(output,input);
	}
	

}
