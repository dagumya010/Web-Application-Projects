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

import mediFind.dal.AppointmentDao;
import mediFind.dal.HealthCareFacilityDao;
import mediFind.dal.UsersDao;
import mediFind.model.Appointment;
import mediFind.model.HealthCareFacility;
import mediFind.model.Users;

@WebServlet("/UserHistory")
public class UserHistory extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected AppointmentDao appointmentDao;
    protected HealthCareFacilityDao hDao; 
    protected UsersDao uDao; 
    
    @Override
   	public void init() throws ServletException { 
    	appointmentDao = AppointmentDao.getInstance();
       	hDao = HealthCareFacilityDao.getInstance();
       	uDao = UsersDao.getInstance();
   	}
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username");
		//System.out.println("from home"+uname);
		
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Appointment> appointments = new ArrayList<>();
        Users u;
        Appointment appointment;
        HealthCareFacility healthCareFacility;
        
        String firstName = null;
		try {
			u = uDao.getUserByUserName(username);
			firstName = u.getFirstName();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
        
        	try {
        		appointments = appointmentDao.getAppointmentsByUserName(username);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	//messages.put("success", "Displaying History for " + username);
        req.setAttribute("appointments", appointments);
		req.setAttribute("username", username);
		req.setAttribute("firstname", firstName);
		req.getRequestDispatcher("/UserHistory.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello!!!!!!!");
		String aId = req.getParameter("bId");
		System.out.println(aId);
		try {
			appointmentDao.delete(Integer.parseInt(aId));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//req.getRequestDispatcher("/UserHistory.jsp").forward(req, resp);
		doGet(req, resp);
	}

}

