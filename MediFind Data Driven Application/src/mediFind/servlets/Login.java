package mediFind.servlets;
import mediFind.dal.*;
import mediFind.model.Users;
import sun.rmi.server.Dispatcher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected UsersDao uDao;
	
	@Override
	public void init() throws ServletException { 
		uDao = UsersDao.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String uname = req.getParameter("uname");
        String pass = req.getParameter("pass");
        if (uname == null || pass == null) {
            messages.put("error", "please enter all fields");
        } 
        else {
        	try {        		
            	Users user = uDao.getUserByUserName(uname);
            	if(user == null) {
            		messages.put("success", "User does not exist!");
            		req.getRequestDispatcher("/Login.jsp").forward(req, resp);
            	}
            	else if (pass.equals(user.getPassword()))
            	{
            		messages.put("success", "Login successful!!");
            		//System.out.println("/HomePage?p="+uname);
            		//resp.sendRedirect("HomePage?username=" + uname);
            		//RequestDispatcher rd = getServletContext().getRequestDispatcher("/HomePage?p="+uname);
            		//rd.forward(req, resp);   
            		//resp.sendRedirect("HomePage?username=" + uname);
            	}
            	else
            	{
            		messages.put("success", "Either UserName or Password is incorrect");
            		req.getRequestDispatcher("/Login.jsp").forward(req, resp);
            	} 
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
        
        if (req.getMethod().equals("POST")) {
			resp.sendRedirect("HomePage?username=" + uname + "&filter=insurance");
		} else {
			req.getRequestDispatcher("/Login.jsp").forward(req, resp);
		}
    }

}
