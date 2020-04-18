package com.tbf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Author: Sunny Liu, Bryce Yong Date: 03/04/2020 This program to load the portfolio database from mySQL into objects
 * 
 */




public class DataLoaderSql {

	private static final Logger LOG = LogManager.getLogger(DataLoaderSql.class);
	
	public static HashMap<Integer, Person> loadPerson() {

		HashMap<Integer, Person> personHashMap = new HashMap<>();

		// loading jdbc driver
		ConnectionFactory.loadDriver();

		// creating connection
		Connection conn = ConnectionFactory.createConnection();

		// prepare query
		String getPerson = "select * from Person p " + "join Address a on p.addressId = a.addressId "
				+ "join State s on s.stateId = a.stateId " + "join Country c on c.countryId = a.countryId;";

		String getEmail = "select * from Email where personId = ? ;";

		PreparedStatement psPerson = null;
		ResultSet rsPerson = null;
		PreparedStatement psEmail = null;
		ResultSet rsEmail = null;

		try {
			psPerson = conn.prepareStatement(getPerson);
			rsPerson = psPerson.executeQuery();

			while (rsPerson.next()) {
				List<String> emailAddresses = new ArrayList<>();
				// proccess the record for person
				int personId = rsPerson.getInt("personId");
				String personCode = rsPerson.getString("personCode");
				String brokerType = rsPerson.getString("brokerType");
				String brokerCode = rsPerson.getString("brokerCode");
				String firstName = rsPerson.getString("firstName");
				String lastName = rsPerson.getString("lastName");
				String street = rsPerson.getString("street");
				String city = rsPerson.getString("city");
				String state = rsPerson.getString("state");
				String zip = rsPerson.getString("zip");
				String country = rsPerson.getString("country");

				Name name = new Name(lastName, firstName);

				Address address = new Address(street, city, state, zip, country);

				psEmail = conn.prepareStatement(getEmail);
				psEmail.setInt(1, personId);

				rsEmail = psEmail.executeQuery();

				while (rsEmail.next()) {
					// proccess email for each person

					String emailAddress = rsEmail.getString("emailAddress");
					emailAddresses.add(emailAddress);
				}
				
				ConnectionFactory.closeConnection(rsEmail);
				ConnectionFactory.closeConnection(psEmail);

				if (brokerType != null && brokerCode != null) {
					Broker broker = new Broker(brokerType, brokerCode);
					Person person = new Person(personCode, broker, name, address, emailAddresses, personId);
					personHashMap.put(personId, person);
				} else {
					Person person = new Person(personCode, name, address, emailAddresses, personId);
					personHashMap.put(personId, person);
				}


							
			}

		} catch (SQLException sqle) {
<<<<<<< HEAD
			ConnectionFactory.closeConnection(rsPerson);
			ConnectionFactory.closeConnection(psPerson);
=======
>>>>>>> branch 'master' of https://github.com/liuai2637/CSCE156TBF.git
			ConnectionFactory.closeConnection(rsEmail);
			ConnectionFactory.closeConnection(psEmail);
<<<<<<< HEAD
=======
			ConnectionFactory.closeConnection(rsPerson);
			ConnectionFactory.closeConnection(psPerson);
>>>>>>> branch 'master' of https://github.com/liuai2637/CSCE156TBF.git
			ConnectionFactory.closeConnection(conn);

			LOG.error("something bad happened", sqle);
			throw new RuntimeException(sqle);
		}

		// close the connection
<<<<<<< HEAD
		ConnectionFactory.closeConnection(rsPerson);
		ConnectionFactory.closeConnection(psPerson);
=======
>>>>>>> branch 'master' of https://github.com/liuai2637/CSCE156TBF.git
		ConnectionFactory.closeConnection(rsEmail);
		ConnectionFactory.closeConnection(psEmail);
<<<<<<< HEAD
=======
		ConnectionFactory.closeConnection(rsPerson);
		ConnectionFactory.closeConnection(psPerson);
>>>>>>> branch 'master' of https://github.com/liuai2637/CSCE156TBF.git
		ConnectionFactory.closeConnection(conn);

		return personHashMap;

	}

