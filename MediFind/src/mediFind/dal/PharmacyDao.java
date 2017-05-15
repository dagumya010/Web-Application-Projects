package mediFind.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediFind.model.Address;
import mediFind.model.Hospital;
import mediFind.model.Owners;
import mediFind.model.Pharmacy;
import mediFind.model.HealthCareFacility.MyEnum;

public class PharmacyDao {

	
protected ConnectionManager connectionManager;
	
	private static PharmacyDao instance = null;
	protected PharmacyDao() {
		connectionManager = new ConnectionManager();
	}
	public static PharmacyDao getInstance() {
		if(instance == null) {
			instance = new PharmacyDao();
		}
		return instance;
	}
	
	public Pharmacy getPharmacyById(int mediFacilityId) throws SQLException {
		String selectPharmacy =
				"SELECT * " +
				"FROM healthcarefacility inner join pharmacy on healthcarefacility.MediFacilityId = pharmacy.MediFacilityId " +
				"inner join owners on healthcarefacility.OwnerId = owners.OwnerId " +
				"inner join address on healthcarefacility.addressId = address.addressId " +
				"WHERE MediFacilityId=?;";
			
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPharmacy);
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
				
				Pharmacy pharmacy = new Pharmacy(mediFacilityId,Name,newOwner,newAddress,appointment,parking,hours,
						timeliness,effectiveness,safety);
				return pharmacy;
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
	
	public List<Pharmacy> getPharmaciesByZipCode(int zipCode) throws SQLException {
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
		String selectPharmacy =
				"SELECT * " +
				"FROM healthcarefacility inner join pharmacy on healthcarefacility.MediFacilityId = pharmacy.MediFacilityId " +
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
				selectStmt = connection.prepareStatement(selectPharmacy);
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
					
					Pharmacy pharmacy = new Pharmacy(mediFacilityId,Name,newOwner,newAddress,appointment,parking,hours,
							MyEnum.ABOVE,MyEnum.ABOVE,MyEnum.ABOVE);
					
					pharmacies.add(pharmacy);
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
			return pharmacies;
	}
}
