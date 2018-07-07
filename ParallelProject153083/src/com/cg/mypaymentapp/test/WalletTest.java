package com.cg.mypaymentapp.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

import static org.junit.Assert.*;

public class WalletTest {
	WalletService service;
	Customer cust1, cust2, cust3;

	@Before
	public void initData() {
		Map<String, Customer> data = new HashMap<String, Customer>();
		cust1 = new Customer("Siva", "99989087905", new Wallet(new BigDecimal(9000)));
		cust2 = new Customer("Subbu", "9963242422", new Wallet(new BigDecimal(6000)));
		cust3 = new Customer("You", "9922950519", new Wallet(new BigDecimal(7000)));

		data.put("9989087905", cust1);
		data.put("9963242422", cust2);
		data.put("9922950519", cust3);
		service = new WalletServiceImpl(data);

	}

	@Test(expected = NullPointerException.class)
	public void testCreateAccount() {

		service.createAccount(null, null, null);

	}

	@Test
	public void testCreateAccount1() {

		Customer c = new Customer();
		Customer cust = new Customer();
		cust = service.createAccount("Siva", "9989087905", new BigDecimal(7000));
		c.setMobileNo("9989087905");
		c.setName("Siva");
		c.setWallet(new Wallet(new BigDecimal(7000)));
		Customer actual = c;
		Customer expected = cust;
		// assertEquals(expected, actual);

	}

	@Test(expected = InvalidInputException.class)
	public void testShowBalance() {
		Customer cust = new Customer();
		cust = service.showBalance("9579405744");

	}

	@Test
	public void testShowBalance2() {

		Customer cust = new Customer();

		cust = service.showBalance("9922950519");
		assertEquals(cust, cust3);

	}

	@Test
	public void testShowBalance3() {

		Customer cust = new Customer();
		cust = service.showBalance("9989087905");
		BigDecimal actual = cust.getWallet().getBalance();
		BigDecimal expected = new BigDecimal(9000);
		assertEquals(expected, actual);

	}

	@Test(expected = InvalidInputException.class)
	public void testFundTransfer() {
		service.fundTransfer(null, null, new BigDecimal(7000));
	}

	@Test
	public void testFundTransfer2() {
		cust1 = service.fundTransfer("9989087905", "9963242422", new BigDecimal(2000));
		BigDecimal actual = cust1.getWallet().getBalance();
		BigDecimal expected = new BigDecimal(8000);
		assertEquals(expected, actual);
	}
	/*
	 * @Test public void testCreateAccount2() {
	 * 
	 * Customer cust = new Customer(); cust = service.createAccount("Siva",
	 * "9989087905", new BigDecimal(9000)); assertEquals("Siva", cust.getName());
	 * 
	 * }
	 * 
	 * @Test public void testCreateAccount3() {
	 * 
	 * Customer cust = new Customer(); cust = service.createAccount("Siva",
	 * "9989087905", new BigDecimal(9000)); assertEquals("9989087905",
	 * cust.getMobileNo());
	 * 
	 * }
	 */

	@Test(expected = InvalidInputException.class)
	public void testDeposit() {
		service.depositAmount("900000000", new BigDecimal(2000));
	}

	@Test
	public void testDeposit2() {
		cust1 = service.depositAmount("9963242422", new BigDecimal(2000));
		BigDecimal actual = cust1.getWallet().getBalance();
		BigDecimal expected = new BigDecimal(8000);
		assertEquals(expected, actual);
	}

	@Test(expected = InvalidInputException.class)
	public void testWithdraw() {
		service.withdrawAmount("900000000", new BigDecimal(2000));
	}

	@Test
	public void testWithdraw2() {
		cust1 = service.withdrawAmount("9963242422", new BigDecimal(2000));
		BigDecimal actual = cust1.getWallet().getBalance();
		BigDecimal expected = new BigDecimal(4000);
		assertEquals(expected, actual);
	}

	@Test
	public void TestValidate() {
		Customer customer = new Customer("Vaish", "8796543210", new Wallet(new BigDecimal(10)));
		service.acceptCustomerDetails(customer);
	}

	@After
	public void testAfter() {
		service = null;
	}

}
