package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frame.Biz;
import com.vo.User;

@Controller
public class MainController {
	
	@Resource(name="ubiz")
	Biz<String,User> biz;
	
	@RequestMapping("/main.mc")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		return mv;
	}
	@RequestMapping("/login.mc")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("center", "login");
		mv.addObject("navi", Navi.login);
		mv.setViewName("main");
		return mv;
	}
	@RequestMapping("/register.mc")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("center", "register");
		mv.addObject("navi", Navi.register);
		mv.setViewName("main");
		return mv;
	}
	@RequestMapping("/aboutus.mc")
	public ModelAndView aboutus() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("center", "aboutus");
		mv.addObject("navi", Navi.aboutus);
		mv.setViewName("main");
		return mv;
	}
	@RequestMapping("/loginimpl.mc")
	public ModelAndView loginimpl(ModelAndView mv, HttpServletRequest request, HttpSession session) {
		String id = request.getParameter("id"); 
		String pwd = request.getParameter("pwd"); 
		
		try {
			User dbuser = biz.get(id);
			if(pwd.equals(dbuser.getPwd())) {
				session.setAttribute("loginuser", dbuser);
				mv.addObject("center", "loginok");
			}
		} catch (Exception e) {
			mv.addObject("center", "loginfail");
			e.printStackTrace();
		}
		mv.setViewName("main");
		return mv;
	}
	@RequestMapping("/logout.mc")
	public ModelAndView logout(ModelAndView mv, HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		mv.setViewName("main");
		return mv;
	}
}
