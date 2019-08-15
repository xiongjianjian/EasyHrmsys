package com.x.controller;

import com.x.entity.DataDic;
import com.x.entity.Department;
import com.x.entity.Employee;
import com.x.entity.PageBean;
import com.x.service.DataDicService;
import com.x.service.DepartmentService;
import com.x.service.EmployeeService;
import com.x.util.PageUtil;
import com.x.util.ResponseUtil;
import com.x.util.StringUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DataDicService dataDicService;
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,Employee s_employee,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(page==null){
			page="1";
			session.setAttribute("s_employee", s_employee);
		}else{
			s_employee=(Employee)session.getAttribute("s_employee");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10); //分页：一页10条（此处先写死）
		List<Employee> employeeList=employeeService.employeeList(pageBean,s_employee);
		int total=employeeService.employeeCount(s_employee);
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/employee/list.do", total, Integer.parseInt(page), pageBean.getPageSize());
		mav.addObject("employeeList", employeeList);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "人事管理");
		mav.addObject("mainPage", "/employee/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("/preSave")
	public ModelAndView preSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("modeName", "人事管理");
		mav.addObject("mainPage","/employee/save.jsp");
		mav.setViewName("main");
		
		DataDic s_dataDic=new DataDic();
		s_dataDic.setDdTypeName("性别");
		List<DataDic> sexDataDicList=dataDicService.dataDicList(null, s_dataDic);
		mav.addObject("sexDataDicList",sexDataDicList);
		
		s_dataDic.setDdTypeName("民族");
		List<DataDic> nationDataDicList=dataDicService.dataDicList(null, s_dataDic);
		mav.addObject("nationDataDicList",nationDataDicList);
		
		s_dataDic.setDdTypeName("政治面貌");
		List<DataDic> zzmmDataDicList=dataDicService.dataDicList(null, s_dataDic);
		mav.addObject("zzmmDataDicList",zzmmDataDicList);
		
		s_dataDic.setDdTypeName("学历");
		List<DataDic> recordDataDicList=dataDicService.dataDicList(null, s_dataDic);
		mav.addObject("recordDataDicList",recordDataDicList);
		
		s_dataDic.setDdTypeName("招聘来源");
		List<DataDic> sourceDataDicList=dataDicService.dataDicList(null, s_dataDic);
		mav.addObject("sourceDataDicList",sourceDataDicList);
		
		s_dataDic.setDdTypeName("聘用形式");
		List<DataDic> formDataDicList=dataDicService.dataDicList(null, s_dataDic);
		mav.addObject("formDataDicList",formDataDicList);
		
		List<Department> departmentList=departmentService.departmentList(null, null);
		mav.addObject("departmentList",departmentList);
		
		if(StringUtil.isEmpty(id)){
			Employee employee=new Employee();
			employee.setEmpPicture("nophoto.jpg");
			mav.addObject("employee", employee);
			mav.addObject("actionName", "雇员信息添加");
		}else{
			mav.addObject("actionName", "雇员信息修改");
			Employee employee=employeeService.loadById(Integer.parseInt(id));
			mav.addObject("employee", employee);
			
		}
		return mav;
	}
	
//	/**
//	 * 弹出模态框，显示雇员的详细信息
//	 * @param id
//	 */
//	@RequestMapping("/view")
//	public ModelAndView view(@RequestParam(value="id",required=false)String id){
//		ModelAndView mav=new ModelAndView();
//		mav.addObject("modeName", "雇员管理");
//		mav.addObject("mainPage","/employee/view.jsp");
//		mav.setViewName("main");
//
//		mav.addObject("actionName", "雇员信息查看");
//		Employee employee=employeeService.loadById(Integer.parseInt(id));
//		mav.addObject("employee", employee);
//		
//		return mav;
//	}
	
	@RequestMapping("/save")
	public String save(@RequestParam("file")MultipartFile file,HttpServletRequest request,Employee employee) throws Exception{
		
		if(file.getSize()!=0){ //文件上传
			String filePath=request.getServletContext().getRealPath("/");
			System.out.println(filePath);
			file.transferTo(new File(filePath+"userImages/"+file.getOriginalFilename()));
			employee.setEmpPicture(file.getOriginalFilename());
		}
		
		if(employee.getEmpId().equals("")){ //添加
			String empNo=employeeService.findLastEmpNo();
			empNo=(Integer.parseInt(empNo)+1)+""; //构造员工工号为当前最大值+1
			employee.setEmpNo(empNo);
			employeeService.add(employee);
		}else{ //修改
			employeeService.update(employee);
		}
		return "redirect:/employee/list.do";
	}
	
	@RequestMapping("/delete")
	public void delete(@RequestParam(value="id",required=false)String id,HttpServletResponse resp) throws Exception{
		employeeService.delete(Integer.parseInt(id));
		JSONObject result=new JSONObject();
		result.put("success", "true");
		ResponseUtil.write(result, resp);
	}
	
}
