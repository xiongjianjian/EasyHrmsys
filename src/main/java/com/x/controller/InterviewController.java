package com.x.controller;

import com.x.entity.Interview;
import com.x.entity.PageBean;
import com.x.entity.Recruit;
import com.x.entity.User;
import com.x.service.InterviewService;
import com.x.service.RecruitService;
import com.x.service.UserService;
import com.x.util.PageUtil;
import com.x.util.ResponseUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/interview")
public class InterviewController {
	
	@Autowired
	private InterviewService interviewService;
	@Autowired
	private RecruitService recruitService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,Interview s_interview,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(page==null){
			page="1";
			session.setAttribute("s_interview", s_interview);
		}else{
			s_interview=(Interview)session.getAttribute("s_interview");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),5); //分页：一页5条（此处先写死）
		List<Interview> interviewList=interviewService.interviewList(pageBean,s_interview);
		int total=interviewService.interviewCount(s_interview);
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/interview/list.do", total, Integer.parseInt(page), pageBean.getPageSize());
		mav.addObject("interviewList", interviewList);
		
		//添加面试信息时 先把招聘合格者选出来
		List<Recruit> recruitList=recruitService.recruitListByStateOk();
		mav.addObject("recruitList", recruitList);
		
		//添加面试官（所有User，非超级管理官）
		List<User> userListExceptSuperAdmin=userService.userListExceptSuperAdmin();
		mav.addObject("userListExceptSuperAdmin", userListExceptSuperAdmin);
				
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "面试管理");
		mav.addObject("mainPage", "/interview/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	/**
	 * 取应聘者ID和面试官ID，在面试表中插入数据,然后删除合格的应聘者数据
	 * @param recruitId
	 * @param userId
	 * @param resp
	 * @throws Exception
	 */
	@RequestMapping("/begin")
	public void newInterview(@RequestParam(value="recruitId",required=false) String recruitId,
			@RequestParam(value="userId",required=false) String userId,
			HttpServletResponse resp) throws Exception{
		
		Interview interview=new Interview(); //面试实体类
		
		//根据主键取得招聘实体类（这里成为应聘者更合适）Recruit对象
		Recruit recruit=recruitService.loadById(Integer.parseInt(recruitId));
		interview.setName(recruit.getName()); //招聘实体类name属性的值存入面试实体类的name属性
		interview.setSex(recruit.getSex()); //招聘实体类sex属性的值存入面试实体类的sex属性
		interview.setIdcard(recruit.getIdcard()); //招聘实体类idcard属性的值存入面试实体类的idcard属性
		
		//根据主键取得面试官
		User currentUser=userService.loadById(Integer.parseInt(userId));
		
		interview.setInterviewer(currentUser.getTrueName()); //设置interview的属性
		interview.setIsOk(0); //设置interview的属性
		interview.setMissReason(""); //设置interview的属性
		interviewService.add(interview); //添加对象
	
		//删除已经面试的应聘者
		recruitService.delete(Integer.parseInt(recruitId));
		
		//返回JSON对象
		JSONObject result=new JSONObject();
		result.put("success", "true");
		ResponseUtil.write(result, resp);
	}
	
	/**
	 * 通过面试
	 */
	@RequestMapping("/setPass")
	public void setPass(@RequestParam(value="id",required=false) String id,
			@RequestParam(value="missReason",required=false) String missReason,
			HttpServletResponse resp) throws Exception{
		Interview interview=interviewService.loadById(Integer.parseInt(id));
		interview.setIsOk(1);
		interview.setMissReason(missReason);
		interviewService.update(interview);
		
		//返回JSON对象
		JSONObject result=new JSONObject();
		result.put("success", "true");
		ResponseUtil.write(result, resp);
	}
	
	/**
	 * 面试淘汰
	 */
	@RequestMapping("/setNotPass")
	public void setNotPass(@RequestParam(value="id",required=false) String id,
			@RequestParam(value="missReason",required=false) String missReason,
			HttpServletResponse resp) throws Exception{
		Interview interview=interviewService.loadById(Integer.parseInt(id));
		interview.setIsOk(-1);
		interview.setMissReason(missReason);
		interviewService.update(interview);
		
		//返回JSON对象
		JSONObject result=new JSONObject();
		result.put("success", "true");
		ResponseUtil.write(result, resp);
	}
}
