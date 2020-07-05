package com.cwl.junit;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cwl.pojo.Auction;
import com.cwl.pojo.AuctionQueryExample;
import com.cwl.pojo.Auctionrecord;
import com.cwl.service.RealAuctionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml","classpath:spring/springmvc*.xml"})
public class TestAuctionMapper {
	@Autowired
	private RealAuctionService realAuctionService;
	@Test
	public void testQuery() {
		Auction auction = realAuctionService.findAuctionAndRecordList(20);
		System.out.println(auction.getAuctionname());
		System.out.println(auction.getAuctiondesc());
		System.out.println(auction.getAuctionendtime());
		System.out.println(auction.getAuctionrecordList());
		List<Auctionrecord> auctionrecordList = auction.getAuctionrecordList();
		for (Auctionrecord auctionrecord : auctionrecordList) {
			System.out.println("\t"+auctionrecord.getAuctiontime()+","+auctionrecord.getAuctionprice()+","+auctionrecord.getUser().getUsername());
		}
	}
	@Test
	public void testQuery2() {
		List<AuctionQueryExample> findTimeOutAuction = realAuctionService.findTimeOutAuction();
		for (AuctionQueryExample auctionQueryExample : findTimeOutAuction) {
			System.out.println(auctionQueryExample.getAuctionname());
			System.out.println(auctionQueryExample.getAuctionprice());
			System.out.println(auctionQueryExample.getAuctionstarttime());
			
			System.out.println(auctionQueryExample.getUsername());
		}
	}
	@Test
	public void testQuery3() {
		List<Auction> list = realAuctionService.findInTimeAuction();
		for (Auction auction : list) {
			System.out.println(auction.getAuctionname());
			System.out.println(auction.getAuctionstarttime());
			List<Auctionrecord> list2 = auction.getAuctionrecordList();
			for (Auctionrecord record : list2) {
				System.out.println("\t"+record.getAuctionprice()+","+record.getUser().getUsername());
			}
		}
	}
}
