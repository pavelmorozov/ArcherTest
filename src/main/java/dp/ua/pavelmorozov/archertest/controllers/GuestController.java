package dp.ua.pavelmorozov.archertest.controllers;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import dp.ua.pavelmorozov.archertest.services.GuestService;

@Controller
public class GuestController {
	@RequestMapping("/guestpage")
	public String guestPage(Map<String, Object> map){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loginName = auth.getName();
		map.put("guestName", loginName);
		return "guestpage";
	}	
	
	@Autowired
	private GuestService guestService;
		
	@RequestMapping("/mailconfirm")
	public String guestPage(Map<String, Object> map, HttpServletRequest request){
		return guestService.mailConfirmation(map, request);
	}	
}
