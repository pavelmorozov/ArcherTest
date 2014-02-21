package dp.ua.pavelmorozov.archertest.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dp.ua.pavelmorozov.archertest.services.TestDAOServise;

/**
 * Maps /login to login.jsp - custom login page 
 */
@Controller
public class AuthControler {

	/**
	 * Maps /login to login.jsp - custom login page 
	 */
    @RequestMapping("/login")
    public String login(Map<String, Object> map) {
    	
    	
    	
    	
        return "login";
    }	    	

}
