package web.component;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frame.Biz;
import com.user.UserBiz;
import com.vo.User;

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import web.dispatcher.UI;

@WebServlet({ "/UserServlet", "/user" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	Biz<String,User> biz;
	
	
    public UserServlet() {
    	biz = new UserBiz();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = request.getParameter("view");
		String cmd = request.getParameter("cmd");
		String next = "main.jsp";// 무조건 next는
		if (view != null) {
			UI.build(request, view); // centef과 nav 만들어진 후 ↓ 다시 main으로
		}else if(cmd != null) {
			if(cmd.equals("userlist")) {
				ArrayList<User> list = null;
				try {
					list = biz.get();
					request.setAttribute("ulist", list);
					UI.build(request, cmd);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(cmd.equals("useradd")) {
				UI.build(request, cmd);
			}else if(cmd.equals("useraddimpl")) {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String name = request.getParameter("name");
				try {
					biz.register(new User(id,pwd,name));
					request.setAttribute("rid", id);
					UI.build(request, cmd);
//					next = "user/rok";
				} catch (Exception e) {
					e.printStackTrace();
					UI.build(request, cmd);
//					next = "user/rfail";
				}
			}else if(cmd.equals("userdetail")) {
				String id = request.getParameter("id");
				User user = null;
				try {
					user = biz.get(id);
					request.setAttribute("ud", user); // ud : userdetail
					UI.build(request, cmd);
//					next = "user/detail";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(cmd.equals("userupdate")) {
				String id = request.getParameter("id");
				User user = null;
				try {
					user = biz.get(id);
					request.setAttribute("uu", user); // uu : userudate
					UI.build(request, cmd);
//					next = "user/update";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(cmd.equals("userupdateimpl")) {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String name = request.getParameter("name");
				
				try {
					biz.modify(new User(id,pwd,name));
					response.sendRedirect("user.do?cmd=userdetail&id="+id);
					return;
				} catch (Exception e) {
					e.printStackTrace();
					next = "user/rfail";
				}
			}else if(cmd.equals("userdelete")) {
				String id = request.getParameter("id");
				try {
					biz.remove(id);
					response.sendRedirect("user.do?cmd=userlist");
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}

}
