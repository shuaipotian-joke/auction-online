<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="wrap">
<!-- main begin-->
  <div class="sale">
    <h1 class="lf">拍卖结束的商品</h1>
    <div class="right rulse">当前用户是：<span class="blue strong"><a href="#" title="张三">张三</a></span></div>
    <div class="cl"></div>
  </div>
<div class="items">
      <ul class="rows even strong">
        <li>名称</li>
        <li>开始时间</li>
        <li>结束时间</li>
        <li>起拍价</li>
        <li class="list-wd">成交价</li>
        <li class="borderno">买家</li>
      </ul>
      <c:forEach varStatus="state" var="listAuction" items="${list}">
	      <ul 
		       	   <c:if test="${state.index%2==0}">class="rows"</c:if>
		       	   <c:if test="${state.index%2!=0}">class="rows even"</c:if>
		       	>
	        <li>${listAuction.auctionname}</li>
	        <li><fmt:formatDate value="${listAuction.auctionstarttime}" pattern="yyyy-MM-dd hh:mm:ss"/></li>
	        <li><fmt:formatDate value="${listAuction.auctionendtime}" pattern="yyyy-MM-dd hh:mm:ss"/></li>
	        <li>${listAuction.auctionstartprice}</li>
	        <li class="list-wd">${listAuction.auctionprice}</li>
	        <li class="borderno red"><a href="#">${listAuction.username}</a></li>
	      </ul>
      </c:forEach>
       
  </div>
  <h1>拍卖中的商品</h1>
  <div class="items records">
      <ul class="rows even strong rowh"> 
        <li>名称</li>
        <li>开始时间</li>
        <li>结束时间</li>
        <li>起拍价</li>
        <li class="borderno record">出价记录</li>
        <div class="cl"></div>
      </ul>
     <c:forEach varStatus="state" var="auction" items="${list2}">
	      <ul 
		       	   <c:if test="${state.index%2==0}">class="rows"</c:if>
		       	   <c:if test="${state.index%2!=0}">class="rows even"</c:if>
		       	>
        <li>${auction.auctionname}</li>
        <li><fmt:formatDate value="${auction.auctionstarttime}" pattern="yyyy-MM-dd hh:mm:ss"/></li>
        <li><fmt:formatDate value="${auction.auctionendtime}" pattern="yyyy-MM-dd hh:mm:ss"/></li>
        <li>${auction.auctionstartprice}</li>
        <li class="borderno blue record">
          <c:forEach var="record" items="${auction.auctionrecordList}">
          	 <p> ${record.user.username}&nbsp;&nbsp;${record.auctionprice}元</p> 
          </c:forEach>
        </li>
        <div class="cl"></div>
      </ul>
      </c:forEach>
  </div>
<!-- main end-->
</div>
</body>
</html>
