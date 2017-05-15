package mediFind.servlets;

import mediFind.dal.*;
import mediFind.model.*;
import mediFind.model.Address.AddressType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediFind.dal.UsersDao;
import mediFind.model.Users;


@WebServlet("/register")
public class Register extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected UsersDao usersDao;
	protected AddressDao addressDao;
	protected InsuranceDao insuranceDao;

	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
		addressDao = AddressDao.getInstance();
		insuranceDao = InsuranceDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("insurances", insuranceDao.getAllInsurances());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getRequestDispatcher("/Register.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		String username = null;

		try {
			// Retrieve and validate name.
			username = req.getParameter("username");

			if (usersDao.getUserByUserName(username) == null) {
				String password = req.getParameter("password");
				String verifypassword = req.getParameter("verifypassword");
				if (username == null || username.trim().isEmpty()) {
					messages.put("failure", "Invalid UserName");
				} else if (password == null || password.trim().isEmpty()) {
					messages.put("failure", "Invalid Password");
				} else if (!password.equals(verifypassword)) {
					messages.put("failure", "Passwords does not match");
				} else {
					// Create the BlogUser.
					String firstName = req.getParameter("firstname");
					String lastName = req.getParameter("lastname");
					String email = req.getParameter("email");
					String phone = req.getParameter("phone");
					String street = req.getParameter("street");
					String city = req.getParameter("city");
					String state = req.getParameter("state");
					int zipcode = Integer.parseInt(req.getParameter("zipcode"));
					int insuranceId = Integer.parseInt(req.getParameter("insuranceId"));
					AddressType type = Enum.valueOf(AddressType.class, "User");
					Address address = new Address(type,street,city,state,zipcode);
					addressDao.create(address);
					messages.put("success", "Successfully created address " + username);
					Insurance insurance;
					insurance = insuranceDao.getInsuranceById(insuranceId);

					Users user = new Users(username, password, firstName, lastName, phone, email, address, insurance);
					user = usersDao.create(user);
					messages.put("success", "Successfully created " + username);
				}
			} else {
				messages.put("failure", "User already exists!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException(e);
		}

		if (req.getMethod().equals("POST")) {
			resp.sendRedirect("HomePage?username=" + username);
		} else {
			req.getRequestDispatcher("/Register.jsp").forward(req, resp);
		}
	}
}

