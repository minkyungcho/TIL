package com.sds;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request : 들어오는거
		// response : 나가는거 ㄱ
		System.out.println("doGet Start...");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println(id + " " + pwd);

		// 결과는 HTML5로 뿌려줘야함
//		response.sendRedirect("ok,sptj++sp"); // 현재 servlet에서 다른쪽으로 이동, "ok.jsp" 서버프로그램 (web 폴더에 위치)
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(3);
		list.add(8);
		
		ArrayList<Item> list2 = new ArrayList<Item>();
		list2.add(new Item("k1", 1000));
		list2.add(new Item("k2", 2000));
		list2.add(new Item("k3", 3000));
		list2.add(new Item("k4", 4000));
		list2.add(new Item("k5", 5000));
		
		request.setAttribute("list1", list);
		request.setAttribute("list2", list);
		request.setAttribute("id", id);
		request.setAttribute("cnt", 7);
//		request.setAttribute("pwd", pwd);
//		RequestDispatcher rd = request.getRequestDispatcher("ok.jsp");
//		rd.forward(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("ok.jsp");
		rd.forward(request, response);

//		response.setContentType("text/html;charset=UTF-8"); // 
//		response.setCharacterEncoding("UTF-8"); // response로 나가는 타입
//		PrintWriter out = response.getWriter();
//		out.println("<h1>LOGIN OK</h1>");
//		out.println("<h1>"+id+"님 환영합니다.</h1>");
//		out.close();

	}

}
