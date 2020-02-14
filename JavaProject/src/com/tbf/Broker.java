package com.tbf;

	public class Broker {
		private String type;
		private String brokerCode;

		public Broker(String type, String brokerCode) {
			this.type = type;
			this.brokerCode = brokerCode;
		}

		public String getType() {
			return type;
		}

		public String getCode() {
			return brokerCode;
		}
		public boolean isBroker() {
			if(this.type.isBlank()) {
				return false;
			} else {
				return true;
			}
		}
	}

