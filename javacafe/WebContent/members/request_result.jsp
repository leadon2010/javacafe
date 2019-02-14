<%@page import="java.util.HashMap"%>
<%@page import="jdbc.Employees"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<%
	//List<Employees>
	ArrayList<Employees> emplist = new ArrayList<Employees>();
	emplist.add(new Employees(999, "firstname999", "last_name999", "email999", null, "job_id999"));
	emplist.add(new Employees(998, "firstname998", "last_name998", "email998", null, "job_id998"));
	request.setAttribute("list", emplist);

	//Map<String, Employees>
	HashMap<String, Object> maphs = new HashMap<String, Object>();
	maphs.put("emp", new Employees(998, "firstname998", "last_name998", "email998", null, "job_id998"));
	maphs.put("dept", "총무");
	application.setAttribute("map", maphs);

	//Employees
	Employees emp = new Employees(998, "firstname998", "last_name998", "email998", null, "job_id998");
	session.setAttribute("emp", emp);
%>

연산자
<br>
eq: ${list[1].first_name == "firstname998" }
<br>
and : ${list[1].first_name == "firstname998" and list[1].last_name eq "last_name997" }
<br>
<hr>

<!-- list.get(1).getFirst_name() -->
사원명::${requestScope.list[0].first_name }
<br>
이메일::${sessionScope.emp.email}
<br>
map사원::${applicationScope.map.dept} == ${map["dept"]}
<br>
map사원::${applicationScope.map.emp.first_name} ==
${map["emp"].first_name}
<br>


이름:${param.username}
<br>
직업:${param.job}
<br>
length:${fn:length(paramValues.favorite)}
<br>
관심분야:${paramValues.favorite }
<br>
<c:forEach items="${paramValues.favorite}" var="i" varStatus="st">관심분야${st.count}:${i }<br>
</c:forEach>
<br>
클라이언트정보
<hr>
클라이언트IP:${pageContext.request.remoteAddr}
<br>
컨텍스트 이름:
<br>
요청URI:
<br>
header 브라우저정보:${header["User-Agent"]}
<br>
쿠키읽기 username:${cookie.username.value}
<br>
쿠키읽기 userid:${cookie.userid.value}
<br>
