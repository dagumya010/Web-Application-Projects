package mediFind.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.management.RuntimeErrorException;

import mediFind.model.*;

public class AddressDao {
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static AddressDao instance = null;
	protected AddressDao() {
		connectionManager = new ConnectionManager();
	}
	public static AddressDao getInstance() {
		if(instance == null) {
			instance = new AddressDao();
		}
		return instance;
	} 
	
	public Address create(Address address) throws SQLException{
		String insertAddress = "INSERT INTO address(AddressType,Street,City,State,ZipCode) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAddress, PreparedStatement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, address.getAddressType().name());
			insertStmt.setString(2, address.getStreet());
			insertStmt.setString(3, address.getCity());
			insertStmt.setString(4, address.getState());
			insertStmt.setInt(5, address.getZipCode());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int addressId = -1;
			if(resultKey.next()) {
				addressId = resultKey.getInt(1);
				address.setAddressId(addressId);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			return address;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public Address getAddressById(int addressId) throws SQLException{
		String selectRestaurant = "SELECT AddressId,AddressType,Street,City,State,ZipCode "
				+ "FROM address WHERE addressId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setInt(1, addressId);	
			results = selectStmt.executeQuery();
			if(results.next()) {
				Integer resultId = results.getInt("AddressId");
				String resultType = results.getString("AddressType");
				String resultStreet = results.getString("Street");
				String resultCity = results.getString("City");
				String resultState = results.getString("State");
				Integer resultZip = results.getInt("ZipCode");
				
				Address address = new Address(resultId,resultType, resultStreet, resultCity, resultState, resultZip);
				return address;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public void deleteAddressById(int addressId) throws SQLException {
		String deleteCreditCard = "DELETE FROM address WHERE AddressId=?;";
		try (Connection connection = connectionManager.getConnection();
				PreparedStatement deleteStmt = connection.prepareStatement(deleteCreditCard)) {
			deleteStmt.setInt(1, addressId);
			deleteStmt.executeUpdate();
		}
	}
	
	public Address updateAddresswithId(Integer aId, Address address) throws SQLException{
		String updateAdd = "UPDATE address SET Street=?,City=?,State=?,ZipCode=? "
				+ "WHERE AddressId=?;";
		
		try (Connection connection = connectionManager.getConnection();
				PreparedStatement updateStmt = connection.prepareStatement(updateAdd)) {
			updateStmt.setString(1, address.getStreet());
			updateStmt.setString(2, address.getCity());
			updateStmt.setString(3, address.getState());
			updateStmt.setInt(4, address.getZipCode());
			updateStmt.setInt(5, aId);
			
			updateStmt.executeUpdate();
			
			return address;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
