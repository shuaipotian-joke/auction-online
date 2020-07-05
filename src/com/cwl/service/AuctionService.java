package com.cwl.service;

import com.cwl.pojo.Auction;
import com.cwl.pojo.Auctionuser;

public interface AuctionService {
	public Auctionuser login(String username,String userpassword);
	public void register(Auctionuser user);
	public boolean checkUserExist(String username);
}
