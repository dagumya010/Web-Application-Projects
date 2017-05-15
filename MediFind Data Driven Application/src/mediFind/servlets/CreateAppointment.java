package mediFind.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediFind.dal.AppointmentDao;
import mediFind.dal.HealthCareFacilityDao;
import mediFind.dal.UsersDao;
import mediFind.model.Appointment;
import mediFind.model.HealthCareFacility;
import mediFind.model.Users;

@WebServlet("/CreateAppointment")
public class CreateAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Test objects
	protected AppointmentDao appointmentDao;
	protected UsersDao user_dao;
	protected HealthCareFacilityDao healthCare_dao;
	protected String hcId;
	protected String username;

	@Override
	public void init() throws ServletException {
		appointmentDao = AppointmentDao.getInstance();
		user_dao = UsersDao.getInstance();
		healthCare_dao = HealthCareFacilityDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		hcId = request.getParameter("hcid");
		username = request.getParameter("username");
		Integer hc = Integer.parseInt(hcId);
		request.setAttribute("username", username);
		try {
			request.setAttribute("hcName",healthCare_dao.getHealthCareFacilityById(hc).getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("hcid", hcId);
		request.setAttribute("username", username);
		request.getRequestDispatcher("/CreateAppointment.jsp").forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		System.out.println(username+"AppointPost"+hcId);
		Integer id = Integer.parseInt(hcId);
		// Retrieve the appointment details for the Users
		// need
		// we get healthcare facility from outside
		// username
		HealthCareFacility facility = null;
		Users user = null;
		try {
			user = user_dao.getUserByUserName(username);
			facility = healthCare_dao.getHealthCareFacilityById(id);
		} catch (SQLException e1) { 
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String string = request.getParameter("date");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
		LocalDate date = LocalDate.parse(string, formatter);
		Date startingDate = Date.valueOf(date);
		
		string = request.getParameter("time"); 
		LocalTime time = null;
		time = LocalTime.parse(string);		
		Time startingTime = Time.valueOf(time);
		try {
			// Make the object with the user inputs
			Appointment appointment = new Appointment(facility, user, startingDate, startingTime, "New Appointment");
			// pass the object to the Dao
			appointment = appointmentDao.create(appointment);
			//
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
 
		messages.put("success", "Your appointment has been confirmed!!");
		response.sendRedirect("CreateAppointment?hcid="+hcId+"&username="+username);
	}
}
