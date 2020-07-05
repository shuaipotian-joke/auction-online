package com.cwl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwl.mapper.AuctionMapper;
import com.cwl.mapper.AuctionuserMapper;
import com.cwl.pojo.Auction;
import com.cwl.pojo.AuctionExample;
import com.cwl.pojo.Auctionuser;
import com.cwl.pojo.AuctionuserExample;
import com.cwl.service.AuctionService;
@Service
public class AuctionServiceImpl implements AuctionService {
	@Autowired
	private AuctionuserMapper auctionuserMapper;
	@Override
	public Auctionuser login(String username, String userpassword) {
		AuctionuserExample example = new AuctionuserExample();
		AuctionuserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andUserpasswordEqualTo(userpassword);
		List<Auctionuser> list = auctionuserMapper.selectByExample(example);
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void register(Auctionuser user) {
		user.setUserisadmin(0);
		auctionuserMapper.insert(user);
		
	}

	@Override
	public boolean checkUserExist(String username) {
		AuctionuserExample example = new AuctionuserExample();
		AuctionuserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<Auctionuser> list = auctionuserMapper.selectByExample(example);
		if(list!=null&&list.size()>0) {
			return true;
		}
		return false;
	}

}
