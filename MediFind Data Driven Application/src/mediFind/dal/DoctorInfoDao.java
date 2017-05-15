package mediFind.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediFind.model.DoctorInfo;
import mediFind.model.HealthCareFacility;
import mediFind.model.Speciality;

public class DoctorInfoDao {
	protected ConnectionManager connectionManager;
	private static DoctorInfoDao instance = null;
	protected DoctorInfoDao() {
		connectionManager = new ConnectionManager();
	}
	public static DoctorInfoDao getInstance() {
		if(instance == null){
			instance = new DoctorInfoDao();
		}
		return instance;
	}
	
	/*
	 * CREATE - INSERT STATEMENT
	 */
public DoctorInfo create(DoctorInfo doctorInfo) throws SQLException{
		String insertIntoDoctorInfo = "INSERT INTO DoctorInfo(DoctorId,MediFacilityId,SpecialityId,FirstName,LastName,Hours,Gender) VALUES (?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertIntoDoctorInfo);
			insertStmt.setInt(1, doctorInfo.getDoctorId());
			insertStmt.setInt(2, doctorInfo.getMediFacility().getMediFacilityId());
			insertStmt.setInt(3, doctorInfo.getSpeciality().getSpecialtyId());
			insertStmt.setString(4, doctorInfo.getFirstName());
			insertStmt.setString(5, doctorInfo.getLastName());
			insertStmt.setString(6, doctorInfo.getHour());
			insertStmt.setString(7, doctorInfo.getGender().name());		
			insertStmt.executeUpdate();
			return doctorInfo;
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}finally{
			if(connection != null){
				connection.close();
			}if(connection != null){
				connection.close();
			}
		}		
	}

public List<DoctorInfo> getDoctorInfoByHealthCareFacilityId(int medifacilityId) throws SQLException {
	List<DoctorInfo> doctors = new ArrayList<DoctorInfo>();
	String selectDoctors = "SELECT Intermediate.DoctorId, Intermediate.MediFacilityId, Intermediate.SpecialityId, Intermediate.FirstName, Intermediate.LastName, Intermediate.hours, Intermediate.gender from healthcarefacility inner join (SELECT doctorinfo.DoctorId ,doctorinfo.MediFacilityId, doctorinfo.SpecialityId, doctorinfo.FirstName, doctorinfo.LastName, doctorinfo.hours, doctorinfo.gender FROM doctorinfo inner join speciality on doctorinfo.SpecialityId = speciality.SpecialityId) AS Intermediate on healthcarefacility.MediFacilityId = Intermediate.MediFacilityId where healthcarefacility.MediFacilityId = ?;";
	Connection connection = null; 
	PreparedStatement selectStmt = null; 
	ResultSet results = null;
	
	try{
		connection = connectionManager.getConnection();
		selectStmt = connection.prepareStatement(selectDoctors);
		selectStmt.setInt(1, medifacilityId);
		results = selectStmt.executeQuery();
		SpecialityDao specialityDao = SpecialityDao.getInstance();
		HealthCareFacilityDao healthcarefacilityDao = HealthCareFacilityDao.getInstance();
		while(results.next()){
			int doctorId = results.getInt(1);
			//System.out.println("BBBB"+doctorId);
			HealthCareFacility mediFacilityId = healthcarefacilityDao.getHealthCareFacilityById(results.getInt(2));
			//System.out.println(mediFacilityId.getMediFacilityId());
			Speciality doctor_speciality  = specialityDao.getSpecialityById(results.getInt(3));
			//System.out.println(doctor_speciality.getSpeciality());
			String firstName = results.getString(4);
			//System.out.println(firstName);
			String lastName = results.getString(5);
			//System.out.println(lastName);
			String hours = results.getString(6);
			//System.out.println(hours);
			DoctorInfo.Gender gender = DoctorInfo.Gender.valueOf(results.getString(7));
			//System.out.println(gender); 
			DoctorInfo doctorInformation = new DoctorInfo(doctorId, mediFacilityId, doctor_speciality, firstName, lastName, hours, gender );
			doctors.add(doctorInformation);		
		}
		
	}catch (SQLException e) {
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
	return doctors;
	
}


public DoctorInfo delete(DoctorInfo doctorInfo)throws SQLException{
	String deleteDoctor = "DELETE FROM DoctorInfo WHERE DoctorInfo=?;";
	Connection connection = null; 
	PreparedStatement deleteStmt = null; 
	
	try{
		connection = connectionManager.getConnection();
		deleteStmt = connection.prepareStatement(deleteDoctor);
		deleteStmt.setInt(1, doctorInfo.getDoctorId());
		deleteStmt.executeUpdate();
		return null;
	}catch(SQLException e){
		e.printStackTrace();
		throw e;
	}finally {
		if(connection != null){
			connection.close();
		}
		if(deleteStmt != null){
			deleteStmt.close();
		}
	}
	
}
}
