package dp.ua.pavelmorozov.archertest.controllers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;




import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

















//import dp.ua.pavelmorozov.archertest.domain.User;
import dp.ua.pavelmorozov.archertest.services.AdminService;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	private static final String ADMINPAGE= "adminpage";  
	
	/**
	 * Searches users by string searchUser
	 */
	@RequestMapping("/" + ADMINPAGE + "/searchUser")
	public String searchUser(HttpServletRequest request, 
			HttpServletResponse response,
			Map<String, Object> map) throws Exception{

		String searchUser = request.getParameter("searchString");
		if (searchUser!=null){
			if (!StringUtils.hasLength(searchUser)){
				return "redirect:/" + ADMINPAGE + "/balance";
			}
		}
		
		adminService.searchUser(map, request);
		return "balancemanagement";
	}
	
	@RequestMapping("/" + ADMINPAGE + "/searchRegistryRecords")
	public String searchRegistryRecords(HttpServletRequest request, 
			Map<String, Object> map) throws Exception{
		
		String fromDateStr = request.getParameter("fromDate");
		String toDateStr = request.getParameter("toDate");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		java.util.Date fromDate = null;
		java.util.Date toDate = null;
		
		if ((fromDateStr==null)&&(toDateStr==null)){
			if (request.getSession().getAttribute("searchRegistryRecords")==null) {
//				request.getSession().setAttribute("fromDate",null);
//				request.getSession().setAttribute("toDate",null);
				return "redirect:/" + ADMINPAGE + "/registryRecords";				
			}
		}else{
			try {
				fromDate = formatter.parse(fromDateStr);
				toDate = formatter.parse(toDateStr);
				request.getSession().setAttribute("fromDate",fromDate);
				request.getSession().setAttribute("toDate",toDate);
				System.out.println("AdminController: searchRegistryRecords. Date from param: "+formatter.format(fromDate)+" - "+formatter.format(toDate));
			} catch (ParseException e) {
				e.printStackTrace();
//				request.getSession().setAttribute("fromDate",null);
//				request.getSession().setAttribute("toDate",null);				
				return "redirect:/" + ADMINPAGE + "/registryRecords";
			}
		}
		
		adminService.searchRegistryRecords(map, request, fromDate, toDate);
		return "registryRecords";
	}
	
	/**
	 * Shows register records
	 */
	@RequestMapping("/"+ADMINPAGE+"/registryRecords")
	public String registryRecords(HttpServletRequest request, 
			HttpServletResponse response,
			Map<String, Object> map) throws Exception{
		request.getSession().setAttribute("fromDate",null);
		request.getSession().setAttribute("toDate",null);
		adminService.listRegister(
				map,
				request);
		return "registryRecords";
	}
		
	@RequestMapping(value = "/" + ADMINPAGE + "/fillUserAccount",  produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String fillUserAccount (
			@RequestParam(value = "account") String accountId,
			@RequestParam(value = "amount") String amount
			) throws Exception{
		String balance = adminService.fillUserAccount( accountId, amount);
		String response = "{\"text\":\"Пополнение на сумму: "
				+amount+" выполнено успешно\",\"balance\":\""+
				balance+"\"}";
		return response;
	}
    
	@RequestMapping("/"+ADMINPAGE+"/balance")
	public String adminpage(HttpServletRequest request, 
			HttpServletResponse response,
			Map<String, Object> map) throws Exception{

		adminService.listUser(
				map,
				request);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loginName = auth.getName();
		return "balancemanagement";
	}	

}
