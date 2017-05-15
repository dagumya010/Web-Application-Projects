package mediFind.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mediFind.model.HealthCareFacility;
import mediFind.model.RatingAndReview;
import mediFind.model.Users;

public class RatingAndReviewDao {

	protected ConnectionManager connectionManager;
	private static RatingAndReviewDao instance = null;

	protected RatingAndReviewDao() {
		connectionManager = new ConnectionManager();
	}

	public static RatingAndReviewDao getInstance() {
		if (instance == null) {
			instance = new RatingAndReviewDao();
		}
		return instance;
	}

	public RatingAndReview create(RatingAndReview rating) throws SQLException {
		String insertRating = "INSERT INTO ratingandreview(Username,MediFacilityId,Ratings,Reviews) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRating,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, rating.getUserName().getUserName());
			insertStmt.setInt(2, rating.getMediFacility().getMediFacilityId());
			insertStmt.setFloat(3, rating.getRatings());
			insertStmt.setString(4, rating.getReviews());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int ratingsAndReviewId = -1;
			if(resultKey.next()) {
				ratingsAndReviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			rating.setRatingAndReviewId(ratingsAndReviewId);
			
			return rating;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	public RatingAndReview delete(RatingAndReview rating) throws SQLException {
		String deleteRating = "DELETE FROM ratingandreview WHERE RatingAndReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;

		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRating);
			deleteStmt.setInt(1, rating.getRatingAndReviewId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	public List<RatingAndReview> getReviewsByMEdiFacilityId(Integer mediId) throws SQLException {
		List<RatingAndReview> reviews = new ArrayList<RatingAndReview>();
		String selectReviews =
			"SELECT RatingAndReviewId,UserName,MediFacilityId,Ratings,Reviews " +
			"FROM ratingandreview " +
			"WHERE MediFacilityId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, mediId);
			results = selectStmt.executeQuery();
			UsersDao uDao = UsersDao.getInstance();
			HealthCareFacilityDao hDao = HealthCareFacilityDao.getInstance();
			while(results.next()) {
				int Id = results.getInt("RatingAndReviewId");
				String rs = results.getString("Reviews");
				Float rating = results.getFloat("Ratings");
				String resultU = results.getString("UserName");

				Users u = uDao.getUserByUserName(resultU);
				HealthCareFacility h = hDao.getHealthCareFacilityById(mediId);
				RatingAndReview review = new RatingAndReview(Id, u,h, rs,rating);
				reviews.add(review);
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
		return reviews;
	}

}
