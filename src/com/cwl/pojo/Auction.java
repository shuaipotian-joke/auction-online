package com.cwl.pojo;

import java.util.Date;
import java.util.List;

public class Auction {
    private Integer auctionid;

    private String auctionname;

    private Double auctionstartprice;

    private Double auctionupset;

    private Date auctionstarttime;

    private Date auctionendtime;

    private String auctionpic;

    private String auctionpictype;

    private String auctiondesc;

    private List<Auctionrecord> auctionrecordList;
    public List<Auctionrecord> getAuctionrecordList() {
		return auctionrecordList;
	}

	public void setAuctionrecordList(List<Auctionrecord> auctionrecordList) {
		this.auctionrecordList = auctionrecordList;
	}

	public Integer getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Integer auctionid) {
        this.auctionid = auctionid;
    }

    public String getAuctionname() {
        return auctionname;
    }

    public void setAuctionname(String auctionname) {
        this.auctionname = auctionname == null ? null : auctionname.trim();
    }

    public Double getAuctionstartprice() {
        return auctionstartprice;
    }

    public void setAuctionstartprice(Double auctionstartprice) {
        this.auctionstartprice = auctionstartprice;
    }

    public Double getAuctionupset() {
        return auctionupset;
    }

    public void setAuctionupset(Double auctionupset) {
        this.auctionupset = auctionupset;
    }

    public Date getAuctionstarttime() {
        return auctionstarttime;
    }

    public void setAuctionstarttime(Date auctionstarttime) {
        this.auctionstarttime = auctionstarttime;
    }

    public Date getAuctionendtime() {
        return auctionendtime;
    }

    public void setAuctionendtime(Date auctionendtime) {
        this.auctionendtime = auctionendtime;
    }

    public String getAuctionpic() {
        return auctionpic;
    }

    public void setAuctionpic(String auctionpic) {
        this.auctionpic = auctionpic == null ? null : auctionpic.trim();
    }

    public String getAuctionpictype() {
        return auctionpictype;
    }

    public void setAuctionpictype(String auctionpictype) {
        this.auctionpictype = auctionpictype == null ? null : auctionpictype.trim();
    }

    public String getAuctiondesc() {
        return auctiondesc;
    }

    public void setAuctiondesc(String auctiondesc) {
        this.auctiondesc = auctiondesc == null ? null : auctiondesc.trim();
    }
}