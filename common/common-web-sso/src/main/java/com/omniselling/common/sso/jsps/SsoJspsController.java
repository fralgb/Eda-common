package com.omniselling.common.sso.jsps;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SsoJspsController {
	
	
	@RequestMapping(value = "/index.jsp", method = RequestMethod.GET)
	public void index (
			HttpServletRequest request, 
			HttpServletResponse response,
			String reportFileName) throws IOException{
		
		StringBuilder responseString = new StringBuilder();
		responseString.append("<html>");
		responseString.append(	"<body>");
		responseString.append(		"<script>");
		responseString.append(			"function cors (){");
		responseString.append(				"var search = location.search;");
		responseString.append(				"search = search.replace('?', '').split('&');");
		responseString.append(				"var o = {};");
		responseString.append(				"search.forEach(function (cur) {");
		responseString.append(					"var i = cur.split('=');");
		responseString.append(					"o[i[0]] = i[1];");
		responseString.append(				"});");
		responseString.append(				"var ifr = document.createElement('iframe');");
		responseString.append(				"ifr.style.display = 'none';");
		responseString.append(				"ifr.src ='" + request.getParameter("redirect_url") + "/#/cors/login';");
		responseString.append(				"document.body.appendChild(ifr);");
		responseString.append(			"}");
		responseString.append(			"cors();");
		responseString.append(		"</script>");
		responseString.append(	"</body>");
		responseString.append("</html>");
		response.setContentType("text/html");
		response.getOutputStream().write(responseString.toString().getBytes("UTF-8"));
	}
	
	@RequestMapping(value = "/logout.jsp", method = RequestMethod.GET)
	public void logout (
			HttpServletRequest request, 
			HttpServletResponse response,
			String reportFileName) throws IOException{
		
		StringBuilder responseString = new StringBuilder();
		responseString.append("<html>");
		responseString.append(	"<body>");
		responseString.append(		"<script>");
		responseString.append(			"function cors (){");
		responseString.append(				"var search = location.search;");
		responseString.append(				"search = search.replace('?', '').split('&');");
		responseString.append(				"var o = {};");
		responseString.append(				"search.forEach(function (cur) {");
		responseString.append(					"var i = cur.split('=');");
		responseString.append(					"o[i[0]] = i[1];");
		responseString.append(				"});");
		responseString.append(				"var ifr = document.createElement('iframe');");
		responseString.append(				"ifr.style.display = 'none';");
		responseString.append(				"ifr.src ='" + request.getParameter("redirect_url") + "/#/cors/logout';");
		responseString.append(				"document.body.appendChild(ifr);");
		responseString.append(			"}");
		responseString.append(			"cors();");
		responseString.append(		"</script>");
		responseString.append(	"</body>");
		responseString.append("</html>");
		response.setContentType("text/html");
		response.getOutputStream().write(responseString.toString().getBytes("UTF-8"));
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout2 (
			HttpServletRequest request, 
			HttpServletResponse response,
			String reportFileName) throws IOException{
		
		StringBuilder responseString = new StringBuilder();
		responseString.append("<html>");
		responseString.append(	"<body>");
		responseString.append(		"<script>");
		responseString.append(			"function cors (){");
		responseString.append(				"var search = location.search;");
		responseString.append(				"search = search.replace('?', '').split('&');");
		responseString.append(				"var o = {};");
		responseString.append(				"search.forEach(function (cur) {");
		responseString.append(					"var i = cur.split('=');");
		responseString.append(					"o[i[0]] = i[1];");
		responseString.append(				"});");
		responseString.append(				"var ifr = document.createElement('iframe');");
		responseString.append(				"ifr.style.display = 'none';");
		responseString.append(				"ifr.src ='" + request.getParameter("redirect_url") + "/#/cors/logout';");
		responseString.append(				"document.body.appendChild(ifr);");
		responseString.append(			"}");
		responseString.append(			"cors();");
		responseString.append(		"</script>");
		responseString.append(	"</body>");
		responseString.append("</html>");
		response.setContentType("text/html");
		response.getOutputStream().write(responseString.toString().getBytes("UTF-8"));
	}
}
