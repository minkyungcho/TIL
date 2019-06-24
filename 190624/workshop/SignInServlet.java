package com.sds;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet({ "/SignInServlet", "/signin" })
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	// Check
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd1 = request.getParameter("pwd1");
		String result_id = "1";
		String result_pwd1 = "1";
		Pattern p = Pattern.compile("(^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{6,20}$)");


		if(pwd1 == null) {
			if(id.equals("aaaa")) {
				result_id = "0";
			}
			PrintWriter out_id = response.getWriter();
			out_id.print(result_id);
			out_id.close();
		}
		if(id == null) {
			Matcher m = p.matcher(pwd1);
			if(!m.find()) {
				result_pwd1 = "0";
			}
			PrintWriter out_pwd = response.getWriter();
			out_pwd.print(result_pwd1);
			out_pwd.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd1 = request.getParameter("pwd1");
		String name = request.getParameter("name");
		System.out.println(id+" "+pwd1+" "+name);
		response.sendRedirect("signinok.html");
	}

}
