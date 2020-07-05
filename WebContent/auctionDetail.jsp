<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="wrap">
<!-- main begin-->
  <div class="sale">
    <h1 class="lf">在线拍卖系统</h1>
    <div class="logout right"><a href="${pageContext.request.contextPath}/logout" title="注销">注销</a></div>
  </div>
  <div class="items sg-font lf">
      <ul class="rows">
        <li>名称：</li>
        <li class="borderno">${auction.auctionname}</li>
      </ul>
      <ul class="rows">
        <li>描述：</li>
        <li class="borderno">${auction.auctiondesc}</li>
      </ul>
      <ul class="rows">
        <li>开始时间：</li>
        <li class="borderno"><fmt:formatDate value="${auction.auctionstarttime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
      </ul>
      <ul class="rows">
        <li>结束时间：</li>
        <li class="borderno"><fmt:formatDate value="${auction.auctionendtime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
      </ul>
      <ul class="rows border-no">
        <li>起拍价：</li>
        <li class="borderno">${auction.auctionstartprice}</li>
      </ul>
  </div>
  <div class="rg borders"><img src="images/ad20.jpg" width="270" height="185" alt="" /></div>
  <div class="cl"></div>
  <div class="top10 salebd">
  	<form action="${pageContext.request.contextPath}/saveAuctionRecord" method="post">
       <p>
       <label for="sale">出价：</label>
       <input type="text"  class="inputwd" id="sale" name="auctionprice"/>
       <input type=hidden name="auctionid" value="${auction.auctionid}"/>
       <input type="submit" value="竞 拍" class="spbg buttombg f14  sale-buttom" />
       </p>
       <p class="f14 red">不能低于最高竞拍价</p>
  	</form>
  </div>
  <div class="top10">
    <input name="" type="submit" value="刷 新" class="spbg buttombg f14" />
    <input name="" type="submit" value="返回列表" class="spbg buttombg f14" />
  </div>
  <div class="offer">
    <h3>出价记录</h3>
    <div class="items sg-font">
      <ul class="rows even strong">
        <li>竞拍时间</li>
        <li>竞拍价格</li>
        <li class="borderno">竞拍人</li>
      </ul>
      <c:if test="${fn:length(list)>0}">
      <c:forEach var="detail" items="${list}" varStatus="state">
	      <ul 
	       	   <c:if test="${state.index%2==0}">class="rows"</c:if>
	       	   <c:if test="${state.index%2!=0}">class="rows even"</c:if>
	       	>
	        <li><fmt:formatDate value="${detail.auctiontime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
	        <li>${detail.auctionprice}</li>
	        <li class="borderno">${detail.user.username}</li>
	      </ul>
      </c:forEach>
      </c:if>
      <c:if test="${fn:length(list)==0}">
      	<ul class="strong" style="text-align: center;">
      		<li>无竞拍记录</li>
      	</ul>
      	</c:if>
  </div>
  </div>
<!-- main end-->
</div>
</body>
</html>
