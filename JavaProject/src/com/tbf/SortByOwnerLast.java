package com.tbf;

import java.util.Comparator;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Class for sorting by owner last name alphabetically
 * 28 February 2020
 */

public class SortByOwnerLast implements Comparator<PortfolioString> {
	   
	    public int compare(PortfolioString a, PortfolioString b){ 
	    	int x = a.getpOwnerLast().compareTo(b.getpOwnerLast());
	    	if(x == 0) {
	    		x = a.getpOwnerFirst().compareTo(b.getpOwnerFirst());
	    	}
	        return x ; 
	    } 
	} 

	