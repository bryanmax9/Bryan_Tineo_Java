package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.sound.sampled.SourceDataLine;

public class Customer {
    private int id;
    private String name;
    private List<AccountRecord> charges = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        
        //We are adding all charges from the list, first we are going to get the charge of each object and then sum them up
        int sum = charges.stream().map(obj-> obj.getCharge() ).reduce(0,Integer::sum);

        return sum;
    }

    public List<AccountRecord> getCharges() {
        return charges;
    }

    @Override
    public String toString() {
        
        return "Costumer ID: "+ this.getId()+", Name: "+this.getName()+", Balance: "+ getBalance();
    }
}
