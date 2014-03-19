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

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RegisterDAO registerDAO;
	
	@Autowired
	private AccountDAO accountDAO;	
	
	/**
	 * Takes PagedListHolder, navigation parameters and make Map.
	 */
	private void buildMap(PagedListHolder objectList, Map map, HttpServletRequest request){
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
		//System.out.println(pagePath);

		map.put("objectList", objectList);
		map.put("pagePath", pagePath);
		map.put("pagesList", pagesList);
		map.put("currentPage", currentPage+1);
	}
	
	@Override
	@Transactional
	public void searchRegistryRecords( Map map, HttpServletRequest request, Date fromDate, Date  toDate){
		
//		String fromDateStr = request.getParameter("fromDate");
//		String toDateStr = request.getParameter("toDate");
//		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		DateTime toDateTime = new DateTime(toDate).plusDays(1);
		Date toDatePlus1 = toDateTime.toDate();

		PagedListHolder<Register> registryRecords;
		
		registryRecords = (PagedListHolder) request.getSession().getAttribute("searchRegistryRecords");
		if (registryRecords==null) {
			registryRecords = new PagedListHolder<Register>(registerDAO.searchRecords(fromDate, toDatePlus1));
//			request.getSession().setAttribute("fromDate", fromDate);
//			request.getSession().setAttribute("toDate", toDate);
			request.getSession().setAttribute("searchRegistryRecords", registryRecords);			
		}
		
		buildMap(registryRecords , map, request);
	}
	
	@Override
	@Transactional	
	public void searchUser( Map map, HttpServletRequest request){
		String searchUser = request.getParameter("searchString");
		PagedListHolder searchUserList = null;
		
		if (searchUser!=null){
			if (!StringUtils.hasLength(searchUser)){
				PagedListHolder userList = new PagedListHolder(userDAO.listUser());
				buildMap(userList, map,request);
			} else {
				searchUserList = new PagedListHolder(userDAO.searchUser(searchUser));
				request.getSession().setAttribute("searchUserList", searchUserList);
				request.getSession().setAttribute("searchString", searchUser);
				buildMap(searchUserList , map, request);
			}
		}else{
			searchUserList = (PagedListHolder) request.getSession().getAttribute("searchUserList");
			searchUser = (String) request.getSession().getAttribute("searchString");
			//map.put("searchString", searchUser);
			buildMap(searchUserList , map, request);
		}
	}
	
	@Override
	@Transactional	
	public void listRegister(Map map, HttpServletRequest request){
		PagedListHolder registerList = new PagedListHolder(registerDAO.listRegister());
		buildMap(registerList, map, request);
	}
	
	@Override
	@Transactional
	public void listUser(Map map, HttpServletRequest request)
	{
		PagedListHolder userList = new PagedListHolder(userDAO.listUser());
		request.getSession().setAttribute("searchString", null);
		//map.put("searchString", null);
		
//		userList.setPage(0);
//		User u = (User) userList.getSource().get(0);
//		String s = new SimpleDateFormat("dd/MM/yyyy").format(u.getCreated());
//		System.out.println("Converted date: "+ s);
		
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
