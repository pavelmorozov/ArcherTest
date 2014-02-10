package dp.ua.pavelmorozov.archertest.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class testUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		System.out.println("Getting access "
				+ "details from testUserDetailsService!");
		
		//List<String> roles = new ArrayList<String>();
		//roles.add("ROLE_USER");
		//roles.add("ROLE_ADMIN");
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				
		
		return new User(
				login,
				"1",
				true,
				true,
				true,
				true,
				authorities);
	}

}
