package com.cwl.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwl.mapper.AuctionCustomMapper;
import com.cwl.mapper.AuctionMapper;
import com.cwl.mapper.AuctionrecordMapper;
import com.cwl.pojo.Auction;
import com.cwl.pojo.AuctionExample;
import com.cwl.pojo.AuctionQueryExample;
import com.cwl.pojo.Auctionrecord;
import com.cwl.service.RealAuctionService;
import com.cwl.utils.AuctionPriceException;
@Service
public class RealAuctionServiceImpl implements RealAuctionService {
	@Autowired
	private AuctionMapper auctionMapper;
	@Autowired
	private AuctionCustomMapper auctionCustomMapper;
	@Autowired
	private AuctionrecordMapper auctionrecordMapper;
	@Override
	public List<Auction> queryAuctions(Auction auction) {
		AuctionExample example = new AuctionExample();
		AuctionExample.Criteria criteria= example.createCriteria();
		if(auction!=null) {
			if(auction.getAuctionname()!=null&&!"".equals(auction.getAuctionname())) {
				criteria.andAuctionnameLike("%"+auction.getAuctionname()+"%");
			}
			if(auction.getAuctiondesc()!=null&&!"".equals(auction.getAuctiondesc())) {
				criteria.andAuctiondescLike("%"+auction.getAuctiondesc()+"%");
			}
			if(auction.getAuctionstarttime()!=null) {
				criteria.andAuctionstarttimeGreaterThan(auction.getAuctionstarttime());
			}
			if(auction.getAuctionendtime()!=null) {
				criteria.andAuctionendtimeLessThan(auction.getAuctionendtime());
			}
			if(auction.getAuctionstartprice()!=null) {
				criteria.andAuctionstartpriceGreaterThan(auction.getAuctionstartprice());
			}
		}
		example.setOrderByClause("auctionstarttime desc");
		List<Auction> list = auctionMapper.selectByExample(example);
		return list;
	}
	@Override
	public Auction findAuctionAndRecordList(int auctionid) {
		return auctionCustomMapper.findAuctionAndRecordList(auctionid);
	}
	@Override
	public void addAuctionRecord(Auctionrecord auctionrecord) throws Exception {
		Auction auction = auctionCustomMapper.findAuctionAndRecordList(auctionrecord.getAuctionid());
		if(auction.getAuctionendtime().after(new Date())==false) {
			throw new AuctionPriceException("拍卖时间已结束");
		}
		if(auction.getAuctionrecordList()!=null&&auction.getAuctionrecordList().size()>0) {
			Auctionrecord maxRecord = auction.getAuctionrecordList().get(0);
			if(auctionrecord.getAuctionprice()<=maxRecord.getAuctionprice()) {
				throw new AuctionPriceException("价格比需要比最高竞价价格更高");
			}
		}else {
			if(auctionrecord.getAuctionprice()<=auction.getAuctionstartprice()) {
				throw new AuctionPriceException("价格比需要与起拍价");
			}
		}
		auctionrecordMapper.insert(auctionrecord);
	}
	@Override
	public void addAuction(Auction auction) {
		auctionMapper.insertSelective(auction);
	}
	@Override
	public void removeAuctionAndAuctionrecord(Integer auctionid) {
		
		auctionrecordMapper.deleteByAuctionId(auctionid);
		
	}
	@Override
	public void removeAutcion(int id) {
		auctionMapper.deleteByPrimaryKey(id);
	}
	@Override
	public Auction findAuction(int id) {
		return auctionMapper.selectByPrimaryKey(id);
	}
	@Override
	public void updateOneAuction(Auction auction) {
		auctionMapper.updateByPrimaryKeySelective(auction);
	}
	@Override
	public List<AuctionQueryExample> findTimeOutAuction() {
		
		return auctionCustomMapper.selectTimeOutAuction();
	}
	@Override
	public List<Auction> findInTimeAuction() {
		return auctionCustomMapper.findAuctionNoEndtimeList();
	}

}
