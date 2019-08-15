package com.x.controller;

import com.x.entity.DataDic;
import com.x.entity.PageBean;
import com.x.entity.Recruit;
import com.x.service.DataDicService;
import com.x.service.RecruitService;
import com.x.util.PageUtil;
import com.x.util.ResponseUtil;
import com.x.util.StringUtil;
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
@RequestMapping("/recruit")
public class RecruitController {

	@Autowired
	private RecruitService recruitService;
	@Autowired
	private DataDicService dataDicService;
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value="page",required=false) String page,Recruit s_recruit,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(page==null){
			page="1";
			session.setAttribute("s_recruit", s_recruit);
		}else{
			s_recruit=(Recruit)session.getAttribute("s_recruit");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),5); //分页：一页5条（此处先写死）
		List<Recruit> recruitList=recruitService.recruitList(pageBean,s_recruit);
		int total=recruitService.recruitCount(s_recruit);
		String pageCode=PageUtil.getPagation(request.getContextPath()+ "/recruit/list.do", total, Integer.parseInt(page), pageBean.getPageSize());
		mav.addObject("recruitList", recruitList);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "招聘管理");
		mav.addObject("mainPage", "/recruit/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("/preSave")
	public ModelAndView preSave(@RequestParam(value="id",required=false) String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("modeName", "招聘管理");
		mav.addObject("mainPage", "/recruit/save.jsp");
		List<Recruit> recruitList=recruitService.recruitList(null, null);
		mav.addObject("recruitList", recruitList);
		mav.setViewName("main");
		
		DataDic s_dataDic=new DataDic();
		s_dataDic.setDdTypeName("招聘来源");
		List<DataDic> recruitFromDataDicList=dataDicService.dataDicList(null, s_dataDic);
		mav.addObject("recruitFromDataDicList",recruitFromDataDicList);
		
		if(StringUtil.isEmpty(id)){
			mav.addObject("actionName", "招聘信息添加");
		}else{
			mav.addObject("actionName", "招聘信息修改");
			Recruit recruit=recruitService.loadById(Integer.parseInt(id));
			mav.addObject("recruit", recruit);
			
		}
		return mav;
	}
	
	@RequestMapping("/save")
	public String save(Recruit recruit){
		if(recruit.getId()==null){
			recruitService.add(recruit);
		}else{
			recruitService.update(recruit);
		}
		return "redirect:/recruit/list.do";
	}
	
	@RequestMapping("/delete")
	public void delete(@RequestParam(value="id",required=false) String id,HttpServletResponse resp) throws Exception{
		recruitService.delete(Integer.parseInt(id));
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(result, resp);
	}
	
	@RequestMapping("/checkHealth")
	public void setHealth(@RequestParam(value="id",required=false) String id,HttpServletResponse resp) throws Exception{
		recruitService.setHealth(Integer.parseInt(id));
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(result, resp);
	}
	
	@RequestMapping("/checkIdcard")
	public void setIdcard(@RequestParam(value="id",required=false) String id,HttpServletResponse resp) throws Exception{
		recruitService.setIdcard(Integer.parseInt(id));
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(result, resp);
	}
	
}
