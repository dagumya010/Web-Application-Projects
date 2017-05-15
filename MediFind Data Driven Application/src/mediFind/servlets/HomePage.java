package mediFind.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediFind.dal.*;
import mediFind.model.*;


/**
 * Servlet implementation class HomePage
 */
@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected HealthCareFacilityDao hDao; 
    protected UsersDao uDao; 
    protected InsuranceDao iDao;
    protected HospitalDao hospDao;
    protected ClinicDao cDao;
    protected PharmacyDao pDao;
   
    @Override
	public void init() throws ServletException { 
    	hDao = HealthCareFacilityDao.getInstance();
    	uDao = UsersDao.getInstance();
    	iDao = InsuranceDao.getInstance();
    	hospDao = HospitalDao.getInstance();
    	cDao = ClinicDao.getInstance();
    	pDao = PharmacyDao.getInstance();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		String uname = req.getParameter("username");
		String firstName= null;
		try {
			firstName = uDao.getUserByUserName(uname).getFirstName();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String zipcodeParam = req.getParameter("zipcode");
		int zip;
		if (zipcodeParam == null || zipcodeParam.isEmpty()) {
			try {
				zip = uDao.getUserByUserName(uname).getAddress().getZipCode();
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} else {
			zip = Integer.valueOf(zipcodeParam);
		}
		Set<String> filtersSet = new HashSet<>();
		String filterParams[] = req.getParameterValues("filter");
		if (filterParams != null) {
			for (int i = 0; i < filterParams.length; ++i) {
				filtersSet.add(filterParams[i]);
			}
		}

        List<HealthCareFacility> hc = new ArrayList<HealthCareFacility>();
        List<Hospital> hosp = new ArrayList<Hospital>();
        List<Clinic> clinics = new ArrayList<Clinic>();
        List<Pharmacy> pharmas = new ArrayList<Pharmacy>();
        Integer insId = null;
       
        
        if (filtersSet.contains("insurance")) {	        	       
			try {
        		insId = uDao.getUserByUserName(uname).getInsurance().getInsuranceId();
            	hc = hDao.getHealthCareFacilityByInsuranceId(insId,zip);
            	System.out.println("fetched healthcares size ->" + hc.size());
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for your Insurance : " + insId);
        	req.setAttribute("hc", hc);
        
        } else if (filtersSet.contains("hospital")) {
        	try {
	            	hosp = hospDao.getHospitalByZipCode(zip);
	            	System.out.println("fetched healthcares size ->" + hosp.size());
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying Hospital results for your ZipCode : " + zip);
        	req.setAttribute("hc", hosp);
        } else if (filtersSet.contains("clinic")) {
        	try {
            	clinics = cDao.getClinicsByZipCode(zip);
            	System.out.println("fetched healthcares size ->" + clinics.size());
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying Clinics results for your ZipCode : " + zip);
	        req.setAttribute("hc", clinics);
        } else if (filtersSet.contains("pharmacy")) {
        	try {
            	pharmas = pDao.getPharmaciesByZipCode(zip);
            	System.out.println("fetched healthcares size ->" + pharmas.size());
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying Pharmacies results for your ZipCode : " + zip);
	        req.setAttribute("hc", pharmas);
        } else {
        	// this block takes care of "All" healthcare types
        	try {
        		hc = hDao.getHealthCareFacilityByZipCode(zip);
        		System.out.println("fetched healthcares size ->" + hc.size());
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for your ZipCode : " + zip);
	        req.setAttribute("hc", hc);
        }
                
		req.setAttribute("username", uname);
		req.setAttribute("firstname", firstName);
		req.setAttribute("filtersSet", filtersSet);
		req.setAttribute("zipcode", zip);
		req.getRequestDispatcher("/HomePage.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
