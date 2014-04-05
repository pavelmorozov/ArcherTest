package dp.ua.pavelmorozov.archertest.controllers;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

		return adminService.searchUser(map, request);
	}
	
	/**
	 * Search registry records by date interval
	 */
	@RequestMapping("/" + ADMINPAGE + "/searchRegistryRecords")
	public String searchRegistryRecords(HttpServletRequest request, 
			Map<String, Object> map) throws Exception{
		return adminService.searchRegistryRecords(map, request);
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
		request.getSession().setAttribute("searchString", null);
		adminService.listUser(
				map,
				request);
		return "balancemanagement";
	}	
}
