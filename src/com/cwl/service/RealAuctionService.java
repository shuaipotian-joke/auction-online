package com.cwl.service;

import java.util.List;

import com.cwl.pojo.Auction;
import com.cwl.pojo.AuctionQueryExample;
import com.cwl.pojo.Auctionrecord;

public interface RealAuctionService {
	public List<Auction> queryAuctions(Auction auction);
	public Auction findAuctionAndRecordList(int auctionid);
	public void addAuctionRecord(Auctionrecord auctionrecord) throws Exception;
	public void addAuction(Auction auction);
	public void removeAuctionAndAuctionrecord(Integer auctionid);
	public void removeAutcion(int id);
	public Auction findAuction(int id);
	public void updateOneAuction(Auction auction);
	public List<AuctionQueryExample> findTimeOutAuction();
	public List<Auction> findInTimeAuction();
}
