package dp.ua.pavelmorozov.archertest.services;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.joda.time.DateTime;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import dp.ua.pavelmorozov.archertest.dao.AccountDAO;
import dp.ua.pavelmorozov.archertest.dao.RegisterDAO;
import dp.ua.pavelmorozov.archertest.dao.UserDAO;
import dp.ua.pavelmorozov.archertest.domain.Account;
import dp.ua.pavelmorozov.archertest.domain.User;
import dp.ua.pavelmorozov.archertest.domain.Register;

/**
 * Implements Admin features
 */
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RegisterDAO registerDAO;
	
	@Autowired
	private AccountDAO accountDAO;
	
	/**
	 * Takes PagedListHolder - objects for table in view, navigation parameters from HttpServletRequest and make Map.
	 */
	private void buildMap(PagedListHolder objectList, Map <String, Object> map, HttpServletRequest request){
		int pageSize = 10;
		Integer pagesListSize = 5; 
		String pageNavy = request.getParameter("pageNavy");
		String pageNumber = request.getParameter("pageNumber");
		objectList.setPageSize(pageSize);
		int pageCount = objectList.getPageCount();
		Integer currentPage = 0;
		if (!((pageNavy==null)&&(pageNumber==null))) {
			if (pageNavy!=null) {
				currentPage=(Integer) request.getSession().getAttribute("currentPage");
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
		objectList.setPage(currentPage);
		request.getSession().setAttribute("currentPage", currentPage);		
		List<Integer> pagesList = new ArrayList <Integer>();
		Integer startPoint = currentPage - (pagesListSize-1)/2;  
		for (Integer i = startPoint ; i < pagesListSize + startPoint ; i++)
			{
				if (( i >= 0 ) && ( i <= pageCount-1 )) {
					pagesList.add(i+1);
				}
			}
		String pagePath = request.getContextPath()+request.getServletPath();
		map.put("objectList", objectList);
		map.put("pagePath", pagePath);
		map.put("pagesList", pagesList);
		map.put("currentPage", currentPage+1);
	}
	
	/**
	 * Search registry in date interval, return String for controller answer.
	 */
	@Override
	@Transactional
	public String searchRegistryRecords(Map<String, Object> map, HttpServletRequest request){
		String fromDateStr = request.getParameter("fromDate");
		String toDateStr = request.getParameter("toDate");
		java.util.Date fromDate = null;
		java.util.Date toDate = null;
		if ((fromDateStr!=null)&&(fromDateStr!=null)){//request parameter dates set
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			try {
				fromDate = formatter.parse(fromDateStr);
				toDate = formatter.parse(toDateStr);
				request.getSession().setAttribute("fromDate",fromDate);
				request.getSession().setAttribute("toDate",toDate);
			} catch (ParseException e) {
				e.printStackTrace();
				return "redirect:/adminpage/registryRecords";
			}
		}else{
			fromDate = (Date) request.getSession().getAttribute("fromDate");
			toDate =  (Date) request.getSession().getAttribute("toDate");
		}
		DateTime toDateTime = new DateTime(toDate).plusDays(1);
		toDate = toDateTime.toDate();
		PagedListHolder<Register> registryRecords = new PagedListHolder<Register>
			(registerDAO.searchRecords(fromDate, toDate));
		buildMap(registryRecords , map, request);
		return "registryRecords";
	}
	
	/**
	 * Searches user by string from HttpServletRequest parameter 
	 */
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public String searchUser(Map<String, Object> map, HttpServletRequest request){
		String searchUser = request.getParameter("searchString");
		PagedListHolder<User> searchUserList = null;
		
		if (searchUser!=null){
			if (!StringUtils.hasLength(searchUser)){
				return "redirect:/adminpage/balance";
			} else {
				searchUserList = new PagedListHolder<User>(userDAO.searchUser(searchUser));
				request.getSession().setAttribute("searchUserList", searchUserList);
				request.getSession().setAttribute("searchString", searchUser);
				buildMap(searchUserList , map, request);
			}
		}else{
			searchUserList = (PagedListHolder<User>) request.getSession().getAttribute("searchUserList");
			searchUser = (String) request.getSession().getAttribute("searchString");
			buildMap(searchUserList , map, request);
		}
		return "balancemanagement";
	}
	
	@Override
	@Transactional	
	public void listRegister(Map<String, Object> map, HttpServletRequest request){
		PagedListHolder<Register> registerList = new PagedListHolder<Register>(registerDAO.listRegister());
		buildMap(registerList, map, request);
	}
	
	@Override
	@Transactional
	public void listUser(Map<String, Object> map, HttpServletRequest request)
	{
		PagedListHolder<User> userList = new PagedListHolder<User>(userDAO.listUser());
		buildMap(userList, map,request);
	}
	
	/**
	 * Find user by Id, fill account and returns user balance
	 */
	@Override
	@Transactional	
	public String fillUserAccount(String id, String amount){
		User user = userDAO.getUser(Integer.parseInt(id));
		BigDecimal balance = user.getBalance();
		MathContext mc = new MathContext(16);
		amount = amount.replace(',', '.');
		System.out.println(amount);
		BigDecimal amountBD= new BigDecimal (amount);
		balance = balance.add(amountBD,mc);
		user.setBalance(balance);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loginName = auth.getName();
		Account admin = accountDAO.getAccount(loginName) ;
		Register regRecord = new Register(user, admin, amountBD);
		registerDAO.saveRegister(regRecord);
		return user.getBalance().toString();
	}	
}
