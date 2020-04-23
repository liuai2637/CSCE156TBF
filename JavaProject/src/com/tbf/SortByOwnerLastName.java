package com.tbf;

import java.util.Comparator;

public class SortByOwnerLastName implements Comparator<Portfolio>{
	
    public int compare(Portfolio a, Portfolio b){ 
    	int x = a.getOwner().getName().getLastName().compareTo(b.getOwner().getName().getLastName());
    	if(x == 0) {
    		x = a.getOwner().getName().getFirstName().compareTo(b.getOwner().getName().getFirstName());
    	}
        return x ; 
    } 
	
}
