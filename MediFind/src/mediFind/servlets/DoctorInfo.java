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

import mediFind.dal.DoctorInfoDao;
import mediFind.dal.HealthCareFacilityDao;
import mediFind.dal.SpecialityDao;

@WebServlet("/DoctorInfo")
public class DoctorInfo extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected HealthCareFacilityDao hDao;
	protected SpecialityDao sDao;
	protected DoctorInfoDao doctor_Dao;
	
	public void init() throws ServletException {
		hDao = HealthCareFacilityDao.getInstance();
		sDao = SpecialityDao.getInstance();
		doctor_Dao = DoctorInfoDao.getInstance();
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String hcId = req.getParameter("hcid");
		String username = req.getParameter("username");
		System.out.println(hcId);
		
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		
		List<mediFind.model.DoctorInfo> doctorList = new ArrayList<mediFind.model.DoctorInfo>();
		
		try{
			doctorList = doctor_Dao.getDoctorInfoByHealthCareFacilityId(Integer.parseInt(hcId));
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		req.setAttribute("doctors", doctorList);
		req.setAttribute("hcid", hcId);
		req.setAttribute("username", username);
		req.getRequestDispatcher("/DoctorInfo.jsp").forward(req, resp);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
