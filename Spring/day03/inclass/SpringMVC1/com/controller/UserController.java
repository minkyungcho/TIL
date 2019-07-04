package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@RequestMapping("/useradd.mc")
	public ModelAndView uadd() {
		ModelAndView mv= new ModelAndView();
		mv.setViewName("user/add");		
		return mv;
	}
	@RequestMapping("/userlist.mc")
	public ModelAndView ulist() {
		ModelAndView mv= new ModelAndView();
		mv.setViewName("user/list");		
		return mv;
	}
}