	public static HashMap<Integer, Asset> loadAsset() {

		HashMap<Integer, Asset> assetHashMap = new HashMap<>();

		// conncetion driver
		ConnectionFactory.loadDriver();

		// creating connection
		Connection conn = ConnectionFactory.createConnection();

		// prepare query
		String getAsset = "select * from Asset;";

		// process data
		PreparedStatement psAsset = null;
		ResultSet rsAsset = null;

		try {
			psAsset = conn.prepareStatement(getAsset);
			rsAsset = psAsset.executeQuery();

			while (rsAsset.next()) {
				int assetId = rsAsset.getInt("assetId");
				String code = rsAsset.getString("code");
				String label = rsAsset.getString("label");
				String type = rsAsset.getString("type");

				if (type.equals("D")) { // if its deposit account
					double apr = rsAsset.getDouble("apr");
					Asset asset = new DepositAccount(code, label, type, apr);
					assetHashMap.put(assetId, asset);
				} else if (type.equals("S")) { // if its a stock
					double baseQuarterlyDividend = rsAsset.getDouble("baseQuarterlyDividend");
					double baseRateOfReturn = rsAsset.getDouble("baseRateOfReturn");
					String stockSymbol = rsAsset.getString("stockSymbol");
					double sharePrice = rsAsset.getDouble("sharePrice");
					double beta = rsAsset.getDouble("beta");
					Asset asset = new Stock(code, label, type, baseQuarterlyDividend, baseRateOfReturn, beta,
							stockSymbol, sharePrice);
					assetHashMap.put(assetId, asset);
				} else if (type.equals("P")) { // if its a private investment
					double baseQuarterlyDividend = rsAsset.getDouble("baseQuarterlyDividend");
					double baseRateOfReturn = rsAsset.getDouble("baseRateOfReturn");
					double totalAmount = rsAsset.getDouble("totalAmount");
					double omega = rsAsset.getDouble("omega");
					Asset asset = new PrivateInvestment(code, label, type, baseQuarterlyDividend, baseRateOfReturn,
							omega, totalAmount);
					assetHashMap.put(assetId, asset);
				}
			}

		} catch (SQLException sqle) {
			ConnectionFactory.closeConnection(rsAsset);
			ConnectionFactory.closeConnection(psAsset);
			ConnectionFactory.closeConnection(conn);

			LOG.error("something bad happened", sqle);
			throw new RuntimeException(sqle);
		}
		//close connection
		ConnectionFactory.closeConnection(rsAsset);
		ConnectionFactory.closeConnection(psAsset);
		ConnectionFactory.closeConnection(conn);

		return assetHashMap;
	}

