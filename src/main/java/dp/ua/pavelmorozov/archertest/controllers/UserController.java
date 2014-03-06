package dp.ua.pavelmorozov.archertest.controllers;

import java.util.Locale;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import dp.ua.pavelmorozov.archertest.domain.User;

import dp.ua.pavelmorozov.archertest.services.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;	
	
	@RequestMapping("/userpage")
	public String userpage(Map<String, Object> map){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loginName = auth.getName();		
		User user = userService.getUser(loginName);
		map.put("userName", user.getEmail());
		map.put("balance", user.getBalance());
		return "userpage";
	}
}
