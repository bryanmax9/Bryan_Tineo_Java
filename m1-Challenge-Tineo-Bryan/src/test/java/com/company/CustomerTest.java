package com.company;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class CustomerTest {
    @Test
    public void shouldCalculateBalance(){

        //TESTTING (TEST as if I was in main)

        //Setup
        Customer customer = new Customer();
        AccountRecord accountRecord = new AccountRecord();
        AccountRecord accountRecord2 = new AccountRecord();


        //Just creating One customer. setting id and name
        customer.setId(1);
        customer.setName("Comp 123");



        //Creatting our custtomer #1 charge
        accountRecord.setCharge(-20000);
        accountRecord.setChargeDate("12-01-2021");


        //Creatting our custtomer #2 charge
        accountRecord2.setCharge(-10000);
        accountRecord2.setChargeDate("12-02-2021");

        //Adding the records to the customer
        customer.getCharges().add(accountRecord);
        customer.getCharges().add(accountRecord2);

        //Since we have  one transaction -20000 and the otther -10000, we spect tthe  sum of this two values to be equal tto -30000
        assertEquals(-30000, customer.getBalance());


        //if you run this test you will se that the test passes :^)

    }
}