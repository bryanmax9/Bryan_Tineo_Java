package com.company;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    //Creating a hash map to store the Account records of each customer 
    private static HashMap<String,List<AccountRecord>> customerAccountList = new HashMap<String,List<AccountRecord>>();


    public static void main(String[] args) {

        /* 
         * HERE WE ARE SAVING ALL RECORDS IN A HASH MAP, EACH CUSTOMER IS DEFINED BY AN ID IN THE HASHMAP JUST LIKE THEIR ACTUAL ID
         */

        //looping throug the customer data
        for(String[] data:customerData){
            //creates an object account record for each data array string
            AccountRecord accountRecord = new AccountRecord();

            //setting charge, chargeDate
            accountRecord.setCharge(Integer.parseInt(data[2]));
            accountRecord.setChargeDate(data[3]);

            //If statement in order to prevent duplicate customer
            if(customerAccountList.containsKey(data[0])){
                //if the key exists inside the Account record list then
                //we create a temporary list called temp
                //temp will get the list of the customer depending on the customer id
                List<AccountRecord> temp=customerAccountList.get(data[0]);


                //Since temp is the Customer array of account records
                //Now to this customer we are adding this new account record so we dont create duplicate customers
                temp.add(accountRecord);

                //Now that temp has the customer with the its list of records updated
                //Now the updated customer we are putting again to the customer Account List depending of its id of the customer
                customerAccountList.put(data[0], temp);
            }else{

                //If we are in the first transaction of the customer
                //then we will create a list for the customer for the customer to store its account records
                List<AccountRecord> temp=new ArrayList<AccountRecord>();

                //Now, we store the first Account record to the list of account records of the customer
                temp.add(accountRecord);

                //Now with the updated array, store it into the customer account list depending of the customer id
                customerAccountList.putIfAbsent(data[0], temp);

            }
        }

        

        /* 
         * AFTER STORING THE DATA OF EACH ACCOUNT RECORD, HARDCODE AND CREATE ONE BY ONE EACH CUSTOMER
         */

        //Creating the 3 costumer classes
        //Since by viewing the data we can see that there is a total of 3 costumers
        //So, we create a class for each costumer
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();

        //Array of the customers
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);

        
        //Hardcoding the id of each object of costumer
        customer1.setId(1);
        customer2.setId(2);
        customer3.setId(3);
        
        //Hardcoding the name of costumer
        customer1.setName("Wayne Enterprises");
        customer2.setName("Daily Planet");
        customer3.setName("Ace Chemical");        
        
        
        //Accessing the private list of charges from customer object
        try {
            //ENABLING CHARGES so we can modify charges list in each customer
            Field privateField = Customer.class.getDeclaredField("charges");
            //Setting to true in order to add our records to each customer
            privateField.setAccessible(true);


            //We are doing a for loop since we want to get the list depeding of the id of the customer from the hashmap

            for(String keys: customerAccountList.keySet()){
                //Keys in this loop is the current key of the customer from that we are matching the keys of each customer
                if(keys =="1"){
                    //If the current key is 1 for customer one, then:
                    // we are going to pass to the set the object customer and the list of Account records that was inside the hashmap
                    privateField.set(customer1,customerAccountList.get(keys) );

                }else if(keys =="2"){
                    //We do the same but for customer 2
                    privateField.set(customer2, customerAccountList.get(keys));

                }else if(keys =="3"){
                    //We do the same but with customer 3
                    privateField.set(customer3, customerAccountList.get(keys));
                }
                
    
            }
             
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
        

    

        //We are going to printt the negative and positive balances
        System.out.println("Positive accounts:");
        //Here we are filtering and getting only accounts witth posittive balance and  then printing each 
        customers.stream().filter(obj-> obj.getBalance()>0).forEach(posObj->System.out.println(posObj));
        

        System.out.println("Negative accounts:");
        //Here we are filtering and getting only accounts with negative balance and  then printing each 
        customers.stream().filter(obj-> obj.getBalance()<0).forEach(posObj->System.out.println(posObj));
    }
}
