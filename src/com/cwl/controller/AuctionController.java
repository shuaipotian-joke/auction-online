package com.cwl.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cwl.pojo.Auctionuser;
import com.cwl.service.AuctionService;

@Controller
public class AuctionController {
	@Autowired
	AuctionService auctionService;
	@RequestMapping("/doLogin")
	public String login(String username,String userpassword,HttpSession session,Model model,String inputCode) {
		String numrand = (String) session.getAttribute("numrand");
		if(!numrand.equals(inputCode)) {
			model.addAttribute("msg", "验证码错误");
			return "login";
		}
		Auctionuser loginUser = auctionService.login(username, userpassword);
		if(loginUser!=null) {
			session.setAttribute("user", loginUser);
			return "redirect:/queryAuctions";
		}else {
			model.addAttribute("msg", "账号或密码错误");
			return "login";
		}
	}
	@RequestMapping("/register")
	public String register(Model model,@ModelAttribute("registerUser") @Validated Auctionuser user,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			for (FieldError fieldError : errors) {
				model.addAttribute(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return "register";
		}
		auctionService.register(user);
		return "login";
	}
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
	
	@RequestMapping("/checkUserExist")
	@ResponseBody
	public String checkUserExist(String username) {
		System.out.println(username);
		boolean isExist = auctionService.checkUserExist(username);
		System.out.println(isExist);
		return isExist+"";
	}
}
