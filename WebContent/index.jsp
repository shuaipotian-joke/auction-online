<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/WebCalendar.js" type="text/javascript"></script>
</head>

<body>
<div class="wrap">
<!-- main begin-->
  <div class="sale">
    <h1 class="lf">在线拍卖系统</h1>
    <div class="logout right"><a href="${pageContext.request.contextPath}/logout" title="注销">注销</a></div>
  </div>
  <div class="forms">
  	<form id="form_query" action="queryAuctions" method="post"/>
  		<input id="page" name="pageNum" type="hidden" value="1"/>
	    <label for="name">名称</label>
	    <input name="auctionname" type="text" class="nwinput" id="name" value="${condition.auctionname}"/>
	    <label for="names">描述</label>
	    <input name="auctiondesc" type="text" id="names" class="nwinput" value="${condition.auctiondesc}"/>
	    <label for="time">开始时间</label>
	    <input name="auctionstarttime" type="text" id="time" class="nwinput" 
	    value="<fmt:formatDate value="${condition.auctionstarttime}" />"
	    onclick="selectDate(this,'yyyy-MM-dd')"/>
	    <label for="end-time">结束时间</label>
	    <input name="auctionendtime" type="text" id="end-time" class="nwinput"
	    value="<fmt:formatDate value="${condition.auctionendtime}" />"
	     onclick="selectDate(this,'yyyy-MM-dd')" />
	    <label for="price">起拍价</label>
	    <input name="auctionstartprice" type="text" id="price" class="nwinput" value="${condition.auctionstartprice}"/>
	    <input type="submit"  value="查询" class="spbg buttombg f14  sale-buttom"/>
    </form>
    <c:if test="${sessionScope.user.userisadmin==1}">
	    <input type="button" onclick="location='${pageContext.request.contextPath}/addAuction.jsp'" value="发布" class="spbg buttombg f14  sale-buttom buttomb"/>
    </c:if>
    <c:if test="${sessionScope.user.userisadmin==0}">
	    <input type="button" onclick="location='${pageContext.request.contextPath}/listAuctionResult'" value="竞拍结果" class="spbg buttombg f14  sale-buttom buttomb"/>
    </c:if>
  </div>
  <div class="items">
      <ul class="rows even strong">
        <li>名称</li>
        <li class="list-wd">描述</li>
        <li>开始时间</li>
        <li>结束时间</li>
        <li>起拍价</li>
        <li class="borderno">操作</li>
      </ul>
      <c:forEach var="auction" items="${auctionList}" varStatus="state">
	       <ul 
	       	   <c:if test="${state.index%2==0}">class="rows"</c:if>
	       	   <c:if test="${state.index%2!=0}">class="rows even"</c:if>
	       	>
	        <li>${auction.auctionname}</li>
	        <li class="list-wd">${auction.auctiondesc}</li>
	        <li>
	        	<fmt:formatDate value="${auction.auctionstarttime}" />
	        </li>
	        <li>
	        	<fmt:formatDate value="${auction.auctionendtime}" />
	        </li>
	        <li>${auction.auctionstartprice}</li>
	        <li class="borderno red">
		        <c:if test="${sessionScope.user.userisadmin==1}">
		    		<a href="${pageContext.request.contextPath}/listUpdate/${auction.auctionid}">修改</a>
		        	<a href="${pageContext.request.contextPath}/removeAuction/${auction.auctionid}">删除</a>
	   			 </c:if>
	   			 <c:if test="${sessionScope.user.userisadmin==0}">
		    		<a href="${pageContext.request.contextPath}/toDetail/${auction.auctionid}">竞拍</a>
	   			 </c:if>
	        </li>
	      </ul>
      </c:forEach>
     
      <div class="page">
      	<%-- 【当前第${page.pageNum}页，总共${page.pages}页，总共${page.total}条记录】
        <a href="${pageContext.request.contextPath}/queryAuctions?pageNum=1">首页</a>
        <a href="${pageContext.request.contextPath}/queryAuctions?pageNum=${page.prePage}">上一页</a>
        <a href="${pageContext.request.contextPath}/queryAuctions?pageNum=${page.nextPage}">下一页</a>
        <a href="${pageContext.request.contextPath}/queryAuctions?pageNum=${page.pages}">尾页</a>  --%>
        	【当前第${page.pageNum}页，总共${page.pages}页，总共${page.total}条记录】
        <a href="javascript:jumpPage(1)">首页</a>
        <a href="javascript:jumpPage(${page.prePage})">上一页</a>
        <a href="javascript:jumpPage(${page.nextPage})">下一页</a>
        <a href="javascript:jumpPage(${page.pages})">尾页</a>
      </div>
  </div>
  <script>
  
   	function jumpPage(page_no){
   		document.getElementById("page").value=page_no;
   		document.getElementById("form_query").submit();
   	}
  </script>
<!-- main end-->
</div>
</body>
</html>
