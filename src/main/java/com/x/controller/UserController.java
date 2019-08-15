package com.x.controller;

import com.x.entity.PageBean;
import com.x.entity.User;
import com.x.service.UserService;
import com.x.util.PageUtil;
import com.x.util.ResponseUtil;
import com.x.util.StringUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request){
		User currentUser=userService.login(user);
		if(currentUser.getUserId()==null){
			request.setAttribute("errorMsg", "用户或密码错误");
			request.setAttribute("currentUser", user);
			return "login";
		}else{
			HttpSession session=request.getSession();
			session.setAttribute("currentUser", currentUser);
			return "redirect:/main.jsp";
		}
	}
	@RequestMapping("/logout")
	public String login(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.removeAttribute("currentUser");
		return "redirect:/login.jsp";
	}
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,User s_user,HttpServletRequest req){
		ModelAndView mav=new ModelAndView();
		HttpSession session=req.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("s_user", s_user);
		}else{
			s_user=(User)session.getAttribute("s_user");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),5); //分页：一页5条（此处先写死）
		List<User> userList=userService.userList(pageBean,s_user);
		int total=userService.userCount(s_user);
		String pageCode=PageUtil.getPagation(req.getContextPath()+ "/user/list.do", total, Integer.parseInt(page), pageBean.getPageSize());
		mav.addObject("userList", userList);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "系统用户管理");
		mav.addObject("mainPage", "/user/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	@RequestMapping("/preSave")
	public ModelAndView preSave(@RequestParam(value="userId",required=false)String userId){
		ModelAndView mav=new ModelAndView();
		mav.addObject("modeName", "系统用户管理");
		mav.addObject("mainPage", "/user/save.jsp");
		mav.setViewName("main");
		if(StringUtil.isEmpty(userId)){
			mav.addObject("actionName", "系统用户添加");
		}else{
			mav.addObject("actionName", "系统用户修改");
			User user=userService.loadById(Integer.parseInt(userId));
			mav.addObject("user", user);
			
		}
		return mav;
	}
	@RequestMapping("/save")
	public String save(User user){
		if(user.getUserId()==null){
			userService.add(user);
		}else{
			userService.update(user);
		}
		return "redirect:/user/list.do";
	}
	@RequestMapping("/delete")
	public void delete(@RequestParam(value="userId",required=false)String userId,HttpServletResponse resp) throws Exception{
		userService.delete(Integer.parseInt(userId));
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(result, resp);
	}
}
