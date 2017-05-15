package mediFind.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediFind.model.Address;
import mediFind.model.HealthCareFacility;
import mediFind.model.Owners;
import mediFind.model.HealthCareFacility.MyEnum;
import mediFind.model.Hospital;

public class HospitalDao {
	
protected ConnectionManager connectionManager;
	
	private static HospitalDao instance = null;
	protected HospitalDao() {
		connectionManager = new ConnectionManager();
	}
	public static HospitalDao getInstance() {
		if(instance == null) {
			instance = new HospitalDao();
		}
		return instance;
	}
	
	public Hospital getHospitalById(int mediFacilityId) throws SQLException {
		String selectHospital =
				"SELECT * " +
				"FROM healthcarefacility inner join hospital on healthcarefacility.MediFacilityId = hospital.MediFacilityId " +
				"inner join owners on healthcarefacility.OwnerId = owners.OwnerId " +
				"inner join address on healthcarefacility.addressId = address.addressId " +
				"WHERE MediFacilityId=?;";
			
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHospital);
			selectStmt.setInt(1, mediFacilityId);
			results = selectStmt.executeQuery();
			OwnersDao ownersDao = OwnersDao.getInstance();
			AddressDao addressDao = AddressDao.getInstance();
			HealthCareFacilityDao healthCareFacilityDao = HealthCareFacilityDao.getInstance();
			if(results.next()) {
				Boolean erAvailability = results.getBoolean("ERAvailability");
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
				
				Hospital hospital = new Hospital(mediFacilityId,Name,newOwner,newAddress,appointment,parking,hours,
						timeliness,effectiveness,safety,erAvailability);
				return hospital;
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
	
	public List<Hospital> getHospitalByZipCode(int zipCode) throws SQLException {
		List<Hospital> hospitals = new ArrayList<Hospital>();
		String selectHospitals =
				"SELECT hospital.MediFacilityId, healthcarefacility.Name, healthcarefacility.OwnerId, "
				+ "healthcarefacility.AddressId, healthcarefacility.Appointments, healthcarefacility.Parking,"
				+ "healthcarefacility.Hours, hospital.ERAvailability " +
				"FROM healthcarefacility inner join hospital on healthcarefacility.MediFacilityId = hospital.MediFacilityId " +
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
				selectStmt = connection.prepareStatement(selectHospitals);
				selectStmt.setInt(1, zipCode);
				results = selectStmt.executeQuery();
				
				while(results.next()) {
					int mediFacilityId = results.getInt(1);
					String Name = results.getString(2);
					int ownerId = results.getInt(3);
					Owners newOwner = ownersDao.getOwnerById(ownerId);
					int addressId = results.getInt(4);
					Address newAddress = addressDao.getAddressById(addressId);
					Boolean appointment = results.getBoolean(5);
					Boolean parking = results.getBoolean(6);
					String hours = results.getString(7);
					Boolean erAvailability = results.getBoolean(8);
					//MyEnum timeliness = Enum.valueOf(MyEnum.class, results.getString("Timeliness"));
					//MyEnum effectiveness = Enum.valueOf(MyEnum.class, results.getString("Effectiveness"));
					//MyEnum safety = Enum.valueOf(MyEnum.class, results.getString("Safety"));
					
					Hospital hospital = new Hospital(mediFacilityId, Name,newOwner,newAddress,appointment,parking,hours,
							MyEnum.ABOVE,MyEnum.ABOVE,MyEnum.ABOVE,erAvailability);
					
					hospitals.add(hospital);
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
			return hospitals;
	}
	
	
	public List<Hospital> getHospitalWithERByZipCode(int zipCode) throws SQLException {
		List<Hospital> hospitals = new ArrayList<Hospital>();
		String selectHospitals =
				"SELECT * " +
				"FROM healthcarefacility inner join hospital on healthcarefacility.MediFacilityId = hospital.MediFacilityId " +
				"inner join owners on healthcarefacility.OwnerId = owners.OwnerId " +
				"inner join address on healthcarefacility.addressId = address.addressId " +	
				"WHERE address.ZipCode=? and hospital.ERAvailability =1;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			OwnersDao ownersDao = OwnersDao.getInstance();
			AddressDao addressDao = AddressDao.getInstance();
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectHospitals);
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
					MyEnum timeliness = Enum.valueOf(MyEnum.class, results.getString("Timeliness"));
					MyEnum effectiveness = Enum.valueOf(MyEnum.class, results.getString("Effectiveness"));
					MyEnum safety = Enum.valueOf(MyEnum.class, results.getString("Safety"));
					
					Hospital hospital = new Hospital(mediFacilityId,Name,newOwner,newAddress,appointment,parking,hours,
							timeliness,effectiveness,safety,erAvailability);
					
					hospitals.add(hospital);
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
			return hospitals;
	}

}
