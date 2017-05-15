package mediFind.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediFind.model.*;

public class InsuranceDao {
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static InsuranceDao instance = null;
	protected InsuranceDao() {
		connectionManager = new ConnectionManager();
	}
	public static InsuranceDao getInstance() {
		if(instance == null) {
			instance = new InsuranceDao();
		}
		return instance;
	}
	
	public Insurance getInsuranceById(int insuranceId) throws SQLException{
		String selectIns = "SELECT InsuranceId,Name "
				+ "FROM insurance WHERE InsuranceId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectIns);
			selectStmt.setInt(1, insuranceId);	
			results = selectStmt.executeQuery();
			if(results.next()) { 
				Integer resultId = results.getInt("InsuranceId");
				String resultName = results.getString("Name");
				
				Insurance insurance = new Insurance(resultId,resultName);
				return insurance;
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
	
	public Insurance getInsuranceByName(String insName) throws SQLException{
		String selectIns = "SELECT InsuranceId,Name "
				+ "FROM insurance WHERE Name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectIns);
			selectStmt.setString(1, insName);	
			results = selectStmt.executeQuery();
			if(results.next()) { 
				Integer resultId = results.getInt("InsuranceId");
				String resultName = results.getString("Name");
				
				Insurance insurance = new Insurance(resultId,resultName);
				return insurance;
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
	
	public List<Insurance> getAllInsurances() throws SQLException {
		String getAll = "SELECT InsuranceId, Name FROM insurance";
		List<Insurance> insurancesList = new ArrayList<>();
		try (Connection connection = connectionManager.getConnection();
				PreparedStatement selectStmt = connection.prepareStatement(getAll)) {
			ResultSet resultSet = selectStmt.executeQuery();
			while (resultSet.next()) {
				insurancesList.add(
						new Insurance(resultSet.getInt("InsuranceId"), resultSet.getString("Name")));
			}
		}
		return insurancesList;
	}
}
