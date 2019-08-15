package com.x.controller;

import com.x.entity.Department;
import com.x.entity.PageBean;
import com.x.service.DepartmentService;
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

/**
 * 部门管理建立在雇员管理基础之上
 * @author 稚
 *
 */

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,Department s_department,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(page==null){
			page="1";
			session.setAttribute("s_department", s_department);
		}else{
			s_department=(Department)session.getAttribute("s_department");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),5); //分页：一页5条（此处先写死）
		List<Department> departmentList=departmentService.departmentList(pageBean,s_department);
		int total=departmentService.departmentCount(s_department);
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/department/list.do", total, Integer.parseInt(page), pageBean.getPageSize());
		mav.addObject("departmentList", departmentList);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "部门管理");
		mav.addObject("mainPage", "/department/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	@RequestMapping("/preSave")
	public ModelAndView preSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("modeName", "部门管理");
		mav.addObject("mainPage","/department/save.jsp");
		mav.setViewName("main");
		if(StringUtil.isEmpty(id)){
			mav.addObject("actionName", "部门信息添加");
		}else{
			mav.addObject("actionName", "部门信息修改");
			Department department=departmentService.loadById(Integer.parseInt(id));
			mav.addObject("department", department);
			
		}
		return mav;
	}
	
	@RequestMapping("/save")
	public String save(Department department){
		if(department.getDeptId()==null){
			departmentService.add(department);
		}else{
			departmentService.update(department);
		}
		return "redirect:/department/list.do";
	}
	
	@RequestMapping("/delete")
	public void delete(@RequestParam(value="id",required=false)String id,HttpServletResponse resp) throws Exception{
		departmentService.delete(Integer.parseInt(id));
		JSONObject result=new JSONObject();
		result.put("success", "true");
		ResponseUtil.write(result, resp);
	}
	
}
