package mediFind.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediFind.model.Address;
import mediFind.model.HealthCareFacility;
import mediFind.model.HealthCareFacility.MyEnum;
import mediFind.model.Owners;


public class HealthCareFacilityDao {

protected ConnectionManager connectionManager;
	
	private static HealthCareFacilityDao instance = null;
	protected HealthCareFacilityDao() {
		connectionManager = new ConnectionManager();
	}
	public static HealthCareFacilityDao getInstance() {
		if(instance == null) {
			instance = new HealthCareFacilityDao();
		}
		return instance;
	}
	
	public HealthCareFacility getHealthCareFacilityById(int mediFacilityId) throws SQLException {
		String selectHealthCareFacility =
				"SELECT * " +
				"FROM healthcarefacility inner join owners on healthcarefacility.OwnerId = owners.OwnerId " +
				"inner join address on healthcarefacility.addressId = address.addressId " +
				"WHERE MediFacilityId=?;";
			
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHealthCareFacility);
			selectStmt.setInt(1, mediFacilityId);
			results = selectStmt.executeQuery();
			OwnersDao ownersDao = OwnersDao.getInstance();
			AddressDao addressDao = AddressDao.getInstance();
			if(results.next()) {
				String Name = results.getString("Name");
				int ownerId = results.getInt("OwnerId");
				Owners newOwner = ownersDao.getOwnerById(ownerId);
				int addressId = results.getInt("AddressId");
				Address newAddress = addressDao.getAddressById(addressId);
				Boolean appointment = results.getBoolean("Appointments");
				Boolean parking = results.getBoolean("Parking");
				String hours = results.getString("Hours");
				//MyEnum timeliness = MyEnum.valueOf(MyEnum.class, results.getString("Timeliness"));
				//MyEnum effectiveness = MyEnum.valueOf(MyEnum.class, results.getString("Effectiveness"));
				//MyEnum safety = MyEnum.valueOf(MyEnum.class, results.getString("Safety"));
				 
				HealthCareFacility healthCareFacility = new HealthCareFacility(mediFacilityId,Name,newOwner,newAddress,appointment,parking,hours,
						MyEnum.ABOVE,MyEnum.ABOVE,MyEnum.ABOVE);
				return healthCareFacility;
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
	
	public List<HealthCareFacility> getHealthCareFacilityByZipCode(int zipCode) throws SQLException {
		List<HealthCareFacility> healthCareFacilities = new ArrayList<HealthCareFacility>();
		String selectHealthCareFacilities =
				"SELECT * " +
				"FROM healthcarefacility inner join owners on healthcarefacility.OwnerId = owners.OwnerId " +
				"inner join address on healthcarefacility.addressId = address.addressId " +
				"WHERE address.ZipCode=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			OwnersDao ownersDao = OwnersDao.getInstance();
			AddressDao addressDao = AddressDao.getInstance();
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectHealthCareFacilities);
				selectStmt.setInt(1, zipCode);
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int mediFacilityId = results.getInt("MediFacilityId");
					String Name = results.getString("Name");
					int ownerId = results.getInt("OwnerId");
					Owners newOwner = ownersDao.getOwnerById(ownerId);
					int addressId = results.getInt("AddressId");
					Address newAddress = addressDao.getAddressById(addressId);
					Boolean appointment = results.getBoolean("Appointments");
					Boolean parking = results.getBoolean("Parking");
					String hours = results.getString("Hours");
					//MyEnum timeliness = Enum.valueOf(MyEnum.class, results.getString("Timeliness"));
					//MyEnum effectiveness = Enum.valueOf(MyEnum.class, results.getString("Effectiveness"));
					//MyEnum safety = Enum.valueOf(MyEnum.class, results.getString("Safety"));
					
					HealthCareFacility healthCareFacility = new HealthCareFacility(mediFacilityId,Name,newOwner,newAddress,appointment,parking,hours,
							MyEnum.ABOVE,MyEnum.ABOVE,MyEnum.ABOVE);
					healthCareFacilities.add(healthCareFacility);
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
			return healthCareFacilities;
	}
	
	public List<HealthCareFacility> getHealthCareFacilityByInsuranceId(int insId, int zip) throws SQLException {
		List<HealthCareFacility> healthCareFacilities = new ArrayList<HealthCareFacility>();
		String selectHealthCareFacilities =
				"SELECT * " +
				"FROM healthcarefacility inner join owners on healthcarefacility.OwnerId = owners.OwnerId " +
				"inner join address on healthcarefacility.addressId = address.addressId " +
				"inner join insurancepartners on healthcarefacility.MediFacilityId = insurancepartners.MediFacilityId " +
				"WHERE insurancepartners.insuranceId=? AND address.ZipCode=? limit 10;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			OwnersDao ownersDao = OwnersDao.getInstance();
			AddressDao addressDao = AddressDao.getInstance();
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectHealthCareFacilities);
				selectStmt.setInt(1, insId);
				selectStmt.setInt(2, zip);
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int mediFacilityId = results.getInt("MediFacilityId");
					String Name = results.getString("Name");
					int ownerId = results.getInt("OwnerId");
					Owners newOwner = ownersDao.getOwnerById(ownerId);
					int addressId = results.getInt("AddressId");
					Address newAddress = addressDao.getAddressById(addressId);
					Boolean appointment = results.getBoolean("Appointments");
					Boolean parking = results.getBoolean("Parking");
					String hours = results.getString("Hours");
					//MyEnum timeliness = Enum.valueOf(MyEnum.class, results.getString("Timeliness"));
					//MyEnum effectiveness = Enum.valueOf(MyEnum.class, results.getString("Effectiveness"));
					//MyEnum safety = Enum.valueOf(MyEnum.class, results.getString("Safety"));
					
					HealthCareFacility healthCareFacility = new HealthCareFacility(mediFacilityId,Name,newOwner,newAddress,appointment,parking,hours,
							MyEnum.ABOVE,MyEnum.ABOVE,MyEnum.ABOVE);
					healthCareFacilities.add(healthCareFacility);
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
			return healthCareFacilities;
	}
	
}
