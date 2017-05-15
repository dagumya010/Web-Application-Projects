package mediFind.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediFind.model.Address;
import mediFind.model.Clinic;
import mediFind.model.Owners;
import mediFind.model.HealthCareFacility.MyEnum;

public class ClinicDao {
	protected ConnectionManager connectionManager;
	
	private static ClinicDao instance = null;
	protected ClinicDao() {
		connectionManager = new ConnectionManager();
	}
	public static ClinicDao getInstance() {
		if(instance == null) {
			instance = new ClinicDao();
		}
		return instance;
	}
	
	public Clinic getClinicById(int mediFacilityId) throws SQLException {
		String selectClinic =
				"SELECT * " +
				"FROM healthcarefacility inner join clinic on healthcarefacility.MediFacilityId = clinic.MediFacilityId " +
				"inner join owners on healthcarefacility.OwnerId = owners.OwnerId " +
				"inner join address on healthcarefacility.addressId = address.addressId " +
				"WHERE MediFacilityId=?;";
			
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectClinic);
			selectStmt.setInt(1, mediFacilityId);
			results = selectStmt.executeQuery();
			OwnersDao ownersDao = OwnersDao.getInstance();
			AddressDao addressDao = AddressDao.getInstance();
			HealthCareFacilityDao healthCareFacilityDao = HealthCareFacilityDao.getInstance();
			if(results.next()) {
				String Name = results.getString("Name");
				int ownerId = results.getInt("OwnerId");
				Owners newOwner = ownersDao.getOwnerById(ownerId);
				int addressId = results.getInt("AddressId");
				Address newAddress = addressDao.getAddressById(addressId);
				Boolean appointment = results.getBoolean("Appointments");
				Boolean parking = results.getBoolean("Parking");
				String hours = results.getString("Hours");
				MyEnum timeliness = Enum.valueOf(MyEnum.class, results.getString("Timeliness"));
				MyEnum effectiveness = Enum.valueOf(MyEnum.class, results.getString("Effectiveness"));
				MyEnum safety = Enum.valueOf(MyEnum.class, results.getString("Safety"));
				
				Clinic clinic = new Clinic(mediFacilityId,Name,newOwner,newAddress,appointment,parking,hours,
						timeliness,effectiveness,safety);
				return clinic;
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
	
	public List<Clinic> getClinicsByZipCode(int zipCode) throws SQLException {
		List<Clinic> clinics = new ArrayList<Clinic>();
		String selectClinic =
				"SELECT * " +
				"FROM healthcarefacility inner join clinic on healthcarefacility.MediFacilityId = clinic.MediFacilityId " +
				"inner join owners on healthcarefacility.OwnerId = owners.OwnerId " +
				"inner join address on healthcarefacility.addressId = address.addressId " +	
				"WHERE address.ZipCode=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			OwnersDao ownersDao = OwnersDao.getInstance();
			AddressDao addressDao = AddressDao.getInstance();
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectClinic);
				selectStmt.setInt(1, zipCode);
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int mediFacilityId = results.getInt("MediFacilityId");
					Boolean erAvailability = results.getBoolean("ERAvailability");
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
					
					Clinic clinic = new Clinic(mediFacilityId,Name,newOwner,newAddress,appointment,parking,hours,
							MyEnum.ABOVE,MyEnum.ABOVE,MyEnum.ABOVE);
					
					clinics.add(clinic);
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
			return clinics;
	}

}
