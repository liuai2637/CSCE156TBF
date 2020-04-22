package com.tbf;

import java.util.Comparator;

public class LinkedList <T> {
	
	private Node<T> head;
	
	public LinkedList() {
		this.head = null;
	}
	
	public T getElement(int index) {
		
	}
	
	
	
	
	
	
	
	
	Comparator<Portfolio> sortByOwnerLastName = new Comparator<Portfolio>() {
	    public int compare(Portfolio a, Portfolio b){ 
	    	int x = a.getOwner().getName().getLastName().compareTo(b.getOwner().getName().getLastName());
	    	if(x == 0) {
	    		x = a.getOwner().getName().getFirstName().compareTo(b.getOwner().getName().getFirstName());
	    	}
	        return x ; 
	    } 
	};
	
	Comparator<Portfolio> sortByPorfolioValueDesc = new Comparator<Portfolio>() {
	    public int compare(Portfolio a, Portfolio b){     	
	    	if(a.getPortValue() == b.getPortValue()) {
	    		return 0;
	    	} else if (a.getPortValue() > b.getPortValue()) {
	    		return 1;
	    	} else {
	    		return -1;
	    	}
	    } 
	};
	
	Comparator<Portfolio> sortByManager = new Comparator<Portfolio>() {
	    public int compare(Portfolio a, Portfolio b){ 
	      	int x = a.getManager().getBroker().getType().compareTo(b.getManager().getBroker().getType());
	      	if(x == 0) {
	    		x = a.getManager().getName().getFirstName().compareTo(b.getManager().getName().getFirstName());
	    	}
	    	return x;
	    } 
	};

}
