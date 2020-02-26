package com.tbf;

import java.util.Comparator;

public class SortByOwnerLast implements Comparator<PortfolioString> {
	   
	    public int compare(PortfolioString a, PortfolioString b){ 
	        return a.getpOwnerLast().compareTo(b.getpOwnerLast()); 
	    } 
	} 

	