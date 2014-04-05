package dp.ua.pavelmorozov.archertest.services;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import dp.ua.pavelmorozov.archertest.dao.UserDAO;
import dp.ua.pavelmorozov.archertest.dao.ValidationDAO;
import dp.ua.pavelmorozov.archertest.domain.Validation;
import dp.ua.pavelmorozov.archertest.domain.User;

/**
 * Service implements new user registration (GUEST_ROLE)  
  */
@Service
public class RegisterGuestServiceImpl implements RegisterGuestService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ValidationDAO validationDAO;
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
    @Autowired
    private JavaMailSender mailSender;
    
    private String getURL(HttpServletRequest req) {
        String scheme = req.getScheme();             // http
        String serverName = req.getServerName();     // hostname.com
        int serverPort = req.getServerPort();        // 80
        String contextPath = req.getContextPath();   // /mywebapp

        // Reconstruct original requesting URL
        StringBuffer url =  new StringBuffer();
        url.append(scheme).append("://").append(serverName);

        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }

        url.append(contextPath);
        
        return url.toString();
    }
    
    /**
     * Returns true if no exception on send email occurred 
     * @param validation
     * @return
     */
    private boolean checkEmail(final Validation validation, final String url){
    	System.out.println("checkEmail");
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
        	public void prepare(MimeMessage mimeMessage) throws Exception {
        		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true,"utf-8");
                mimeMessageHelper.setTo(validation.getGuest().getEmail());
                mimeMessageHelper.setSubject("Добро пожаловать! dp.ua.pavelmorozov.archertest");
                StringBuilder text = new StringBuilder();
                text.append("<html>");
                text.append("<body>");
                text.append("<h3>Добро пожаловать!</h3>");
                text.append("<p>Для завершения регистрации перейдите: </p>" +
                			"<a href=\""+url+"/mailconfirm?uuid="
    						+validation.getUuid()+"\">Confirm email!</a>");
                text.append("</body>");
                text.append("</html>");
                // Sets the text
                mimeMessageHelper.setText(text.toString(), true);
        	}
        };
        
        try {
            this.mailSender.send(preparator);
            return true;
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
            return false;
       }        
    }

    @Autowired
    @Qualifier("authenticationManager")
    protected AuthenticationManager authenticationManager;    

    private void authenticateUserAndSetSession(User user, String password,
            HttpServletRequest request)
    {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getEmail(), password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }    
    
	@Override
	@Transactional
	public void saveGuest(String email, String password,
			String password_repeat, Map <String, Object> map,
			HttpServletRequest request) throws RuntimeException {
		if (!password.equals(password_repeat)) {
    		map.put("message", "Ошибка! Указанные пароли не совпадают.");
    		throw new RuntimeException();//transaction rollback
    	}
    	if (userDAO.getUser(email)!=null){
    		map.put("message", "Ошибка! Пользователь с таким именем уже зарегистрирован.");
    		throw new RuntimeException();//transaction rollback
    	}
    	Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
   		Matcher emailMatcher = emailPattern.matcher(email);
		if(!emailMatcher.matches()){
    		map.put("message", "Ошибка! Проверьте адрес почты.");
    		throw new RuntimeException();//transaction rollback
		}
		
		User guest = null;
		Validation validation = null;
		Md5PasswordEncoder md5Password = new Md5PasswordEncoder();
		String encodedPassword = md5Password.encodePassword(password, "");
		guest = new User( email, encodedPassword, new BigDecimal(0));
    	guest.setRole("GUEST");
    	try{	
	    	userDAO.saveUser(guest);
	   		validation = new Validation(guest,
	   				UUID.randomUUID().toString());
	   		validationDAO.saveValidation(validation);
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException();//transaction rollback
		}
   		
   		if (checkEmail(validation, getURL(request))){
			authenticateUserAndSetSession(guest, password, request);
			String message= "Пользователь, "+email+
					", зарегистрирован. Для начала работы проверьте почту.";
			map.put("message", message);
   		}else{
   			String message = "При отправке сообщения по адресу: "
			+ email + " произошла ошибка.";
			map.put("message", message);
			throw new RuntimeException();//transaction rollback
   		}
	}

}
