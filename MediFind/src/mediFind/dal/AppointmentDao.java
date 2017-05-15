package mediFind.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import mediFind.model.Appointment;
import mediFind.model.HealthCareFacility;
import mediFind.model.Users;

public class AppointmentDao {
	protected ConnectionManager connectionManager;

	private static AppointmentDao instance = null;
	protected AppointmentDao() {
		connectionManager = new ConnectionManager();
	}
	public static AppointmentDao getInstance() {
		if(instance == null) {
			instance = new AppointmentDao();
		}
		return instance;
	}
	
	public Appointment create(Appointment appointment) throws SQLException{
		String insertAppointment = "INSERT INTO appointment(UserName,"
				+ "MediFacilityId,AppointmentDate,AppointmentTime,Prescription) VALUES (?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAppointment, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, appointment.getUserName().getUserName());
			insertStmt.setInt(2, appointment.getMediFacility().getMediFacilityId());
			insertStmt.setDate(3, appointment.getAppointmentDate());
			insertStmt.setTime(4, appointment.getAppointmentTime());
			insertStmt.setString(5, appointment.getPrescription());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int appId = -1;
			if(resultKey.next()) {
				appId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			appointment.setAppointmentId(appId);
			return appointment;
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}finally{
			if(connection != null){
				connection.close();
			}if(insertStmt != null){
				insertStmt.close();
			}
		}
	}
	
	
	public List<Appointment> getAppointmentsByUserName(String userName) throws SQLException{
		List<Appointment> appointments = new ArrayList<Appointment>();
		String selectAppointment =
				"SELECT * " +
				"FROM appointment inner join healthcarefacility on healthcarefacility.MediFacilityId = appointment.MediFacilityId " +
				"WHERE appointment.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		HealthCareFacilityDao healthCareFacilityDao = HealthCareFacilityDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAppointment);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int mediFacilityId = results.getInt("MediFacilityId");
				HealthCareFacility healthCareFacility = healthCareFacilityDao.getHealthCareFacilityById(mediFacilityId);
				int appointmentId= results.getInt("AppointmentId");
				Date appointmentDate = results.getDate("AppointmentDate");
				Time appointmentTime = results.getTime("AppointmentTime");
				String prescription = results.getString("Prescription");
				Users user = usersDao.getUserByUserName(userName);
				
				Appointment appointment = new Appointment(appointmentId, healthCareFacility, user, appointmentDate, appointmentTime, prescription);
				appointments.add(appointment);
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
		return appointments;
	}
		
		public Appointment delete(Integer aId) throws SQLException{
			String deleteString = "DELETE FROM appointment WHERE AppointmentId=?;";
			Connection connection = null;
			PreparedStatement deleteStmt = null; 
			
			try {
				connection = connectionManager.getConnection();
				deleteStmt = connection.prepareStatement(deleteString);
				deleteStmt.setInt(1, aId);
				deleteStmt.executeUpdate();
				return null;
			}catch(SQLException e){
				e.printStackTrace();
				throw e;
		}finally{
			if (connection != null){
				connection.close();
			}if(deleteStmt != null){
				deleteStmt.close();
			}
		}
		}
}
		