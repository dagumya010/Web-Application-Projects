package mediFind.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediFind.dal.HealthCareFacilityDao;
import mediFind.dal.RatingAndReviewDao;
import mediFind.dal.UsersDao;
import mediFind.model.HealthCareFacility;
import mediFind.model.RatingAndReview;
import mediFind.model.Users;

@WebServlet("/CreateRating")
public class CreateRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RatingAndReviewDao ratingsDao;
	// Test Objects
	protected UsersDao user_dao;
	protected HealthCareFacilityDao healthCare_dao;
	protected String hospitalId; 
	protected String username; 
	
	public void init() throws ServletException {
		ratingsDao = RatingAndReviewDao.getInstance();
		user_dao = UsersDao.getInstance();
		healthCare_dao = HealthCareFacilityDao.getInstance();
	}
public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		hospitalId = request.getParameter("hcid");
		username = request.getParameter("username");
		int hcId = Integer.parseInt(hospitalId);
		request.setAttribute("username", username);
		try {
			request.setAttribute("hcName",healthCare_dao.getHealthCareFacilityById(hcId).getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("hcid", hospitalId);
		request.setAttribute("username", username);
		request.getRequestDispatcher("/CreateRating.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		float ratings = Float.parseFloat(request.getParameter("rating"));
		String review = request.getParameter("review");
		// Test objects
		int hcId = Integer.parseInt(hospitalId);
		Users user = null;
		HealthCareFacility facility = null;
		try {
			user = user_dao.getUserByUserName(username);
			facility = healthCare_dao.getHealthCareFacilityById(hcId);
			RatingAndReview ratingModel = new RatingAndReview(user, facility, review, ratings);
			RatingAndReview rating = ratingsDao.create(ratingModel);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		messages.put("success", "SuccessFully Added rating and review");
		response.sendRedirect("CreateRating?hcid="+hcId+"&username="+username);
	}
}
