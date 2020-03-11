package com.tbf;

/**
 * 
 * @author Sunny Liu and Bryce Yong
 * Constructor class for broker
 * 14 February 2020
 */

	public class Broker {
		private String type;		//"E" for Expert, "J" for Junior (Determines Fees and Commissions)
		private String brokerCode;	//Unique code for each Broker (Maybe natural Id for SQL Database?)

		//The only constructor
		public Broker(String type, String brokerCode) {
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

