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
import com.oreilly.servlet.MultipartRequest;
import com.product.ProductBiz;
import com.vo.Product;
import com.vo.User;

import web.dispatcher.UI;

@WebServlet({ "/ProductServlet", "/product" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Biz<Integer, Product> biz;
    public ProductServlet() {
    	biz = new ProductBiz();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String view = request.getParameter("view");
		String cmd = request.getParameter("cmd");
		String next = "main.jsp";// 무조건 next는
		if (view != null) {
			UI.build(request, view); // centef과 nav 만들어진 후 ↓ 다시 main으로
		}else if(cmd != null) {
			if(cmd.equals("productlist")) {
				ArrayList<Product> list = null;
				try {
					list = biz.get();
					request.setAttribute("plist", list);
					UI.build(request, cmd);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(cmd.equals("productadd")) {
				UI.build(request, cmd);
			}else if(cmd.equals("productaddimpl")) {
				MultipartRequest mr = new MultipartRequest(request, "C:\\web\\day1444\\web\\img", 1024 * 1024 * 100,
						"UTF-8");
				String name = mr.getParameter("name");
				String price = mr.getParameter("price");
				String imgname = mr.getOriginalFileName("imgname");
				try {
					biz.register(new Product(name, Double.parseDouble(price), imgname));
					UI.build(request, cmd);
//					next = "product/pok";
				} catch (Exception e) {
					e.printStackTrace();
					next = "product/pfail";
				}
			}else if(cmd.equals("productdetail")) {
				String id = request.getParameter("id");
				Product p = null;
				try {
					p = biz.get(Integer.parseInt(id));
					request.setAttribute("pd", p); // ud : userdetail
					UI.build(request, cmd);
//					next = "user/detail";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(cmd.equals("productdelete")) {
				String id = request.getParameter("id");
				try {
					biz.remove(Integer.parseInt(id));
					response.sendRedirect("product.do?cmd=productlist");
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(cmd.equals("productupdate")) {
				String id = request.getParameter("id");
				Product p = null;
				try {
					p = biz.get(Integer.parseInt(id));
					request.setAttribute("pu", p); // uu : userudate
					UI.build(request, cmd);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(cmd.equals("productupdateimpl")) {
				MultipartRequest mr = new MultipartRequest(request, "C:\\web\\day1444\\web\\img", 1024 * 1024 * 100,
						"UTF-8");
				String id = mr.getParameter("id");
				String name = mr.getParameter("name");
				String price = mr.getParameter("price");
				String imgname = mr.getParameter("imgname");
				String newimgname = mr.getOriginalFileName("newimgname");
				if(newimgname == null || newimgname.equals("")) {
					try {
						biz.modify(new Product(Integer.parseInt(id), name, Double.parseDouble(price), imgname));
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					try {
						biz.modify(new Product(Integer.parseInt(id), name, Double.parseDouble(price), newimgname));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				response.sendRedirect("product.do?cmd=productdetail&id="+Integer.parseInt(id));
				return;
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}

}
