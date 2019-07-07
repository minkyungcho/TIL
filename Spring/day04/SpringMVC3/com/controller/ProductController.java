package com.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frame.Biz;
import com.vo.Product;

@Controller
public class ProductController {
	@Resource(name="pbiz")
	Biz<Integer, Product> biz;
	
	@RequestMapping("/productadd.mc")
	public ModelAndView padd() {
		ModelAndView mv= new ModelAndView();
		mv.addObject("center", "product/add");
		mv.addObject("navi", Navi.productadd);
		mv.setViewName("main");		
		return mv;
	}
	@RequestMapping("/productaddimpl.mc")
	public ModelAndView paddimpl(ModelAndView mv, Product product) {
		String imgname = product.getMf().getOriginalFilename();
		product.setImgname(imgname);
		
		try {
			biz.register(product);
			Util.saveFile(product.getMf());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.setViewName("main");		
		return mv;
	}
	@RequestMapping("/productlist.mc")
	public ModelAndView plist() {
		ModelAndView mv= new ModelAndView();
		ArrayList<Product> list = null;
		try {
			list = biz.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("plist", list);
		mv.addObject("center", "product/list");
		mv.addObject("navi", Navi.productlist);
		mv.setViewName("main");		
		return mv;
	}
	@RequestMapping("/pdetail.mc")
	public ModelAndView userdetail(ModelAndView mv, Integer id) {
		Product p = null;
		
		try {
			p = biz.get(id);
			mv.addObject("pdetail", p);
			mv.addObject("center", "product/detail");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("main");		
		return mv;
	}
	@RequestMapping("/pdelete.mc")
	public String pdelete(ModelAndView mv, Integer id) {
		
		try {
			biz.remove(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:plist.mc";
	}
	@RequestMapping("/pupdate.mc")
	public ModelAndView pupdate(ModelAndView mv, Integer id) {
		Product p = null;
		
		try {
			p = biz.get(id);
			mv.addObject("pupdate", p);
			mv.addObject("center", "product/update");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("main");
		return mv;
	}
	@RequestMapping("/pupdateimpl.mc")
	public String pupdateimpl(ModelAndView mv, Product p) {
		String imgname = p.getMf().getOriginalFilename();
		p.setImgname(imgname);
		
		return "redirect:userdetail.mc?id="+p.getId();
	}
	
	
}
