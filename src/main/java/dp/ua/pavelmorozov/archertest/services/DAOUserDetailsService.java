package dp.ua.pavelmorozov.archertest.services;

import dp.ua.pavelmorozov.archertest.dao.AccountDAO;
//import dp.ua.pavelmorozov.archertest.dao.AccountDAOImpl;
import dp.ua.pavelmorozov.archertest.domain.Account;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class DAOUserDetailsService implements UserDetailsService{
	
	@Autowired
    private AccountDAO accountDAO;	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String login)
	throws UsernameNotFoundException {
		
		System.out.println("Getting access details for: "+ login 
				+ ", from DAOUserDetailsService");
		
		Account account = accountDAO.getAccount(login); 
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		if (account.getRole().equals("USER")) {
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}else if (account.getRole().equals("ADMIN")) {
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		
		
		//List<String> roles = new ArrayList<String>();
		//roles.add("ROLE_USER");
		//roles.add("ROLE_ADMIN");
		
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		
//		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		
		return new User(
				account.getEmail(),
				account.getPass(),
				true,
				true,
				true,
				true,
				authorities);
	}
}
