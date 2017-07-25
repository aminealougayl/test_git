package org.sqli.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.sqli.Customer;
import org.sqli.Printer;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class CustomerTest {

	@Spy
	private Printer printer = new Printer();

	private Customer customer;

	@Captor
	private ArgumentCaptor<String> messageCaptor;

	@Before
	public void before() {
		customer = new Customer();
		customer.addProduct("P01", 22, "Promo_123");

	}

	@Test
	public void canOrderProductWhenIsNotYetOrdered() {
		assertTrue(customer.addProduct("P02", 3, "Promo_103"));
	}

	@Test
	public void canNotOrderProductWhenIsAlreadyOrdered() {
		assertFalse(customer.addProduct("P01", 2, "Promo_123"));
	}

	@Test
	public void canNotUseDiscountWhenOrdersLessThenFive() {
		assertFalse(customer.canUseDiscount());
	}

	@Test
	public void canNotDiscountWhenOrdersGreaterThanFourAndAtLeastOneOrderHasNoDiscount() {
		customer.addProduct("P02", 2, "Promo_12");
		customer.addProduct("P03", 1, "Promo_1243");
		customer.addProduct("P04", 3, "Promo_40");
		customer.addProduct("P05", 7, "Promo_41");
		customer.addProduct("P06", 6, null);
		assertFalse(customer.canUseDiscount());
	}

	@Test
	public void canUseDiscountWhenOrdersGreaterThanFourAndAllOrdersHasDiscount() {
		customer = new Customer();
		customer.addProduct("P02", 2, null);
		customer.addProduct("P03", 1, null);
		customer.addProduct("P04", 3, null);
		customer.addProduct("P05", 7, null);
		customer.addProduct("P06", 6, null);
		assertTrue(customer.canUseDiscount());
	}

	@Test
	public void canPrintOrdersIfClient() {
		customer.print(printer);
		Mockito.verify(printer).print(messageCaptor.capture());
	}

	@Test
	public void canNotPrintOrdersIfNotClient() {
		customer = new Customer();
		customer.print(printer);
		Mockito.verify(printer, times(0)).print(messageCaptor.capture());
	}
}
