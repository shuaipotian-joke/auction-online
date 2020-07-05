package com.cwl.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.cwl.utils.AuctionPriceException;

@ControllerAdvice
public class MyException {
	@ExceptionHandler(AuctionPriceException.class)
	public ModelAndView priceException(Exception e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", "全局异常消息"+e.getMessage());
		mv.setViewName("error");
		return mv;
	}
}
