package com.x.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Response工具类
 * @author 稚
 *
 */
public class ResponseUtil {

	public static void write(Object o,HttpServletResponse response)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
