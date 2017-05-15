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

import com.sun.xml.internal.ws.handler.HandlerException;

import mediFind.dal.*;
import mediFind.model.*;
import mediFind.model.Address.AddressType;

@WebServlet("/profile")
public class Profile extends HttpServlet{ 
	private static final long serialVersionUID = 3467331869784434167L;
	private String username;
	private Users user;
	protected UsersDao usersDao;
	protected AddressDao aDao;
	protected InsuranceDao iDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
		aDao = AddressDao.getInstance();
		iDao = InsuranceDao.getInstance();
	}
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		try {
			req.setAttribute("insurances", iDao.getAllInsurances());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        username = req.getParameter("username");
		try {
			user = usersDao.getUserByUserName(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException(e);
		}
        
//        if (user != null) {
        	Address address = user.getAddress();
        	// Create the BlogUser.
        	req.setAttribute("username", user.getUserName());
        	req.setAttribute("firstName", user.getFirstName());
        	req.setAttribute("lastName", user.getLastName());
        	req.setAttribute("email", user.getEmail());
        	req.setAttribute("phone", user.getPhoneNumber());
        	req.setAttribute("street", address.getStreet());
        	req.setAttribute("city", address.getCity());
        	req.setAttribute("state", address.getState());
        	req.setAttribute("zipcode", address.getZipCode());
        	req.setAttribute("insuranceName", user.getInsurance().getName());
//        }
        
        req.getRequestDispatcher("/Profile.jsp").forward(req, resp);
    }
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		if(req.getParameter("update") != null)
		{
			String fname = req.getParameter("fname");
			String lname = req.getParameter("lname");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			String street = req.getParameter("street");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			String zip = req.getParameter("zipcode");
			String insId = req.getParameter("insuranceId");
			//String insName = req.getParameter("insuranceName");
			Address add1 = new Address(street, city, state, Integer.parseInt(zip)); 
			Insurance ins = null;
			try {
				ins = iDao.getInsuranceById(Integer.parseInt(insId));
				//ins = iDao.getInsuranceByName(insName);
				System.out.println("$$4"+user.getAddress().getAddressId()+"%%%%%%%%");
				add1 = aDao.updateAddresswithId(user.getAddress().getAddressId(), add1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Users user1 = new Users(username,user.getPassword(), fname, lname, phone, email,add1,ins);
			try {
				user = usersDao.updateUser(username, user1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new IOException(e);
			}
			
			Address address = user.getAddress();
        	req.setAttribute("username", user.getUserName());
        	req.setAttribute("firstName", user.getFirstName());
        	req.setAttribute("lastName", user.getLastName());
        	req.setAttribute("email", user.getEmail());
        	req.setAttribute("phone", user.getPhoneNumber());
        	req.setAttribute("street", address.getStreet());
        	req.setAttribute("city", address.getCity());
        	req.setAttribute("state", address.getState());
        	req.setAttribute("zipcode", address.getZipCode());
        	req.setAttribute("insuranceName", user.getInsurance().getName());
        	try {
    			req.setAttribute("insurances", iDao.getAllInsurances());
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	resp.sendRedirect("profile?username=" + username);
		}
	}
}
