package mediFind.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mediFind.model.*;

public class InsurancePartnersDao {
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static InsurancePartnersDao instance = null;
	protected InsurancePartnersDao() {
		connectionManager = new ConnectionManager();
	}
	public static InsurancePartnersDao getInstance() {
		if(instance == null) {
			instance = new InsurancePartnersDao();
		}
		return instance;
	}
	
	public InsurancePartners getInsurancePartnersByInsuranceId(Insurance ins) throws SQLException{
		String selectRestaurant = "SELECT InsuranceId,MediFacilityId"
				+ "FROM insurancepartners WHERE InsuranceId=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setInt(1, ins.getInsuranceId());
			results = selectStmt.executeQuery();
			InsuranceDao iDoa = InsuranceDao.getInstance();
			HealthCareFacilityDao hDao = HealthCareFacilityDao.getInstance();
			
			if(results.next()) {
				Integer resultIns = results.getInt("InsuranceId");
				Integer resultMed = results.getInt("MediFacilityId");
				Insurance i = iDoa.getInsuranceById(resultIns);
				HealthCareFacility h = hDao.getHealthCareFacilityById(resultMed);
				InsurancePartners inspartners = new InsurancePartners(i,h);
				return inspartners;
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
	
	public InsurancePartners getInsurancePartnersByHealthCareId(HealthCareFacility hc) throws SQLException{
		String selectRestaurant = "SELECT InsuranceId,MediFacilityId"
				+ "FROM insurancepartners WHERE MediFacilityId=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setInt(1, hc.getMediFacilityId());
			results = selectStmt.executeQuery();
			InsuranceDao iDoa = InsuranceDao.getInstance();
			HealthCareFacilityDao hDao = HealthCareFacilityDao.getInstance();
			
			if(results.next()) {
				Integer resultIns = results.getInt("InsuranceId");
				Integer resultMed = results.getInt("MediFacilityId");
				Insurance i = iDoa.getInsuranceById(resultIns);
				HealthCareFacility h = hDao.getHealthCareFacilityById(resultMed);
				InsurancePartners inspartners = new InsurancePartners(i,h);
				return inspartners;
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
}
