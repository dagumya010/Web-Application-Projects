package mediFind.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mediFind.dal.HealthCareFacilityDao;
import mediFind.dal.RatingAndReviewDao;
import mediFind.model.HealthCareFacility;
import mediFind.model.RatingAndReview;

/**
 * Servlet implementation class HospitalDetail
 */
@WebServlet("/hospitaldetail")
public class HospitalDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected HealthCareFacilityDao hDao; 
	protected RatingAndReviewDao rDao;
       
	public void init() throws ServletException { 
    	hDao = HealthCareFacilityDao.getInstance();
    	rDao = RatingAndReviewDao.getInstance(); 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String hcId = req.getParameter("id");
		String username = req.getParameter("username");
		String zipcode = req.getParameter("zipcode");
		System.out.println(username+"****");
		
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<RatingAndReview> rList = new ArrayList<RatingAndReview>();
        HealthCareFacility hc = null;
        
        try {
			hc = hDao.getHealthCareFacilityById(Integer.parseInt(hcId));
			rList = rDao.getReviewsByMEdiFacilityId(Integer.parseInt(hcId));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        req.setAttribute("hc", hc);
        req.setAttribute("rList", rList);
        req.setAttribute("username", username);
        req.setAttribute("zipcode", zipcode);
		req.getRequestDispatcher("/HospitalDetail.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
