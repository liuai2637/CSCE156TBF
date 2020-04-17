package com.tbf;

import java.util.Comparator;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Class for sorting by owner last name alphabetically
 * 28 February 2020
 */

public class SortByOwnerLast implements Comparator<Portfolio> {
	   
	    public int compare(Portfolio a, Portfolio b){ 
	    	int x = a.getOwner().getName().getLastName().compareTo(b.getOwner().getName().getLastName());
	    	if(x == 0) {
	    		x = a.getOwner().getName().getFirstName().compareTo(b.getOwner().getName().getFirstName());
	    	}
	    	if(x == 0) {
	    		x = a.getManager().getName().getFirstName().compareTo(b.getManager().getName().getFirstName());
	    	}
	        return x ; 
	    } 
	} 

	