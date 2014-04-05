package dp.ua.pavelmorozov.archertest.controllers;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import dp.ua.pavelmorozov.archertest.services.RegisterGuestService;

@Controller
public class AuthController {
	@RequestMapping(value = "/")
	public String home() {
		return "login";
	}
		
	/**
	 * Maps /login to login.jsp - custom login page 
	 */
    @RequestMapping("/login")
    public String login(Map<String, Object> map) {
        return "login";
    }

    @Autowired
    private RegisterGuestService registerGuestService;
    
    @RequestMapping("/register")
    public String register() {
        return "register";
    }    
    
	/**
	 * Maps /register to register.jsp - custom login page 
	 */
    @RequestMapping("/regUser")
    public String regUser(@RequestParam("email") String email,
    		@RequestParam("password") String password,
    		@RequestParam("password_repeat") String password_repeat, 
    		HttpServletRequest request,
    		Map <String, Object> map) {
    	try {
    		registerGuestService.saveGuest(email, password, password_repeat, map, request);
    		return "redirect:/guestpage";
    	}catch (Exception e){
    		e.printStackTrace();
    		return "register";
    	}
    }
}