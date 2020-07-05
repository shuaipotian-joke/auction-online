package com.cwl.mapper;

import java.util.List;

import com.cwl.pojo.Auction;
import com.cwl.pojo.AuctionQueryExample;

public interface AuctionCustomMapper {
	public Auction findAuctionAndRecordList(int auctionid);

	public List<AuctionQueryExample> selectTimeOutAuction();

	public List<Auction> findAuctionNoEndtimeList();
}
