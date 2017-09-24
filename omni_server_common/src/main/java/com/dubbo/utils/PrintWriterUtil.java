package com.dubbo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @description <p>工具类</p> 
 * @author ljh
 * @description ajax spring controller json out
 */
public class PrintWriterUtil {

private final static Logger LOG = LoggerFactory.getLogger(PrintWriterUtil.class);
	
	public static void reder(HttpServletResponse response, String text) {
		response.reset();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			LOG.error("response写出异常->", e);
		}
		out.write(text);
		out.flush();
		out.close();
	}
	
}
