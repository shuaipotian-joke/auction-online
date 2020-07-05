package com.cwl.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cwl.pojo.Auction;
import com.cwl.pojo.AuctionQueryExample;
import com.cwl.pojo.Auctionrecord;
import com.cwl.pojo.Auctionuser;
import com.cwl.service.RealAuctionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class RealAuctionController {
	@Autowired
	private RealAuctionService service;
	public static final int PAGE_SIZE = 8;
	@RequestMapping("/queryAuctions")
	public ModelAndView queryAuctions(
			@RequestParam(defaultValue = "1",required = false,value = "pageNum")int pageNum,
			@ModelAttribute("condition")Auction auction) {
		ModelAndView mv = new ModelAndView();
		PageHelper.startPage(pageNum, PAGE_SIZE);
		List<Auction> list = service.queryAuctions(auction);
		PageInfo page = new PageInfo<>(list);
		
		mv.addObject("auctionList", list);
		mv.addObject("page", page);
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("toDetail/{id}")
	public ModelAndView toDetail(@PathVariable int id) {
		ModelAndView mv = new ModelAndView();
		Auction auction = service.findAuctionAndRecordList(id);
		List<Auctionrecord> list = auction.getAuctionrecordList();
		mv.addObject("list", list);
		mv.addObject("auction", auction);
		mv.setViewName("auctionDetail");
		return mv;
	}
	@RequestMapping("saveAuctionRecord")
	public String saveAuctionRecord(Auctionrecord auctionrecord,HttpSession session,Model model) throws Exception {
		
			auctionrecord.setAuctiontime(new Date());
			Auctionuser user = (Auctionuser)session.getAttribute("user");
			auctionrecord.setUserid(user.getUserid());
			service.addAuctionRecord(auctionrecord);
		
		
		return "redirect:/toDetail/"+auctionrecord.getAuctionid();
	}
	@RequestMapping("/addAuction")
	public String addAuction(Auction auction,MultipartFile itemPic,HttpSession session) {
		System.out.println("前");
		try {
			String path = session.getServletContext().getRealPath("images");
			File file = new File(path,itemPic.getOriginalFilename());
			System.out.println(itemPic.getOriginalFilename());
			auction.setAuctionpictype("jpg");
			itemPic.transferTo(file);
			auction.setAuctionpic(itemPic.getOriginalFilename());
			service.addAuction(auction);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("后");
		return "redirect:/queryAuctions";
	}
	@RequestMapping("/removeAuction/{id}")
	public String removeAuction(@PathVariable int id) {
		service.removeAuctionAndAuctionrecord(id);
		service.removeAutcion(id);
		return "redirect:/queryAuctions";
	}
	@RequestMapping("/listUpdate/{id}")
	public String listUpdate(@PathVariable int id,Model model) {
		model.addAttribute("auction", service.findAuction(id));
		return "updateAuction";
	}
	@RequestMapping("/updateAuction")
	public String updateAuction(Auction auction,MultipartFile pic,HttpSession session) {
		try {
			String path = session.getServletContext().getRealPath("images");
			File file = new File(path,pic.getOriginalFilename());
			pic.transferTo(file);
			auction.setAuctionpic(pic.getOriginalFilename());
			service.updateOneAuction(auction);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/queryAuctions";
	}
	@RequestMapping("/listAuctionResult")
	public String listAuctionResult(Model model) {
		List<AuctionQueryExample> list = service.findTimeOutAuction();
		model.addAttribute("list", list);
		List<Auction> list2 = service.findInTimeAuction();
		model.addAttribute("list2", list2);
		return "AuctionResult";
	}

}
