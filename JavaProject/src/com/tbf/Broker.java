package com.tbf;

import java.util.List;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for broker
 * 14 February 2020
 */

	public class Broker extends Person{
		private String type;		//"E" for Expert, "J" for Junior (Determines Fees and Commissions)
		private String brokerCode;	//Unique code for each Broker (Maybe natural Id for SQL Database?)
		private double fee;
		private double commissionRate;

		public double getFee() {
			if (this.getType().equals("E")) {
				fee = 0.0;
			} else if (this.getType().equals("J")) {
				fee = 75;
			}
			return fee;
		}
		
		public double getCommissionRate() {
			if (this.getType().equals("E")) {
				commissionRate = .0375;
			} else {
				commissionRate = .0125;
			}
			return commissionRate;
		}


		//The only constructor
		public Broker(String personCode, Name name, Address address, 
			   List<String> emailAddresses, String type, 
			   String brokerCode) {
			super(personCode, name, address, emailAddresses);
			this.type = type;
			this.brokerCode = brokerCode;
		}

		//Returns either "E" or "J"
		public String getType() {
			return type;
		}

		//Returns the broker's unique code
		public String getCode() {
			return brokerCode;
		}
		
		//Determines whether the broker object is empty (meaning not a broker)
		public boolean isBroker() {
			if(this.type.isBlank()) {
				return false;
			} else {
				return true;
			}
		}
	}