	public static ArrayList<Portfolio> loadPortfolio() {
		
		LOG.info("loading all portfolios...");
		ArrayList<Portfolio> portfolioList = new ArrayList<>();

		// loading driver
		ConnectionFactory.loadDriver();

		// creating connection
		Connection conn = ConnectionFactory.createConnection();

		// prepare query
		String getPortfolio = "select * from Portfolio;";
		String getPortfolioAsset = "select * from PortfolioAsset where portfolioId = ?;";

		// process data
		PreparedStatement psPortfolio = null;
		ResultSet rsPortfolio = null;
		PreparedStatement psPortfolioAsset = null;
		ResultSet rsPortfolioAsset = null;

		try {
			psPortfolio = conn.prepareStatement(getPortfolio);
			rsPortfolio = psPortfolio.executeQuery();

			HashMap<Integer, Person> personHashMap = loadPerson();
			HashMap<Integer, Asset> assetHashMap = loadAsset();

			while (rsPortfolio.next()) {
				List<Asset> assets = new ArrayList<>();
				int portfolioId = rsPortfolio.getInt("portfolioId");
				String code = rsPortfolio.getString("code");
				int ownerId = rsPortfolio.getInt("ownerId");
				int managerId = rsPortfolio.getInt("managerId");
				int beneficiaryId = rsPortfolio.getInt("beneficiaryId");

				Person owner = personHashMap.get(ownerId);
				Person manager = personHashMap.get(managerId);
				Person beneficiary = personHashMap.get(beneficiaryId);

				psPortfolioAsset = conn.prepareStatement(getPortfolioAsset);
				psPortfolioAsset.setInt(1, portfolioId);
				rsPortfolioAsset = psPortfolioAsset.executeQuery();

				while (rsPortfolioAsset.next()) {
					int assetId = rsPortfolioAsset.getInt("assetId");
					Asset asset = assetHashMap.get(assetId);

					double value = rsPortfolioAsset.getDouble("value");
					//making a deep copy
					if (asset.getType().equals("D")) {
						DepositAccount d = (DepositAccount) asset;
						String copyCode = d.getCode();
						String label = d.getLabel();
						String type = d.getType();
						double apr = d.getApr();
						DepositAccount copy = new DepositAccount(copyCode, label, type, apr);
						copy.setNumAsset(value);
						assets.add(copy);
					} else if (asset.getType().equals("S")) {
						Stock s = (Stock) asset;
						String copyCode = s.getCode();
						String label = s.getLabel();
						String type = s.getType();
						double baseQuarterlyDividend = s.getBaseQuarterlyDividend();
						double baseRateOfReturn = s.getBaseRateOfReturn();
						String stockSymbol = s.getStockSymbol();
						double sharePrice = s.getSharePrice();
						double beta = s.getBeta();
						Stock copy = new Stock(copyCode, label, type, baseQuarterlyDividend, baseRateOfReturn, beta,
								stockSymbol, sharePrice);
						copy.setNumAsset(value);
						assets.add(copy);
					} else if (asset.getType().equals("P")) {
						PrivateInvestment p = (PrivateInvestment) asset;
						String copyCode = p.getCode();
						String label = p.getLabel();
						String type = p.getType();
						double baseQuarterlyDividend = p.getBaseQuarterlyDividend();
						double baseRateOfReturn = p.getBaseRateOfReturn();
						double totalAmount = p.getTotalAmount();
						double omega = p.getOmega();
						PrivateInvestment copy = new PrivateInvestment(copyCode, label, type, baseQuarterlyDividend,
								baseRateOfReturn, omega, totalAmount);
						copy.setNumAsset(value);
						assets.add(copy);
					}

					
					
				}
				ConnectionFactory.closeConnection(rsPortfolioAsset);
				ConnectionFactory.closeConnection(psPortfolioAsset);
				Portfolio portfolio = new Portfolio(code, owner, manager, beneficiary, assets);
				portfolioList.add(portfolio);
			}

		} catch (SQLException sqle) {
<<<<<<< HEAD
			ConnectionFactory.closeConnection(rsPortfolio);
			ConnectionFactory.closeConnection(psPortfolio);
=======
>>>>>>> branch 'master' of https://github.com/liuai2637/CSCE156TBF.git
			ConnectionFactory.closeConnection(rsPortfolioAsset);
			ConnectionFactory.closeConnection(psPortfolioAsset);
<<<<<<< HEAD
=======
			ConnectionFactory.closeConnection(rsPortfolio);
			ConnectionFactory.closeConnection(psPortfolio);
>>>>>>> branch 'master' of https://github.com/liuai2637/CSCE156TBF.git
			ConnectionFactory.closeConnection(conn);
			LOG.error("something bad happened", sqle);
			throw new RuntimeException(sqle);
		}
		//close connection
<<<<<<< HEAD
		ConnectionFactory.closeConnection(rsPortfolio);
		ConnectionFactory.closeConnection(psPortfolio);
=======
>>>>>>> branch 'master' of https://github.com/liuai2637/CSCE156TBF.git
		ConnectionFactory.closeConnection(rsPortfolioAsset);
		ConnectionFactory.closeConnection(psPortfolioAsset);
<<<<<<< HEAD
=======
		ConnectionFactory.closeConnection(rsPortfolio);
		ConnectionFactory.closeConnection(psPortfolio);
>>>>>>> branch 'master' of https://github.com/liuai2637/CSCE156TBF.git
		ConnectionFactory.closeConnection(conn);

		return portfolioList;
	}

}
