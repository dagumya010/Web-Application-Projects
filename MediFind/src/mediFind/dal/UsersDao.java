package mediFind.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mediFind.model.*;

public class UsersDao {
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	} 
	
	public Users create(Users user) throws SQLException{
		
		String insertUser = "INSERT INTO users(UserName,Password,FirstName,LastName,PhoneNumber,Email,"
				+ "AddressId,InsuranceId) VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, user.getUserName());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getFirstName());
			insertStmt.setString(4, user.getLastName());
			insertStmt.setString(5, user.getPhoneNumber());
			insertStmt.setString(6, user.getEmail());
			insertStmt.setInt(7, user.getAddress().getAddressId());
			insertStmt.setInt(8, user.getInsurance().getInsuranceId());
					
			insertStmt.executeUpdate();	
			return user;
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
	
	public Users getUserByUserName(String username) throws SQLException{
		System.out.println("here!!!!!!!");
		String selectUser = "SELECT UserName,Password,FirstName,LastName,PhoneNumber,Email,"
				+ "AddressId,InsuranceId FROM users WHERE UserName=?;";
		
		ResultSet results = null;
		try (Connection connection = connectionManager.getConnection();
				PreparedStatement selectStmt = connection.prepareStatement(selectUser)) {
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();
			AddressDao aDoa = AddressDao.getInstance();
			InsuranceDao insuranceDao = InsuranceDao.getInstance();
			if(results.next()) {
				String resultUName = results.getString("UserName");
				String resultPass = results.getString("Password");
				String resultFName = results.getString("FirstName");
				String resultLName = results.getString("LastName");
				String resultPhone = results.getString("PhoneNumber");
				String resultEmail = results.getString("Email");
				Integer resultAddress = results.getInt("AddressId");
				Integer resultInsurance = results.getInt("InsuranceId");
				Insurance i = insuranceDao.getInsuranceById(resultInsurance);
				Address a = aDoa.getAddressById(resultAddress);
				
				Users user = new Users(resultUName,resultPass,resultFName,resultLName,resultPhone,resultEmail,a,i);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public Users updateUser(String username, Users user) throws SQLException {
		String updateUser = "UPDATE users SET FirstName=?,LastName=?,PhoneNumber=?,Email=?,"
				+ "InsuranceId=? WHERE UserName=?;";
		try (Connection connection = connectionManager.getConnection();
				PreparedStatement updateStmt = connection.prepareStatement(updateUser)) {
			updateStmt.setString(1, user.getFirstName());
			updateStmt.setString(2, user.getLastName());
			updateStmt.setString(3, user.getPhoneNumber());
			updateStmt.setString(4, user.getEmail());
			updateStmt.setInt(5, user.getInsurance().getInsuranceId());
			updateStmt.setString(6, username);
			
			updateStmt.executeUpdate();
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}