package com.tbf;

import java.util.Comparator;

public class SortByOwnerLast implements Comparator<PortfolioString> {
	   
	    public int compare(PortfolioString a, PortfolioString b){ 
	    	int x = a.getpOwnerLast().compareTo(b.getpOwnerLast());
	    	if(x == 0) {
	    		x = a.getpOwnerFirst().compareTo(b.getpOwnerFirst());
	    	}
	        return x ; 
	    } 
	} 

	