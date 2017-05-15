package mediFind.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mediFind.model.Speciality;

public class SpecialityDao {
	
	protected ConnectionManager connectionManager;

	private static SpecialityDao instance = null;
	protected SpecialityDao() {
		connectionManager = new ConnectionManager();
	}
	public static SpecialityDao getInstance() {
		if(instance == null) {
			instance = new SpecialityDao();
		}
		return instance;
	}
	
	public Speciality create(Speciality speciality) throws SQLException{
		String create = "INSERT INTO Speciality(SpecialityId,Speciality) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try{
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(create);
			insertStmt.setInt(1, speciality.getSpecialtyId());
			insertStmt.setString(2, speciality.getSpeciality());
			insertStmt.executeUpdate();
			return speciality;
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public Speciality getSpecialityById(int specialityId) throws SQLException {
		String selectSpeciality = 
				"SELECT * FROM medifindapplication.speciality where SpecialityId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try{
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSpeciality);
			selectStmt.setInt(1, specialityId);
			results = selectStmt.executeQuery();
			if(results.next()){
				int specialityId1 = results.getInt("SpecialityId");
				String speciality = results.getString("Speciality");
				
				Speciality speciality1 = new Speciality(specialityId1,speciality);
				return speciality1;
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}finally {
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
	
	public Speciality delete(Speciality speciality) throws SQLException{
		String deleteSpeciality = "DELETE FROM Speciality WHERE SpecialityId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		
		try{
			connection = connectionManager.getConnection();
			 deleteStmt = connection.prepareStatement(deleteSpeciality);
			 deleteStmt.setInt(1, speciality.getSpecialtyId());
			 deleteStmt.executeUpdate();
			return null;
	}catch (SQLException e) {
		e.printStackTrace();
		throw e;
	} finally {
		if(connection != null){
			connection.close();
		}if(deleteSpeciality != null){
			deleteStmt.close();
		}
	}

}
}
