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
			mv.addObject("navi", Navi.productdetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("main");		
		return mv;
	}
	@RequestMapping("/pdelete.mc")
	public String pdelete(Integer id) {
		System.out.println(id);
		try {
			biz.remove(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:productlist.mc";
	}
	@RequestMapping("/pupdate.mc")
	public ModelAndView pupdate(ModelAndView mv, Integer id) {
		Product p = null;
		
		try {
			p = biz.get(id);
			mv.addObject("pupdate", p);
			mv.addObject("center", "product/update");
			mv.addObject("navi", Navi.productupdate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("main");
		return mv;
	}
	@RequestMapping("/pupdateimpl.mc")
	public String pupdateimpl(ModelAndView mv, Product p) {
		System.out.println(p);
		if(p.getMf().getSize() == 0) {
			try {
				biz.modify(p);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			Util.saveFile(p.getMf());
			String imgname = p.getMf().getOriginalFilename();
			p.setImgname(imgname);
			
			try {
				biz.modify(p);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "redirect:pdetail.mc?id="+p.getId();
	}
	
	
}
