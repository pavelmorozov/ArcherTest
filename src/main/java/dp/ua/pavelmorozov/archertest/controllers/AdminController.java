package dp.ua.pavelmorozov.archertest.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
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
	
//	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
//	public @ResponseBody
//	String test() {
//		String str = "123";
//		System.out.println(str);
//		return str;
//	}
		
	@RequestMapping(value = "/fillUserAccount",  produces = "text/html; charset=UTF-8")
	public 
	@ResponseBody String fillUserAccount (
			@RequestParam(value = "account") String accountId,
			@RequestParam(value = "amount") String amount
			) throws Exception{
		
		System.out.println(" - fillUserAccount() controller method");
		System.out.println(" - Пополнить: "+ accountId + " на сумму: " + amount);
		
		String balance = adminService.fillUserAccount( accountId, amount);
		
		System.out.println(" - Счет пополнен ");
		
		String response = "{\"text\":\"Пополнение на сумму: "+amount+" выполнено успешно\",\"balance\":\""+
				balance+"\"}";
		
		return response;
	}
    
	@RequestMapping("/adminpage")
	//@RequestMapping("/adminpage/{page}")
	public String adminpage(HttpServletRequest request, 
			HttpServletResponse response,
			Map<String, Object> map) throws Exception{

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loginName = auth.getName();
		
		String pageNavy = request.getParameter("pageNavy");
		String pageNumber = request.getParameter("pageNumber");
		System.out.println(pageNavy);
		
		PagedListHolder userList = new PagedListHolder(adminService.listUser()); 
		int pageSize = 3;
		userList.setPageSize(pageSize);
		//int currentPage = userList.getPage();
		int pageCount = userList.getPageCount();
		Integer currentPage = 0;
		
		if ((pageNavy==null)&&(pageNumber==null)) {

		}else{
			if (pageNavy!=null) {
				currentPage = (Integer) request.getSession().getAttribute("currentPage");
				if (pageNavy.equals("last")) {
					currentPage=pageCount-1;
				}
				if (pageNavy.equals("first")) {
					currentPage=0;
				}
				if (pageNavy.equals("prev")) {
					currentPage = ((currentPage>0)?currentPage-1:0);
				}		
				if (pageNavy.equals("next")) {
					currentPage = ((currentPage<pageCount-1)?currentPage+1:0);
				}
			}
			if (pageNumber!=null) {
				currentPage = Integer.parseInt(pageNumber)-1; 
			}
		}
		
		userList.setPage(currentPage);
		request.getSession().setAttribute("currentPage", currentPage);		
		
		Integer pagesListSize = 5;
		List<Integer> pagesList = new ArrayList <Integer>();
		
		Integer startPoint = currentPage - (pagesListSize-1)/2;  
		for (Integer i = startPoint ; i < pagesListSize + startPoint ; i++)
			{
				if (( i >= 0 ) && ( i <= pageCount-1 )) {
					pagesList.add(i+1);
				}
			}

		String pagePath = request.getContextPath()+request.getServletPath();
		//System.out.println(pagePath);
		
		map.put("login", loginName);
		map.put("userList", userList);
		map.put("pagePath", pagePath);
		map.put("pagesList", pagesList);
		map.put("currentPage", currentPage+1);
		return "balancemanagement";
	}	

}
