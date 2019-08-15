package com.x.controller;

import com.x.entity.DataDic;
import com.x.entity.DataDicType;
import com.x.entity.PageBean;
import com.x.service.DataDicService;
import com.x.service.DataDicTypeService;
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
@RequestMapping("/dataDic")
public class DataDicController {
	
	@Autowired
	private DataDicService dataDicService;
	
	@Autowired
	private DataDicTypeService dataDicTypeService;
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page, DataDic s_dataDic, HttpServletRequest req){
		ModelAndView mav=new ModelAndView();
		HttpSession session=req.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("s_dataDic", s_dataDic);
		}else{
			s_dataDic=(DataDic)session.getAttribute("s_dataDic");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10); //分页：一页10条（此处先写死）
		List<DataDic> dataDicList=dataDicService.dataDicList(pageBean,s_dataDic);
		int total=dataDicService.dataDicCount(s_dataDic);
		String pageCode= PageUtil.getPagation(req.getContextPath()+ "/dataDic/list.do", total, Integer.parseInt(page), pageBean.getPageSize());
		mav.addObject("dataDicList", dataDicList);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "系统数据管理");
		mav.addObject("mainPage", "/dataDic/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	@RequestMapping("/preSave")
	public ModelAndView preSave(@RequestParam(value="ddId",required=false)String ddId){
		ModelAndView mav=new ModelAndView();
		mav.addObject("modeName", "系统数据管理");
		mav.addObject("mainPage", "/dataDic/save.jsp");

		mav.setViewName("main");
		
		//数据字典类别（构建下拉框）
		List<DataDicType> dataDicTypeList=dataDicTypeService.dataDicTypeList(null, null);
		mav.addObject("dataDicTypeList", dataDicTypeList);
		
		if(StringUtil.isEmpty(ddId)){
			mav.addObject("actionName", "系统数据添加");
		}else{
			mav.addObject("actionName", "系统数据修改");
			DataDic dataDic=dataDicService.loadById(Integer.parseInt(ddId)); //根据主键返回DataDic对象
			mav.addObject("dataDic", dataDic);
		}
		return mav;
	}
	@RequestMapping("/save")
	public String save(DataDic dataDic){
		if(dataDic.getDdId()==null){
			dataDicService.add(dataDic);
		}else{
			dataDicService.update(dataDic);
		}
		return "redirect:/dataDic/list.do";
	}
	@RequestMapping("/delete")
	public void delete(@RequestParam(value="ddId",required=false)String ddId,HttpServletResponse resp) throws Exception{
		dataDicService.delete(Integer.parseInt(ddId));
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(result, resp);
	}
	
}
