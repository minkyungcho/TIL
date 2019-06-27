package com.sds;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/RegisterServlet", "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("service Start...");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String gender = request.getParameter("gender");
		String job = request.getParameter("job");
		String hobbys[] = request.getParameterValues("hobby");
		System.out.println(id + " " + pwd + " " + gender + " " + job + " " + hobbys);

		request.setAttribute("id", id);
		request.setAttribute("pwd", pwd);
		request.setAttribute("gender", gender);
		request.setAttribute("job", job);
		request.setAttribute("hobby", hobbys);

		RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
		rd.forward(request, response);
	}

}
