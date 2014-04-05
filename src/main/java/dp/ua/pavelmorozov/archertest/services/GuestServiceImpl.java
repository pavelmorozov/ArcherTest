package dp.ua.pavelmorozov.archertest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import dp.ua.pavelmorozov.archertest.dao.UserDAO;
import dp.ua.pavelmorozov.archertest.dao.ValidationDAO;
import dp.ua.pavelmorozov.archertest.domain.Validation;
import dp.ua.pavelmorozov.archertest.domain.User;

@Service
public class GuestServiceImpl implements GuestService{
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ValidationDAO validationDAO;
	
	@Autowired
    @Qualifier("authenticationManager")
    protected AuthenticationManager authenticationManager;    

    private void authenticateUserAndSetSession(User user,
    		HttpServletRequest request)
    {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
    	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    	Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),auth.getCredentials(),authorities);
    	SecurityContextHolder.getContext().setAuthentication(newAuth);
    }	
	
	@Override
	@Transactional
	public String mailConfirmation(Map<String, Object> map, HttpServletRequest request){
		String controllerAnswer=null;
		String uuid = request.getParameter("uuid");
		if (uuid!=null){
			Validation validation = validationDAO.getValidation(uuid);
			if (validation!=null){
				User user = (User) validation.getGuest();
				user.setRole("USER");
				userDAO.saveUser(user);
				validationDAO.removeValidation(validation);
	   			authenticateUserAndSetSession(user, request);
	   			map.put("userName", user.getEmail());
	   			controllerAnswer = "userpagewelcome";
			}else{
				controllerAnswer = "redirect:/login";
			}
		}else{
			controllerAnswer = "redirect:/login";
		}
		return controllerAnswer;
	}
}
