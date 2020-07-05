package com.cwl.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception ex) {
		ModelAndView mv = new ModelAndView();
		if(ex instanceof AuctionPriceException) {
			AuctionPriceException priceException = (AuctionPriceException)ex;
			mv.addObject("errorMsg", priceException.getMessage());
			mv.setViewName("error");
		}else {
			mv.addObject("errorMsg", "未知异常");
			mv.setViewName("error");
		}
		return mv;
	}

}
