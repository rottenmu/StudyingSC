package com.cn.demo.test01.config;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

@WebServlet(urlPatterns = "/druid/*", initParams={  
	    @WebInitParam(name="allow",value=""),// IP白名单 (没有配置或者为空，则允许所有访问)  
	    @WebInitParam(name="deny",value="127.0.0.1"),// IP黑名单 (存在共同时，deny优先于allow)  
	    @WebInitParam(name="loginUsername",value="root"),// 用户名  
	    @WebInitParam(name="loginPassword",value="123456"),// 密码  
	    @WebInitParam(name="resetEnable",value="true")// 禁用HTML页面上的“Reset All”功能  
	})
public class DruidStatViewServlet extends StatViewServlet {
	private static final long serialVersionUID = 2359758657306626394L;
 
}