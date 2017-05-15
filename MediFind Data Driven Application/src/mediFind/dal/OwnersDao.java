package mediFind.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mediFind.model.Owners;

public class OwnersDao {
	
protected ConnectionManager connectionManager;
	
	private static OwnersDao instance = null;
	protected OwnersDao() {
		connectionManager = new ConnectionManager();
	}
	public static OwnersDao getInstance() {
		if(instance == null) {
			instance = new OwnersDao();
		}
		return instance;
	}
	 
	public Owners getOwnerById(int ownerId) throws SQLException {
		String selectOwners =
				"SELECT * " +
				"FROM  owners " +
				"WHERE OwnerId=?;";
			
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOwners);
			selectStmt.setInt(1, ownerId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				String Name = results.getString("OwnerName");
				Owners newOwner = new Owners(ownerId,Name);
			
				return newOwner;
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
