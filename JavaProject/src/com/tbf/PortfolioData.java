package com.tbf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: Sunny Liu, Bryce Yong
 * Date: 04/17/20
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class PortfolioData {

	/**
	 * Method that removes every person record from the database
	 */
	public static void removeAllPersons() {

		ConnectionFactory.loadDriver();

		// creating connection
		Connection conn = ConnectionFactory.createConnection();

		// prepare query
		String queryEmail = "delete from Email;";
		String queryPortfolioAsset = "delete from PortfolioAsset;";
		String queryPortfolio = "delete from Portfolio;";
		String queryPerson = "delete from Person;";

		PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(queryEmail);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryPortfolioAsset);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryPortfolio);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryPerson);
			ps.executeUpdate();

		} catch (SQLException e) {	
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		} 

		// close connection
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);

	}

	/**
	 * Removes the person record from the database corresponding to the provided
	 * <code>personCode</code>
	 * 
	 * @param personCode
	 */
	public static void removePerson(String personCode) {

		// creating connection
		Connection conn = ConnectionFactory.createConnection();

		// prepare query that delete each element including the person first and then delete the person 
		String queryEmail = "delete from Email where personId = (select personId from Person where personCode = ?);";
		String queryOwner = "delete from PortfolioAsset where portfolioId in (select portfolioId from Portfolio where ownerId = (select personId from Person where personCode = ?));";
		String queryManager = "delete from PortfolioAsset where portfolioId in (select portfolioId from Portfolio where managerId = (select personId from Person where personCode = ?))";
		String queryBeneficiary = "delete from PortfolioAsset where portfolioId in (select portfolioId from Portfolio where beneficiaryId = (select personId from Person where personCode = ?));";
		String queryPortfolioOwner = "delete from Portfolio where ownerId = (select personId from Person where personCode = ?);";
		String queryPortfolioManager = "delete from Portfolio where managerId = (select personId from Person where personCode = ?);";
		String queryPortfolioBeneficiary = "delete from Portfolio where beneficiaryId = (select personId from Person where personCode = ?);";
		String queryPerson = "delete from Person where personCode = ?;";

		PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(queryEmail);
			ps.setString(1, personCode);
			ps.executeUpdate();
			

			ps = conn.prepareStatement(queryOwner);
			ps.setString(1, personCode);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryManager);
			ps.setString(1, personCode);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryBeneficiary);
			ps.setString(1, personCode);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryPortfolioOwner);
			ps.setString(1, personCode);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryPortfolioManager);
			ps.setString(1, personCode);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryPortfolioBeneficiary);
			ps.setString(1, personCode);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryPerson);
			ps.setString(1, personCode);
			ps.executeUpdate();

		} catch (SQLException e) {
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		}

		// close connection
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);
	}

	/**
	 * Method to add a person record to the database with the provided data. The
	 * <code>brokerType</code> will either be "E" or "J" (Expert or Junior) or
	 * <code>null</code> if the person is not a broker.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 * @param brokerType
	 */

	public static int addState(String state) {

		Connection conn = ConnectionFactory.createConnection();

		// prepare query that add a state into the database
		String querySearch = "select stateId from State where state = ?";
		String queryState = "insert into State (state) values (?)";

		PreparedStatement ps = null;
		ResultSet rs = null;
		int stateId = -1;

		try {
			// check duplicates
			ps = conn.prepareStatement(querySearch);
			ps.setString(1, state);
			rs = ps.executeQuery();

			if(rs.next()) {
				stateId = rs.getInt("stateId");
			}	else  {
				ps = conn.prepareStatement(queryState);
				ps.setString(1, state);
				ps.executeUpdate();
				
				ps = conn.prepareStatement(querySearch);
				ps.setString(1, state);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					stateId = rs.getInt("stateId");
				}

			}

		} catch (SQLException e) {
			ConnectionFactory.closeConnection(rs);
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		}

		// close connection
		ConnectionFactory.closeConnection(rs);
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);
		return stateId;

	}
	
	public static int addCountry(String country) {

		Connection conn = ConnectionFactory.createConnection();

		// prepare query
		String querySearch = "select countryId from Country where country = ?";
		String queryCountry = "insert into Country (country) values (?)";

		PreparedStatement ps = null;
		ResultSet rs = null;
		int countryId = -1;

		try {
			// check duplicates
			ps = conn.prepareStatement(querySearch);
			ps.setString(1, country);
			rs = ps.executeQuery();

			if(rs.next()) {
				countryId = rs.getInt("countryId");
			}	else  {
				ps = conn.prepareStatement(queryCountry);
				ps.setString(1, country);
				ps.executeUpdate();
				
				ps = conn.prepareStatement(querySearch);
				ps.setString(1, country);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					countryId = rs.getInt("countryId");
				}

			}

		} catch (SQLException e) {
			ConnectionFactory.closeConnection(rs);
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		}

		// close connection
		ConnectionFactory.closeConnection(rs);
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);
		return countryId;

	}
	
	
	
	
	public static int addAddress(String street, String city, String state, String zip, String country) {

		Connection conn = ConnectionFactory.createConnection();

		// prepare query to add address
		String querySearch = "select addressId from Address where street = ?";
		String queryAddress = "insert into Address (street, city, stateId, zip, countryId) values (?, ?, ?, ?, ?);";

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int stateId = addState(state);
		int countryId = addCountry(country);
		int addressId = -1;

		try {
			// check duplicates
			ps = conn.prepareStatement(querySearch);
			ps.setString(1, street);
			rs = ps.executeQuery();

			if(rs.next()) {
				addressId = rs.getInt("addressId");

			}	else  {
				ps = conn.prepareStatement(queryAddress);
				ps.setString(1, street);
				ps.setString(2, city);
				ps.setInt(3, stateId);
				ps.setString(4, zip);
				ps.setInt(5, countryId);
				ps.executeUpdate();
				
				ps = conn.prepareStatement(querySearch);
				ps.setString(1, street);
				rs = ps.executeQuery();
				if(rs.next()) {
					addressId = rs.getInt("addressId");

				}

			}

		} catch (SQLException e) {
			ConnectionFactory.closeConnection(rs);
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		}

		// close connection
		ConnectionFactory.closeConnection(rs);
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);
		return addressId;

	}

	public static void addPerson(String personCode, String firstName, String lastName, String street, String city,
			String state, String zip, String country, String brokerType, String secBrokerId) {

		// creating connection
		Connection conn = ConnectionFactory.createConnection();

		// prepare query to add person
		
		String queryPerson = "insert into Person (personCode, brokerType, brokerCode, lastName, firstName, addressId) values (?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = null;
		try {
			// check duplicates
			int addressId = addAddress(street, city, state, zip, country);

			ps = conn.prepareStatement(queryPerson);
			ps.setString(1, personCode);
			ps.setString(2, brokerType);
			ps.setString(3, secBrokerId);
			ps.setString(4, lastName);
			ps.setString(5, firstName);
			ps.setInt(6, addressId);
			ps.executeUpdate();

		} catch (SQLException e) {
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		}

		// close connection
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);

	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {

		// creating connection
		Connection conn = ConnectionFactory.createConnection();

		// prepare query
		String query = "insert into Email (emailAddress, personId) values (?, (select personId from Person where personCode = ?));";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, personCode);

			ps.executeUpdate();
		} catch (SQLException e) {
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		}

		// close connection
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);

	}

	/**
	 * Removes all asset records from the database
	 */
	public static void removeAllAssets() {

		ConnectionFactory.loadDriver();

		// creating connection
		Connection conn = ConnectionFactory.createConnection();

		// prepare query
		String queryPortfolioAsset = "delete from PortfolioAsset;";
		String queryAsset = "delete from Asset;";

		PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(queryPortfolioAsset);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryAsset);
			ps.executeUpdate();

		} catch (SQLException e) {
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		}

		// close connection
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);

	}

	/**
	 * Removes the asset record from the database corresponding to the provided
	 * <code>assetCode</code>
	 * 
	 * @param assetCode
	 */
	public static void removeAsset(String assetCode) {

		// creating connection
		Connection conn = ConnectionFactory.createConnection();

		// prepare query
		String queryPortfolioAsset = "delete from PortfolioAsset where assetId in (select assetId from Asset where code = ?);";
		String queryAsset = "delete from Asset where code = ?;";

		PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(queryPortfolioAsset);
			ps.setString(1, assetCode);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryAsset);
			ps.setString(1, assetCode);
			ps.executeUpdate();

		} catch (SQLException e) {
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		}

		// close connection
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);

	}

	/**
	 * Adds a deposit account asset record to the database with the provided data.
	 * 
	 * @param assetCode
	 * @param label
	 * @param apr       - Annual Percentage Rate on the scale [0, 1]
	 */
	public static void addDepositAccount(String assetCode, String label, double apr) {

		// creating connection
		Connection conn = ConnectionFactory.createConnection();

		// prepare query
		String query = "insert into Asset (code, label, type, apr) values (?, ?, ?, ?);";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, assetCode);
			ps.setString(2, label);
			ps.setString(3, "D");
			ps.setDouble(4, apr);

			ps.executeUpdate();
		} catch (SQLException e) {
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		}

		// close connection
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);

	}

	/**
	 * Adds a private investment asset record to the database with the provided
	 * data.
	 * 
	 * @param assetCode
	 * @param label
	 * @param quarterlyDividend
	 * @param baseRateOfReturn  on the scale [0, 1]
	 * @param baseOmega
	 * @param totalValue
	 */
	public static void addPrivateInvestment(String assetCode, String label, Double quarterlyDividend,
			Double baseRateOfReturn, Double baseOmega, Double totalValue) {

		// creating connection
		Connection conn = ConnectionFactory.createConnection();

		// prepare query
		String query = "insert into Asset (code, label, type, baseQuarterlyDividend, baseRateOfReturn, totalAmount, omega) values (?, ?, ?, ?, ?, ?, ?);";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, assetCode);
			ps.setString(2, label);
			ps.setString(3, "P");
			ps.setDouble(4, quarterlyDividend);
			ps.setDouble(5, baseRateOfReturn);
			ps.setDouble(6, totalValue);
			ps.setDouble(7, baseOmega);

			ps.executeUpdate();
		} catch (SQLException e) {
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		}

		// close connection
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);

	}

	/**
	 * Adds a stock asset record to the database with the provided data.
	 * 
	 * @param assetCode
	 * @param label
	 * @param quarterlyDividend
	 * @param baseRateOfReturn  on the scale [0, 1]
	 * @param beta
	 * @param stockSymbol
	 * @param sharePrice
	 */
	public static void addStock(String assetCode, String label, Double quarterlyDividend, Double baseRateOfReturn,
			Double beta, String stockSymbol, Double sharePrice) {

		// creating connection
		Connection conn = ConnectionFactory.createConnection();

		// prepare query
		String query = "insert into Asset (code, label, type, baseQuarterlyDividend, baseRateOfReturn, beta, stockSymbol, sharePrice) values (?, ?, ?, ?, ?, ?, ?, ?);";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, assetCode);
			ps.setString(2, label);
			ps.setString(3, "S");
			ps.setDouble(4, quarterlyDividend);
			ps.setDouble(5, baseRateOfReturn);
			ps.setDouble(6, beta);
			ps.setString(7, stockSymbol);
			ps.setDouble(8, sharePrice);

			ps.executeUpdate();
		} catch (SQLException e) {
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		}

		// close connection
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);

	}

	/**
	 * Removes all portfolio records from the database
	 */
	public static void removeAllPortfolios() {

		ConnectionFactory.loadDriver();

		// creating connection
		Connection conn = ConnectionFactory.createConnection();

		// prepare query
		String queryPortfolioAsset = "delete from PortfolioAsset;";
		String queryPortfolio = "delete from Portfolio;";

		PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(queryPortfolioAsset);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryPortfolio);
			ps.executeUpdate();

		} catch (SQLException e) {
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		}

		// close connection
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);

	}

	/**
	 * Removes the portfolio record from the database corresponding to the provided
	 * <code>portfolioCode</code>
	 * 
	 * @param portfolioCode
	 */
	public static void removePortfolio(String portfolioCode) {

		// creating connection
		Connection conn = ConnectionFactory.createConnection();

		// prepare query
		String queryPortfolioAsset = "delete from PortfolioAsset where portfolioId in (select portfolioId from Portfolio where code = ?);";
		String queryPortfolio = "delete from Portfolio where code = ?;";

		PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(queryPortfolioAsset);
			ps.setString(1, portfolioCode);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryPortfolio);
			ps.setString(1, portfolioCode);
			ps.executeUpdate();

		} catch (SQLException e) {
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		}

		// close connection
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);

	}

	/**
	 * Adds a portfolio records to the database with the given data. If the
	 * portfolio has no beneficiary, the <code>beneficiaryCode</code> will be
	 * <code>null</code>
	 * 
	 * @param portfolioCode
	 * @param ownerCode
	 * @param managerCode
	 * @param beneficiaryCode
	 */
	public static void addPortfolio(String portfolioCode, String ownerCode, String managerCode,
			String beneficiaryCode) {

		// creating connection
		Connection conn = ConnectionFactory.createConnection();
		
		// prepare query
		String query = null;
		if(beneficiaryCode != null) {
			query = "insert into Portfolio (code, ownerId, managerId, beneficiaryId) values (?, (select personId from Person where personCode = ?), (select personId from Person where personCode = ?), (select personId from Person where personCode = ?));";
		} else {
			query = "insert into Portfolio (code, ownerId, managerId) values (?, (select personId from Person where personCode = ?), (select personId from Person where personCode = ?))";
		}

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, portfolioCode);
			ps.setString(2, ownerCode);
			ps.setString(3, managerCode);
			
			if(beneficiaryCode != null) {
				ps.setString(4, beneficiaryCode);
			} 
			

			ps.executeUpdate();
		} catch (SQLException e) {
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		}

		// close connection
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);

	}

	/**
	 * Associates the asset record corresponding to <code>assetCode</code> with the
	 * portfolio corresponding to the provided <code>portfolioCode</code>. The third
	 * parameter, <code>value</code> is interpreted as a <i>balance</i>, <i>number
	 * of shares</i> or <i>stake percentage</i> (on the scale [0, 1]) depending on
	 * the type of asset the <code>assetCode</code> is associated with.
	 * 
	 * @param portfolioCode
	 * @param assetCode
	 * @param value
	 */
	public static void addAsset(String portfolioCode, String assetCode, double value) {

		// creating connection
		Connection conn = ConnectionFactory.createConnection();

		// prepare query
		String query = "insert into PortfolioAsset (portfolioId, assetId, value) values"
				+ "	((select portfolioId from Portfolio where code = ?), (select assetId from Asset where code = ?), ?);";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, portfolioCode);
			ps.setString(2, assetCode);
			ps.setDouble(3, value);

			ps.executeUpdate();
		} catch (SQLException e) {
			ConnectionFactory.closeConnection(ps);
			ConnectionFactory.closeConnection(conn);
			throw new RuntimeException(e);
		}

		// close connection
		ConnectionFactory.closeConnection(ps);
		ConnectionFactory.closeConnection(conn);

	}

}
