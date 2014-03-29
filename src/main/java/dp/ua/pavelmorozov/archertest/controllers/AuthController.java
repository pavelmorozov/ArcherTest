package dp.ua.pavelmorozov.archertest.controllers;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Maps /login to login.jsp - custom login page 
 */
@Controller
public class AuthController {
	/**
	 * Maps /login to login.jsp - custom login page 
	 */
    @RequestMapping("/login")
    public String login(Map<String, Object> map) {
        return "login";
    }
    
	/**
	 * Maps /register to register.jsp - custom login page 
	 */
    @RequestMapping("/register")
    public String register(Map<String, Object> map) {
        return "register";
    }    
}